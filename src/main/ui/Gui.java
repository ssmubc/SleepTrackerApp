//package ui;/*
// * SimpleTableSelectionDemo.java requires no other files.
// */
//
//import model.SleepModel;
//import model.SleepPerWeek;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import javax.swing.JFrame;
//import javax.swing.WindowConstants;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.Writer;
//import java.util.List;
//
//import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
//
//
//
///**
// * SimpleTableSelectionDemo is just like SimpleTableDemo,
// * except that it detects selections, printing information
// * about the current selection to standard output.
// */
//public class Gui extends JPanel implements ActionListener {
//    private boolean DEBUG = false;
//    private boolean ALLOW_COLUMN_SELECTION = true;
//    private boolean ALLOW_ROW_SELECTION = true;
//    private static final String JSON_STORE = "./data/weeklySleep.json";
//
//    private JsonReader jsonReader;
//    private JsonWriter jsonWriter;
//    private SleepPerWeek sleepPerWeek;
//    private ImageIcon sleepBackgroundImage;
//    private DefaultListModel<SleepModel> sleepModelDefaultListModel;
//    private JButton addSleep;
//    private JTextField t1;
//    private JTextField t2;
//    private JTextField t3;
//    private JLabel dayOfWeek;
//    private JLabel hoursSlept;
//    private JLabel examOrNot;
//
//    private JPanel entriesPage;
//
//
//    public Gui() {
//        super(new GridLayout(1,3));
//        JFrame frame = new JFrame("SleepTracker");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Create and set up the content pane.
//        setOpaque(true); //content panes must be opaque
//        frame.setContentPane(this);
//
//        //Display the window.
//        //initializeButtons();
//        initializations();
//        initializeButtons();
//        frame.pack();
//        frame.setVisible(true);
//
//        startLoadPrompt();
//        exitSavePrompt();
//
//    }
//
//    public void initializations() {
//
//        String[] columnNames = {"Day of the week",
//                "Hours slept",
//                "Have exams soon"};
//
//        Object[][] data = {
//                {"Monday", 5,
//                        false},
//                {"Tues", 6.7,
//                        true},
//                {"Wed", 8.9,
//                        false},
//                {"Thurs", 10,
//                        false},
//                {"Friday", 3,
//                        true}
//        };
//
//        jsonReader = new JsonReader(JSON_STORE);
//        jsonWriter = new JsonWriter(JSON_STORE);
//        sleepPerWeek = new SleepPerWeek();
//        sleepBackgroundImage = new ImageIcon("./data/sleepingBackground.jpg");
//
//        final JTable table = new JTable(data, columnNames);
//        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
//        table.setFillsViewportHeight(true);
//
//        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//
//        //Create the scroll pane and add the table to it.
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        //Add the scroll pane to this panel.
//        add(scrollPane);
//    }
//
//
//    private void initializeButtons() {
//        JPanel buttonPane = new JPanel();
//        buttonPane.setLayout(new FlowLayout());
//
//        JButton newSleepLog = new JButton("New sleep entry");
//        newSleepLog.setActionCommand("New");
//        newSleepLog.addActionListener(new Gui.ButtonListener());
//
//        JButton deleteSleepLog = new JButton("Delete sleep entry");
//        deleteSleepLog.setActionCommand("Delete");
//        deleteSleepLog.addActionListener(new Gui.ButtonListener());
//
//        JButton saveSleepLog = new JButton("Save sleep entry");
//        saveSleepLog.setActionCommand("Save");
//        saveSleepLog.addActionListener(new Gui.ButtonListener());
//
//        buttonPane.add(newSleepLog);
//        buttonPane.add(deleteSleepLog);
//        buttonPane.add(saveSleepLog);
//
//        // EDIT BELOW LINE change page end to something else.
//        add(buttonPane, BorderLayout.PAGE_END);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//
//
//    public class ButtonListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            switch (e.getActionCommand()) {
//                case "New":
//                    newSleepEntryAction();
//                    break;
//                case "Delete":
//                    //deleteSleepEntry();
//                    break;
//                case "Save":
//                    saveCarListings();
//            }
//        }
//
//    }
//
//    public void createListingsPage() {
//
//        addSleep = new JButton("Add Sleep Entry");
//        addSleep.setActionCommand("New");
//        addSleep.addActionListener(this);
//
//        dayOfWeek = new JLabel("Enter day of the week: ");
//        t1 = new JTextField(10);
//        hoursSlept = new JLabel("Enter the number of hours slept: ");
//        t2 = new JTextField(10);
//        examOrNot = new JLabel("Enter if you had an exam or not: ");
//        t3 = new JTextField(10);
//
//        listingLabelSettings();
//
//    }
//    // EFFECTS: Changes certain attributes of the labels and text fields
//    private void listingLabelSettings() {
//
//        addSleep.setBackground(Color.BLACK);
//        addSleep.setForeground(Color.WHITE);
//        addSleep.setFont(new Font("Arial", Font.BOLD, 12));
//
//        dayOfWeek.setFont(new Font("ComicSans", Font.BOLD, 24));
//        hoursSlept.setFont(new Font("ComicSans", Font.BOLD, 24));
//        examOrNot.setFont(new Font("ComicSans", Font.BOLD, 24));
//
//        t1.setMaximumSize(new Dimension(1200, 400));
//        t2.setMaximumSize(new Dimension(1200, 400));
//        t3.setMaximumSize(new Dimension(1200, 400));
//
//    }
//
//    public void addMenuButton(JButton button1, JPanel panel) {
//        button1.setFont(new Font("Arial", Font.BOLD, 12));
//        button1.setBackground(Color.BLACK);
//        button1.setForeground(Color.white);
//        panel.add(button1);
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//        setResizable(false);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: Creates the panel that displays the option for the user to input their car
//    public void makeListYourCarPanel() {
//        entriesPage  = new JPanel(new GridLayout(0, 2));
//        JButton mainMenuButton = new JButton("Return to Main Menu");
//        mainMenuButton.setActionCommand("Return to main menu");
//        mainMenuButton.addActionListener(this);
//        addMenuButton(mainMenuButton, entriesPage);
//
//        createListingsPage();
//        addLabelsToListings();
//    }
//
//    // EFFECTS: Adds the user input labels onto the panel
//    public void addLabelsToListings() {
//        entriesPage.add(addSleep);
//        entriesPage.add(dayOfWeek);
//        entriesPage.add(t1);
//        entriesPage.add(hoursSlept);
//        entriesPage.add(t2);
//        entriesPage.add(examOrNot);
//        entriesPage.add(t3);
//    }
//
//    private void startLoadPrompt() {
//        int loadOption = JOptionPane.showConfirmDialog(null,
//                "Do you want to load your last entry?", "Load Entry", JOptionPane.YES_NO_OPTION);
//        if (loadOption == JOptionPane.YES_OPTION) {
//            try {
//                sleepPerWeek = jsonReader.read();
//                addToWeeklySleepLog();
//            } catch (IOException e) { // DID NOT INCLUDE InvalidInputException HERE!!
//                System.out.println("Unable to read from file " + JSON_STORE);
//            }
//        }
//    }
//
//
//    private void addToWeeklySleepLog() {
//        sleepModelDefaultListModel.clear();
//        List<SleepModel> sleepModelList = sleepPerWeek.getSleepPerWeek();
//        for (SleepModel sleepModel : sleepModelList) {
//            sleepModelDefaultListModel.addElement(sleepModel);
//        }
//    }
//
////    private void saveCarListings() {
////        addWindowListener(new WindowAdapter() {
////            @Override
////            public void windowClosing(WindowEvent windowEvent) {
////                int save = JOptionPane.showConfirmDialog(null,
////                        "Do you want to save your last entry?", "Save File", JOptionPane.YES_NO_OPTION);
////                if (save == JOptionPane.YES_OPTION) {
////                    try {
////                        jsonWriter.open();
////                        jsonWriter.write(sleepPerWeek);
////                        jsonWriter.close();
////                    } catch (FileNotFoundException e) {
////                        System.out.println("Unable to write to file: " + JSON_STORE);
////                    }
////                }
////            }
////        });
////    }
////
////        public void windowClosing(WindowEvent windowEvent) {
////            int save = JOptionPane.showConfirmDialog(null,
////                    "Do you want to save your last entry?", "Save File", JOptionPane.YES_NO_OPTION);
////            if (save == JOptionPane.YES_OPTION) {
////                try {
////                    jsonWriter.open();
////                    jsonWriter.write(sleepPerWeek);
////                    jsonWriter.close();
////                } catch (FileNotFoundException e) {
////                    System.out.println("Unable to write to file: " + JSON_STORE);
////                }
////            }
////        }
//
//    private void exitSavePrompt() {
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent windowEvent) {
//                int save = JOptionPane.showConfirmDialog(null,
//                        "Do you want to save your last entry?", "Save File", JOptionPane.YES_NO_OPTION);
//                if (save == JOptionPane.YES_OPTION) {
//                    try {
//                        jsonWriter.open();
//                        jsonWriter.write(sleepPerWeek);
//                        jsonWriter.close();
//                    } catch (FileNotFoundException e) {
//                        System.out.println("Unable to write to file: " + JSON_STORE);
//                    }
//                }
//            }
//        });
//    }
//
//    private void saveCarListings() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(sleepPerWeek);
//            jsonWriter.close();
//            System.out.println("Sleep Model saved to file ");
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to save");
//        }
//    }
//}