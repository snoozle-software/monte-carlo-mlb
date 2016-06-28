package storage;

import java.text.ParseException;

/**
 * @author Gregory Wagner 
 * Snoozle Software - www.snoozle.net
 * 
 * Class - Matchup
 * 
 *  Container for game matchup data
 *  Date - June 27, 2016
 */
public class MatchUp {

	// attributes 
	private String gameid = "";
	private String date = null;
	private String visName = "";
	private String homeName = "";
	private int homenum = 0;
	private int visnum = 0;;
	private int homeHits = 0;
	private int visHits = 0;
	private int homeErr = 0;
	private int visErr = 0;
	private int homeRuns = 0;
	private int visRuns = 0;
	private String visInn = "";
	private String homeInn = "";

	// ------------------ CONSTRUCTORS ------------------------------
	public MatchUp(String gameid, String date, int homenum, int visnum) throws ParseException {
		this.gameid = gameid;
		this.date = date; 
		this.homenum = homenum;
		this.visnum = visnum;
	}
	
	public MatchUp(String gameid, String date, int homenum, int visnum, int visRuns, int visHits,
			int visErr, String visInn, int homeRuns, int homeHits, int homeErr, String homeInn) throws ParseException {
		this.gameid = gameid;
		this.date = date;
		this.homenum = homenum;
		this.visnum = visnum;
		this.homeHits = homeHits;
		this.visHits = visHits;
		this.homeErr = homeErr;
		this.visErr = visErr;
		this.homeRuns = homeRuns;
		this.visRuns = visRuns;
		this.visInn = visInn;
		this.homeInn = homeInn;
	}

	
	
	public MatchUp(String gameid, String date, String homeName, String visName, int homenum, int visnum, int visRuns, int visHits,
			int visErr, String visInn, int homeRuns, int homeHits, int homeErr, String homeInn) throws ParseException {
		super();
		this.gameid = gameid;
		this.date = date;
		this.visName = visName;
		this.homeName = homeName;
		this.homenum = homenum;
		this.visnum = visnum;
		this.homeHits = homeHits;
		this.visHits = visHits;
		this.homeErr = homeErr;
		this.visErr = visErr;
		this.homeRuns = homeRuns;
		this.visRuns = visRuns;
		this.visInn = visInn;
		this.homeInn = homeInn;
	}

	public MatchUp() {
	}

	// ------------------- GETTERS AND SETTERS ---------------------------------
	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHomenum() {
		return homenum;
	}

	public void setHomenum(int homenum) {
		this.homenum = homenum;
	}

	public int getVisnum() {
		return visnum;
	}

	public void setVisnum(int visnum) {
		this.visnum = visnum;
	}

	public int getHomeHits() {
		return homeHits;
	}

	public void setHomeHits(int homeHits) {
		this.homeHits = homeHits;
	}

	public int getVisHits() {
		return visHits;
	}

	public void setVisHits(int visHits) {
		this.visHits = visHits;
	}

	public int getHomeErr() {
		return homeErr;
	}

	public void setHomeErr(int homeErr) {
		this.homeErr = homeErr;
	}

	public int getVisErr() {
		return visErr;
	}

	public void setVisErr(int visErr) {
		this.visErr = visErr;
	}

	public int getHomeRuns() {
		return homeRuns;
	}

	public void setHomeRuns(int homeRuns) {
		this.homeRuns = homeRuns;
	}

	public int getVisRuns() {
		return visRuns;
	}

	public void setVisRuns(int visRuns) {
		this.visRuns = visRuns;
	}

	public String getVisInn() {
		return visInn;
	}

	public void setVisInn(String visInn) {
		this.visInn = visInn;
	}

	public String getHomeInn() {
		return homeInn;
	}

	public void setHomeInn(String homeInn) {
		this.homeInn = homeInn;
	}
	
	public String getVisName() {
		return visName;
	}

	public void setVisName(String visName) {
		this.visName = visName;
	}

	public String getHomeName() {
		return homeName;
	}

	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}

	@Override
	public String toString() {
		return "MatchUp [gameid=" + gameid + ", date=" + date + ", homenum=" + homenum + ", visnum=" + visnum
				+ ", homeHits=" + homeHits + ", visHits=" + visHits + ", homeErr=" + homeErr + ", visErr=" + visErr
				+ ", homeRuns=" + homeRuns + ", visRuns=" + visRuns + ", visInn=" + visInn + ", homeInn=" + homeInn
				+ "]";
	}
	
} // class Matchup
