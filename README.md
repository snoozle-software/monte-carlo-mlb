# Monte Carlo MLB Simulator

The Monte Carlo MLB Simulator from [Snoozle Software](http://www.snoozle.net) is an open source project that uses real player data to simulate Major League Baseball games and return basic statistics for selected games including the probability of which each team will win and the mean and standard deviation of the combined score of simulated games. 

## Overview

This open source project uses the same MC simulation for game prediction that the [Snooze Sports MLB Game Predictor](http://sports.snoozle.net/mlb/predictions.jsp) uses. Based the inputs, the process connects to the Snooze Sports API server via GET messages, and receives JSON messages with data and statistics to run the simulations locally. 

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
Games for 2016-05-01
1 Astros at Athletics
2 Royals at Mariners
3 Padres at Dodgers
4 Rockies at Diamondbacks
5 Yankees at Red Sox
6 Giants at Mets
7 Blue Jays at Rays
8 White Sox at Orioles
9 Reds at Pirates
10 Marlins at Brewers
11 Tigers at Twins
12 Nationals at Cardinals
13 Braves at Cubs
14 Indians at Phillies
15 Angels at Rangers
Pick game number (0 to exit): 
9
2016-05-01 Reds at Pirates
Prob Away Team Wins: 38.20%
Prob Home Team Wins: 61.80%
Average Comb Score: 9.55
Median Comb Score: 9.00
Std Comb Score: 4.42
```

## Performance

The combined score compared with over/under predictions from bookmakers from 2015, the combined score predictor is better than chance, the probability for picking the winners still needs work.

## Related Products

[MLB Predictions](http://sports.snoozle.net/mlb/predictions.jsp)

[MLB Odds](http://sports.snoozle.net/mlb/betting-lines.jsp)

## License

This code is licensed under the [MIT license](https://opensource.org/licenses/MIT). 