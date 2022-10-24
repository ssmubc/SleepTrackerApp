package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Creates weekly sleep pattern lists
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
        return sleepPerWeek;
    }


    // EFFECTS: returns the sleepPerWeek list.
    public List<SleepModel> getSleepPerWeek() {
        return sleepPerWeek;
    }

    public String getMonth() {
        return month;
    }


    // REQUIRES: sleep != null
    // MODIFIES: this
    // EFFECTS: removes a sleep entry from the log.
    public void removeSleepModel(SleepModel sleep) {
        sleepPerWeek.remove(sleep);
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



    @Override // ASK TA
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

