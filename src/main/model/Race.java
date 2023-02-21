package model;

import java.time.Duration;
import java.time.LocalDate;

/*
Represents a race, specifically a particular occurrence of participating in a particular event. A race has
statistics including the date it was run, the results, and whether it was a new personal best at the time (all from
the individual athlete's perspective).
*/
public class Race {

    private LocalDate date;
    private Duration time;
    private boolean isPB;
    private int placement;

    // REQUIRES: time is a positive non-zero Duration, placement >= 1
    // EFFECTS: creates a race with a date, result time, and placement, indicating if it is a PB
    public Race(LocalDate date, Duration time, int placement, boolean isPB) {
        // stub
    }

    public LocalDate getDate() {
        return date;
    }

    public Duration getTime() {
        return time;
    }

    public boolean isPB() {
        return isPB;
    }

    public int getPlacement() {
        return placement;
    }



}
