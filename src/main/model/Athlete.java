package model;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
Represents an athlete with track events that they compete in
 */
public class Athlete {

    private String name;
    private Map<String, Event> events;

    // EFFECTS: creates an athlete with a given name and no events
    public Athlete(String name) {
        this.name = name;
        events = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: if event with same name does not yet exist, then adds event using its name as its key and returns true,
    // otherwise does nothing and returns false
    public boolean addEvent(Event event) {
        String eventName = event.getName();
        if (getEvent(eventName) == null) {
            events.put(eventName, event);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns the number of events that this athlete competes in
    public int numEvents() {
        return events.size();
    }

    // EFFECTS: returns a set of the names of all the events the athlete competes in
    public Set<String> getEventNames() {
        return events.keySet();
    }

    // EFFECTS: returns the event with the given name or null if no event with that name exists
    public Event getEvent(String name) {
        return events.get(name);
    }
}
