package model;

import java.util.ArrayList;
import java.util.List;

public class SleepPerWeek {

    private List<SleepModel> sleepPerWeek;

    public SleepPerWeek() {
        this.sleepPerWeek = new ArrayList<SleepModel>();
    }

//    public List<SleepModel> addSleepModel(SleepModel sleep) {
//        if (!sleepPerWeek.contains(sleep)) {
//            sleepPerWeek.add(sleep);
//            return sleepPerWeek;
//            //return true;
//        } else {
//            return sleepPerWeek;
//            //return false;
//        }
//    }

    public List<SleepModel> addSleepModel(SleepModel sleep) {
        if (!sleepPerWeek.contains(sleep)) {
            sleepPerWeek.add(sleep);
        }
        return sleepPerWeek;
    }

    public List<SleepModel> getSleepPerWeek() {
        return sleepPerWeek;
    }



    // REQUIRES: sleep != null
    // MODIFIES: this
//    // EFFECTS: removes a sleep entry from the log.
//    public void removeSleepModel(SleepModel sleep) {
//        sleepPerWeek.remove(sleep);
//
//    }
    public void removeSleepModel(SleepModel sleep) {
        sleepPerWeek.remove(sleep);

    }



    //
    // MODIFIES: this
    // EFFECTS: replace a sleep entry in the log with another sleep entry.
    public void editSleepModel(SleepModel original, SleepModel replacement) {
        sleepPerWeek.set(sleepPerWeek.indexOf(original), replacement);
    }



}
