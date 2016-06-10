package storage;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// season stats for predictions
public class PitchingStats {
	
	private static final String statString = "http://espn.go.com/mlb/player/stats/_/id/";
	private static final String statTail = "/type/pitching/";
	
	public final static double onBaseAve = .317;
	public final static double aveNumOfPitchesAve = 90.0;
	public final static double minInningsPitched = 50.0;
	
	private int playerID = 0;
	private double onBase = 0.0;
	private double inningsPitched = 0.0; 
	private double aveNumOfPitches = 0.0;
	
	// Constructors
	public PitchingStats(int playerID, double onBase, double aveNumOfPitches, double inningsPitched) {
		super();
		this.playerID = playerID;
		this.onBase = onBase;
		this.aveNumOfPitches = aveNumOfPitches;
		this.inningsPitched = inningsPitched;
	}
	
	public PitchingStats(int playerID) {
		super();
		this.playerID = playerID;
	}
	
	public PitchingStats() {
		super();
	}

	// getters and setters
	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public double getOnBase() {
		return onBase;
	}

	public void setOnBase(double onBase) {
		this.onBase = onBase;
	}

	public double getAveNumOfPitches() {
		return aveNumOfPitches;
	}

	public void setAveNumOfPitches(double aveNumOfPitches) {
		this.aveNumOfPitches = aveNumOfPitches;
	}

	public double getInningsPitched() {
		return inningsPitched;
	}

	public void setInningsPitched(double inningsPitched) {
		this.inningsPitched = inningsPitched;
	}

	public static PitchingStats getDefault() {
		PitchingStats pitchingStats = new PitchingStats(0);
		
		pitchingStats.onBase = onBaseAve;
		pitchingStats.aveNumOfPitches= aveNumOfPitchesAve; 
		pitchingStats.inningsPitched = minInningsPitched;
		return pitchingStats;
	}

	
}
