package persistence;

import model.TrackEvent;
import model.TrackEventCategory;
import model.Race;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkRace(Race r, LocalDate date, Duration time, boolean isPB, int placement) {
        assertEquals(date, r.getDate());
        assertEquals(time, r.getTime());
        assertEquals(isPB, r.isPB());
        assertEquals(placement, r.getPlacement());
    }

    protected void checkEvent(TrackEvent e, int dist, float laps, TrackEventCategory category, Duration goalTime, int numRaces) {
        assertEquals(dist, e.getDistance());
        assertEquals(laps, e.getLaps());
        assertEquals(category, e.getCategory());
        assertEquals(goalTime, e.getGoalTime());
        assertEquals(numRaces, e.numRaces());
    }
}
