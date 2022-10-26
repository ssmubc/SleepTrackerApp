package ui;

import java.io.FileNotFoundException;

// Runs the user interface (SleepTrackerApp)
public class Main {
    public static void main(String[] args) {
        try {
            new SleepTrackerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
