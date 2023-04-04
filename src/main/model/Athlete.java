package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
Represents an athlete with track events that they compete in
 */
public class Athlete implements Writable {

    private String name;
    private Map<String, TrackEvent> events;

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
    //          otherwise does nothing and returns false
    public boolean addEvent(TrackEvent trackEvent) {
        String eventName = trackEvent.getName();
        if (getEvent(eventName) == null) {
            events.put(eventName, trackEvent);
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
    public TrackEvent getEvent(String name) {
        return events.get(name);
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("events", eventsToJson());
        return json;
    }

    // EFFECTS: returns this athlete's events as a JSON Array
    private JSONArray eventsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (TrackEvent evt : events.values()) {
            jsonArray.put(evt.toJson());
        }

        return jsonArray;
    }
}
