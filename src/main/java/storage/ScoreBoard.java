package storage;

import java.util.Vector;

public class ScoreBoard {
	
	// attributes 
	private Vector<Integer> frames = new Vector<Integer>(); // frame alternames top bottom for the inning, so 1 would be the bottom of the 1st, 8 top of the 4th
	private int awayHits = 0;
	private int homeHits = 0;
	private int awayErrors = 0;
	private int homeErrors = 0;
	private int awayTeam = 0;
	private int homeTeam = 0;
	private int currentFrame = -1;
	/**
	 * 
	 */
	public ScoreBoard() {
		super();
	}
	

	public ScoreBoard(int awayTeamNum, int homeTeamNum) {
		super();
		this.awayTeam = awayTeamNum;
		this.homeTeam = homeTeamNum;
	}
	
	// accessors
	public void addFrame()
	{
		frames.add(0);
		currentFrame++;
	}
	
	// adds run to current frame
	public void incRun()
	{
		frames.set(currentFrame, frames.get(currentFrame)+1);
	}
	
	// increments errors
	public void incAwayError()
	{
		awayErrors++;
	}
	
	public void incHomeError()
	{
		homeErrors++;
	}
	
	public void incError()
	{
		if((currentFrame % 2) == 1)
		{
			incAwayError();
		}
		else
		{
			incHomeError();
		}
	}

	// error getters and setters
	public int getAwayErrors() {
		return awayErrors;
	}

	public void setAwayErrors(int awayErrors) {
		this.awayErrors = awayErrors;
	}

	public int getHomeErrors() {
		return homeErrors;
	}
	

	public void setHomeErrors(int homeErrors) {
		this.homeErrors = homeErrors;
	}
	
	// increment hits
	public void incAwayHits()
	{
		awayHits++;
	}
	
	public void incHomeHits()
	{
		homeHits++;
	}
	
	public void incHits()
	{
		if((currentFrame % 2) == 0)
		{
			incAwayHits();
		}
		else
		{
			incHomeHits();
		}
	}

	public int getAwayHits() {
		return awayHits;
	}

	public void setAwayHits(int awayHits) {
		this.awayHits = awayHits;
	}

	public int getHomeHits() {
		return homeHits;
	}

	public void setHomeHits(int homeHits) {
		this.homeHits = homeHits;
	}


	public Vector<Integer> getFrames() {
		return frames;
	}


	public int getAwayTeam() {
		return awayTeam;
	}


	public void setAwayTeam(int awayTeam) {
		this.awayTeam = awayTeam;
	}


	public int getHomeTeam() {
		return homeTeam;
	}


	public void setHomeTeam(int homeTeam) {
		this.homeTeam = homeTeam;
	}


	public int getCurrentFrame() {
		return currentFrame;
	}
	
	public int getRunsHome()
	{
		int sumRuns = 0;
		for(int frame = 0; frame < frames.size() ; frame++)
		{
			if((frame % 2) == 1)
			{
				sumRuns += frames.get(frame);
			}
		} // for(int runs : frames)
		return sumRuns;
	}
	
	public int getRunsAway()
	{
		int sumRuns = 0;
		for(int frame = 0; frame < frames.size() ; frame++)
		{
			if((frame % 2) == 0)
			{
				sumRuns += frames.get(frame);
			}
		} // for(int runs : frames)
		return sumRuns;
	}


	@Override
	public String toString() {
		String frame = "";
		if((currentFrame % 2) == 0)
		{
			frame += "Top of inning ";
			
		}
		else
		{
			frame += "Bottom of inning ";
		}
		int inning = currentFrame / 2 + 1;
		return "ScoreBoard: " + frame + inning + " Runs: " + getRunsAway() + " " + getRunsHome() 
				  + " Hits: " + getAwayHits() + " " + getHomeHits() 
				  + " Errors: " + getAwayErrors() + " " + getHomeErrors();
	}
	
	
	

}
