package model;

public class SleepModel {
    // delete or rename this class!
    private double targetSleepPerDay;
    private String dayOfTheWeek;
    private boolean examOrNot;
    private double actualSleepPerDay;
    private double sleepDifference;
    private double averageSleepPerWeek;

    // REQUIRES: dayOfTheWeek has a non-zero length, actualSleepPerDay >= 0 hours;
    // EFFECTS: actualSleepPerDay is set to sleep; a SleepModel object is created that includes
    // the day of the week, sleep on that day, and whether there was an exam(s) or not in the upcoming week.
    public SleepModel(String dayOfTheWeek, double sleep, boolean examOrNot) {
        this.dayOfTheWeek = dayOfTheWeek;
        actualSleepPerDay = sleep;
        this.examOrNot = examOrNot;
        //this.targetSleepPerDay = getTargetSleepPerDay();
    }

    public double getTargetSleepPerDay() {
        return targetSleepPerDay;
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

    public double calculateSleepDifference() {
        return actualSleepPerDay - targetSleepPerDay;
    }













}
