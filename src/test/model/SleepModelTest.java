package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        EventLog.getInstance().clear();
    }

    // EventLog and Event class related tests below:
//
    @Test
    void testClear() {
        EventLog.getInstance().clear();
        assertEquals("Event log cleared.", EventLog.getInstance().iterator().next().getDescription());

    }

    @Test
    void testEventLog() {
        weeklySleep.addSleepModel(dailySleep);
        EventLog eventlog = EventLog.getInstance();
        Event event = null;

        for (Event event1 : eventlog) {
            event = event1;
            System.out.println(event.getDescription());
        }
        assert event != null;
        assertEquals("Added sleep entry to your log",event.getDescription());
    }

    @Test
    void testEventLogRemove() {
        weeklySleep.removeSleepModel(0);
        EventLog eventlog = EventLog.getInstance();
        Event event = null;

        for (Event event1 : eventlog) {
            event = event1;
            System.out.println(event.getDescription());
        }

        assertEquals("Removed a sleep entry from your log", event.getDescription());
    }

    @Test
    void testEquals() throws InterruptedException {
        Event event1 = new Event("Added sleep entry to your log");
        Event event2 = new Event("Added sleep entry to your log");
        Event event3 = new Event("Random event");
        String str = "Random event";
        Event event4 = null;
        assertTrue(event1.equals(event2) && event2.equals(event1));
        assertFalse(event1.equals(event3));
        assertFalse(event1.equals(str));
        assertFalse(event1.equals(event4));
        TimeUnit.SECONDS.sleep(1); // sleep for 1 second
        Event event5 = new Event("Added sleep entry to your log");
        assertFalse(event1.equals(event5));
    }

    @Test
    void testHashCode() {
        Event event1 = new Event("Added sleep entry to your log");
        Event event2 = new Event("Added sleep entry to your log");
        Event event3 = new Event("Random event");

        assertTrue(event1.hashCode() == event2.hashCode());
        assertFalse(event1.hashCode() == event3.hashCode());

    }

    @Test
    void testToString() {
        assertEquals(Calendar.getInstance().getTime() + "\n" +
                        EventLog.getInstance().iterator().next().getDescription(),
                EventLog.getInstance().iterator().next().toString());
    }

    @Test
    void testGetDate() {
        Event event1 = new Event("Added sleep entry to your log");
        assertEquals(Calendar.getInstance().getTime(), event1.getDate());
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