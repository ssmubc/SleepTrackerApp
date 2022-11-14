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
        return sleepPerWeek;
    }
//    public List<SleepModel> addSleepModel(SleepModel sleep) {
//        if (sleep != null) {
//            sleepPerWeek.add(sleep);
//
//        }
//        return sleepPerWeek;
//    }


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


    // added on Nov 9
    public Boolean removeSleepModel(int index) {
        if (index < sleepPerWeek.size()) {
            sleepPerWeek.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: lists all the cars in list and their respective details
    public String getSleepEntries() {
        String entries = "";
        for (int i = 0; i < sleepPerWeek.size(); i++) {
            entries += "Sleep Entry #" + (i + 1) + "\n" + sleepPerWeek.get(i).getDayOfTheWeek()
                    + "\n" + sleepPerWeek.get(i).getActualSleepPerDay() + "\n"
                    + sleepPerWeek.get(i).getExamOrNot() + "\n" + "\n";
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


//    // ADDED NOV 9
//    public List<String> makeEntries(int index) {
//        String entry = "Day: " + sleepPerWeek.get(index).getActualSleepPerDay() + "Number of hours slept: " +
//        sleepPerWeek.get(index).getActualSleepPerDay()
//                + "Had an exam: " + sleepPerWeek.get(index).getExamOrNot() + ""
//    }



    // ADDED NOV 9th
    public double hoursWithoutExam() {
        double hours = 0;
        for (SleepModel sleep : sleepPerWeek) {
            if (!sleep.getExamOrNot()) {
                hours += sleep.getActualSleepPerDay();
            }
        }
        return hours;
    }

    public double hoursWithExam() {
        double hours = 0;
        for (SleepModel sleep : sleepPerWeek) {
            if (sleep.getExamOrNot()) {
                hours += sleep.getActualSleepPerDay();
            }
        }
        return hours;
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