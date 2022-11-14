//package ui;
//
//import model.SleepModel;
//import model.SleepPerWeek;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import javax.swing.*;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//
//public class MyGui extends JFrame implements ListSelectionListener {
//    private static final String JSON_STORE = "./data/weeklySleep.json";
//    private static final int WIDTH = 800;
//    private static final int HEIGHT = 700;
//
//    private DefaultListModel<SleepModel> sleepModelDefaultListModel;
//    private DefaultListModel<String> workloadDefaultListModel;
//
//    private JList<SleepModel> sleepModelJList;
//
//    private JsonReader jsonReader;
//    private JsonWriter jsonWriter;
//    private SleepPerWeek sleepPerWeek;
//    private ImageIcon sleepBackgroundImage;
//    private JLabel workloadLabel;
//    List<String> workload;
//    //CreateSleepEntry createSleepEntry;
//
////    private JPanel panel;
////    private JButton addSleep;
////    private JLabel dayOfWeekLabel;
////    private JLabel hoursSleptLabel;
////    private JLabel examOrNotLabel;
////    private JTextField dayOfWeek;
////    private JTextField hoursSlept;
////    private JTextField exam;
//
//    public MyGui() {
//        super("Welcome to your Sleep Tracker");
//        setLayout(new BorderLayout());
//        setMinimumSize(new Dimension(WIDTH, HEIGHT));
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        initializeBackend();
//        initializeHeader(); // See what to do here
//        initializeSleepPerWeek();
//        initializeButtons();
//        pack();
//        setVisible(true);
//
//        startLoadPrompt();
//        exitSavePrompt();
//    }
//
//    // CITATION: image source: https://www.istockphoto.com/vector/man-sleeping-guy-lies-on-bed-under
//    // -duvet-at-night-comfortable-sleep-time-at-home-gm1254563127-366726001
//    // MODIFIES: this
//    // EFFECTS: initializes jsonReader and jsonWriter, sets up image, and the fields.
//    private void initializeBackend() {
//        jsonReader = new JsonReader(JSON_STORE);
//        jsonWriter = new JsonWriter(JSON_STORE);
//        sleepPerWeek = new SleepPerWeek();
//        sleepBackgroundImage = new ImageIcon("./data/sleepingBackground.jpg");
//        sleepModelDefaultListModel = new DefaultListModel<>();
//        workloadDefaultListModel = new DefaultListModel<>();
//    }
//
//    // CITATION: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/
//    // WorkRoomApp.java
//    // MODIFIES: this
//    // EFFECTS: initializes load option on menu
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
//    // CITATION: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/
//    // main/ui/WorkRoomApp.java
//    // EFFECTS: Initializes the save prompt and then exists
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
//                    dispose();
//                }
//            }
//        });
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes the menu plane for hours slept with and without exams
//    private void initializeHeader() {
//        JPanel headerPanel = new JPanel();
//        headerPanel.setLayout(new FlowLayout());
//
//        JPanel datePane = initializeDatePanel();
//        JPanel workloadPane = initializeWorkloadPanel();
//        headerPanel.add(datePane);
//        headerPanel.add(Box.createHorizontalStrut(50));
//        headerPanel.add(workloadPane);
//
//        add(headerPanel, BorderLayout.PAGE_START);
//    }
//
//    private JPanel initializeWorkloadPanel() {
//        workloadLabel = new JLabel("Your workload on that day");
//        JPanel workloadPanel = new JPanel();
//        workloadPanel.add(workloadLabel);
//        workloadPanel.setPreferredSize(new Dimension(200, 30));
//        workloadPanel.setBorder(BorderFactory.createLineBorder(Color.black));
//        return workloadPanel;
//    }
//
//    // MODIFIES: this
//    // EFFECTS: Creates a month panel in the starting menu
//    private JPanel initializeDatePanel() {
//        JLabel dateToday = new JLabel("Entries this month: " + sleepPerWeek.getMonth());
//        JPanel datePanel = new JPanel();
//        datePanel.add(dateToday);
//        datePanel.setPreferredSize(new Dimension(300, 30));
//        datePanel.setBorder(BorderFactory.createLineBorder(Color.black));
//        return datePanel;
//    }
//
////    // MODIFIES: this
////    // EFFECTS: initializes the detailed entries panel
////    private JPanel initializeEntries() {
////        detailedEntries = new JLabel("Your sleep entry description");
////        JPanel entryPanel = new JPanel();
////        entryPanel.add(detailedEntries);
////        entryPanel.setPreferredSize(new Dimension(200, 30));
////        entryPanel.setBorder(BorderFactory.createLineBorder(Color.black));
////        return entryPanel;
////    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes a split plane that displays the entries for the week
//    private void initializeSleepPerWeek() {
//        JSplitPane weeklyLogPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
//        weeklyLogPane.setOneTouchExpandable(true);
//        weeklyLogPane.setDividerLocation(400);
//        JScrollPane weeklyNamesPane = weeklyNamesPane();
//        JScrollPane weeklyWorkloadPane = weeklyWorkloadPane();
//
//        weeklyLogPane.add(weeklyNamesPane);
//        weeklyLogPane.add(weeklyWorkloadPane);
//        weeklyLogPane.setMinimumSize(new Dimension(700, 300));
//        weeklyLogPane.setPreferredSize(new Dimension(500, 300)); // WHY DO WE NEED TO SET DIMENSION 2 TIMES
//        weeklyLogPane.setBorder(BorderFactory.createLineBorder(Color.black));
//
//        add(weeklyLogPane, BorderLayout.CENTER);
//
//    }
//
////    // MODIFIES: this
////    // EFFECTS: initializes a split plane that displays the entries for the week
////    private void initializeSleepPerWeek() {
////        JPanel weeklyLogPane = new JPanel();
////        weeklyLogPane.setComponentPopupMenu(true);
////        weeklyLogPane.setOneTouchExpandable(true);
////        weeklyLogPane.setDividerLocation(400);
////        JScrollPane weeklyNamesPane = weeklyNamesPane();
////        //JScrollPane weeklyEntriesPane = weeklyEntriesPane();
////
////        weeklyLogPane.add(weeklyNamesPane);
////        //weeklyLogPane.add(weeklyEntriesPane);
////        weeklyLogPane.setMinimumSize(new Dimension(700, 300));
////        weeklyLogPane.setPreferredSize(new Dimension(500, 300)); // WHY DO WE NEED TO SET DIMENSION 2 TIMES
////        weeklyLogPane.setBorder(BorderFactory.createLineBorder(Color.black));
////
////        add(weeklyLogPane, BorderLayout.CENTER);
////
////    }
//
//    private JScrollPane weeklyNamesPane() {
//        sleepModelJList = new JList<>(sleepModelDefaultListModel);
//        sleepModelJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        sleepModelJList.setSelectedIndex(0);
//        sleepModelJList.addListSelectionListener(this);
//        sleepModelJList.setVisibleRowCount(10);
//        sleepModelJList.setCellRenderer(new WeeklyNamesCell());
//
//        JScrollPane sleepScrollPane = new JScrollPane(sleepModelJList);
//        sleepScrollPane.createVerticalScrollBar();
//        sleepScrollPane.setHorizontalScrollBar(null); // SEE IF WE NEED TO REMOVE THIS !!!
//        return sleepScrollPane;
//    }
//
//    private JScrollPane weeklyWorkloadPane() {
//        JList<String> sleepEntryWindow = new JList<>(workloadDefaultListModel);
//        sleepEntryWindow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        sleepEntryWindow.setSelectedIndex(0);
//        sleepEntryWindow.setVisibleRowCount(10);
//
//        JScrollPane workloadScrollPane = new JScrollPane(sleepEntryWindow);
//        workloadScrollPane.createVerticalScrollBar();
//        workloadScrollPane.setHorizontalScrollBar(null); // SEE IF WE NEED TO REMOVE THIS !!!
//        return workloadScrollPane;
//        // COME BACK HERE see if you need to change into list
//    }
//
//    private void initializeButtons() {
//        JPanel buttonPane = new JPanel();
//        buttonPane.setLayout(new FlowLayout());
//
//        JButton newSleepLog = new JButton("New sleep entry");
//        newSleepLog.setActionCommand("New");
//        newSleepLog.addActionListener(new ButtonListener());
//
////        JButton addSleepLog = new JButton("Add sleep entry");
////        addSleepLog.setActionCommand("Add");
////        addSleepLog.addActionListener(new ButtonListener());
//
//        JButton deleteSleepLog = new JButton("Delete sleep entry");
//        deleteSleepLog.setActionCommand("Delete");
//        deleteSleepLog.addActionListener(new ButtonListener());
//
//        buttonPane.add(newSleepLog);
//        //buttonPane.add(addSleepLog);
//        buttonPane.add(deleteSleepLog);
//
//        // EDIT BELOW LINE change page end to something else.
//        add(buttonPane, BorderLayout.PAGE_END);
//    }
//
//    // We need to use clear because JList does not have a set option so we need to clear it and then
//    // read from jsonReader and add the elements onto the default list
//    // SEE: https://stackoverflow.com/questions/13597903/how-to-clear-a-jlist-in-java
//    // https://stackoverflow.com/questions/5200096/defaultlistmodel-clear-errors
//    // https://www.daniweb.com/programming/software-development/threads/322578/jlist-update-problem
//
//    private void addToWeeklySleepLog() {
//        sleepModelDefaultListModel.clear();
//        List<SleepModel> sleepModelList = sleepPerWeek.getSleepPerWeek();
//        for (SleepModel sleepModel : sleepModelList) {
//            sleepModelDefaultListModel.addElement(sleepModel);
//        }
//        // DID NOT ADD ANY MORE UPDATES HERE.
//    }
//
//    private void updateWorkloadWindow(int index) {
//        workloadDefaultListModel.clear();
//        if (index != -1) {
//            sleepModelJList.setSelectedIndex(index);
//            SleepModel sleepModel = sleepPerWeek.getSleepPerWeek().get(index);
//            workload = sleepModel.getWorkload();
//            for (String work : workload) {
//                workloadDefaultListModel.addElement(work);
//            }
//        }
//    }
//
//    @Override
//    public void valueChanged(ListSelectionEvent e) {
//        if (e.getValueIsAdjusting()) {
//            int index = sleepModelJList.getSelectedIndex();
//            updateWorkloadWindow(index);
//        }
//    }




