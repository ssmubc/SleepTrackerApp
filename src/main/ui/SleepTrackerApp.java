package ui;

import model.SleepModel;
import model.SleepPerWeek;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Sleep Tracker application
public class SleepTrackerApp {
    private static final String JSON_STORE = "./data/weeklySleep.json";
    private SleepModel sleep;
    private Scanner input;
    private SleepPerWeek weeklyLog;
    private List<SleepModel> myLog; // change to getLog in weekly class
    private int sleepLogNum;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the Sleep Tracker application
    public SleepTrackerApp() throws FileNotFoundException {
        //input = new Scanner(System.in);
        weeklyLog = new SleepPerWeek();
        myLog = new ArrayList<SleepModel>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTrackerApp();

    }


    // CITATION: studied and referenced:
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java
    // MODIFIES: this
    // EFFECTS: processes user input
    public void runTrackerApp() {
        boolean shouldContinue = true;
        String userCommand = null;

        initialization();

        while (shouldContinue) {
            displayMenu();
            userCommand = input.next();
            userCommand = userCommand.toLowerCase();

            if (userCommand.equals("q")) {
                shouldContinue = false;
            } else {
                processCommand(userCommand);
            }
        }
        System.out.println("\nThank you for connecting with your Sleep Pattern Tracker! See you soon!");
    }

    // MODIFIES: this
    // EFFECTS: processes the user's command
    private void processCommand(String command) {
        switch (command) {
            case "n":
                makeNewEntry();
                break;
            case "v":
                viewSleepLog();
                break;
            case "e":
                editSleepEntry();
                break;
            case "d":
                deleteSleepEntry();
                break;
            case "s":
                saveSleepEntries();
                break;
            case "l":
                loadSleepEntries();
                break;
            default:
                System.out.println("Your input is not valid. Please select an option from the menu.");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes a sleep entry
    public void initialization() {
        sleep = new SleepModel("Wednesday", 5.0, false);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // EFFECTS: displays a menu of options for the user to choose from.
    public void displayMenu() {
        System.out.println("Thank you for connected with your Sleep Pattern Tracker! How can we help you today?");
        System.out.println("\tn -> Add a new sleep entry to my weekly log");
        System.out.println("\tv -> View my weekly Sleep log");
        System.out.println("\te -> Edit a log.");
        System.out.println("\td -> Delete a sleep entry from my weekly log");
        System.out.println("\ts -> save my weekly sleep log to file");
        System.out.println("\tl -> load weekly sleep log from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: creates a sleep entry
    private void makeNewEntry() {
        System.out.print("Enter the day of the week from the options (Mon, Tues, Wed, Thurs, Friday, Sat, Sunday): ");
        String dayOfTheWeek = input.next();
        System.out.println("Enter the number of hours you slept today: ");
        Double hoursOfSleep = input.nextDouble();
        System.out.println("Are there any exams coming up this week (true/false): ");
        String examOrNot = input.next();
        if (examOrNot.equals("true") || examOrNot.equals("false")) {
            SleepModel sleep = new SleepModel(dayOfTheWeek, hoursOfSleep, Boolean.getBoolean(examOrNot));
            myLog.add(sleep);
            System.out.println("Your entry has been added to your sleep log.");
        } else {
            System.out.println("If you have an exam this week enter: true, else enter: false for this question");
            makeNewEntry();
        }

    }


    // EFFECTS: user views the weekly sleep log
    private void viewSleepLog() {
        System.out.println("Here is your sleep log so far: \n");
        for (SleepModel sleep : myLog) {
            sleepLogNum++;
            System.out.println("Your sleep details for your " + sleepLogNum + " log entry is: ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Day of the week " + sleep.getDayOfTheWeek() + ", Number of hours you slept "
                    + sleep.getActualSleepPerDay() + ", You had an exam: " + sleep.getExamOrNot());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        }
        sleepLogNum = 0;

    }

    // MODIFIES: this
    // EFFECTS: user edits an entry from the log.
    private void editSleepEntry() {
        System.out.println("Enter the day of the week that you want to edit");
        String dayOfTheWeek = input.next();
        SleepModel editSleep = null;
        for (SleepModel sleep : myLog) {
            if (sleep.getDayOfTheWeek().equals(dayOfTheWeek)) {
                editSleep = sleep;
            }
        }
        if (editSleep != null) {
            System.out.println("Enter the correct number of hours you slept: ");
            Double correctHours = input.nextDouble();
            System.out.println("Enter whether you have exams or not this week (true/false): ");
            String correctedExams = input.next();
            if (correctedExams.equals("true") || correctedExams.equals("false")) {
                Boolean changeExams = Boolean.valueOf(correctedExams);
                SleepModel correctedSleep = new SleepModel(editSleep.getDayOfTheWeek(), correctHours, changeExams);
                myLog.set(myLog.indexOf(editSleep), correctedSleep);
                viewSleepLog();
            } else {
                System.out.println("If you have an exam this week enter: true, else enter: false for this question");
                editSleepEntry();
            }

        }

    }

    // MODIFIES: this
    // EFFECTS: user deletes an entry from the log.
    private void deleteSleepEntry() {
        System.out.println("Enter the day of the week that you want to delete: ");
        String dayOfTheWeek = input.next();
        SleepModel removedSleep = null;
        for (SleepModel sleep : myLog) {
            if (sleep.getDayOfTheWeek().equals(dayOfTheWeek)) {
                removedSleep = sleep;

            }
        }
        if (removedSleep != null) {
            myLog.remove(removedSleep);
            System.out.println("Your chosen entry has been removed from the log.");
        }
        viewSleepLog();
    }

    // EFFECTS: saves the workroom to file
    private void saveSleepEntries() {
        try {
            jsonWriter.open();
            jsonWriter.write(weeklyLog);
            jsonWriter.close();
            System.out.println("Saved " + weeklyLog.getMonth() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadSleepEntries() {
        try {
            weeklyLog = jsonReader.read();
            System.out.println("Loaded " + weeklyLog.getMonth() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}