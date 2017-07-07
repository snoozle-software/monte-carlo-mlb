package storage;

/**
 * @author Gregory Wagner
 * Snoozle Software - www.snoozle.net
 * 
 * Class - HitterBoxScoreStats
 * 
 * Stores box score information for hitters
 * Date - July 1, 2016
 */
public class HitterBoxScoreStats extends HitterStats {

	// Attributes 
	private double hitByPitch = 0;
	private double sacFlies = 0;
	private double doubles = 0;
	private double triples = 0;
	private double hr = 0;
	private double singles = 0;
	private double tpa = 0;
	
	// Constructors 
	public HitterBoxScoreStats() {
		super();
	}
	
	public HitterBoxScoreStats(int playerID) {
		super(playerID);
	}

	public HitterBoxScoreStats(int playerID, int atBats, int runs, int hits, int rbi, int baseOnBalls, int strikeOuts,
			int numOfPitches, double battingAve, double onBasePer, double sluggingPer, double hitByPitch,
			double sacFlies, double doubles, double triples, double hr, double singles,
			double tpa) {
		super(playerID, atBats, runs, hits, rbi, baseOnBalls, strikeOuts, numOfPitches, battingAve, onBasePer,
				sluggingPer);
		this.hitByPitch = hitByPitch;
		this.sacFlies = sacFlies;
		this.doubles = doubles;
		this.triples = triples;
		this.hr = hr;
		this.singles = singles;
		this.tpa = tpa;
	}

	// Getters and Setters
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

	public double getTpa() {
		return tpa;
	}

	public void setTpa(double tpa) {
		this.tpa = tpa;
	}

	@Override
	public String toString() {
		return "HitterBoxScoreStats [hitByPitch=" + hitByPitch + ", sacFlies=" + sacFlies + ", doubles=" + doubles
				+ ", triples=" + triples + ", hr=" + hr + ", singles=" + singles + ", tpa=" + tpa + ", player()="
				+ getPlayerID() + ", atBats=" + getAtBats() + ", runs=" + getRuns() + ", Hits="
				+ getHits() + ", Rbi=" + getRbi() + ", BaseOnBalls=" + getBaseOnBalls() + ", StrikeOuts="
				+ getStrikeOuts() + ", NumOfPitches=" + getNumOfPitches() + ", BattingAve=" + getBattingAve()
				+ ", OnBasePer=" + getOnBasePer() + ", SluggingPer=" + getSluggingPer() + "]";
	}

	
	
	
	
}
