package simulator;

import java.util.ArrayList;
import java.util.Random;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import storage.BattingStats;
import storage.PitchingStats;
import storage.ScoreBoard;
import storage.Situation;

public class MonteCarloGame {

	private long seed = 0;
	private static ScoreBoard scoreBoard = null;
	private static Random random = new Random();
	
	private static final int numbOfBins = 200;
	
	private ArrayList<BattingStats> awayLineUp = new ArrayList<BattingStats>();
	private ArrayList<BattingStats> homeLineUp = new ArrayList<BattingStats>();
	private ArrayList<PitchingStats> awayPitching = new ArrayList<PitchingStats>();
	private ArrayList<PitchingStats> homePitching = new ArrayList<PitchingStats>();
	int homeBatting = 0;
	int awayBatting = 0;
	private boolean gameCompleted;
	private int homeWins = 0;
	private int awayWins = 0;
	private int numberOfGames = 1;
	private double homeWinProb = 0.0;
	private double awayWinProb = 0.0;
	private double aveCombScore = 0.0;
	private double stdCombScore = 0.0;
	private double medCombScore = 0.0;
	private ArrayList<Integer> combHisto = new ArrayList<Integer>();
	DescriptiveStatistics combScore = new DescriptiveStatistics();
	private double homeWinsUpper = 0.0;
	private double homeWinsLower = 0.0;
	private double awayWinsLower;

	private final static int EMPTYBASE = 0;
	private final static int numInnings = 9;
	
	

	// Constructors 
	public MonteCarloGame(ArrayList<BattingStats> awayLineUp, ArrayList<BattingStats> homeLineUp,
			ArrayList<PitchingStats> awayPitching, ArrayList<PitchingStats> homePitching) {
		initHist();
		this.awayLineUp = awayLineUp;
		this.homeLineUp = homeLineUp;
		this.awayPitching = awayPitching;
		this.homePitching = homePitching;
		
		random.setSeed(seed);
	}
	
	public MonteCarloGame(ArrayList<BattingStats> awayLineUp, ArrayList<BattingStats> homeLineUp,
			ArrayList<PitchingStats> awayPitching, ArrayList<PitchingStats> homePitching, int seed) {
		initHist();
		this.awayLineUp = awayLineUp;
		this.homeLineUp = homeLineUp;
		this.awayPitching = awayPitching;
		this.homePitching = homePitching;
		this.seed = seed;
		
		random.setSeed(seed);
	}
	

	
	public MonteCarloGame(ArrayList<BattingStats> awayLineUp, ArrayList<BattingStats> homeLineUp,
			ArrayList<PitchingStats> awayPitching, ArrayList<PitchingStats> homePitching, int seed, int numberOfGames) {
		initHist();
		this.awayLineUp = awayLineUp;
		this.homeLineUp = homeLineUp;
		this.awayPitching = awayPitching;
		this.homePitching = homePitching;
		this.seed = seed;
		this.numberOfGames = numberOfGames;
		
		random.setSeed(seed);
	}
	
	public MonteCarloGame()
	{
		initHist();
		random.setSeed(seed);
		
		getLineUps();
	}
	
	// Simulates one game based on the seed, teams, pitchers and lineups
	private ScoreBoard simulateGame() {
		
		scoreBoard = new ScoreBoard();
		scoreBoard.addFrame();
		
		gameCompleted = false;

		homeBatting = 0;
		awayBatting = 0;
		
		while(!gameCompleted)	
		{
			playFrame();
			scoreBoard.addFrame();
			if(scoreBoard.getCurrentFrame() >= numInnings*2 && (scoreBoard.getRunsAway() != scoreBoard.getRunsHome())) // check for ties 
			{
				gameCompleted = true;
			}
		}
		
		return scoreBoard;
	}
	
	public void simulateGames()
	{
		simulateGames(numberOfGames);
	}
	
	public void simulateGames(int numberOfGames)
	{
		double bias = 0.0;
		simulateGames(numberOfGames, bias);
	}
	
