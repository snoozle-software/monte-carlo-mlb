package storage;

// Stats for hitters 
public class HitterStats {
	
	private int playerID = 0;
	private int atBats = 0; //at bat
	private int runs = 0; // runs
	private int hits = 0; // hits;
	private int rbi = 0; // rbis
	private int baseOnBalls = 0; // 
	private int strikeOuts = 0; 
	private int numOfPitches = 0;
	private double battingAve = 0.0;
	private double onBasePer = 0.0;
	private double sluggingPer = 0.0;
	
	// constructors
	
	
	
	public HitterStats() {
		super();
	}
	
	public HitterStats(int playerID) {
		super();
		this.playerID = playerID;
	}
	
	/**
	 * @param playerID
	 * @param atBats
	 * @param runs
	 * @param hits
	 * @param rbi
	 * @param baseOnBalls
	 * @param strikeOuts
	 * @param numOfPitches
	 * @param battingAve
	 * @param onBasePer
	 * @param sluggingPer
	 */
	public HitterStats(int playerID, int atBats, int runs, int hits, int rbi,
			int baseOnBalls, int strikeOuts, int numOfPitches,
			double battingAve, double onBasePer, double sluggingPer) {
		super();
		this.playerID = playerID;
		this.atBats = atBats;
		this.runs = runs;
		this.hits = hits;
		this.rbi = rbi;
		this.baseOnBalls = baseOnBalls;
		this.strikeOuts = strikeOuts;
		this.numOfPitches = numOfPitches;
		this.battingAve = battingAve;
		this.onBasePer = onBasePer;
		this.sluggingPer = sluggingPer;
	}

	// getters and setters
	

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getAtBats() {
		return atBats;
	}
	public void setAtBats(int atBats) {
		this.atBats = atBats;
	}
	public int getRuns() {
		return runs;
	}
	public void setRuns(int runs) {
		this.runs = runs;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getRbi() {
		return rbi;
	}
	public void setRbi(int rbi) {
		this.rbi = rbi;
	}
	public int getBaseOnBalls() {
		return baseOnBalls;
	}
	public void setBaseOnBalls(int baseOnBalls) {
		this.baseOnBalls = baseOnBalls;
	}
	public int getStrikeOuts() {
		return strikeOuts;
	}
	public void setStrikeOuts(int strikeOuts) {
		this.strikeOuts = strikeOuts;
	}
	public int getNumOfPitches() {
		return numOfPitches;
	}
	public void setNumOfPitches(int numOfPitches) {
		this.numOfPitches = numOfPitches;
	}
	public double getBattingAve() {
		return battingAve;
	}
	public void setBattingAve(double battingAve) {
		this.battingAve = battingAve;
	}
	public double getOnBasePer() {
		return onBasePer;
	}
	public void setOnBasePer(double onBasePer) {
		this.onBasePer = onBasePer;
	}
	public double getSluggingPer() {
		return sluggingPer;
	}
	public void setSluggingPer(double sluggingPer) {
		this.sluggingPer = sluggingPer;
	}

	@Override
	public String toString() {
		return "HitterStats [playerID=" + playerID + ", \natBats=" + atBats
				+ ", \nruns=" + runs + ", \nhits=" + hits + ", \nrbi=" + rbi
				+ ", \nbaseOnBalls=" + baseOnBalls + ", \nstrikeOuts="
				+ strikeOuts + ", \nnumOfPitches=" + numOfPitches
				+ ", \nbattingAve=" + battingAve + ", \nonBasePer="
				+ onBasePer + ", \nsluggingPer=" + sluggingPer + "]";
	}

	@Override
	public boolean equals(Object obj) {
		HitterStats cmpStats = (HitterStats)obj;
		
		return (this.playerID == cmpStats.getPlayerID());
	}
	
	
}
