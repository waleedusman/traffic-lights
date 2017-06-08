package com.example;

public class TrafficLightSimulator {
    public static void main(String[] args) {
        System.setProperty(
                "java.util.logging.SimpleFormatter.format",
                "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$-6s %5$s%6$s%n");

        new Intersection().open();
    }
}
