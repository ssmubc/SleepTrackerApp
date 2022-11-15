package ui;

import model.SleepModel;
import model.SleepPerWeek;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


// CITATIONS: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/
// ButtonDemo.java
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/RadioButtonDemoProject/src/components/
// RadioButtonDemo.java
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonHtmlDemoProject/src/components/
// ButtonHtmlDemo.java
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/ButtonDemo.java
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/LabelDemoProject/src/components/LabelDemo.java

// Creates a GUI application for user to track their sleep entries
public class CreateGUI extends JFrame implements ActionListener {

    // Json file that will save entries
    private static final String JSON_STORE = "./data/weeklySleep.json";

    private SleepPerWeek sleepPerWeek;
    private SleepModel sleep;

    private JPanel mainMenu;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;

    private JPanel sleepEntriesPanel;
    private JLabel entries;

    private String dayOfWeek;
    private double hoursSlept;
    private boolean examOrNot;

    // EFFECTS: constructs a JFrame as the welcome page with buttons and an image
    public CreateGUI() {
        super("Welcome to your sleep tracker!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        initializeMenu();
        makeSleepEntriesPanel();

        JLabel welcomeLabel = new JLabel("Welcome to your SleepTracker!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        JLabel mainScreenImage = new JLabel();
        mainMenu.add(welcomeLabel);
        addImageToLabel(mainScreenImage);

        initializeMenuButtons();

        addActionToButton();

        mainMenu.setVisible(true);


        //sleepPerWeek = new SleepPerWeek();

    }

    // MODIFIES: this
    // EFFECTS: creates the main menu and sets the background color
    public void initializeMenu() {
        mainMenu = new JPanel();
        mainMenu.setBackground(Color.white);
        add(mainMenu);
        entries = new JLabel();
        entries.setText("Please enter your sleep entries");
    }

    // MODIFIES: this
    // EFFECTS: sets the buttons on the menu with their labels
    public void initializeMenuButtons() {
        button1 = new JButton("View current entries");
        button2 = new JButton("Add your sleep entry");
        button3 = new JButton("Remove your sleep entry");
        button4 = new JButton("Save entries file");
        button5 = new JButton("Load entries file");
        button6 = new JButton("Exit application");

        addButton(button1, mainMenu);
        addButton(button2, mainMenu);
        addButton(button3, mainMenu);
        addButton(button4, mainMenu);
        addButton(button5, mainMenu);
        addButton(button6, mainMenu);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to mainMenu
    public void addButton(JButton b1, JPanel panel) {
        b1.setFont(new Font("Arial", Font.BOLD, 12));
        b1.setForeground(Color.black);
        b1.setBackground(Color.white);
        panel.add(b1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    // MODIFIES: this
    // EFFECTS: creates a button and adds it to the given panel, changing various attributes of the
    //          color and text of the button
    public void addMenuButton(JButton button1, JPanel panel) {
        button1.setFont(new Font("Arial", Font.BOLD, 12));
        button1.setForeground(Color.black);
        button1.setBackground(Color.white);
        panel.add(button1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    // CITATION: https://media.istockphoto.com/id/1254563127/vector/man-sleeping-guy-lies-on-bed-under-duvet-at-night-
    // comfortable-sleep-time-at-home-vector-flat.jpg?s=1024x1024&w=is&k=20&c=3qtPhIy_uNABI78QwkB-7Z3FSRuD6ACzmm
    // XM2RI7HHE=
    // MODIFIES: this
    // EFFECTS: Creates the image on the main menu and its it to the panel
    public void addImageToLabel(JLabel label) {
        label.setIcon(new ImageIcon("./data/sleepingBackground.jpg"));
        label.setMinimumSize(new Dimension(20,20));
        mainMenu.add(label);
    }

    // MODIFIES: this
    // EFFECTS: sets each button to their respective action
    public void addActionToButton() {

        button1.addActionListener(this);
        button1.setActionCommand("View entries");
        button2.addActionListener(this);
        button2.setActionCommand("Add your entries");
        button3.addActionListener(this);
        button3.setActionCommand("Delete your entry");
        button4.addActionListener(this);
        button4.setActionCommand("Save entries file");
        button5.addActionListener(this);
        button5.setActionCommand("Load entries file");
        button6.addActionListener(this);
        button6.setActionCommand("Exit application");

    }


    // MODIFIES: this
    // EFFECTS: calls the given methods when a certain button is clicked on
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("View entries")) {
            initializeSleepEntriesPanel();
        } else if (ae.getActionCommand().equals("Add your entries")) {
            createEntriesPage();
        } else if (ae.getActionCommand().equals("Delete your entry")) {
            removeSleepEntry();
        } else if (ae.getActionCommand().equals("Save entries file")) {
            saveSleepEntries();
        } else if (ae.getActionCommand().equals("Exit application")) {
            System.exit(0);
        } else if (ae.getActionCommand().equals("Return to main menu")) {
            returnToMainMenu();
        } else if (ae.getActionCommand().equals("Load entries file")) {
            loadSleepEntries();
        }
    }

    // CITATION: https://www.youtube.com/watch?v=arcTW_znJYY
    // CITATION: http://www.java2s.com/Tutorial/Java/0240__Swing/UsingJOptionPanewithapredefinedselections.htm
    // MODIFIES: this
    // EFFECTS: allows the user to input their sleep entry and adds it to SleepPerWeek, and prints it on
    // the sleepEntriesPanel.
    public void createEntriesPage() {
        String hours;
//        response = JOptionPane.showInputDialog("Enter day of the week:");
//        dayOfWeek = response;

        Object[] selectionValues = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String initialSelection = "Sunday";
        Object selection = JOptionPane.showInputDialog(null, "Enter day of the week:",
                "Day of the week", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        dayOfWeek = String.valueOf(selection);

//
        try {
            hours = JOptionPane.showInputDialog("Enter number of hours slept:");
            if (hours != null) {
                hoursSlept = Double.parseDouble(hours);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid input, please try again");
            createEntriesPage();
        }

        // DO I NEED THIS PARENT COMPONENT TO BE NULL?
        Object[] selectionValues2 = {"true", "false"};
        String initialSelection2 = "false";
        Object selection2 = JOptionPane.showInputDialog(null, "Enter day of the week:",
                "Exams coming up", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, initialSelection2);
        examOrNot = Boolean.parseBoolean(selection2.toString());


//
//        response = JOptionPane.showInputDialog("Enter if you have an exam soon:");
//        if (response != null) {
//            examOrNot = Boolean.parseBoolean(response);
//        }
//
//
        sleep = new SleepModel(dayOfWeek, hoursSlept, examOrNot);
        sleepPerWeek.addSleepModel(sleep);
        entries.setText("<html><pre>Current entry: \n" + sleepPerWeek.getSleepEntries() + "\n</pre></html>");
        System.out.println("Successfully added!!");
        System.out.println(sleepPerWeek.getSleepPerWeek());

        // BELOW IS WORKING SOLUTION

//        String response;
//        response = JOptionPane.showInputDialog("Enter day of the week:");
//        dayOfWeek = response;
//
//        try {
//            response = JOptionPane.showInputDialog("Enter number of hours slept:");
//            if (response != null) {
//                hoursSlept = Double.parseDouble(response);
//            }
//        } catch (NumberFormatException | IndexOutOfBoundsException e) {
//            System.out.println("Invalid input, please try again");
//            createEntriesPage();
//        }
//
//        response = JOptionPane.showInputDialog("Enter if you have an exam soon:");
//        if (response != null) {
//            examOrNot = Boolean.parseBoolean(response);
//        }
//
//
//        sleep = new SleepModel(dayOfWeek, hoursSlept, examOrNot);
//        sleepPerWeek.addSleepModel(sleep);
//        entries.setText("<html><pre>Current entry: \n" + sleepPerWeek.getSleepEntries() + "\n</pre></html>");
//        System.out.println("Successfully added!!");
//        System.out.println(sleepPerWeek.getSleepPerWeek());

    }


    // EFFECTS: makes the sleepEntriesPanel visible and mainMenu non-visible.
    public void initializeSleepEntriesPanel() {
        add(sleepEntriesPanel);
        sleepEntriesPanel.setVisible(true);
        mainMenu.setVisible(false);
    }


    // EFFECTS: makes the panel with all the sleep entries listed. Sets the return to menu button and scrollbar.
    public void makeSleepEntriesPanel() {
        sleepEntriesPanel = new JPanel(new GridLayout(0, 1));
        JScrollPane scroll = new JScrollPane(entries, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton mainMenuButton = new JButton();
        mainMenuButton.setText("Return to Main Menu");
        mainMenuButton.setActionCommand("Return to main menu");
        mainMenuButton.addActionListener(this);
        addMenuButton(mainMenuButton, sleepEntriesPanel);
        //mainMenuButton.setForeground(Color.BLACK);

        entries.setFont(new Font("ComicSans", Font.BOLD, 12));
        sleepEntriesPanel.add(scroll);

    }

    // MODIFIES: this
    // EFFECTS: removes an entry from the list of entries after the user provides the day of the entry.
    // If an entry with the day of the week is made then it will delete that entry otherwise it will not.
    public void removeSleepEntry() {
        try {
            String response;
            Object[] selectionValues = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            String initialSelection = "Sunday";
            Object selection = JOptionPane.showInputDialog(null,
                    "Which day of the week do you want to remove",
                    "Remove an entry", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
            response = String.valueOf(selection);
            SleepModel sleep2 = new SleepModel("", 0, false);
            for (SleepModel sleep : sleepPerWeek.getSleepPerWeek()) {
                if (sleep.getDayOfTheWeek().equals(response)) {
                    sleep2 = sleep;
                }
            }
            if (!sleep2.getDayOfTheWeek().equals("")) {
                sleepPerWeek.removeSleepModel(sleep2);
                entries.setText("<html><pre>Current entries: \n" + sleepPerWeek.getSleepEntries() + "\n</pre></html>");
                System.out.println("The sleep entries is no longer existing");
            } else {
                System.out.println("There is no entry for " + response);
            }
        } catch (IndexOutOfBoundsException e) {
            entries.setText("Please provide a valid input");
        }
    }

    // Previous remove function working but no scroll options
//            try {
//        String response;
//        response = JOptionPane.showInputDialog("Which day of the week do you want to remove");
//        SleepModel sleep2 = new SleepModel("", 0, false);
//        for (SleepModel sleep : sleepPerWeek.getSleepPerWeek()) {
//            if (sleep.getDayOfTheWeek().equals(response)) {
//                sleep2 = sleep;
//            }
//        }
//        if (!sleep2.getDayOfTheWeek().equals("")) {
//            sleepPerWeek.removeSleepModel(sleep2);
//            entries.setText("<html><pre>Current entries: \n" + sleepPerWeek.getSleepEntries() + "\n</pre></html>");
//            System.out.println("The sleep entries is no longer existing");
//        } else {
//            System.out.println("There is no entry for " + response);
//        }
//    } catch (IndexOutOfBoundsException e) {
//        entries.setText("Please provide a valid input");
//    }





    // CITATION: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/
    // CITATION: https://www.freecodecamp.org/news/pre-tag-in-html-example-code/
    // MODIFIES: this
    // EFFECTS: loads the entries from the json file.
    private void loadSleepEntries() {
        try {
            JsonReader reader = new JsonReader(JSON_STORE);
            sleepPerWeek = reader.read();
            entries.setText("<html><pre>Current Entries: \n" + sleepPerWeek.getSleepEntries() + "\n</pre></html>");
            System.out.println("Entries loaded from file " + "./data/weeklySleep.json");
        } catch (IOException e) {
            entries.setText("No Entries added yet");
        } catch (IndexOutOfBoundsException e) {
            entries.setText("Please initialize entries file before proceeding");
        }
    }

    // CITATION: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/
    // EFFECTS: saves the entries to the json file
    private void saveSleepEntries() {
        try {
            JsonWriter writer = new JsonWriter(JSON_STORE);
            writer.open();
            writer.write(sleepPerWeek);
            writer.close();
            System.out.println("Sleep entries saved to file " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Could not save to file.");
        }
    }


    // EFFECTS: Makes the main menu visible and the other panel, non-visible
    public void returnToMainMenu() {
        mainMenu.setVisible(true);
        sleepEntriesPanel.setVisible(false);
    }

}
