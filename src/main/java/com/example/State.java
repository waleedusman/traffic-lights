package com.example;

import static com.example.Colour.GREEN;
import static com.example.Colour.RED;
import static com.example.Colour.YELLOW;

// Treat states as immutable.
enum State {
    STOP(RED, 0), GO(GREEN, 270L), PRE_STOP(YELLOW, 30);

    static {
        STOP.nextState = GO;
        GO.nextState = PRE_STOP;
        PRE_STOP.nextState = STOP;
    }

    private Colour colour;
    private long durationSeconds;
    private State nextState;

    State(Colour colour, long durationSeconds) {
        this.colour = colour;
        this.durationSeconds = durationSeconds;
    }

    public State nextState() {
        return nextState;
    }

    public Colour colour() {
        return colour;
    }

    public long durationSeconds() {
        return durationSeconds;
    }
}
