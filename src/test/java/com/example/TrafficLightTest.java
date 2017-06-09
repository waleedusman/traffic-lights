package com.example;

import org.junit.Test;

import static com.example.Colour.GREEN;
import static com.example.Colour.RED;
import static com.example.Colour.YELLOW;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class TrafficLightTest {
    @Test
    public void shouldBeRedWhenCreated() {
        assertThat(new TrafficLight("test").getColour(), is(equalTo(RED)));
    }

    @Test
    public void shouldChangeColourToGreenWhenRed() {
        TrafficLight trafficLight = new TrafficLight("test");
        trafficLight.changeState();
        assertThat(trafficLight.getColour(), is(equalTo(GREEN)));
    }

    @Test
    public void shouldChangeColourToYellowWhenGreen() {
        TrafficLight trafficLight = new TrafficLight("test");
        trafficLight.changeState();
        trafficLight.changeState();
        assertThat(trafficLight.getColour(), is(equalTo(YELLOW)));
    }

    @Test
    public void shouldChangeColourToRedWhenYellow() {
        TrafficLight trafficLight = new TrafficLight("test");
        trafficLight.changeState();
        trafficLight.changeState();
        trafficLight.changeState();
        assertThat(trafficLight.getColour(), is(equalTo(RED)));
    }
}
