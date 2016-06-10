package launch;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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

public class Main {
	
	private static final String baseURL = "http://localhost:5000/api";
	///api?league=mlb&statType=montecarlostats&gameid=350405116"; 
	
	private final static String USER_AGENT = "Snoozle";
	private final static int numOfMCGames = 1000;
	private static HashMap<Integer, String> gameIdHash = new HashMap<Integer, String>();

	public static void main(String[] args) throws IOException {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Pick game date (YYYY-MM-DD): ");
		String gameDate = scanner.nextLine();
		
		
		String urlGamedateParameters = "league=mlb&statType=dailygames&gamedate="+gameDate;
		String matchupsJson = getJsonAPI(baseURL,urlGamedateParameters);
		String gameMenu = getGameMenu(matchupsJson);
		System.out.println("Games for " + gameDate);
		System.out.print(gameMenu);
		System.out.println("Pick game number: ");
		int gameNumber = scanner.nextInt(); 
		String gameID = gameIdHash.get(gameNumber);
		
		String urlGameParameters = "league=mlb&statType=montecarlostats&gameid="+gameID;

		String mcStatsJson = getJsonAPI(baseURL,urlGameParameters);
		
		//print result
		System.out.println(mcStatsJson);
		
		JsonElement jelement = new JsonParser().parse(mcStatsJson);
		JsonObject  jsonObj = jelement.getAsJsonObject();
		if(jsonObj.get("success").getAsBoolean())
		{
			Gson gson = new Gson();
			String date = jsonObj.get("gamedate").toString();
			String awayTeam = jsonObj.get("awayTeam").toString();
			String homeTeam = jsonObj.get("homeTeam").toString();
			Type battingStatsArray = new TypeToken<ArrayList<BattingStats>>(){}.getType();
			ArrayList<BattingStats> awayLineup = 
					gson.fromJson(jsonObj.get("awayLineUp"), battingStatsArray);
			ArrayList<BattingStats> homeLineup = 
					gson.fromJson(jsonObj.get("homeLineUp"), battingStatsArray);
			Type pitchingStatsArray = new TypeToken<ArrayList<PitchingStats>>(){}.getType();
			ArrayList<PitchingStats> awayPitching = 
					gson.fromJson(jsonObj.get("awayPitching"), pitchingStatsArray);
			ArrayList<PitchingStats> homePitching = 
					gson.fromJson(jsonObj.get("homePitching"), pitchingStatsArray);
			
			MonteCarloGame mcGame = new MonteCarloGame(awayLineup,homeLineup,awayPitching,homePitching);
			String gameTitle = String.format("%s %s at %s", 
					date.replace("\"", ""), awayTeam.replace("\"", ""), homeTeam.replace("\"", ""));
			System.out.println(gameTitle);
			mcGame.setNumberOfGames(numOfMCGames);
			mcGame.simulateGames();
			System.out.println(String.format("Prob Away Team Wins: %.2f%%", mcGame.getAwayWinProb()*100));
			System.out.println(String.format("Prob Home Team Wins: %.2f%%", mcGame.getHomeWinProb()*100));
			System.out.println(String.format("Average Comb Score: %.2f",mcGame.getAveCombScore()));
			System.out.println(String.format("Median Comb Score: %.2f",mcGame.getMedCombScore()));
			System.out.println(String.format("Std Comb Score: %.2f%%",mcGame.getStdCombScore()));
		} // if(jsonObj.get("success").getAsBoolean())
	} // main
	
	//
	// Creates a menu from a json 
	//
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

	// 
	// Gets data from the API
	//
	
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
		}
		
		con.disconnect();
		
		return returnString;
	}

	

}
