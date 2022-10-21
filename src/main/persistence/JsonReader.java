package persistence;

import model.Category;
import model.SleepModel;
import model.SleepPerWeek;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads SleepPerWeek from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads SleepPerWeek from the file and returns it
    // throws IOException if an error occurs reading data from file
    public SleepPerWeek read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSleepPerWeek(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }



    // EFFECTS: parses sleepPerWeek from JSON object and returns it
    private SleepPerWeek parseSleepPerWeek(JSONObject jsonObject) {
        SleepPerWeek sleepPerWeek = new SleepPerWeek();
        addSleepModels(sleepPerWeek, jsonObject);
        return sleepPerWeek;
    }

    // MODIFIES: sleepPerWeek
    // EFFECTS: parses SleepModels from JSON object and adds it to SleepPerWeek
    private void addSleepModels(SleepPerWeek sleepPerWeek, JSONObject jsonObject) {
        //Category category = Category.valueOf(jsonObject.getString("Enter the day of the week from the options: "));
        JSONArray jsonArray = jsonObject.getJSONArray("SleepModels");
        for (Object json : jsonArray) {
            JSONObject nextSleepModel = (JSONObject) json;
            addSleepModel(sleepPerWeek, nextSleepModel);
        }
    }


    // MODIFIES: sleepPerWeek
    // EFFECTS: parses sleepModel from JSON object and adds it to sleepPerWeek
    private void addSleepModel(SleepPerWeek sleepPerWeek, JSONObject jsonObject) {
        String dayOfTheWeek =
                jsonObject.getString("Enter the day of the week (Mon, Tues, Wed, Thurs, Friday, Sat, Sunday): ");
        Double numOfHoursSlept = jsonObject.getDouble("Enter the number of hours you slept today: ");
        Boolean examOrNot = jsonObject.getBoolean("Are there any exams coming up this week (true/false): ");
        SleepModel sleepModel = new SleepModel(dayOfTheWeek, numOfHoursSlept, examOrNot);
        sleepPerWeek.addSleepModel(sleepModel);
    }
}
