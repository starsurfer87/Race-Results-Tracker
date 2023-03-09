package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.Duration;
import java.time.LocalDate;

/*
Represents a race, specifically a particular occurrence of participating in a particular event. A race has
statistics including the date it was run, the results, and whether it was a new personal best at the time (all from
the individual athlete's perspective).
*/
public class Race implements Writable {

    private LocalDate date;
    private Duration time;
    private boolean isPB;
    private int placement;

    // REQUIRES: time is a positive non-zero Duration, placement >= 1
    // EFFECTS: creates a race with a date, result time, and placement, indicating if it is a PB
    public Race(LocalDate date, Duration time, int placement, boolean isPB) {
        this.date = date;
        this.time = time;
        this.placement = placement;
        this.isPB = isPB;
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

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("time", time);
        json.put("placement", placement);
        return json;
    }
}
