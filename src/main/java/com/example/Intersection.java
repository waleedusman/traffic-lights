package com.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

import static com.example.Colour.GREEN;
import static com.example.Colour.RED;
import static com.example.Colour.YELLOW;
import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;

class Intersection {
    private static final Logger LOG = Logger.getLogger(Intersection.class.getName());

    private TrafficLight t1;
    private TrafficLight t2;

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public Intersection() {
        t1 = new TrafficLight("N");
        t2 = new TrafficLight("E");
    }

    public TrafficLight getT1() {
        return t1;
    }

    public TrafficLight getT2() {
        return t2;
    }

    public void open() {
        transitionToGreen(t1, t2);
    }

    public void transitionToGreen(TrafficLight active, TrafficLight next) {
        LOG.info(format("Changing %s from %s to %s", active.getId(), active.getColour(), GREEN));
        active.setColour(GREEN);
        executor.schedule(() -> transitionToYellow(active, next), 270L, SECONDS);
    }

    public void transitionToYellow(TrafficLight active, TrafficLight next) {
        LOG.info(format("Changing %s from %s to %s", active.getId(), active.getColour(), YELLOW));
        active.setColour(YELLOW);
        executor.schedule(() -> transitionToRed(active, next), 30L, SECONDS);
    }

    public void transitionToRed(TrafficLight active, TrafficLight next) {
        LOG.info(format("Changing %s from %s to %s", active.getId(), active.getColour(), RED));
        active.setColour(RED);
        executor.schedule(() -> transitionToGreen(next, active), 0L, SECONDS);
    }
}