//    @Override
//    public void valueChanged(ListSelectionEvent e) {
//
//    }

//    @Override
//    public void valueChanged(ListSelectionEvent e) {
//        if (e.getValueIsAdjusting()) {
//            int index = sleepModelJList.getSelectedIndex();
//            updateSleepEntriesWindow(index);
//        }
//
//    }

//    private void updateSleepEntriesWindow(int index) {
//        entriesDefaultListModel.clear();
//        if (index != -1) {
//            sleepModelJList.setSelectedIndex(index);
//            SleepModel sleepModel = sleepPerWeek.getSleepPerWeek().get(index);
//            Double hours = sleepModel.getActualSleepPerDay();
//            String
//        }
//
//    }
//
//    // Create a separate class for this below...
//    class ButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            switch (e.getActionCommand()) {
//                case "New":
//                    newSleepEntryAction();
//                    break;
//                case "Delete":
//                    deleteSleepEntry();
//                    break;
//
//            }
//        }

        // Older version
//        // MODIFIES: this, sleepPerWeek
//        // EFFECTS: creates popup
//        private void newSleepEntryAction() {
//            createSleepEntry = new CreateSleepEntry();
//            JPanel panel = createSleepEntry.getPanel();
//
//            int optionPaneValue = JOptionPane.showConfirmDialog(null, panel, "A new sleep entry: ",
//                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, sleepBackgroundImage);
//            if (optionPaneValue == JOptionPane.OK_OPTION) {
//                try {
//                    String dayOfTheWeek = createSleepEntry.getDayOfTheWeek();
//                    double numberOfHours = createSleepEntry.getHoursSlept();
//                    boolean examOrNot = createSleepEntry.getExamOrNot();
//                    SleepModel sleepModel = new SleepModel(dayOfTheWeek, numberOfHours, examOrNot);
//                    sleepPerWeek.addSleepModel(sleepModel);
//                    addToWeeklySleepLog();
//                    //int index = sleepModelDefaultListModel.indexOf(sleepModel);
//
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input");
//                }
//            }
//        }

