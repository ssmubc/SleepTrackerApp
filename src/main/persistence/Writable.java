package persistence;

import org.json.JSONObject;

// CITATION: studied and referenced:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents an interface Writable, that returns this as a JSON object.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();


}
