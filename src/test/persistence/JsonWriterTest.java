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

public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
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
            Athlete a = new Athlete("Skye");
            JsonWriter writer = new JsonWriter("./data/TestWriterAthleteNoEventsOrRaces.json");
            writer.open();
            writer.write(a);
            writer.close();

//            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
//            wr = reader.read();
//            assertEquals("My work room", wr.getName());
//            assertEquals(0, wr.numThingies());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

        fail("test incomplete"); // TODO
    }

    @Test
    void testWriterAthleteWithEventsAndRaces() {
        try {
            Athlete a = new Athlete("Skye");

            Event midDist800 = new Event(800, EventCategory.MID_DIST);
            a.addEvent(midDist800);
            midDist800.setGoalTime(Duration.parse("PT2M20S"));
            midDist800.addRace(LocalDate.of(2022, 6, 24), Duration.parse("PT2M23.40S"), 9);
            midDist800.addRace(LocalDate.of(2022, 6, 24), Duration.parse("PT2M25.11S"), 18);

            Event steeple3000 = new Event(3000, EventCategory.STEEPLECHASE);
            a.addEvent(steeple3000);

            JsonWriter writer = new JsonWriter("./data/TestWriterAthleteWithEventsAndRaces.json");

            writer.open();
            writer.write(a);
            writer.close();

//            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
//            wr = reader.read();
//            assertEquals("My work room", wr.getName());
//            List<Thingy> thingies = wr.getThingies();
//            assertEquals(2, thingies.size());
//            checkThingy("saw", Category.METALWORK, thingies.get(0));
//            checkThingy("needle", Category.STITCHING, thingies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

        fail("test incomplete"); // TODO
    }
}
