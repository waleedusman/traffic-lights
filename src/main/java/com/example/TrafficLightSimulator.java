package com.example;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TrafficLightSimulator {
    private static final long DEFAULT_EXECUTION_TIME = 30;

    public static void main(String[] args) {
        // set the log output to be in a single line
        System.setProperty(
                "java.util.logging.SimpleFormatter.format",
                "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$-6s %5$s%6$s%n");

        new Intersection().open();
        Executors.newSingleThreadScheduledExecutor().schedule(
                () -> System.exit(0), DEFAULT_EXECUTION_TIME, TimeUnit.MINUTES);
    }
}
