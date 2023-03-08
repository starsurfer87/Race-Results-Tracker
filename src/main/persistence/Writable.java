package persistence;

import netscape.javascript.JSObject;

public interface Writable {
    // code from Writable interface in JsonSerializationDemo project
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: returns this as a JSON object
    JSObject toJson();
}
