package model;


// Enumeration of the different categories of track events
public enum TrackEventCategory {
    SPRINT("Sprint"),
    MID_DIST("Middle Distance"),
    LONG_DIST("Long Distance"),
    STEEPLECHASE("Steeplechase"),
    RACE_WALK("Race Walk"),
    HURDLES("Hurdles");

    private final String name;

    // EFFECTS: constructs an event category with the given name
    TrackEventCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
