package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// Represents a daily sleep pattern entry
public class SleepModel implements Writable {
    private String dayOfTheWeek;       // tracks the day of the week the sleep entry was created for.
    private boolean examOrNot;         // whether there is an exam or not in the upcoming week.
    private double actualSleepPerDay;  // the number of hours slept on that day.


    /*
    REQUIRES: dayOfTheWeek has a non-zero length, actualSleepPerDay >= 0 hours;
    EFFECTS: actualSleepPerDay is set to sleep; a SleepModel object is created that includes
    the day of the week, sleep on that day, and whether is an exam(s) or not in the upcoming week (true/false).
     */
    public SleepModel(String dayOfTheWeek, double sleep, boolean examOrNot) {
        this.dayOfTheWeek = dayOfTheWeek;
        actualSleepPerDay = sleep;
        this.examOrNot = examOrNot;
    }

    // getters:

    // EFFECTS: returns the string, dayOfTheWeek
    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    // EFFECTS: returns the boolean, examOrNot
    public boolean getExamOrNot() {
        return examOrNot;
    }

    // EFFECTS: returns the double, actualSleepPerDay
    public double getActualSleepPerDay() {
        return actualSleepPerDay;
    }
    


    // CITATION: studied and referenced:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: creates a new Json object with the corresponding values in the daily sleep entry
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Day of the week", dayOfTheWeek);
        json.put("Hours slept today", actualSleepPerDay);
        json.put("Have exams coming up", examOrNot);
        return json;
    }
}