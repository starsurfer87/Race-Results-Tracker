package persistence;

import model.Event;
import model.EventCategory;
import model.Race;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkRace(Race race, LocalDate date, Duration time, boolean isPB, int placement) {
        assertEquals(date, race.getDate());
        assertEquals(time, race.getTime());
        assertEquals(isPB, race.isPB());
        assertEquals(placement, race.getPlacement());
    }

    protected void checkEvent(Event event, int distance, float laps, EventCategory category, Duration goalTime) {
        assertEquals(distance, event.getDistance());
        assertEquals(laps, event.getLaps());
        assertEquals(category, event.getCategory());
        assertEquals(goalTime, event.getGoalTime());
    }
}
