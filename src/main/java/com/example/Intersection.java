package com.example;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.example.State.STOP;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.concurrent.TimeUnit.SECONDS;

class Intersection {
    private Set<TrafficLight> northSouth;
    private Set<TrafficLight> eastWest;

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

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
        transition(northSouth, eastWest);
    }

    private void transition(Set<TrafficLight> active, Set<TrafficLight> next) {
        active.parallelStream().forEach(TrafficLight::changeState);
        State newState = active.stream().findFirst().get().getState();
        if (newState.equals(STOP)) {
            executor.schedule(() -> transition(next, active), newState.durationSeconds(), SECONDS);
        } else {
            executor.schedule(() -> transition(active, next), newState.durationSeconds(), SECONDS);
        }
    }
}
