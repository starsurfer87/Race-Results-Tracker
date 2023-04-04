package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AthleteTest {

    Athlete athlete;
    TrackEvent midDist1500v1;
    TrackEvent midDist1500v2;
    TrackEvent hurdles110;

    @BeforeEach
    public void setup() {
        athlete = new Athlete("Skye");
        midDist1500v1 = new TrackEvent(1500, TrackEventCategory.MID_DIST);
        midDist1500v2 = new TrackEvent(1500, TrackEventCategory.MID_DIST);
        hurdles110 = new TrackEvent(110, TrackEventCategory.HURDLES);
    }

    @Test
    public void testConstructor() {
        assertEquals("Skye", athlete.getName());
        assertEquals(0, athlete.numEvents());
    }

    @Test
    public void testAddEvent() {
        assertTrue(athlete.addEvent(midDist1500v1));
        assertEquals(1, athlete.numEvents());
        assertEquals(midDist1500v1, athlete.getEvent(midDist1500v1.getName()));

        assertFalse(athlete.addEvent(midDist1500v2));
        assertEquals(1, athlete.numEvents());
        assertEquals(midDist1500v1, athlete.getEvent(midDist1500v2.getName()));
    }

    @Test
    public void testNumEvents() {
        assertEquals(0, athlete.numEvents());
        athlete.addEvent(midDist1500v1);
        assertEquals(1, athlete.numEvents());
        athlete.addEvent(hurdles110);
        assertEquals(2, athlete.numEvents());
    }

    @Test
    public void testGetEventNames() {
        Set<String> expectedNames = new HashSet<>();
        assertEquals(expectedNames, athlete.getEventNames());

        athlete.addEvent(midDist1500v1);
        athlete.addEvent(hurdles110);
        expectedNames.add(midDist1500v1.getName());
        expectedNames.add(hurdles110.getName());
        assertEquals(expectedNames, athlete.getEventNames());
    }

    @Test
    public void testGetEventExists() {
        athlete.addEvent(hurdles110);
        assertEquals(hurdles110, athlete.getEvent(hurdles110.getName()));
    }

    @Test
    public void testGetEventDoesNotExist() {
        assertNull(athlete.getEvent("1500m"));
    }
}
