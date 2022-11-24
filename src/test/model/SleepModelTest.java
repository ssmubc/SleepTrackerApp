package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the SleepModel and SleepPerWeek
class SleepModelTest {
    private SleepModel dailySleep;
    private SleepPerWeek weeklySleep;
    private SleepModel correctSleep;
    private SleepModel model2;
    private SleepModel model3;
    @BeforeEach
    void runBefore() {
        dailySleep = new SleepModel("Monday", 6.0, true);
        correctSleep = new SleepModel("Monday", 8.0, false);
        weeklySleep = new SleepPerWeek();
        model2 = new SleepModel("Tuesday", 8.0, false);
        model3 = new SleepModel("Friday", 2.0, true);
        weeklySleep.addSleepModel(dailySleep);
    }

    @Test
    void testEventLog() {
        weeklySleep.addSleepModel(dailySleep);
        EventLog eventlog = EventLog.getInstance();
        assertEquals("Added sleep entry to your log",
                eventlog.iterator().next().getDescription());
    }

    @Test
    void testEventLogRemove() {
        weeklySleep.removeSleepModel(0);
        EventLog eventlog = EventLog.getInstance();
        Event event =null;
        for (Event event1 : eventlog) {
            event = event1;
            System.out.println(event.getDescription());
        }

        assertEquals("Removed a sleep entry from your log", event.getDescription());
//        assertEquals("Removed a sleep entry from your log",
//                eventlog.iterator().next().getDescription());
    }

    @Test
    void testConstructor() {
        assertEquals("Monday", dailySleep.getDayOfTheWeek());
        assertEquals(6.0, dailySleep.getActualSleepPerDay());
        assertTrue(dailySleep.getExamOrNot());
        assertTrue(dailySleep.getActualSleepPerDay() > 0);
        LocalDateTime dateTime = LocalDateTime.now();
        assertEquals(weeklySleep.getMonth(), dateTime.getMonth().name());
    }

    @Test
    void testAddSleepModel() {
        weeklySleep.addSleepModel(dailySleep);
        List<SleepModel> log = weeklySleep.getSleepPerWeek();
        assertTrue(log.contains(dailySleep));
        weeklySleep.addSleepModel(dailySleep);
        assertEquals(1, log.size());
    }

    @Test
    void testEditSleepModel() {
        List<SleepModel> log = weeklySleep.getSleepPerWeek();
        weeklySleep.addSleepModel(dailySleep);
        weeklySleep.editSleepModel(dailySleep, correctSleep);
        assertTrue(log.contains(correctSleep));
        assertFalse(log.contains(dailySleep));
    }


    @Test
    void testRemoveSleepModelWithIndex() {
        weeklySleep.addSleepModel(dailySleep);

        weeklySleep.addSleepModel(model2);
        weeklySleep.addSleepModel(model3);
        assertTrue(weeklySleep.removeSleepModel(0));
        assertFalse(weeklySleep.getSleepPerWeek().contains(dailySleep));
        assertTrue(weeklySleep.getSleepPerWeek().contains(model2));
        assertTrue(weeklySleep.getSleepPerWeek().contains(model3));
        assertFalse(weeklySleep.removeSleepModel(10));
    }

    @Test
    void testGetSleepEntries() {
        weeklySleep.addSleepModel(dailySleep);
        weeklySleep.addSleepModel(model2);

        String entries = "Sleep Entry #1" + "\n" + "Day of the week: Monday" + "\n" + "Hours slept today: 6.0" + "\n"
                + "Have exams coming up: true" + "\n" + "\n" +
                "Sleep Entry #2" + "\n" + "Day of the week: Tuesday" + "\n" + "Hours slept today: 8.0" + "\n"
                + "Have exams coming up: false" + "\n" + "\n";
        assertEquals(entries, weeklySleep.getSleepEntries());


    }




}