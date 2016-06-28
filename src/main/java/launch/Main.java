package launch;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import simulator.MonteCarloGame;
import storage.BattingStats;
import storage.MatchUp;
import storage.PitchingStats;


/**
 * @author Gregory Wagner
 * Snoozle Software - www.snoozle.net
 * 
 * Class - Main
 * 
 * Main class contains the main and input processing methods
 * Date - June 27, 2016
 */

public class Main {
	
	// Constants
	private static final String baseURL = "http://sports.snoozle.net/api";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private final static String USER_AGENT = "Snoozle";
	private final static int numOfMCGames = 1000;
	
	// Attributes
	private static HashMap<Integer, String> gameIdHash = new HashMap<Integer, String>();

/*
 *  Main Method
 *  
 *  Gets the loop started and pushes and pulls data from the server, and called the Monte Carlo simulator
 */
	public static void main(String[] args) throws IOException {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		int gameNumber = -1;
		// Basic do while menu loop
		do
		{
			// Gets the date for pulling data
			System.out.flush();
			System.out.println("Pick game date (YYYY-MM-DD) OR 0 to exit: ");
			String gameDate = scanner.nextLine();
			
			// Test validity of date
			try {
				if(gameDate.trim().equals("0")) // exit if 0 for date
				{
					return;
				}
				Date testDate = sdf.parse(gameDate);
				gameDate = sdf.format(testDate);
			} catch (ParseException e) {
				System.out.println("Invalid date, please try again");
				continue;
			}
			
			// Sends date to server GET call
			String urlGamedateParameters = "league=mlb&statType=dailygames&gamedate="+gameDate;
			String matchupsJson = getJsonAPI(baseURL,urlGamedateParameters);
			
			
			// Generates game selection menu
			String gameMenu = getGameMenu(matchupsJson);
			
			// Error checking
			if((gameMenu == null) || gameMenu.equals(""))
			{
				System.out.println("No games for " + gameDate);
				continue;
			}
			
			// Prints out game selection menu
			System.out.println("Games for " + gameDate);
			System.out.print(gameMenu);
			System.out.println("Pick game number (0 to exit): ");
			gameNumber = Integer.parseInt(scanner.nextLine());
			
			// Menu item selection
			if(gameNumber > 0)
			{
				// Check if valid selection and transmits game number to api
				String gameID = gameIdHash.get(gameNumber);

				if(gameID == null)
				{
					System.out.println("Invalid game id");
					continue;
				}
				String urlGameParameters = "league=mlb&statType=montecarlostats&gameid="+gameID;
		
				String mcStatsJson = getJsonAPI(baseURL,urlGameParameters);
				
				// processes JSON return message with line up and pitcher data
				JsonElement jelement = new JsonParser().parse(mcStatsJson);
				JsonObject  jsonObj = jelement.getAsJsonObject();
				if(jsonObj.get("success").getAsBoolean())
				{
					Gson gson = new Gson();
					String date = jsonObj.get("gamedate").toString();
					String awayTeam = jsonObj.get("awayTeam").toString();
					String homeTeam = jsonObj.get("homeTeam").toString();
					Type battingStatsArray = new TypeToken<ArrayList<BattingStats>>(){}.getType();
					
					// setting line ups
					ArrayList<BattingStats> awayLineup = 
							gson.fromJson(jsonObj.get("awayLineUp"), battingStatsArray);
					ArrayList<BattingStats> homeLineup = 
							gson.fromJson(jsonObj.get("homeLineUp"), battingStatsArray);
					Type pitchingStatsArray = new TypeToken<ArrayList<PitchingStats>>(){}.getType();
					ArrayList<PitchingStats> awayPitching = 
							gson.fromJson(jsonObj.get("awayPitching"), pitchingStatsArray);
					ArrayList<PitchingStats> homePitching = 
							gson.fromJson(jsonObj.get("homePitching"), pitchingStatsArray);
					
					// Game simulator
					MonteCarloGame mcGame = new MonteCarloGame(awayLineup,homeLineup,awayPitching,homePitching);
					String gameTitle = String.format("%s %s at %s", 
							date.replace("\"", ""), awayTeam.replace("\"", ""), homeTeam.replace("\"", ""));
					System.out.println(gameTitle);
					mcGame.setNumberOfGames(numOfMCGames);
					mcGame.simulateGames();
					
					// Print out results
					System.out.println(String.format("Prob Away Team Wins: %.2f%%", mcGame.getAwayWinProb()*100));
					System.out.println(String.format("Prob Home Team Wins: %.2f%%", mcGame.getHomeWinProb()*100));
					System.out.println(String.format("Average Comb Score: %.2f",mcGame.getAveCombScore()));
					System.out.println(String.format("Median Comb Score: %.2f",mcGame.getMedCombScore()));
					System.out.println(String.format("Std Comb Score: %.2f",mcGame.getStdCombScore()));
				} // if(jsonObj.get("success").getAsBoolean())
			}// else if(gameNumber > 0)
		}while(gameNumber != 0); // do
	} // main
	
	/*
	 * getGameMenu - method
	 * input matchupJson
	 * output: menu String
	 * Creates a menu from a json date data, returns empty string if date has no games 
	 */
	private static String getGameMenu(String matchupsJson) {
		
		String gameString = "";
		JsonElement jelement = new JsonParser().parse(matchupsJson);
		JsonObject  jsonObj = jelement.getAsJsonObject();
		Type matchupArrayType = new TypeToken<ArrayList<MatchUp>>(){}.getType();
		
		if(jsonObj.get("success").getAsBoolean())
		{
			Gson gson = new Gson();
			ArrayList<MatchUp> matchUpArray = gson.fromJson(jsonObj.get("matchups"), matchupArrayType);
			
			for(int ii = 0; ii < matchUpArray.size(); ii++)
			{
				MatchUp matchUp = matchUpArray.get(ii);
				gameString += String.format("%d %s at %s\n",ii+1,matchUp.getVisName(),matchUp.getHomeName());
				gameIdHash.put(ii+1, matchUp.getGameid());
			}
		}
		
		return gameString;
	}

	/*
	 * method - getJsonAPI 
	 * input: url of game string, and parameters
	 * output: json string of from game
	 *  
	 * Gets data from the API
	 */
	
	public static String getJsonAPI(String urlString, String postParameters) throws IOException
	{
		String returnString = null;
		
		URL url = new URL(urlString);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(postParameters);
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		if(responseCode == 200)
		{
		
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			returnString = response.toString();
		} // if(responseCode == 200)
		
		con.disconnect();
		
		return returnString;
	} // public static String getJsonAPI(String urlString, String postParameters) throws IOException

	

} // class main
