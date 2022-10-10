package model;

public class SleepModel {
    // delete or rename this class!
    private String dayOfTheWeek;
    private boolean examOrNot;
    private double actualSleepPerDay;

    // REQUIRES: dayOfTheWeek has a non-zero length, actualSleepPerDay >= 0 hours;
    // EFFECTS: actualSleepPerDay is set to sleep; a SleepModel object is created that includes
    // the day of the week, sleep on that day, and whether there was an exam(s) or not in the upcoming week.
    public SleepModel(String dayOfTheWeek, double sleep, boolean examOrNot) {
        this.dayOfTheWeek = dayOfTheWeek;
        actualSleepPerDay = sleep;
        this.examOrNot = examOrNot;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public boolean getExamOrNot() {
        return examOrNot;
    }

    public double getActualSleepPerDay() {
        return actualSleepPerDay;
    }














}