	// Simulates one game based on the seed, teams, pitchers and lineups
	public void simulateGames(int numberOfGames, double bias) {
		
		if((awayLineUp.size() < 9) || (homeLineUp.size() < 9) ||
		   (awayPitching.size() == 0) || (homePitching.size() == 0))
		{
			System.out.println("No lineups no game played");
			return;
		}
		
		this.numberOfGames = numberOfGames;
		
		homeWins = 0;
		awayWins = 0;
		
		for(int ii = 0; ii <  numberOfGames; ii++)
		{
			ScoreBoard scoreBoard = simulateGame();
			int combRuns = scoreBoard.getRunsAway() 
					     + scoreBoard.getRunsHome()
					     + (int)Math.round(bias);
			combScore.addValue((double)combRuns);
			combHisto.set(combRuns, combHisto.get(combRuns)+1);
			if(scoreBoard.getRunsHome() > scoreBoard.getRunsAway())
			{
				homeWins++;
			}
			else
			{
				awayWins++;
			}
		}
		
		homeWinProb = (double)homeWins/(double)numberOfGames;
		awayWinProb = (double)awayWins/(double)numberOfGames;
		aveCombScore = combScore.getMean();
		stdCombScore = combScore.getStandardDeviation();
		medCombScore = combScore.getPercentile(50.0);
		
	}

	// initialize histogram with 0s from 1 to 50
	private void initHist()
	{
		for(int ii = 0; ii < numbOfBins; ii++)
		{
			combHisto.add(0);
		}
	}
	
	private void getLineUps() {
		awayLineUp.add(new BattingStats(1));
		awayLineUp.add(new BattingStats(2));
		awayLineUp.add(new BattingStats(3));
		awayLineUp.add(new BattingStats(4));
		awayLineUp.add(new BattingStats(5));
		awayLineUp.add(new BattingStats(6));
		awayLineUp.add(new BattingStats(7));
		awayLineUp.add(new BattingStats(8));
		awayLineUp.add(new BattingStats(9));
		awayPitching.add(PitchingStats.getDefault());
		

		homeLineUp.add(new BattingStats(1));
		homeLineUp.add(new BattingStats(2));
		homeLineUp.add(new BattingStats(3));
		homeLineUp.add(new BattingStats(4));
		homeLineUp.add(new BattingStats(5));
		homeLineUp.add(new BattingStats(6));
		homeLineUp.add(new BattingStats(7));
		homeLineUp.add(new BattingStats(8));
		homeLineUp.add(new BattingStats(9));
		homePitching.add(PitchingStats.getDefault());
		
	}
	

	// plays a frame
	private void playFrame() {
		Situation situation = new Situation();
		int battingNum;
		ArrayList<BattingStats> battingOrder;
		PitchingStats pitchingStats;
		boolean isAwayBatting = (scoreBoard.getCurrentFrame()%2 == 0);
		
		// away batting
		if(isAwayBatting)
		{
			battingNum = awayBatting;
			battingOrder = awayLineUp;
			pitchingStats = awayPitching.get(0);
		}
		else
		{
			battingNum = homeBatting;
			battingOrder = homeLineUp;
			pitchingStats = homePitching.get(0);
		}
		
		while(situation.getOuts() < 3)
		{

			if(!isAwayBatting && 
		       (scoreBoard.getCurrentFrame() > 17) &&
		       (scoreBoard.getRunsHome() > scoreBoard.getRunsAway())) // check if home team winning in bottom of 9th and beyond
			{
				gameCompleted = true;
				return;
			}
			
			situation = atBat(battingOrder.get(battingNum), pitchingStats, situation);
			
			battingNum++;
			if(battingNum >= battingOrder.size())
			{
				battingNum = 0;
			}
		}
		if(isAwayBatting)
		{
			awayBatting = battingNum;
		}
		else
		{
			homeBatting = battingNum;
		}
		
		
	}

	// plays at bat and relays outcome
	private Situation atBat(BattingStats battingStats, PitchingStats pitchingStats, Situation situation) {
		
		Double onBaseProb = getOnBaseProb(battingStats, pitchingStats);
		double uniformDraw = random.nextDouble();
		if(uniformDraw < onBaseProb)
		{
			situation = onBaseOutcome(battingStats, pitchingStats, situation);
		}
		// out
		else
		{
			situation.resetCount();
			situation.setOuts(situation.getOuts()+1);
		}
		
		return situation;
	}

