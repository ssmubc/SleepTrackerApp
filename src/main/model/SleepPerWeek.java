package model;

import java.util.ArrayList;
import java.util.List;

public class SleepPerWeek {

    private List<SleepModel> sleepPerWeek;  // a log of the sleep entries throughout the week


    // EFFECTS: creates an array list containing objects from the SleepModel class.
    public SleepPerWeek() {
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

}
