package com.example;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

import static com.example.Colour.GREEN;
import static com.example.Colour.RED;
import static com.example.Colour.YELLOW;
import static com.google.common.collect.Sets.newHashSet;
import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;

class Intersection {
    private static final Logger LOG = Logger.getLogger(Intersection.class.getName());

    private Set<TrafficLight> northSouth;
    private Set<TrafficLight> eastWest;

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public Intersection() {
        northSouth = newHashSet(new TrafficLight("N"), new TrafficLight("S"));
        eastWest = newHashSet(new TrafficLight("E"), new TrafficLight("W"));
    }

    public Set<TrafficLight> getNorthSouth() {
        return northSouth;
    }

    public Set<TrafficLight> getEastWest() {
        return eastWest;
    }

    public void open() {
        transitionToGreen(northSouth, eastWest);
    }

    private void transitionToGreen(Set<TrafficLight> active, Set<TrafficLight> next) {
        active.forEach(t -> {
            LOG.info(format("Changing %s from %s to %s", t.getId(), t.getColour(), GREEN));
            t.setColour(GREEN);
        });
        executor.schedule(() -> transitionToYellow(active, next), 270L, SECONDS);
    }

    private void transitionToYellow(Set<TrafficLight> active, Set<TrafficLight> next) {
        active.forEach(t -> {
            LOG.info(format("Changing %s from %s to %s", t.getId(), t.getColour(), YELLOW));
            t.setColour(YELLOW);
        });
        executor.schedule(() -> transitionToRed(active, next), 30L, SECONDS);
    }

    private void transitionToRed(Set<TrafficLight> active, Set<TrafficLight> next) {
        active.forEach(t -> {
            LOG.info(format("Changing %s from %s to %s", t.getId(), t.getColour(), RED));
            t.setColour(RED);
        });
        executor.schedule(() -> transitionToGreen(next, active), 0L, SECONDS);
    }
}
