package model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

/*
Represents a track event of a certain distance and category, with a goal time and a list of races of the event that
the athlete has participated in.
*/
public class Event {

    public static final int LAP_DIST = 400;

    private int distance; // in meters;
    private float laps;
    private EventCategory category;
    private Duration goalTime;
    private List<Race> races;


    // REQUIRES: distance > 0
    // EFFECTS: creates an event of a given distance (in meters) and category with no races
    public Event(int distance, EventCategory category) {
        // stub
    }

    // EFFECTS: returns name of event based on its distance and category
    //          no contribution from category if category is SPRINT, MID-DIST, or LONG-DIST
    public String getName() {
        return null; // stub
    }

    // REQUIRES: races is not an empty list
    // EFFECTS: returns the athlete's current personal best time for this event
    public Duration getPB() {
        return null; // stub
    }

    // EFFECTS: returns a list of races in which the athlete achieved a new personal best time for this event
    public List<Race> getAllPBs() {
        return null; // stub
    }

    // REQUIRES: date is more recent than the date of the most recent race in races, time is a positive non-zero
    //           Duration, placement >= 1
    // MODIFIES: this
    // EFFECTS: adds a race with a date, result time, and placement, maintaining chronological order of race (most
    //          recent first) and determining whether the race is a PB
    public void addRace(LocalDate date, Duration time, int placement) {
        // stub
    }

    // REQUIRES: a goal time has been set, distance >= LAP_DIST
    // EFFECTS: returns the lap split time needed for athlete to achieve their PB
    public Duration goalTimeLapSplit() {
        return null; // stub
    }

    // REQUIRES: 0 <= i < numRaces()
    // EFFECTS: returns the race at index i of races
    public Race getRace(int i) {
        return null; // stub
    }

    // REQUIRES: 0 <= i < numRaces()
    // MODIFIES: this
    // EFFECTS: removes the race at index i of races
    public void removeRace(int i) {
        // stub
    }

    // EFFECTS: returns the number of races of this event
    public int numRaces() {
        return 0; // stub
    }

    public int getDistance() {
        return distance;
    }

    public float getLaps() {
        return laps;
    }

    public EventCategory getCategory() {
        return category;
    }

    public Duration getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(Duration goalTime) {
        this.goalTime = goalTime;
    }

}
