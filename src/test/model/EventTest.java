package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    private static final LocalDate MAY10_2021 = LocalDate.of(2021, 5, 10);
    private static final LocalDate MAY11_2021 = LocalDate.of(2021, 5, 11);
    private static final LocalDate APRIL18_2022 = LocalDate.of(2022, 4, 18);

    private Event veryShortSprint;
    private Event hurdles110;
    private Event midDist1500;

    @BeforeEach
    public void setup() {
        veryShortSprint = new Event(1, EventCategory.SPRINT);
        hurdles110 = new Event(110, EventCategory.HURDLES);
        midDist1500 = new Event(1500, EventCategory.MID_DIST);

        midDist1500.addRace(MAY10_2021, Duration.ofSeconds(300), 5); //PB
        midDist1500.addRace(MAY10_2021, Duration.ofSeconds(305), 8);
        midDist1500.addRace(MAY11_2021, Duration.ofSeconds(320), 3);
        midDist1500.addRace(APRIL18_2022, Duration.ofSeconds(299), 2); //PB
        midDist1500.addRace(APRIL18_2022, Duration.ofSeconds(280), 1); //PB
        midDist1500.addRace(APRIL18_2022, Duration.ofSeconds(281), 3);
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
    public void testGetPB() {
        assertEquals(Duration.ofSeconds(280), midDist1500.getPB());
    }

    @Test
    public void testGetAllPBsNoRaces() {
        List<Race> pbList = hurdles110.getAllPBs();
        assertEquals(0, pbList.size());
    }

    @Test
    public void testGetAllPBsOneRace() {
        hurdles110.addRace(MAY10_2021, Duration.ofSeconds(18), 1);
        assertEquals(1, hurdles110.numRaces());
        assertTrue(hurdles110.getRace(0).isPB());

        List<Race> pbList = hurdles110.getAllPBs();
        assertEquals(1, pbList.size());
        checkCorrectRace(pbList.get(0), MAY10_2021, 18, 1, true);
    }

    @Test
    public void testGetAllPBsMultipleRaces() {
        List<Race> pbList = midDist1500.getAllPBs();
        assertEquals(3, pbList.size());
        checkCorrectRace(pbList.get(0), APRIL18_2022, 280, 1, true);
        checkCorrectRace(pbList.get(1), APRIL18_2022, 299, 2, true);
        checkCorrectRace(pbList.get(2), MAY10_2021, 300, 5, true);
    }

    @Test
    public void testAddRace() {
        assertEquals(0, hurdles110.numRaces());

        hurdles110.addRace(MAY10_2021, Duration.ofSeconds(18), 1);
        assertEquals(1, hurdles110.numRaces());
        checkCorrectRace(hurdles110.getRace(0), MAY10_2021, 18, 1, true);

        hurdles110.addRace(MAY11_2021, Duration.ofSeconds(16), 5);
        assertEquals(2, hurdles110.numRaces());
        checkCorrectRace(hurdles110.getRace(0), MAY11_2021, 16, 5, true);
        checkCorrectRace(hurdles110.getRace(1), MAY10_2021, 18, 1, true);

        hurdles110.addRace(APRIL18_2022, Duration.ofSeconds(17), 2);
        assertEquals(3, hurdles110.numRaces());
        checkCorrectRace(hurdles110.getRace(0), APRIL18_2022, 17, 2, false);
        checkCorrectRace(hurdles110.getRace(1), MAY11_2021, 16, 5, true);
        checkCorrectRace(hurdles110.getRace(2), MAY10_2021, 18, 1, true);
    }

    @Test
    public void testAddRaceSameDaySameTime() {
        assertEquals(0, hurdles110.numRaces());

        hurdles110.addRace(MAY10_2021, Duration.ofSeconds(18), 1);
        assertEquals(1, hurdles110.numRaces());
        checkCorrectRace(hurdles110.getRace(0), MAY10_2021, 18, 1, true);

        hurdles110.addRace(MAY10_2021, Duration.ofSeconds(18), 1);
        assertEquals(2, hurdles110.numRaces());
        checkCorrectRace(hurdles110.getRace(0), MAY10_2021, 18, 1, false);
        checkCorrectRace(hurdles110.getRace(1), MAY10_2021, 18, 1, true);
    }

    @Test
    public void testAddRaceSmallPB() {
        assertEquals(0, hurdles110.numRaces());

        hurdles110.addRace(MAY10_2021, Duration.ofSeconds(18), 1);
        assertEquals(1, hurdles110.numRaces());

        hurdles110.addRace(MAY11_2021, Duration.ofSeconds(18).minusMillis(1), 1);
        assertEquals(2, hurdles110.numRaces());
        assertTrue(hurdles110.getRace(0).isPB());
        assertEquals(Duration.ofSeconds(18).minusMillis(1), hurdles110.getRace(0).getTime());
    }

    @Test
    public void testGoalTimeLapSplitOneLapEvent() {
        Event oneLap = new Event(Event.LAP_DIST, EventCategory.SPRINT);
        oneLap.setGoalTime(Duration.ofMinutes(1));
        assertEquals(Duration.ofMinutes(1), oneLap.goalTimeLapSplit());
    }

    @Test
    public void testGoalTimeLapSplitFullLapsEvent() {
        Event midDist800 = new Event(800, EventCategory.MID_DIST);
        midDist800.setGoalTime(Duration.ofMinutes(2).plusSeconds(20));
        assertEquals(Duration.ofSeconds(70), midDist800.goalTimeLapSplit());
    }

    @Test
    public void testGoalTimeLapSplitPartialLapsEvent() {
        midDist1500.setGoalTime(Duration.ofMinutes(5));
        assertEquals(Duration.ofSeconds(80), midDist1500.goalTimeLapSplit());
    }

    @Test
    public void testGetRace() {
        checkCorrectRace(midDist1500.getRace(5), MAY10_2021, 300, 5, true);
        checkCorrectRace(midDist1500.getRace(2), APRIL18_2022, 299, 2, true);
        checkCorrectRace(midDist1500.getRace(0), APRIL18_2022, 281, 3, false);
    }

    @Test
    public void testNumRaces() {
        assertEquals(0, hurdles110.numRaces());
        assertEquals(6, midDist1500.numRaces());
    }

    public void checkCorrectRace(Race race, LocalDate expectedDate, int expectedSeconds, int expectedPlacement, boolean expectedPB) {
        assertEquals(expectedDate, race.getDate());
        assertEquals(Duration.ofSeconds(expectedSeconds), race.getTime());
        assertEquals(expectedPlacement, race.getPlacement());
        assertEquals(expectedPB, race.isPB());
    }
}
