# Monte Carlo MLB Simulator

The Monte Carlo MLB Simulator from [Snoozle Software](http://www.snoozle.net) is an open source project that uses real player data to simulate Major League Baseball games and return basic statistics for selected games including the probability of which each team will win and the mean and standard deviation of the combined score of simulated games. 

## Overview

## Required Software and Installation

The code is built using Maven, so you need Maven and java 8 to run. There is a dockerfile, if have Docker you build the dockerfile to run, as well.

## How to Use

1. Build and run using docker or maven
2. Run (Maven: run script target/bin/webapp[.bat]
3. Enter day of year
```Pick game date (YYYY-MM-DD) OR 0 to exit:
2016-05-01```
4. Select game you want to simulate or choose to exit
```Games for 2016-05-01
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
```

## Related Products

[MLB Predictions](http://sports.snoozle.net/mlb/predictions.jsp)

[MLB Odds](http://sports.snoozle.net/mlb/betting-lines.jsp)

## License

This code is licensed under the [MIT license](https://opensource.org/licenses/MIT). 