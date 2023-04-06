package persistence;

import model.Athlete;
import model.Event;
import model.EventCategory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    public void testWriterInvalidFile() {
        try {
            Athlete a = new Athlete("Skye");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected exception
        }
    }

    @Test
    void testWriterAthleteNoEventsOrRaces() {
        try {
            Athlete a1 = new Athlete("Skye");
            JsonWriter writer = new JsonWriter("./data/TestWriterAthleteNoEventsOrRaces.json");
            writer.open();
            writer.write(a1);
            writer.close();

            JsonReader reader = new JsonReader("./data/TestWriterAthleteNoEventsOrRaces.json");
            Athlete a2 = reader.read();
            assertEquals("Skye", a2.getName());
            assertEquals(0, a2.numEvents());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterAthleteWithEventsAndRaces() {
        try {
            Athlete a1 = new Athlete("Skye");
            addEventsForTesting(a1);
            JsonWriter writer = new JsonWriter("./data/TestWriterAthleteWithEventsAndRaces.json");
            writer.open();
            writer.write(a1);
            writer.close();

            JsonReader reader = new JsonReader("./data/TestWriterAthleteWithEventsAndRaces.json");
            Athlete a2 = reader.read();
            assertEquals("Skye", a2.getName());
            assertEquals(2, a2.numEvents());

            Event midDist800 = a2.getEvent("800m");
            checkEvent(midDist800, 800, (float) 2, EventCategory.MID_DIST, Duration.parse("PT2M20S"), 2);
            checkRace(midDist800.getRace(0), LocalDate.of(2022, 6, 24),
                    Duration.parse("PT2M25.11S"), false,18);
            checkRace(midDist800.getRace(1), LocalDate.of(2022, 6, 24),
                    Duration.parse("PT2M23.40S"), true, 9);

            Event steeple3000 = a2.getEvent("3000m Steeplechase");
            checkEvent(steeple3000, 3000, (float) 7.5, EventCategory.STEEPLECHASE, null, 0);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private void addEventsForTesting(Athlete a) {
        Event midDist800 = new Event(800, EventCategory.MID_DIST);
        a.addEvent(midDist800);
        midDist800.setGoalTime(Duration.parse("PT2M20S"));
        midDist800.addRace(LocalDate.of(2022, 6, 24), Duration.parse("PT2M23.40S"), 9);
        midDist800.addRace(LocalDate.of(2022, 6, 24), Duration.parse("PT2M25.11S"), 18);

        Event steeple3000 = new Event(3000, EventCategory.STEEPLECHASE);
        a.addEvent(steeple3000);
    }
}


