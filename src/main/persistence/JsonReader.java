package persistence;

import model.Athlete;
import model.Event;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

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
        parseEvents(a, jsonObject);
        return a;
    }

    // MODIFIES: a
    // EFFECTS: parses events from JSON object and adds them to athlete
    private void parseEvents(Athlete a, JSONObject jsonObject) {
        // TODO
    }

    // MODIFIES: e
    // EFFECTS: parses races for a particular event from JSON object and adds them to event
    private void parseRaces(Event e, JSONObject jsonObject) {
        // TODO
    }
}
