package persistence;

import model.SleepModel;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {
    protected void checkSleepModel(String dayOfTheWeek, Double sleep, Boolean examOrNot, SleepModel sleepModel) {
        assertEquals(dayOfTheWeek, sleepModel.getDayOfTheWeek());
        assertEquals(sleep, sleepModel.getActualSleepPerDay());
        assertEquals(examOrNot, sleepModel.getExamOrNot());
    }
}




