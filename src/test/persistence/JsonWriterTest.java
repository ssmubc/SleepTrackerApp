package persistence;

import model.SleepModel;
import model.SleepPerWeek;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            SleepPerWeek sleepPerWeek = new SleepPerWeek();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            SleepPerWeek sleepPerWeek = new SleepPerWeek();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySleepLog.json");
            writer.open();
            writer.write(sleepPerWeek);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySleepLog.json");
            sleepPerWeek = reader.read();
            assertEquals(0, sleepPerWeek.numEntries());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            SleepPerWeek sleepPerWeek = new SleepPerWeek();
            sleepPerWeek.addSleepModel(new SleepModel("Saturday", 5.6, false));
            sleepPerWeek.addSleepModel(new SleepModel("Sunday", 7.8, false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSleepLog.json");
            writer.open();
            writer.write(sleepPerWeek);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSleepLog.json");
            sleepPerWeek = reader.read();
            List<SleepModel> sleepEntries = sleepPerWeek.getSleepPerWeek();
            assertEquals(2, sleepEntries.size());
            checkSleepModel("Saturday", 5.6, false, sleepEntries.get(0));
            checkSleepModel("Sunday", 7.8, false, sleepEntries.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

