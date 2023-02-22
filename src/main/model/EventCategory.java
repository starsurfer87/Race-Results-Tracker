package model;


// Enumeration of the different categories of track events
public enum EventCategory {
    SPRINT("Sprint"),
    MID_DIST("Middle Distance"),
    LONG_DIST("Long Distance"),
    STEEPLECHASE("Steeplechase"),
    RACE_WALK("Race Walk"),
    HURDLES("Hurdles");

    private final String name;

    EventCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
