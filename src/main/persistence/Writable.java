package persistence;

import org.json.JSONObject;

/*
Represents an object that can be written as a JSON object
 */
public interface Writable {
    // code from Writable interface in JsonSerializationDemo project
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
