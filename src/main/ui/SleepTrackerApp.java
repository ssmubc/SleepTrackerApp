package ui;

import model.SleepModel;
import model.SleepPerWeek;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SleepTrackerApp {
    private SleepModel sleep;
    private Scanner input;
    private SleepPerWeek weeklyLog;
    private List<SleepModel> myLog;
    private int sleepLogNum;
    private String yes;
    private String no;

    public SleepTrackerApp() {
        weeklyLog = new SleepPerWeek();
        myLog = new ArrayList<SleepModel>();
        runTrackerApp();



    }

    // MODIFIES: this
    // EFFECTS: processes user input (Taken from TellerApp)
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
        System.out.println("\td -> Delete a sleep entry from my weekly log");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: creates a sleep entry
    private void makeNewEntry() {
        System.out.print("Enter the day of the week: ");
        String dayOfTheWeek = input.next();
        System.out.println("Enter the number of hours you slept today: ");
        Double hoursOfSleep = input.nextDouble();
        System.out.println("Are there any exams coming up this week (true/false): ");
        String examOrNot = input.next();
//        if (examOrNot.equals("yes")) {
//            boolean hasExam = true;
//        } else if (examOrNot.equals("no")) {
//            boolean hasExam = false;
//        }

        SleepModel sleep = new SleepModel(dayOfTheWeek, hoursOfSleep, Boolean.getBoolean(examOrNot));
        myLog.add(sleep);
        System.out.println("Your entry has been added to your sleep log.");
        //weeklyLog.addSleepModel(sleep);
    }


    // MODIFIES: this
    // EFFECTS: user views the weekly sleep log
    private void viewSleepLog() {
        System.out.println("Here is your sleep log so far: \n");
        //System.out.println(myLog);

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
    // EFFECTS: user deletes an entry from the log.
    private void deleteSleepEntry() {
        System.out.println("Enter the day of the week that you want to delete: ");
        String dayOfTheWeek = input.next();
        for (SleepModel sleep : myLog) {
            if (sleep.getDayOfTheWeek().equals(dayOfTheWeek)) {
                myLog.remove(sleep);
                System.out.println("Your chosen entry has been removed from the log.");
                viewSleepLog();
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: processes what the user has commanded (Teller)
    private void processCommand(String command) {
        if (command.equals("n")) {
            makeNewEntry();
        } else if (command.equals("v")) {
            viewSleepLog();
        } else if (command.equals("d")) {
            deleteSleepEntry();
        } else {
            System.out.println("Your input is not valid. Please select an option from the menu.");
        }
    }






}
