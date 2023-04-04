package persistence;

import model.Athlete;
import model.TrackEvent;
import model.TrackEventCategory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.util.stream.Stream;

/*
Represents a reader that reads an athlete from JSON data stored in file
 */
public class JsonReader {
    // code based on JsonReader interface in JsonSerializationDemo project
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads athlete from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public Athlete read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAthlete(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    //          throws IOException if an error occurs opening the file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses athlete from JSON object and returns it
    private Athlete parseAthlete(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Athlete a = new Athlete(name);
        parseTrackEvents(a, jsonObject);
        return a;
    }

    // MODIFIES: a
    // EFFECTS: parses events from JSON object and adds them to athlete
    private void parseTrackEvents(Athlete a, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("events");
        for (Object json : jsonArray) {
            JSONObject nextEvent = (JSONObject) json;
            parseTrackEvent(a, nextEvent);
        }
    }

    // MODIFIES: a
    // EFFECTS: parses an event from JSON object and adds it to athlete
    private void parseTrackEvent(Athlete a, JSONObject jsonObject) {
        int distance = jsonObject.getInt("distance");
        TrackEventCategory category = TrackEventCategory.valueOf(jsonObject.getString("category"));
        TrackEvent trackEvent = new TrackEvent(distance, category);
        if (jsonObject.has("goal time")) {
            Duration goalTime = Duration.parse(jsonObject.getString("goal time"));
            trackEvent.setGoalTime(goalTime);
        }
        parseRaces(trackEvent, jsonObject);
        a.addEvent(trackEvent);
    }

    // MODIFIES: evt
    // EFFECTS: parses races for a particular event from JSON object and adds them to event
    private void parseRaces(TrackEvent evt, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("races");
        for (Object json : jsonArray) {
            JSONObject nextRace = (JSONObject) json;
            parseRace(evt, nextRace);
        }
    }

    // MODIFIES: evt
    // EFFECTS: parses a race for a particular event from JSON object and adds it to event
    private void parseRace(TrackEvent evt, JSONObject jsonObject) {
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));
        Duration time = Duration.parse(jsonObject.getString("time"));
        int placement = jsonObject.getInt("placement");
        evt.addRace(date, time, placement);
    }
}