//        // MODIFIES: this, log
//        // EFFECTS: creates popup window and interface for new meal creation,
//        // adds meal to log and database if a meal was created and updates screen
//        private void newSleepEntryAction() {
//            CreateSleepEntry newSleepWindow = new CreateSleepEntry();
//            JPanel panel = newSleepWindow.getPanel();
//
//            int optionPaneValue = JOptionPane.showConfirmDialog(
//                    null, panel,
//                    "Create New Meal",
//                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, sleepBackgroundImage);
//
//            if (optionPaneValue == JOptionPane.OK_OPTION) {
//                try {
//                    String dayOfTheWeek = newSleepWindow.getDayOfTheWeek();
//                    double hours = newSleepWindow.getHoursSlept();
//                    boolean exam = newSleepWindow.getExamOrNot();
//                    SleepModel sleepModel = new SleepModel(dayOfTheWeek, hours, exam);
//                    List<String> workload = newSleepWindow.parseAndReturnWorkloadList();
//
//                    for (String work : workload) {
//                        sleepModel.addWorkload(work.trim());
//                    }
//                    sleepPerWeek.addSleepModel(sleepModel);
//                    addToWeeklySleepLog();
//                    int index = sleepModelDefaultListModel.indexOf(sleepModel);
//                    updateWorkloadWindow(index);
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input!");
//                }
//            }
//        }

