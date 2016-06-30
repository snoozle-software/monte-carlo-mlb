package storage;

/**
 * @author Gregory Wagner 
 * Snoozle Software - www.snoozle.net
 * 
 * Class - Situation
 * 
 *  Containter for Battings Stats
 *  Date - June 27, 2016
 */
public class BattingStats {
	
	// Constants 
	public static final double minTPA = 100.0; // minimum to not have weighted stats
	
	// league averages from 2015
	public  final static double onBaseAve = PitchingStats.onBaseAve;
	public  final static double hitByPitchAve = 53;
	public  final static double sacFliesAve = 41;
	public  final static double atBatsAve = 520;
	public  final static double hitsAve = 1404;
	public  final static double doublesAve = 275;
	public  final static double triplesAve = 31;
	public  final static double hrAve = 164;
	public  final static double singlesAve = hitsAve - doublesAve - triplesAve - hrAve;
	public  final static double bbAve = 469;
	public  final static double tpaAve = 6121;
	
	public final static double probSingleAve = (double)singlesAve / 
			((double)tpaAve * onBaseAve);
	public final static double probDoubleAve = (double)doublesAve / 
			((double)tpaAve * onBaseAve);
	public final static double probTripleAve = (double)triplesAve / 
			((double)tpaAve * onBaseAve);
	public final static double probHRAve = (double)hrAve / 
			((double)tpaAve * onBaseAve);
	public final static double probBBAve = (double)bbAve / 
			((double)tpaAve * onBaseAve);
	public final static double probHitByPitchAve = (double)hitByPitchAve / 
			((double)tpaAve * onBaseAve);
	
	// Attributes
	private int playerID = 0;
	private String playerName = "";
	private double onBase = 0.0;
	private double hitByPitch = 0;
	private double sacFlies = 0;
	private double atBats = 0;
	private double hits = 0;
	private double doubles = 0;
	private double triples = 0;
	private double hr = 0;
	private double singles = 0;
	private double bb = 0;
	private double tpa = Double.MIN_VALUE;
	
	// ---------------------Constructors----------------------------
	public BattingStats(int playerID, double onBase, double hitByPitch, double sacFlies, double atBats, double hits,
			double doubles, double triples, double hr, double singles, double bb, double tpa) {
		super();
		this.playerID = playerID;
		this.onBase = onBase;
		this.hitByPitch = hitByPitch;
		this.sacFlies = sacFlies;
		this.atBats = atBats;
		this.hits = hits;
		this.doubles = doubles;
		this.triples = triples;
		this.hr = hr;
		this.singles = singles;
		this.bb = bb;
		this.tpa = tpa;
	}
	
	public BattingStats(int playerID, String playerName, double onBase, double hitByPitch, double sacFlies, double atBats, double hits,
			double doubles, double triples, double hr, double singles, double bb, double tpa) {
		super();
		this.playerID = playerID;
		this.playerName = playerName;
		this.onBase = onBase;
		this.hitByPitch = hitByPitch;
		this.sacFlies = sacFlies;
		this.atBats = atBats;
		this.hits = hits;
		this.doubles = doubles;
		this.triples = triples;
		this.hr = hr;
		this.singles = singles;
		this.bb = bb;
		this.tpa = tpa;
	}
	
	public BattingStats(int playerID) {
		super();
		this.playerID = playerID;
	}

	public BattingStats() {
		super();
	}

	// -----------------------Getters and setters -----------------------------------------
	public int getPlayerID() {
		return playerID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
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

	public double getHitByPitch() {
		return hitByPitch;
	}

	public void setHitByPitch(double hitByPitch) {
		this.hitByPitch = hitByPitch;
	}

	public double getSacFlies() {
		return sacFlies;
	}

	public void setSacFlies(double sacFlies) {
		this.sacFlies = sacFlies;
	}

	public double getAtBats() {
		return atBats;
	}

	public void setAtBats(double atBats) {
		this.atBats = atBats;
	}

	public double getHits() {
		return hits;
	}

	public void setHits(double hits) {
		this.hits = hits;
	}

	public double getDoubles() {
		return doubles;
	}

	public void setDoubles(double doubles) {
		this.doubles = doubles;
	}

	public double getTriples() {
		return triples;
	}

	public void setTriples(double triples) {
		this.triples = triples;
	}

	public double getHr() {
		return hr;
	}

	public void setHr(double hr) {
		this.hr = hr;
	}

	public double getSingles() {
		return singles;
	}

	public void setSingles(double singles) {
		this.singles = singles;
	}

	public double getBb() {
		return bb;
	}

	public void setBb(double bb) {
		this.bb = bb;
	}

	public double getTpa() {
		return tpa;
	}

	public void setTpa(double tpa) {
		this.tpa = tpa;
	}
}
