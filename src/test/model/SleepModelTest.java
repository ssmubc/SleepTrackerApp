package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SleepModelTest {
    // delete or rename this class!
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

//    @Test
//    void testCalculateSleepDifference() {
//        assertEquals(2.0, dailySleep.calculateSleepDifference());
//    }

    @Test
    void testAddSleepModel() {
        weeklySleep.addSleepModel(dailySleep);
        List<SleepModel> log = weeklySleep.getSleepPerWeek();
        assertTrue(log.contains(dailySleep));
    }

    @Test
    void testRemoveSleepModel() {
        weeklySleep.addSleepModel(dailySleep);
        List<SleepModel> log = weeklySleep.getSleepPerWeek();
        log.remove(dailySleep);
        assertFalse(log.contains(dailySleep));
    }



}