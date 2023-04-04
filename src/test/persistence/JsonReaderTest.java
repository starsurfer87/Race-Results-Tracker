package persistence;

import model.Athlete;
import model.TrackEvent;
import model.TrackEventCategory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("data/noSuchFile.json");
        try {
            Athlete a = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected exception
        }
    }

    @Test
    public void testReaderAthleteNoEventsOrRaces() {
        JsonReader reader = new JsonReader("./data/TestReaderAthleteNoEventsOrRaces.json");
        try {
            Athlete a = reader.read();
            assertEquals("Andre De Grasse", a.getName());
            assertEquals(0, a.numEvents());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderAthleteWithEventsAndRaces() {
        JsonReader reader = new JsonReader("./data/TestReaderAthleteWithEventsAndRaces.json");
        try {
            Athlete a = reader.read();
            assertEquals("Skye", a.getName());
            assertEquals(2, a.numEvents());

            TrackEvent midDist1500 = a.getEvent("1500m");
            checkEvent(midDist1500, 1500, (float) 3.75, TrackEventCategory.MID_DIST,
                    Duration.parse("PT4M50S"), 2);
            checkRace(midDist1500.getRace(0), LocalDate.of(2022, 6, 29),
                    Duration.parse("PT4M54.12S"), true, 9);
            checkRace(midDist1500.getRace(1), LocalDate.of(2022, 3, 26),
                    Duration.parse("PT4M58.18S"), true, 14);

            checkEvent(a.getEvent("400m"), 400, (float) 1, TrackEventCategory.SPRINT,
                    null, 0);

        } catch (IOException e) {
            fail("Couldn't reader from file");
        }
    }
}
