[![Build Status](https://travis-ci.org/waleedusman/traffic-lights.svg?branch=master)](https://travis-ci.org/waleedusman/traffic-lights)

An application that simulates a set of traffic lights at an intersection.

The traffic lights are designated (N, S) and (E, W) like a compass. When switching from green to red, the yellow light must be displayed for 30 seconds prior to it switching to red. The lights will change automatically every 5 minutes.

## Pre-requisites
- Java 8
- gradle 3.3

## Build & Run
In a terminal (assuming macOS), type:
```
gradle build
```

This will compile the code and run the unit tests.

It will also generate an executable JAR for running the simulation which can be run as:
```
java -jar build/libs/traffic-light-sim-1.0.jar
```

The simulation will run until forcibly exited.

## Sample output
Output for 30 minute period:

```
2017-06-09 14:42:20 INFO   Changing S from RED to GREEN
2017-06-09 14:42:20 INFO   Changing N from RED to GREEN
2017-06-09 14:46:50 INFO   Changing N from GREEN to YELLOW
2017-06-09 14:46:50 INFO   Changing S from GREEN to YELLOW
2017-06-09 14:47:20 INFO   Changing N from YELLOW to RED
2017-06-09 14:47:20 INFO   Changing S from YELLOW to RED
2017-06-09 14:47:20 INFO   Changing E from RED to GREEN
2017-06-09 14:47:20 INFO   Changing W from RED to GREEN
2017-06-09 14:51:50 INFO   Changing E from GREEN to YELLOW
2017-06-09 14:51:50 INFO   Changing W from GREEN to YELLOW
2017-06-09 14:52:20 INFO   Changing E from YELLOW to RED
2017-06-09 14:52:20 INFO   Changing W from YELLOW to RED
2017-06-09 14:52:20 INFO   Changing N from RED to GREEN
2017-06-09 14:52:20 INFO   Changing S from RED to GREEN
2017-06-09 14:56:50 INFO   Changing N from GREEN to YELLOW
2017-06-09 14:56:50 INFO   Changing S from GREEN to YELLOW
2017-06-09 14:57:20 INFO   Changing N from YELLOW to RED
2017-06-09 14:57:20 INFO   Changing S from YELLOW to RED
2017-06-09 14:57:20 INFO   Changing E from RED to GREEN
2017-06-09 14:57:20 INFO   Changing W from RED to GREEN
2017-06-09 15:01:50 INFO   Changing E from GREEN to YELLOW
2017-06-09 15:01:50 INFO   Changing W from GREEN to YELLOW
2017-06-09 15:02:20 INFO   Changing E from YELLOW to RED
2017-06-09 15:02:20 INFO   Changing W from YELLOW to RED
2017-06-09 15:02:20 INFO   Changing N from RED to GREEN
2017-06-09 15:02:20 INFO   Changing S from RED to GREEN
2017-06-09 15:06:50 INFO   Changing N from GREEN to YELLOW
2017-06-09 15:06:50 INFO   Changing S from GREEN to YELLOW
2017-06-09 15:07:20 INFO   Changing N from YELLOW to RED
2017-06-09 15:07:20 INFO   Changing S from YELLOW to RED
2017-06-09 15:07:20 INFO   Changing E from RED to GREEN
2017-06-09 15:07:20 INFO   Changing W from RED to GREEN
2017-06-09 15:11:50 INFO   Changing E from GREEN to YELLOW
2017-06-09 15:11:50 INFO   Changing W from GREEN to YELLOW
2017-06-09 15:12:20 INFO   Changing E from YELLOW to RED
2017-06-09 15:12:20 INFO   Changing W from YELLOW to RED
2017-06-09 15:12:20 INFO   Changing N from RED to GREEN
2017-06-09 15:12:20 INFO   Changing S from RED to GREEN
```