	// simulates the type of hit 
	private Situation onBaseOutcome(BattingStats battingStats, PitchingStats pitchingStats, Situation situation) {
		double currentWeight = 1.0;
		double defaultWeight = 0.0;
		
		// If below threshold get weight
		if(battingStats.getAtBats() < BattingStats.minTPA)
		{
			currentWeight = battingStats.getAtBats() / BattingStats.minTPA;
			defaultWeight = (BattingStats.minTPA - battingStats.getAtBats()) 
									/ BattingStats.minTPA;
		}
		
		double probSingleCurrent = (double)battingStats.getSingles() / 
				(double)(battingStats.getTpa() * battingStats.getOnBase());
		double probDoubleCurrent = (double)battingStats.getDoubles() / 
				(double)(battingStats.getTpa() * battingStats.getOnBase());
		double probTripleCurrent = (double)battingStats.getTriples() / 
				(double)(battingStats.getTpa() * battingStats.getOnBase());
		double probHRCurrent = (double)battingStats.getHr() / 
				(double)(battingStats.getTpa() * battingStats.getOnBase());
		double probWalkCurrent = (double)battingStats.getBb() / 
				(double)(battingStats.getTpa() * battingStats.getOnBase());
		double probHitByPitchCurrent = (double)battingStats.getHitByPitch() / 
				(double)(battingStats.getTpa() * battingStats.getOnBase());
		
		double probSingle = probSingleCurrent * currentWeight 
				+ BattingStats.probSingleAve * defaultWeight;
		double probDouble = probDoubleCurrent * currentWeight 
				+ BattingStats.probDoubleAve * defaultWeight;
		double probTriple = probTripleCurrent * currentWeight 
				+ BattingStats.probTripleAve * defaultWeight;
		double probHR = probHRCurrent * currentWeight 
				+ BattingStats.probHRAve * defaultWeight;
		double probWalk = probWalkCurrent * currentWeight 
				+ BattingStats.probBBAve * defaultWeight;
		double probHitByPitch = probHitByPitchCurrent * currentWeight 
				+ BattingStats.probHitByPitchAve * defaultWeight;
		double uniformDraw = random.nextDouble();
		
		// single
		if(uniformDraw < probSingle)
		{
			if(situation.getOnThird() !=  EMPTYBASE)
			{
				situation.setOnThird(EMPTYBASE);
				// run scored
				scoreBoard.incRun();
			}
			
			if(situation.getOnSecond() !=  EMPTYBASE)
			{
				situation.setOnSecond(EMPTYBASE);
				// run scored
				scoreBoard.incRun();
			}
			
			if(situation.getOnFirst() != EMPTYBASE)
			{
				situation.setOnSecond(situation.getOnFirst());
				situation.setOnFirst(EMPTYBASE);
			}			
			
			scoreBoard.incHits();
			situation.setOnFirst(battingStats.getPlayerID());
			
		}
		// double
		else if((uniformDraw >= probSingle)&&(uniformDraw < (probSingle + probDouble)))
		{
			if(situation.getOnThird() !=  EMPTYBASE)
			{
				situation.setOnThird(EMPTYBASE);
				// run scored
				scoreBoard.incRun();
			}
			
			if(situation.getOnSecond() !=  EMPTYBASE)
			{
				situation.setOnSecond(EMPTYBASE);
				// run scored
				scoreBoard.incRun();
			}
			
			if(situation.getOnFirst() != EMPTYBASE)
			{
				situation.setOnThird(situation.getOnFirst());
				situation.setOnFirst(EMPTYBASE);
			}
			
			scoreBoard.incHits();
			situation.setOnSecond(battingStats.getPlayerID());
			
		}
		// triple 
		else if((uniformDraw >= (probSingle + probDouble))
		      &&(uniformDraw < (probSingle + probDouble + probTriple)))
		{
			if(situation.getOnThird() !=  EMPTYBASE)
			{
				situation.setOnThird(EMPTYBASE);
				// run scored
				scoreBoard.incRun();
			}
			
			if(situation.getOnSecond() !=  EMPTYBASE)
			{
				situation.setOnSecond(EMPTYBASE);
				// run scored
				scoreBoard.incRun();
			}
			
			if(situation.getOnFirst() != EMPTYBASE)
			{
				situation.setOnFirst(EMPTYBASE);
				// run scored
				scoreBoard.incRun();
			}
			
			scoreBoard.incHits();
			situation.setOnThird(battingStats.getPlayerID());
		}
		// hr 
		else if((uniformDraw >= (probSingle + probDouble + probTriple))
			  &&(uniformDraw < (probSingle + probDouble + probTriple + probHR)))
		{
			if(situation.getOnThird() !=  EMPTYBASE)
			{
				situation.setOnThird(EMPTYBASE);
				// run scored
				scoreBoard.incRun();
			}
			
			if(situation.getOnSecond() !=  EMPTYBASE)
			{
				situation.setOnSecond(EMPTYBASE);
				// run scored
				scoreBoard.incRun();
			}
			
			if(situation.getOnFirst() != EMPTYBASE)
			{
				situation.setOnFirst(EMPTYBASE);
				// run scored
				scoreBoard.incRun();
			}
			
			// run scored
			scoreBoard.incRun();
		}
		// walk
		else if((uniformDraw >= (probSingle + probDouble + probTriple + probHR))
			  &&(uniformDraw < (probSingle + probDouble + probTriple + probHR + probWalk)))
		{
			if(situation.getOnThird() !=  EMPTYBASE)
			{
				situation.setOnThird(EMPTYBASE);
				// run scored
				scoreBoard.incRun();
			}
			
			if(situation.getOnSecond() !=  EMPTYBASE)
			{
				situation.setOnThird(situation.getOnSecond());
				situation.setOnSecond(EMPTYBASE);
				
			}
			
			if(situation.getOnFirst() != EMPTYBASE)
			{
				situation.setOnSecond(situation.getOnFirst());
				situation.setOnFirst(EMPTYBASE);
			}
			
			situation.setOnFirst(battingStats.getPlayerID());
		}
		// hitByPitch
		else if((uniformDraw >= (probSingle + probDouble + probTriple + probHR + probWalk))
			  &&(uniformDraw < (probSingle + probDouble + probTriple + probHR + probWalk + probHitByPitch)))
		{
			
			if(situation.getOnThird() !=  EMPTYBASE)
			{
				situation.setOnThird(EMPTYBASE);
				// run scored
				scoreBoard.incRun();
			}
			
			if(situation.getOnSecond() !=  EMPTYBASE)
			{
				situation.setOnThird(situation.getOnSecond());
				situation.setOnSecond(EMPTYBASE);
				
			}
			
			if(situation.getOnFirst() != EMPTYBASE)
			{
				situation.setOnSecond(situation.getOnFirst());
				situation.setOnFirst(EMPTYBASE);
			}
			
			situation.setOnFirst(battingStats.getPlayerID());
		}
		
		return situation;
	}

