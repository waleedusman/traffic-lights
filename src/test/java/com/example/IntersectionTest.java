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
import static org.junit.Assert.assertTrue;
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
        assertTrue(intersection.getNorthSouth().stream().allMatch(t -> t.getColour().equals(RED)));
        assertTrue(intersection.getEastWest().stream().allMatch(t -> t.getColour().equals(RED)));
    }

    @Test
    public void shouldSetColourGreenWhenOpen() {
        intersection.open();
        assertTrue(intersection.getNorthSouth().stream().allMatch(t -> t.getColour().equals(GREEN)));
        assertTrue(intersection.getEastWest().stream().allMatch(t -> t.getColour().equals(RED)));
    }

    @Test
    public void shouldSetColourYellowAfterGreen() {
        ArgumentCaptor<Runnable> scheduledTaskCaptor = ArgumentCaptor.forClass(Runnable.class);
        intersection.open();
        verify(executor).schedule(scheduledTaskCaptor.capture(), eq(270L), eq(SECONDS));
        scheduledTaskCaptor.getValue().run();
        assertTrue(intersection.getNorthSouth().stream().allMatch(t -> t.getColour().equals(YELLOW)));
        assertTrue(intersection.getEastWest().stream().allMatch(t -> t.getColour().equals(RED)));
    }

    @Test
    public void shouldSetColourRedAfterYellow() {
        ArgumentCaptor<Runnable> scheduledTaskCaptor = ArgumentCaptor.forClass(Runnable.class);
        intersection.open();
        verify(executor).schedule(scheduledTaskCaptor.capture(), eq(270L), eq(SECONDS));
        scheduledTaskCaptor.getValue().run();
        verify(executor).schedule(scheduledTaskCaptor.capture(), eq(30L), eq(SECONDS));
        scheduledTaskCaptor.getValue().run();
        assertTrue(intersection.getNorthSouth().stream().allMatch(t -> t.getColour().equals(RED)));
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
        assertTrue(intersection.getNorthSouth().stream().allMatch(t -> t.getColour().equals(RED)));
        assertTrue(intersection.getEastWest().stream().allMatch(t -> t.getColour().equals(GREEN)));
    }
}
