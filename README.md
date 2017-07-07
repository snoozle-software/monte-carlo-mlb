# Monte Carlo MLB Simulator

The Monte Carlo MLB Simulator from [Snoozle Software](http://www.snoozle.net) is an open source project that uses real player data to simulate Major League Baseball games and return basic statistics for selected games including the probability of which each team will win and the mean and standard deviation of the combined score of simulated games. 

## Overview

This open source project uses the same MC simulation for game prediction that the [Snoozle Sports MLB Game Predictor](http://sports.snoozle.net/mlb/predictions.jsp) uses. Based the inputs, the process connects to the Snoozle Sports API server via GET messages, and receives JSON messages with data and statistics to run the simulations locally. 

The baseball game simulator uses the player statistics sent from the API. The simulator is a simplified version of a baseball game where it does not model errors, stolen bases, pitcher pick offs, balks, etc. These events are relatively rare and due to the multiple iterations of the MC simulator, these events become noise in the overall performance.

## Required Software and Installation

The code is built using Maven, so you need Maven and java 8 to run. There is a dockerfile, if have Docker you build the dockerfile to run, as well.

## How to Use

1. Build and run using docker or maven
2. Run (Maven: run script target/bin/webapp[.bat]
3. Enter day of year
```
Pick game date (YYYY-MM-DD) OR 0 to exit:
2016-05-01
```
4. Select game you want to simulate or choose to exit
```
2016-05-01 Reds at Pirates
Prob Away Team Wins: 42.90%
Prob Home Team Wins: 57.10%
Average Comb Score: 9.09
Median Comb Score: 9.00
Std Comb Score: 4.29

Reds's Starting Pitcher: Tim Adleman
Reds Hitter's Projected Performance
Player                        	AB	R	H	RBI	BB	AVG	OBP	SLG
Zack Cozart                   	4.66	0.67	1.33	0.46	0.29	0.281	0.327	0.498
Billy Hamilton                	4.50	0.46	1.03	0.28	0.30	0.223	0.271	0.337
Joey Votto                    	3.90	0.60	1.10	0.50	0.77	0.275	0.402	0.471
Brandon Phillips              	4.37	0.46	1.17	0.44	0.18	0.260	0.303	0.367
Jay Bruce                     	4.21	0.60	1.18	0.75	0.32	0.277	0.330	0.563
Eugenio Suarez                	4.07	0.42	0.91	0.56	0.32	0.217	0.283	0.375
Adam Duvall                   	4.10	0.51	1.04	0.66	0.22	0.250	0.291	0.537
Tucker Barnhart               	3.87	0.28	1.05	0.34	0.32	0.263	0.324	0.372
Tim Adleman                   	2.82	0.00	0.00	0.00	0.00	0.000	0.000	0.000

Pirates's Starting Pitcher: Jeff Locke
Pirates Hitter's Projected Performance
Player                        	AB	R	H	RBI	BB	AVG	OBP	SLG
John Jaso                     	4.55	0.63	1.28	0.39	0.49	0.276	0.353	0.383
Andrew McCutchen              	4.52	0.63	1.19	0.56	0.44	0.259	0.327	0.434
Josh Harrison                 	4.59	0.51	1.29	0.45	0.20	0.276	0.314	0.379
Starling Marte                	4.41	0.56	1.42	0.62	0.18	0.314	0.362	0.459
Gregory Polanco               	4.14	0.62	1.25	0.69	0.50	0.296	0.371	0.512
Sean Rodriguez                	4.00	0.59	1.07	0.77	0.46	0.255	0.341	0.507
Jordy Mercer                  	3.87	0.42	1.07	0.47	0.49	0.271	0.354	0.377
Chris Stewart                 	3.74	0.35	0.82	0.35	0.47	0.212	0.317	0.298
Jeff Locke                    	3.84	0.37	0.84	0.39	0.29	0.211	0.264	0.296

```

## Performance

Based on the latest bug fixes and algorithm improves using 2015 pitcher and player stats, using my [optimal betting formula](http://sports.snoozle.net/articles/story/optimized-betting-for-multiple-games-at-the-same-time), the probabilities would beat the house for both the money line and over/under bets. In development is a good performance metric for hitter stats. 

## Player Statistics Update and API

Everyday at 0500 PST, the database release the statistics for the day's before cumulative season statistics for the players and uses the projected lines. Confirmed linesups are set as they become available.

Currently, there is not API key as the system matures an API key will be added.   

## To Do

For hitters, add strike out predictions and position for players. 

For pitchers, need to calculate predicted statistics. Currently, it is assumed starter pitches entire game, need to have middle reliever and closer statistics. 

## Related Products

[MLB Predictions](http://sports.snoozle.net/mlb/predictions.jsp)

[MLB Odds](http://sports.snoozle.net/mlb/betting-lines.jsp)

## License

This code is licensed under the [MIT license](https://opensource.org/licenses/MIT). 