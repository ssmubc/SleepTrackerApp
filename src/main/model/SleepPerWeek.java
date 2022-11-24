package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Represents a list of weekly sleep pattern entries
public class SleepPerWeek implements Writable {

    private List<SleepModel> sleepPerWeek;  // a log of the sleep entries throughout the week
    private String month;



    // EFFECTS: creates an array list containing objects from the SleepModel class.
    public SleepPerWeek() {
        LocalDateTime dateTime = LocalDateTime.now();
        month = dateTime.getMonth().name();
        this.sleepPerWeek = new ArrayList<SleepModel>();
    }


    // MODIFIES: this
    // EFFECTS: sleep is added to the sleepPerWeek list if it does not already contain the sleep entry.
    public List<SleepModel> addSleepModel(SleepModel sleep) {
        if (!sleepPerWeek.contains(sleep)) {
            sleepPerWeek.add(sleep);
        }

        EventLog.getInstance().logEvent(new Event("Added sleep entry to your log"));
        return sleepPerWeek;

    }



    // EFFECTS: returns the sleepPerWeek list.
    public List<SleepModel> getSleepPerWeek() {
        return sleepPerWeek;
    }

    public String getMonth() {
        return month;
    }



    // MODIFIES: this
    // EFFECTS: removes a sleep entry from the log based on its index.
    public Boolean removeSleepModel(int index) {
        if (index < sleepPerWeek.size()) {
            sleepPerWeek.remove(index);

            // Added this line Nov 21
            EventLog.getInstance().logEvent(new Event("Removed a sleep entry from your log"));
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: creates a list of the sleep entries
    public String getSleepEntries() {
        String entries = "";
        for (int i = 0; i < sleepPerWeek.size(); i++) {
            entries += "Sleep Entry #" + (i + 1) + "\n" + "Day of the week: " + sleepPerWeek.get(i).getDayOfTheWeek()
                    + "\n" + "Hours slept today: " + sleepPerWeek.get(i).getActualSleepPerDay() + "\n"
                    + "Have exams coming up: " + sleepPerWeek.get(i).getExamOrNot() + "\n" + "\n";
        }
        return entries;
    }





    // REQUIRES: original != null
    // MODIFIES: this
    // EFFECTS: replace a sleep entry in the log with another sleep entry.
    public void editSleepModel(SleepModel original, SleepModel replacement) {
        sleepPerWeek.set(sleepPerWeek.indexOf(original), replacement);
    }

    // EFFECTS: returns number of thingies in this workroom
    public int numEntries() {
        return sleepPerWeek.size();
    }




    // CITATION: studied and referenced:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: creates a new Json object with the corresponding month and the weekly sleep log
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Month", month);
        json.put("Weekly sleep log", sleepModelsToJson());
        return json;
    }


    // EFFECTS: returns sleepModels in this sleepPerWeek as a JSON array
    private JSONArray sleepModelsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (SleepModel sleepModel : sleepPerWeek) {
            jsonArray.put(sleepModel.toJson());
        }
        return jsonArray;
    }
}