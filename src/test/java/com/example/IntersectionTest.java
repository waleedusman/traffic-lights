package com.example;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.ScheduledExecutorService;

import static com.example.Colour.GREEN;
import static com.example.Colour.RED;
import static com.example.Colour.YELLOW;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class IntersectionTest {
    @InjectMocks
    private Intersection intersection;
    @Mock
    private ScheduledExecutorService executor;

    @Test
    public void shouldSetColourRedWhenCreateIntersection() {
        assertThat(intersection.getT1().getColour(), is(equalTo(RED)));
        assertThat(intersection.getT2().getColour(), is(equalTo(RED)));
    }

    @Test
    public void shouldSetColourGreenWhenOpen() {
        intersection.open();
        assertThat(intersection.getT1().getColour(), is(equalTo(GREEN)));
        assertThat(intersection.getT2().getColour(), is(equalTo(RED)));
    }

    @Test
    public void shouldSetColourYellowAfterGreen() {
        ArgumentCaptor<Runnable> scheduledTaskCaptor = ArgumentCaptor.forClass(Runnable.class);
        intersection.open();
        verify(executor).schedule(scheduledTaskCaptor.capture(), eq(270L), eq(SECONDS));
        scheduledTaskCaptor.getValue().run();
        assertThat(intersection.getT1().getColour(), is(equalTo(YELLOW)));
        assertThat(intersection.getT2().getColour(), is(equalTo(RED)));
    }

    @Test
    public void shouldSetColourRedAfterYellow() {
        ArgumentCaptor<Runnable> scheduledTaskCaptor = ArgumentCaptor.forClass(Runnable.class);
        intersection.open();
        verify(executor).schedule(scheduledTaskCaptor.capture(), eq(270L), eq(SECONDS));
        scheduledTaskCaptor.getValue().run();
        verify(executor).schedule(scheduledTaskCaptor.capture(), eq(30L), eq(SECONDS));
        scheduledTaskCaptor.getValue().run();
        assertThat(intersection.getT1().getColour(), is(equalTo(RED)));
    }

    @Test
    public void shouldSetColourOfNextLightWhenFirstBecomesRed() {
        ArgumentCaptor<Runnable> scheduledTaskCaptor = ArgumentCaptor.forClass(Runnable.class);
        intersection.open();
        verify(executor).schedule(scheduledTaskCaptor.capture(), eq(270L), eq(SECONDS));
        scheduledTaskCaptor.getValue().run();
        verify(executor).schedule(scheduledTaskCaptor.capture(), eq(30L), eq(SECONDS));
        scheduledTaskCaptor.getValue().run();
        verify(executor).schedule(scheduledTaskCaptor.capture(), eq(0L), eq(SECONDS));
        scheduledTaskCaptor.getValue().run();
        assertThat(intersection.getT1().getColour(), is(equalTo(RED)));
        assertThat(intersection.getT2().getColour(), is(equalTo(GREEN)));
    }
}
