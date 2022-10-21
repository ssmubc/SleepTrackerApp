package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the SleepModel and SleepPerWeek
class SleepModelTest {
    private SleepModel dailySleep;
    private SleepPerWeek weeklySleep;
    @BeforeEach
    void runBefore() {
        dailySleep = new SleepModel("Monday", 6.0, true);
        double targetSleepPerDay = 8.0;
        weeklySleep = new SleepPerWeek();
        ArrayList<SleepModel> log = new ArrayList<SleepModel>();
    }

    @Test
    void testConstructor() {
        assertEquals("Monday", dailySleep.getDayOfTheWeek());
        assertEquals(6.0, dailySleep.getActualSleepPerDay());
        assertTrue(dailySleep.getExamOrNot());
        assertTrue(dailySleep.getActualSleepPerDay() > 0);
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
    void testRemoveSleepModel() {
        weeklySleep.addSleepModel(dailySleep);
        List<SleepModel> log = weeklySleep.getSleepPerWeek();
        weeklySleep.removeSleepModel(dailySleep);
        assertFalse(log.contains(dailySleep));
    }

    @Test
    void testEditSleepModel() {
        SleepModel correctSleep = new SleepModel("Monday", 8.0, false);
        List<SleepModel> log = weeklySleep.getSleepPerWeek();
        weeklySleep.addSleepModel(dailySleep);
        weeklySleep.editSleepModel(dailySleep, correctSleep);
        assertTrue(log.contains(correctSleep));
        assertFalse(log.contains(dailySleep));
    }



}