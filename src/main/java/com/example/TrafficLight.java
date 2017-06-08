package com.example;

import java.util.logging.Logger;

import static com.example.State.STOP;
import static java.lang.String.format;

class TrafficLight {
    private static final Logger LOG = Logger.getLogger(TrafficLight.class.getName());

    private String id;

    private State state = STOP;

    public TrafficLight(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Colour getColour() {
        return state.colour();
    }

    public State getState() {
        return state;
    }

    public void changeState() {
        LOG.info(format("Changing %s from %s to %s", id, state.colour(), state.nextState().colour()));
        state = state.nextState();
    }
}
