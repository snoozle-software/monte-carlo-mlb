package storage;


/**
 * @author Gregory Wagner 
 * Snoozle Software - www.snoozle.net
 * 
 * Class - Situation
 * 
 *  Frame situation: count, outs, on-base
 *  Date - June 27, 2016
 */
public class Situation {
	
	// attributes
	private int strikes = 0;
	private int balls = 0;
	private int outs = 0;
	private int onFirst = 0;
	private int onSecond = 0;
	private int onThird = 0;
	private int batting = 0;
	
	// -------------------Constructors---------------------------
	public Situation()
	{
		super();
	}

	//------------- getters and setters ------------------------
	public int getStrikes() {
		return strikes;
	}

	public void setStrikes(int strikes) {
		this.strikes = strikes;
	}

	public int getBalls() {
		return balls;
	}

	public void setBalls(int balls) {
		this.balls = balls;
	}

	public int getOuts() {
		return outs;
	}

	public void setOuts(int outs) {
		this.outs = outs;
	}

	public int getOnFirst() {
		return onFirst;
	}

	public void setOnFirst(int onFirst) {
		removeFromBases(onFirst);
		this.onFirst = onFirst;
	}

	public void removeFromBases(int runner) {
		if (runner == batting)
		{
			batting = 0;
		}
		else if(runner == onFirst)
		{
			onFirst = 0;
		}
		else if(runner == onSecond)
		{
			onSecond = 0;
		}
		else if(runner == onThird)
		{
			onThird = 0;
		}
		
	}

	public int getOnSecond() {
		return onSecond;
	}

	public void setOnSecond(int onSecond) {
		removeFromBases(onSecond);
		this.onSecond = onSecond;
	}

	public int getOnThird() {
		return onThird;
	}

	public void setOnThird(int onThird) {
		removeFromBases(onThird);
		this.onThird = onThird;
	}

	public int getBatting() {
		return batting;
	}

	public void setBatting(int batting) {
		this.batting = batting;
	}
	
	public void resetCount()
	{
		strikes = 0;
		balls = 0;
	}
	
	public void resetOuts()
	{
		outs = 0;
		clearBases();
	}
	
	public void clearBases()
	{

		onFirst = 0;
		onSecond = 0;
		onThird = 0;
	}

	@Override
	public String toString() {
		String returnString = strikes + "-" + balls
				+ " Outs: " + outs + " Batting :" + batting;
		if(onFirst != 0)
		{
			returnString += " On First: " + onFirst;
		}
		if(onSecond != 0)
		{
			returnString += " On Second: " + onSecond;
		}
		if(onThird != 0)
		{
			returnString += " On Third: " + onThird;
		}
		return returnString;
	}

	
}


