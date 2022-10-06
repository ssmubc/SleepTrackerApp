package model;

import java.util.ArrayList;
import java.util.List;

public class SleepPerWeek {

    private float averageSleepPerWeek;
    private List<SleepModel> sleepPerWeek;

    public SleepPerWeek() {
        this.sleepPerWeek = new ArrayList<SleepModel>();
    }

    public List<SleepModel> addSleepModel(SleepModel sleep) {
        if (!sleepPerWeek.contains(sleep)) {
            sleepPerWeek.add(sleep);
            return sleepPerWeek;
            //return true;
        } else {
            return sleepPerWeek;
            //return false;
        }
    }

    public List<SleepModel> getSleepPerWeek() {
        return sleepPerWeek;
    }

    public void removeSleepModel(SleepModel sleep) {
        sleepPerWeek.remove(sleep);

    }


//
//    public float getAverageSleepPerWeek() {
//        return averageSleepPerWeek;
//    }

}