	// calculates the onbase probability taking in account pitching stats
		private Double getOnBaseProb(BattingStats battingStats, PitchingStats pitchingStats) {
			
			double battingOnBase = battingStats.getOnBase();
			
			// if tpa is less the minTpa then get weighed onbase percent
			if(battingStats.getAtBats() < BattingStats.minTPA)
			{
				double currentWeight = battingStats.getAtBats() / BattingStats.minTPA;
				double defaultWeight = (BattingStats.minTPA - battingStats.getAtBats()) 
										/ BattingStats.minTPA;
				battingOnBase = battingStats.getOnBase() * currentWeight 
						+ BattingStats.onBaseAve * defaultWeight;
			}
			
			double pitchingOnBase = pitchingStats.getOnBase();
			if(pitchingStats.getInningsPitched() < PitchingStats.minInningsPitched)
			{
				double currentWeight = pitchingStats.getInningsPitched() / PitchingStats.minInningsPitched;
				double defaultWeight = (PitchingStats.minInningsPitched - pitchingStats.getInningsPitched()) 
										/ PitchingStats.minInningsPitched;
				pitchingOnBase = pitchingStats.getOnBase() * currentWeight 
						+ PitchingStats.onBaseAve * defaultWeight;
			}
			double pitchingBias = pitchingOnBase - PitchingStats.onBaseAve;
			
			return battingOnBase + pitchingBias;
		}
	
	// calculates 
	
	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public int getNumberOfGames() {
		return numberOfGames;
	}

	public void setNumberOfGames(int numberOfGames) {
		this.numberOfGames = numberOfGames;
	}

	public int getHomeWins() {
		return homeWins;
	}

	public int getAwayWins() {
		return awayWins;
	}

	public double getHomeWinProb() {
		return homeWinProb;
	}

	public double getAwayWinProb() {
		return awayWinProb;
	}

	public double getAveCombScore() {
		return aveCombScore;
	}

	public double getStdCombScore() {
		return stdCombScore;
	}

	public double getMedCombScore() {
		return medCombScore;
	}
	
	public double getAwayWinsLower() {
		return awayWinsLower;
	}

	public double getAwayWinsUpper() {
		return awayWinsUpper;
	}

	private double awayWinsUpper;
	
	public double getHomeWinsUpper() {
		return homeWinsUpper;
	}

	public double getHomeWinsLower() {
		return homeWinsLower;
	}

	public double getProbOver(double ou) {
		double probOver = 0.0;
		
		int counter = (int)(ou+0.5);
		int totalOver = 0;
		
		while(counter < numbOfBins)
		{
			totalOver += combHisto.get(counter);
			counter++;
		}
		
		probOver = (double)totalOver/(double)numberOfGames;
		return probOver;
		
	}

	public double getProbUnder(double ou) {
		double probUnder = 0.0;
		
		double counter = 0.0;
		int totalUnder = 0;
		
		while(counter <= ou)
		{
			totalUnder += combHisto.get((int)(counter));
			counter+=1.0;
		}
		
		probUnder = (double)totalUnder/(double)numberOfGames;
		return probUnder;
	}

}
