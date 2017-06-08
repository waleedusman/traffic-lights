package com.example;

class TrafficLight {
    private String id;

    private Colour colour = Colour.RED;

    public TrafficLight(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }
}
