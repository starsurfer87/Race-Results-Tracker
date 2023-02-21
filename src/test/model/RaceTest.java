package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RaceTest {

    private final LocalDate DATE1 = LocalDate.of(2018, 1, 1);
    private final LocalDate DATE2 = LocalDate.of(2022,12,31);

    Race race1;
    Race race2;

    @BeforeEach
    public void setup() {
        race1 = new Race(DATE1, Duration.ofSeconds(1), 1, true);
        race2 = new Race(DATE2, Duration.ofMinutes(5), 8, false);
    }

    @Test
    public void testConstructor() {
        assertEquals(DATE1, race1.getDate());
        assertEquals(Duration.ofSeconds(1), race1.getTime());
        assertEquals(1, race1.getPlacement());
        assertTrue(race1.isPB());

        assertEquals(DATE2, race2.getDate());
        assertEquals(Duration.ofMinutes(5), race2.getTime());
        assertEquals(8, race2.getPlacement());
        assertFalse(race2.isPB());
    }
}
