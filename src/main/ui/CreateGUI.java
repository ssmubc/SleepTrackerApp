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


// CITATIONS (read and studied): https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject
// /src/components/ButtonDemo.java
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

    private double hoursSlept;
    String hours;

    private final Object[] selectionValues = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
            "Saturday"};
    private final String initialSelection = "Sunday";



    // EFFECTS: Creates a JFrame as the welcome page with buttons and an image
    public CreateGUI() {
        super("Welcome to your sleep tracker!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 500));
        createMenu();
        makeSleepEntriesPanel();

        JLabel welcomeLabel = new JLabel("Weekly SleepTracker for Students");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        JLabel mainScreenImage = new JLabel();

        mainMenu.add(welcomeLabel);

        addImageToMenu(mainScreenImage);

        createMenuButtons();

        setActionToButton();

        mainMenu.setVisible(true);

    }


    // EFFECTS: Creates the main menu
    public void createMenu() {
        mainMenu = new JPanel();
        mainMenu.setBackground(Color.white);
        add(mainMenu);
        entries = new JLabel();
        entries.setText("Please enter your sleep entries");
    }


    // EFFECTS: Create the buttons with their labels on the menu
    public void createMenuButtons() {
        button1 = new JButton("Load entries file");
        button2 = new JButton("Add your sleep entry");
        button3 = new JButton("Save entries file");
        button4 = new JButton("View current entries");
        button5 = new JButton("Remove your sleep entry");
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


    // CITATION: https://media.istockphoto.com/id/1254563127/vector/man-sleeping-guy-lies-on-bed-under-duvet-at-night-
    // comfortable-sleep-time-at-home-vector-flat.jpg?s=1024x1024&w=is&k=20&c=3qtPhIy_uNABI78QwkB-7Z3FSRuD6ACzmm
    // XM2RI7HHE=
    // MODIFIES: this
    // EFFECTS: Adds an image to the main menu
    public void addImageToMenu(JLabel label) {
        label.setIcon(new ImageIcon("./data/sleepingBackground.jpg"));
        label.setMinimumSize(new Dimension(23,23));
        mainMenu.add(label);
    }

    // MODIFIES: this
    // EFFECTS: Sets an action for each button
    public void setActionToButton() {
        button1.addActionListener(this);
        button1.setActionCommand("Load entries file");
        button2.addActionListener(this);
        button2.setActionCommand("Add your entries");
        button3.addActionListener(this);
        button3.setActionCommand("Save entries file");
        button4.addActionListener(this);
        button4.setActionCommand("View entries");
        button5.addActionListener(this);
        button5.setActionCommand("Delete your entry");
        button6.addActionListener(this);
        button6.setActionCommand("Exit application");

    }


    // MODIFIES: this
    // EFFECTS: calls the given methods when a certain button is clicked on
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Load entries file")) {
            loadSleepEntries();
        } else if (actionEvent.getActionCommand().equals("Add your entries")) {
            createEntriesPage();
        } else if (actionEvent.getActionCommand().equals("Save entries file")) {
            saveSleepEntries();
        } else if (actionEvent.getActionCommand().equals("View entries")) {
            initializeSleepEntriesPanel();
        } else if (actionEvent.getActionCommand().equals("Delete your entry")) {
            removeSleepEntry();
        } else if (actionEvent.getActionCommand().equals("Return to main menu")) {
            returnToMainMenu();
        } else if (actionEvent.getActionCommand().equals("Exit application")) {
            System.exit(0);
        }
    }

    // CITATION: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/
    // CITATION: https://www.freecodecamp.org/news/pre-tag-in-html-example-code/
    // MODIFIES: this
    // EFFECTS: loads the entries from the json file.
    private void loadSleepEntries() {
        try {
            JsonReader reader = new JsonReader(JSON_STORE);
            sleepPerWeek = reader.read();
            setEntriesOnPanel();
            System.out.println("Entries loaded from file " + "./data/weeklySleep.json");
        } catch (IOException e) {
            System.out.println("Couldn't load from file");
        }
    }

    // EFFECTS: Updates the entries printed on the sleepEntriesPanel.
    public void setEntriesOnPanel() {
        entries.setText("<html><pre><font size=\"4\" face=\"arial\" color=\"black\">Your Sleep Entries: \n" + "\n"
                + sleepPerWeek.getSleepEntries() + "\n</font></pre></html>");
    }


    // CITATION: https://www.youtube.com/watch?v=arcTW_znJYY
    // CITATION: http://www.java2s.com/Tutorial/Java/0240__Swing/UsingJOptionPanewithapredefinedselections.htm
    // CITATION: https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-
    // Opinions/Javas-JOptionPane-showOptionDialog-by-Example
    // MODIFIES: this
    // EFFECTS: allows the user to input their sleep entry and adds it to their list of entries, and prints it on
    // the sleepEntriesPanel.
    public void createEntriesPage() {
        Object selection = JOptionPane.showInputDialog(null, "Enter day of the week:",
                "Day of the week", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);

        hours = JOptionPane.showInputDialog("Enter number of hours slept:");

        Object[] selectionValues2 = {"true", "false"};
        String initialSelection2 = "false";
        Object selection2 = JOptionPane.showInputDialog(null, "Enter if you have exams soon:",
                "Exams coming up", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, initialSelection2);

        if (selection != null && hours != null && selection2 != null) {
            String dayOfWeek = String.valueOf(selection);
            try {
                hoursSlept = Double.parseDouble(hours);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please click the add button again to retry adding your entry");
                returnToMainMenu();
                return;
            }
            sleep = new SleepModel(dayOfWeek, hoursSlept, Boolean.parseBoolean(selection2.toString()));
            sleepPerWeek.addSleepModel(sleep);
            setEntriesOnPanel();
            System.out.println("Successfully added!!");
        } else {
            returnToMainMenu();
        }
    }


    // CITATION: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/
    // EFFECTS: Saves the entries to the json file
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


    // EFFECTS: Makes the panel with all the sleep entries listed. Sets the return to menu button and scrollbar.
    public void makeSleepEntriesPanel() {
        sleepEntriesPanel = new JPanel(new GridLayout(0, 1));
        JScrollPane scroll = new JScrollPane(entries, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton mainMenuButton = new JButton();
        mainMenuButton.setText("Return to Main Menu");
        mainMenuButton.setFont(new Font("Arial", Font.BOLD, 50));
        mainMenuButton.setActionCommand("Return to main menu");
        mainMenuButton.addActionListener(this);
        addButton(mainMenuButton, sleepEntriesPanel);

        sleepEntriesPanel.add(scroll);
    }


    // EFFECTS: Makes the sleepEntriesPanel visible and mainMenu non-visible.
    public void initializeSleepEntriesPanel() {
        add(sleepEntriesPanel);
        sleepEntriesPanel.setVisible(true);
        mainMenu.setVisible(false);
    }


    // EFFECTS: Makes the main menu visible and the other panel, non-visible
    public void returnToMainMenu() {
        mainMenu.setVisible(true);
        sleepEntriesPanel.setVisible(false);
    }



    // CITATIONS: https://stackoverflow.com/questions/22319669/how-to-create-array-inside-for-loop-in-java
    // https://www.geeksforgeeks.org/how-to-add-an-element-to-an-array-in-java/
    // https://www.w3schools.com/HTML/tryit.asp?filename=tryhtml_font
    // MODIFIES: this
    // EFFECTS: Removes an entry from the list of entries after the user provides the entry number.
    // If the user presses the cancel button, they will be redirected to the main menu.
    public void removeSleepEntry() {
        int response = 0;
        int n = sleepPerWeek.getSleepPerWeek().size();
        Object[] selectionValues = new Object[n];
        for (int i = 1; i <= n; i++) {
            selectionValues[i - 1] = i;
        }

        int initialSelection = 0;
        Object selection = JOptionPane.showInputDialog(null,
                "Which entry number listed in the view panel do you want to remove? ",
                "Remove an entry", JOptionPane.QUESTION_MESSAGE, null, selectionValues,
                initialSelection);

        if (selection != null) {
            response = Integer.parseInt(selection.toString());
            sleepPerWeek.removeSleepModel(response - 1);
            setEntriesOnPanel();
            System.out.println("The sleep entries is no longer existing");
        } else {
            returnToMainMenu();
            System.out.println("You have not removed an entry");
        }
    }

}