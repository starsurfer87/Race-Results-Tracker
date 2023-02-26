package model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
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
        this.distance = distance;
        this.laps = (float) distance / LAP_DIST;
        this.category = category;
        this.races = new ArrayList<>();
    }

    // EFFECTS: returns name of event based on its distance and category
    //          no contribution from category if category is a sprint, middle-distance, or long-distance race
    public String getName() {
        String distanceString = distance + "m";
        if (category == EventCategory.SPRINT || category == EventCategory.MID_DIST
                || category == EventCategory.LONG_DIST) {
            return distanceString;
        } else {
            return distanceString + " " + category.getName();
        }
    }


    // EFFECTS: returns the athlete's current personal best time for this event, or null if there are no races for this
    //          event yet
    public Duration getPB() {
        for (Race race : races) {
            if (race.isPB()) {
                return race.getTime();
            }
        }
        return null;
    }

    // EFFECTS: returns a list of races in which the athlete achieved a new personal best time for this event
    public List<Race> getAllPBs() {
        List<Race> pbRaces = new ArrayList<>();
        for (Race race : races) {
            if (race.isPB()) {
                pbRaces.add(race);
            }
        }
        return pbRaces;
    }

    // REQUIRES: date is more recent than or equal to the date of the most recent race in races, time is a positive
    //           non-zero
    //           Duration, placement >= 1
    // MODIFIES: this
    // EFFECTS: adds a race with a date, result time, and placement, determining whether the race is a PB
    //          races are arranged from most recent to least recent (if date is the same, last race added is considered
    //          more recent)
    public void addRace(LocalDate date, Duration time, int placement) {
        Race newRace;

        if (races.size() == 0) {
            newRace = new Race(date, time, placement, true);
        } else {
            Duration priorPB = getPB();
            boolean isPB = time.compareTo(priorPB) < 0;
            newRace = new Race(date, time, placement, isPB);
        }

        races.add(0, newRace);
    }

    // REQUIRES: a goal time has been set, distance >= LAP_DIST
    // EFFECTS: returns the lap split time needed for athlete to achieve their PB
    public Duration goalTimeLapSplit() {
        return goalTime.dividedBy(distance).multipliedBy(LAP_DIST);
    }

    // REQUIRES: 0 <= i < numRaces()
    // EFFECTS: returns the race at index i of the list of this event's races
    public Race getRace(int i) {
        return races.get(i);
    }

    // EFFECTS: returns the number of races of this event
    public int numRaces() {
        return races.size();
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

    // REQUIRES: goalTime is a positive non-zero Duration
    // MODIFIES: this
    // EFFECTS: sets the goal time for the event
    public void setGoalTime(Duration goalTime) {
        this.goalTime = goalTime;
    }

}
