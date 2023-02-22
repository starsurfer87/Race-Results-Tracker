package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    private static final LocalDate MAY10_2021 = LocalDate.of(2021, 5, 10);
    private static final LocalDate APRIL18_2022 = LocalDate.of(2022, 4, 18);
    private static final LocalDate JUNE27_2022 = LocalDate.of(2022, 6, 27);

    private Event veryShortSprint;
    private Event hurdles110;
    private Event midDist1500;

    private Race raceA;
    private Race raceB;
    private Race raceC;

    @BeforeEach
    public void setup() {
        veryShortSprint = new Event(1, EventCategory.SPRINT);
        hurdles110 = new Event(110, EventCategory.HURDLES);
        midDist1500 = new Event(1500, EventCategory.MID_DIST);

        midDist1500.addRace(MAY10_2021, Duration.ofSeconds(300), 5);
        midDist1500.addRace(APRIL18_2022, Duration.ofSeconds(320), 3);
        midDist1500.addRace(JUNE27_2022, Duration.ofSeconds(280), 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, veryShortSprint.getDistance());
        assertEquals((float) 1 / Event.LAP_DIST, veryShortSprint.getLaps());
        assertEquals(EventCategory.SPRINT, veryShortSprint.getCategory());
        assertNull(veryShortSprint.getGoalTime());
        assertEquals(0, veryShortSprint.numRaces());

        assertEquals(110, hurdles110.getDistance());
        assertEquals((float) 110 / Event.LAP_DIST, hurdles110.getLaps());
        assertEquals(EventCategory.HURDLES, hurdles110.getCategory());
        assertNull(hurdles110.getGoalTime());
        assertEquals(0, hurdles110.numRaces());
    }

    @Test
    public void testGetName() {
        assertEquals("110m Hurdles", hurdles110.getName());
        assertEquals("1500m", midDist1500.getName());
    }

    @Test
    public void testGetPBOnlyOne() {

    }

    @Test
    public void testGetPBChooseFromMultiple() {

    }

    @Test
    public void testGetAllPBsNoRaces() {

    }

    @Test
    public void testGetAllPBs() {

    }

    @Test
    public void testAddRace() {
        assertEquals(0, hurdles110.numRaces());

        hurdles110.addRace(MAY10_2021, Duration.ofSeconds(18), 1);
        assertEquals(1, hurdles110.numRaces());
        checkCorrectRace(hurdles110.getRace(0), MAY10_2021, 18, 1, true);

        hurdles110.addRace(APRIL18_2022, Duration.ofSeconds(16), 5);
        assertEquals(2, hurdles110.numRaces());
        checkCorrectRace(hurdles110.getRace(0), APRIL18_2022, 16, 5, true);
        checkCorrectRace(hurdles110.getRace(1), MAY10_2021, 18, 1, true);

        hurdles110.addRace(JUNE27_2022, Duration.ofSeconds(17), 2);
        assertEquals(3, hurdles110.numRaces());
        checkCorrectRace(hurdles110.getRace(0), JUNE27_2022, 17, 2, false);
        checkCorrectRace(hurdles110.getRace(1), APRIL18_2022, 16, 5, true);
        checkCorrectRace(hurdles110.getRace(3), MAY10_2021, 18, 1, true);
    }

    @Test
    public void testGoalTimeLapSplitFullLapsEvent() {

    }

    @Test
    public void testGoalTimeLapSplitPartialLapsEvent() {

    }

    @Test
    public void testGetRace() {
       checkCorrectRace(midDist1500.getRace(2), MAY10_2021, 300, 5, true);
       checkCorrectRace(midDist1500.getRace(1), APRIL18_2022, 320, 3, false);
       checkCorrectRace(midDist1500.getRace(0), JUNE27_2022, 280, 1, true);
    }

    @Test
    public void testRemoveRace() {

    }

    @Test
    public void testNumRaces() {
        assertEquals(0, hurdles110.numRaces());
        assertEquals(3, midDist1500.numRaces());
    }

    public void checkCorrectRace(Race race, LocalDate expectedDate, int expectedSeconds, int expectedPlacement, boolean expectedPB) {
        assertEquals(expectedDate, race.getDate());
        assertEquals(Duration.ofSeconds(expectedSeconds), race.getTime());
        assertEquals(expectedPlacement, race.getPlacement());
        assertEquals(expectedPB, race.isPB());
    }


}
