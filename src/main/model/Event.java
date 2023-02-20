package model;

import java.time.Duration;
import java.util.List;

/*
Represents a track event of a certain distance and category, with a goal time and a list of races of the event that
the athlete has participated in.
*/
public class Event {

    private static final int LAP_DIST = 400;

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
    public String getName() {
        return null; // stub
    }

    // EFFECTS: returns a list of races in which the athlete achieved a personal best time for this event
    public List<Race> getPBs() {
        return null; // stub
    }

    // REQUIRES: date is more recent than the date of the most recent race in races, time is a positive Duration,
    //           placement >= 1
    // MODIFIES: this
    // EFFECTS: adds a race with a date, result time, and placement, maintaining chronological order of race
    public void addRace() {
        // stub
    }

    // REQUIRES: a goal time has been set
    // EFFECTS: returns the lap split time needed for athlete to achieve their PB
    public Duration goalTimeLapSplit() {
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

    public Duration getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(Duration goalTime) {
        this.goalTime = goalTime;
    }

}
