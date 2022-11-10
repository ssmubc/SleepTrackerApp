package ui;

import javax.swing.*;
import java.awt.*;

public class CreateSleepEntry {
    JFrame frame;
    JPanel panel;
    JTextField dayOfWeek;
    JTextField hoursSlept;
    JTextField exam;
    JLabel dayOfWeekLabel;
    JLabel hoursSleptLabel;
    JLabel examOrNotLabel;
    JScrollPane scrollPane;

    public CreateSleepEntry() {
        userPrompts();
//        panel.add(dayOfWeekLabel);
//        panel.add(dayOfWeek);
//        panel.add(hoursSleptLabel);
//        panel.add(hoursSlept);
//        panel.add(examOrNotLabel);
//        panel.add(exam);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMinimumSize(new Dimension(250, 200));
        panel.setPreferredSize(new Dimension(250, 200)); // DO I need this try removing it later.

    }

    private void userPrompts() {
        frame = new JFrame("New sleep entry");
        panel = new JPanel();

        dayOfWeekLabel = new JLabel("Provide the day ");
        hoursSleptLabel = new JLabel("Provide the number ");
        examOrNotLabel = new JLabel("Is there an exam(s) coming up ");

        dayOfWeek = new JTextField();
        dayOfWeek.setMaximumSize(new Dimension(300, 25));
        hoursSlept = new JTextField();
        hoursSlept.setMaximumSize(new Dimension(300, 25));

        scrollPane = new JScrollPane(hoursSlept);

        panel.add(dayOfWeekLabel);
        panel.add(dayOfWeek);
        panel.add(hoursSleptLabel);
        panel.add(hoursSlept);
        //panel.add(examOrNotLabel);
        //panel.add(exam);

    }

    // getters
    // EFFECTS: returns JPanel;
    public JPanel getPanel() {
        return panel;
    }

    // EFFECTS: returns dayOfWeek entry;
    public String getDayOfTheWeek() {
        return dayOfWeek.getText();
    }

    // EFFECTS: returns hoursSlept entry;
    public double getHoursSlept() {
        return Double.parseDouble(hoursSlept.getText());
    }

    // EFFECTS: returns examOrNot entry;
    public boolean getExamOrNot() {
        return Boolean.parseBoolean(exam.getText());
    }




}

