package ui;

import model.SleepModel;

import javax.swing.*;
import java.awt.*;

public class WeeklyNamesCell extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        SleepModel sleepModel = (SleepModel) value;
        String dayOfTheWeek = sleepModel.getDayOfTheWeek();

        String dayOfTheWeekText = "<html>Day of the week: " + dayOfTheWeek + "<br/>Hours slept: "
                + sleepModel.getActualSleepPerDay() + "<br/>Exam or not: " + sleepModel.getExamOrNot();

        setText(dayOfTheWeekText);
        return this;
    }
}