//        public void createSleepModelPage() {
//
//            addSleep = new JButton("Add sleep entry");
//            addSleep.setActionCommand("Add sleep entry");
//            addSleep.addActionListener(this);
//
//            dayOfWeekLabel = new JLabel("Day of the week: ");
//            dayOfWeek = new JTextField(10);
//            hoursSleptLabel = new JLabel("Hours slept: ");
//            hoursSlept = new JTextField(10);
//            examOrNotLabel = new JLabel("Exam or not: ");
//            exam = new JTextField(10);
//
//
////            listed = false;
////            carListed = new JLabel();
////
////            listingLabelSettings();
//
//        }

        // MODIFIES: this, sleepPerWeek
        // EFFECTS: Deletes a selected meal from sleepPerWeek and shows changes on the screen
//        private void deleteSleepEntry() {
//            try {
//                int index = sleepModelJList.getSelectedIndex();
//                //sleepPerWeek.getSleepPerWeek().remove(index); // see if we can use this for simplicity.
//                sleepPerWeek.removeSleepModel(index);
//                addToWeeklySleepLog();
//                updateWorkloadWindow(-1);
//            } catch (ArrayIndexOutOfBoundsException e) {
//                // all good;
//            }
//        }
//    }
//}



//package ui;
//
//import exceptions.InvalidInputException;
//import model.SleepModel;
//import model.SleepPerWeek;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import javax.swing.*;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import javax.swing.plaf.basic.BasicButtonListener;
//import javax.swing.plaf.basic.BasicOptionPaneUI;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//
//public class MyGui extends JFrame implements ListSelectionListener {
//    private static final String JSON_STORE = "./data/weeklySleep.json";
//    private static final int WIDTH = 800;
//    private static final int HEIGHT = 700;
//
//    private DefaultListModel<SleepModel> sleepModelDefaultListModel;
//    private DefaultListModel<String> entriesDefaultListModel;
//
//    private JList<SleepModel> sleepModelJList;
//
//    private JsonReader jsonReader;
//    private JsonWriter jsonWriter;
//    private SleepPerWeek sleepPerWeek;
//    private ImageIcon sleepBackgroundImage;
//    private JLabel detailedEntries;
//    CreateSleepEntry createSleepEntry;
//
//    private JPanel panel;
//    private JButton addSleep;
//    private JLabel dayOfWeekLabel;
//    private JLabel hoursSleptLabel;
//    private JLabel examOrNotLabel;
//    private JTextField dayOfWeek;
//    private JTextField hoursSlept;
//    private JTextField exam;
//
//    public MyGui() {
//        super("Welcome to your Sleep Tracker");
//        setLayout(new BorderLayout());
//        setMinimumSize(new Dimension(WIDTH, HEIGHT));
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        initializeBackend();
//        initializeHeader(); // See what to do here
//        initializeSleepPerWeek();
//        initializeButtons();
//        pack();
//        setVisible(true);
//
//        startLoadPrompt();
//        exitSavePrompt();
//    }
//
//    // CITATION: image source: https://www.istockphoto.com/vector/man-sleeping-guy-lies-on-bed-under
//    // -duvet-at-night-comfortable-sleep-time-at-home-gm1254563127-366726001
//    // MODIFIES: this
//    // EFFECTS: initializes jsonReader and jsonWriter, sets up image, and the fields.
//    private void initializeBackend() {
//        jsonReader = new JsonReader(JSON_STORE);
//        jsonWriter = new JsonWriter(JSON_STORE);
//        sleepPerWeek = new SleepPerWeek();
//        sleepBackgroundImage = new ImageIcon("./data/sleepingBackground.jpg");
//        sleepModelDefaultListModel = new DefaultListModel<>();
//    }
//
//    // CITATION: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/
//    // WorkRoomApp.java
//    // MODIFIES: this
//    // EFFECTS: initializes load option on menu
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
//    // CITATION: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/
//    // main/ui/WorkRoomApp.java
//    // EFFECTS: Initializes the save prompt and then exists
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
//                    dispose();
//                }
//            }
//        });
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes the menu plane for hours slept with and without exams
//    private void initializeHeader() {
//        JPanel headerPanel = new JPanel();
//        headerPanel.setLayout(new FlowLayout());
//
//        JPanel datePane = initializeDatePanel();
//        headerPanel.add(datePane);
//        headerPanel.add(Box.createHorizontalStrut(50));
//
//        add(headerPanel, BorderLayout.PAGE_START);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: Creates a month panel in the starting menu
//    private JPanel initializeDatePanel() {
//        JLabel dateToday = new JLabel("Entries this month: " + sleepPerWeek.getMonth());
//        JPanel datePanel = new JPanel();
//        datePanel.add(dateToday);
//        datePanel.setPreferredSize(new Dimension(900, 1000));
//        datePanel.setBorder(BorderFactory.createLineBorder(Color.black));
//        return datePanel;
//    }
//
////    // MODIFIES: this
////    // EFFECTS: initializes the detailed entries panel
////    private JPanel initializeEntries() {
////        detailedEntries = new JLabel("Your sleep entry description");
////        JPanel entryPanel = new JPanel();
////        entryPanel.add(detailedEntries);
////        entryPanel.setPreferredSize(new Dimension(200, 30));
////        entryPanel.setBorder(BorderFactory.createLineBorder(Color.black));
////        return entryPanel;
////    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes a split plane that displays the entries for the week
//    private void initializeSleepPerWeek() {
//        JSplitPane weeklyLogPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
//        weeklyLogPane.setOneTouchExpandable(true);
//        weeklyLogPane.setDividerLocation(400);
//        JScrollPane weeklyNamesPane = weeklyNamesPane();
//        //JScrollPane weeklyEntriesPane = weeklyEntriesPane();
//
//        weeklyLogPane.add(weeklyNamesPane);
//        //weeklyLogPane.add(weeklyEntriesPane);
//        weeklyLogPane.setMinimumSize(new Dimension(700, 300));
//        weeklyLogPane.setPreferredSize(new Dimension(500, 300)); // WHY DO WE NEED TO SET DIMENSION 2 TIMES
//        weeklyLogPane.setBorder(BorderFactory.createLineBorder(Color.black));
//
//        add(weeklyLogPane, BorderLayout.CENTER);
//
//    }
//
////    // MODIFIES: this
////    // EFFECTS: initializes a split plane that displays the entries for the week
////    private void initializeSleepPerWeek() {
////        panel = new JPanel();
////        //panel.setBounds(40,80,200,200);
////        //panel.setBackground(Color.gray);
////
////        //weeklyLogPane.setComponentPopupMenu(true);
////        //weeklyLogPane.setOneTouchExpandable(true);
////        //weeklyLogPane.setDividerLocation(400);
////        JScrollPane weeklyNamesPane = weeklyNamesPane();
////        //JScrollPane weeklyEntriesPane = weeklyEntriesPane();
////
////        panel.add(weeklyNamesPane);
////        //weeklyLogPane.add(weeklyEntriesPane);
////        panel.setMinimumSize(new Dimension(900, 1000));
////        panel.setPreferredSize(new Dimension(900, 1000)); // WHY DO WE NEED TO SET DIMENSION 2 TIMES
////        panel.setBorder(BorderFactory.createLineBorder(Color.black));
////
////        add(panel, BorderLayout.CENTER);
////
////    }
//
//    private JScrollPane weeklyNamesPane() {
//        sleepModelJList = new JList<>(sleepModelDefaultListModel);
//        sleepModelJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        sleepModelJList.setSelectedIndex(0);
//        sleepModelJList.addListSelectionListener(this);
//        sleepModelJList.setVisibleRowCount(10);
//        sleepModelJList.setCellRenderer(new WeeklyNamesCell());
//
//        JScrollPane sleepScrollPane = new JScrollPane(sleepModelJList);
//        sleepScrollPane.createVerticalScrollBar();
//        sleepScrollPane.setHorizontalScrollBar(null); // SEE IF WE NEED TO REMOVE THIS !!!
//        return sleepScrollPane;
//    }
////
////    private JScrollPane weeklyEntriesPane() {
////        JList<String> sleepEntryWindow = new JList<>(entriesDefaultListModel);
////        sleepEntryWindow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
////        sleepEntryWindow.setSelectedIndex(0);
////        sleepEntryWindow.setVisibleRowCount(10);
////
////        JScrollPane sleepScrollPane = new JScrollPane(sleepEntryWindow);
////        sleepScrollPane.createVerticalScrollBar();
////        sleepScrollPane.setHorizontalScrollBar(null); // SEE IF WE NEED TO REMOVE THIS !!!
////        return sleepScrollPane;
////        // COME BACK HERE see if you need to change into list
////    }
//
//    private void initializeButtons() {
//        JPanel buttonPane = new JPanel();
//        buttonPane.setLayout(new FlowLayout());
//
//        JButton newSleepLog = new JButton("New sleep entry");
//        newSleepLog.setActionCommand("New");
//        newSleepLog.addActionListener(new ButtonListener());
//
////        JButton addSleepLog = new JButton("Add sleep entry");
////        addSleepLog.setActionCommand("Add");
////        addSleepLog.addActionListener(new ButtonListener());
//
//        JButton deleteSleepLog = new JButton("Delete sleep entry");
//        deleteSleepLog.setActionCommand("Delete");
//        deleteSleepLog.addActionListener(new ButtonListener());
//
//        buttonPane.add(newSleepLog);
//        //buttonPane.add(addSleepLog);
//        buttonPane.add(deleteSleepLog);
//
//        // EDIT BELOW LINE change page end to something else.
//        add(buttonPane, BorderLayout.PAGE_END);
//    }
//
//    @Override
//    public void valueChanged(ListSelectionEvent e) {
//
//    }
//    // We need to use clear because JList does not have a set option so we need to clear it and then
//    // read from jsonReader and add the elements onto the default list
//    // SEE: https://stackoverflow.com/questions/13597903/how-to-clear-a-jlist-in-java
//    // https://stackoverflow.com/questions/5200096/defaultlistmodel-clear-errors
//    // https://www.daniweb.com/programming/software-development/threads/322578/jlist-update-problem
//
//    private void addToWeeklySleepLog() {
//        sleepModelDefaultListModel.clear();
//        List<SleepModel> sleepModelList = sleepPerWeek.getSleepPerWeek();
//        for (SleepModel sleepModel : sleepModelList) {
//            sleepModelDefaultListModel.addElement(sleepModel);
//        }
//        // DID NOT ADD ANY MORE UPDATES HERE.
//
//    }
//
////    @Override
////    public void valueChanged(ListSelectionEvent e) {
////
////    }
//
////    @Override
////    public void valueChanged(ListSelectionEvent e) {
////        if (e.getValueIsAdjusting()) {
////            int index = sleepModelJList.getSelectedIndex();
////            updateSleepEntriesWindow(index);
////        }
////
////    }
//
////    private void updateSleepEntriesWindow(int index) {
////        entriesDefaultListModel.clear();
////        if (index != -1) {
////            sleepModelJList.setSelectedIndex(index);
////            SleepModel sleepModel = sleepPerWeek.getSleepPerWeek().get(index);
////            Double hours = sleepModel.getActualSleepPerDay();
////            String
////        }
////
////    }
//
//    // Create a separate class for this below...
//    class ButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            switch (e.getActionCommand()) {
//                case "New":
//                    newSleepEntryAction();
//                    break;
//                case "Delete":
//                    deleteSleepEntry();
//                    break;
//
//            }
//        }
//
//        // Older version
////        // MODIFIES: this, sleepPerWeek
////        // EFFECTS: creates popup
////        private void newSleepEntryAction() {
////            createSleepEntry = new CreateSleepEntry();
////            JPanel panel = createSleepEntry.getPanel();
////
////            int optionPaneValue = JOptionPane.showConfirmDialog(null, panel, "A new sleep entry: ",
////                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, sleepBackgroundImage);
////            if (optionPaneValue == JOptionPane.OK_OPTION) {
////                try {
////                    String dayOfTheWeek = createSleepEntry.getDayOfTheWeek();
////                    double numberOfHours = createSleepEntry.getHoursSlept();
////                    boolean examOrNot = createSleepEntry.getExamOrNot();
////                    SleepModel sleepModel = new SleepModel(dayOfTheWeek, numberOfHours, examOrNot);
////                    sleepPerWeek.addSleepModel(sleepModel);
////                    addToWeeklySleepLog();
////                    //int index = sleepModelDefaultListModel.indexOf(sleepModel);
////
////                } catch (NumberFormatException e) {
////                    System.out.println("Invalid input");
////                }
////            }
////        }
//
//        // MODIFIES: this, log
//        // EFFECTS: creates popup window and interface for new meal creation,
//        // adds meal to log and database if a meal was created and updates screen
//        private void newSleepEntryAction() {
//            CreateSleepEntry newSleepWindow = new CreateSleepEntry();
//            JPanel panel = newSleepWindow.getPanel();
//
//            int optionPaneValue = JOptionPane.showConfirmDialog(
//                    null, panel,
//                    "Create New Meal",
//                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, sleepBackgroundImage);
//
//            if (optionPaneValue == JOptionPane.OK_OPTION) {
//                try {
//                    String dayOfTheWeek = newSleepWindow.getDayOfTheWeek();
//                    double hours = newSleepWindow.getHoursSlept();
//                    boolean exam = newSleepWindow.getExamOrNot();
//                    SleepModel sleepModel = new SleepModel(dayOfTheWeek, hours, exam);
//
//                    sleepPerWeek.addSleepModel(sleepModel);
//                    addToWeeklySleepLog();
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input!");
//                }
//            }
//        }
//
//        public void createSleepModelPage() {
//
//            addSleep = new JButton("Add sleep entry");
//            addSleep.setActionCommand("Add sleep entry");
//            addSleep.addActionListener(this);
//
//            dayOfWeekLabel = new JLabel("Day of the week: ");
//            dayOfWeek = new JTextField(10);
//            hoursSleptLabel = new JLabel("Hours slept: ");
//            hoursSlept = new JTextField(10);
//            examOrNotLabel = new JLabel("Exam or not: ");
//            exam = new JTextField(10);
//
//
////            listed = false;
////            carListed = new JLabel();
////
////            listingLabelSettings();
//
//        }
//
//        // MODIFIES: this, sleepPerWeek
//        // EFFECTS: Deletes a selected meal from sleepPerWeek and shows changes on the screen
//        private void deleteSleepEntry() {
//            try {
//                int index = sleepModelJList.getSelectedIndex();
//                //sleepPerWeek.getSleepPerWeek().remove(index); // see if we can use this for simplicity.
//                sleepPerWeek.removeSleepModel(index);
//                addToWeeklySleepLog();
//            } catch (ArrayIndexOutOfBoundsException e) {
//                // all good;
//            }
//        }
//    }
//}
