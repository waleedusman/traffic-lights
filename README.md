[![Build Status](https://travis-ci.org/waleedusman/traffic-lights.svg?branch=master)](https://travis-ci.org/waleedusman/traffic-lights)

An application that simulates a set of traffic lights at an intersection.

The traffic lights are designated (N, S) and (E, W) like a compass. When switching from green to red, the yellow light must be displayed for 30 seconds prior to it switching to red. The lights will change automatically every 5 minutes.

## Pre-requisites
- Java 8
- gradle 3.3

## Execute Simulation
Download the latest release and execute, e.g.
```
java -jar traffic-light-sim-1.0.jar
```

The simulation will run for 30 minutes.

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

## Sample output
Output for 30 minute period:


## Future
- Allow execution time of simulation to be configurable
- 