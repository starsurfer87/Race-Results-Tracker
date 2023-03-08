package persistence;

public class JsonReader {
    // code based on JsonReader interface in JsonSerializationDemo project
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }
}
