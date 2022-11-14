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

public class WithPopUp extends JFrame implements ActionListener {

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

    private JPanel entriesPage;
    private JButton addSleep;
    private String dayOfWeek;
    private double hoursSlept;
    private boolean examOrNot;


    // Makes a new JFrame with different attributes
    public WithPopUp() {
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

    }

    // EFFECTS: Makes the main menu panel and changes the background color
    public void initializeMenu() {
        mainMenu = new JPanel();
        mainMenu.setBackground(Color.white);
        add(mainMenu);
        entries = new JLabel();
        entries.setText("Please add your sleep entries");
    }

    // EFFECTS: Initializes main menu buttons and gives them labels
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
    public void addButton(JButton button1, JPanel panel) {
        button1.setFont(new Font("Arial", Font.BOLD, 12));
        button1.setForeground(Color.black);
        button1.setBackground(Color.white);
        panel.add(button1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    // EFFECTS: Creates a button and adds it to the given panel, changing various attributes of the
    //          color and text of the button
    public void addMenuButton(JButton button1, JPanel panel) {
        button1.setFont(new Font("Arial", Font.BOLD, 12));
        button1.setBackground(Color.white);
        //button1.setPreferredSize(new Dimension(500, 3));
        button1.setForeground(Color.black);
        panel.add(button1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    // EFFECTS: Creates the image on the main menu and its it to the panel
    public void addImageToLabel(JLabel label) {
        label.setIcon(new ImageIcon("./data/sleepingBackground.jpg"));
        label.setMinimumSize(new Dimension(20,20));
        mainMenu.add(label);
    }

    // MODIFIES: this
    // EFFECTS: Sets each button to their respective action
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


    // EFFECTS: calls the given methods when a certain button is clicked on
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("View entries")) {
            initializeSleepEntriesPanel();
        } else if (ae.getActionCommand().equals("Add your entries")) {
            createEntriesPage();
        } else if (ae.getActionCommand().equals("Delete your entry")) {
            removeSleepEntry(sleep);
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
//    else if (ae.getActionCommand().equals("Add Sleep to entries")) {
//        createEntriesPage();
//    }

    public void createEntriesPage() {

//        addSleep = new JButton("Add Sleep to entries");
//        addSleep.setActionCommand("Add Sleep to entries");
//        addSleep.addActionListener(this);

        //SleepPerWeek sleepPerWeek1 = new SleepPerWeek();
        String response;
        response = JOptionPane.showInputDialog("Enter day of the week:");
        dayOfWeek = response;

        try {
            response = JOptionPane.showInputDialog("Enter number of hours slept:");
            hoursSlept = Double.parseDouble(response);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid input, please try again");
            createEntriesPage();
        }

        response = JOptionPane.showInputDialog("Enter if you have an exam soon:");
        examOrNot = Boolean.parseBoolean(response);

        sleep = new SleepModel(dayOfWeek, hoursSlept, examOrNot);
        sleepPerWeek.addSleepModel(sleep);
        //sleepPerWeek = sleepPerWeek1;
        entries.setText("<html><pre>Current entry: \n" + sleepPerWeek.getSleepEntries() + "\n</pre></html>");
        System.out.println("Successfully added!!");


    }

//    public void makeYourSleepPanel() {
//        entriesPage = new JPanel(new GridLayout(0, 2));
//        JButton mainMenuButton = new JButton("Return to Main Menu");
//
//        mainMenuButton.setBackground(Color.WHITE);
//        mainMenuButton.setFont(new Font("Arial", Font.BOLD, 10));
//        mainMenuButton.setForeground(Color.black);
//
//        mainMenuButton.setActionCommand("Return to main menu");
//        mainMenuButton.addActionListener(this);
//        addMenuButton(mainMenuButton, entriesPage);
//        //addButton(mainMenuButton, entriesPage);
//        createEntriesPage();
//    }

    public void initializeSleepEntriesPanel() {
        add(sleepEntriesPanel);
        sleepEntriesPanel.setVisible(true);
        mainMenu.setVisible(false);
        //sleepEntriesPanel.setVisible(false);
    }

//   public void initializeSleepModelPanel() {
//        add(sleepEntriesPanel);
//        sleepEntriesPanel.setVisible(true);
//        mainMenu.setVisible(false);
//        entriesPage.setVisible(false);
//    }

    public void makeSleepEntriesPanel() {
        sleepEntriesPanel = new JPanel(new GridLayout(0, 1));
        JScrollPane scroll = new JScrollPane(entries, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton mainMenuButton = new JButton();
        mainMenuButton.setText("Return to Main Menu");
        mainMenuButton.setActionCommand("Return to main menu");
        mainMenuButton.addActionListener(this);
        addMenuButton(mainMenuButton, sleepEntriesPanel);
        mainMenuButton.setForeground(Color.BLACK);

        entries.setFont(new Font("ComicSans", Font.BOLD, 12));
        sleepEntriesPanel.add(scroll);

    }

    public void removeSleepEntry(SleepModel sleep) {

        try {
            sleepPerWeek.removeSleepModel(sleep);
            entries.setText("<html><pre>Current entries: \n" + sleepPerWeek.getSleepEntries() + "\n</pre></html>");
            System.out.println("The sleep entries is no longer existing");
        } catch (NullPointerException e) {
            System.out.println("Please add a sleep before attempting to remove it");
        } catch (IndexOutOfBoundsException e) {
            entries.setText("Please initialize entries file before proceeding");
        }
    }


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

    private void saveSleepEntries() {
        try {
            //FileOutputStream
            JsonWriter writer = new JsonWriter(JSON_STORE);
            writer.write(sleepPerWeek);
            writer.close();
            System.out.println("Sleep entries saved to file " + JSON_STORE);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        }
    }

//    private void saveSleepEntries() {
//        try {
//            JsonWriter writer = new JsonWriter(JSON_STORE);
//            writer.write(sleepPerWeek);
//            writer.close();
//            System.out.println("Sleep entries saved to file " + JSON_STORE);
//        } catch (NullPointerException e) {
//            System.out.println("You need to load the file first");
//        }
//    }

    public void returnToMainMenu() {
        mainMenu.setVisible(true);
        sleepEntriesPanel.setVisible(false);
        //entriesPage.setVisible(false);
    }

}