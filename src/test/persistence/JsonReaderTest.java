package persistence;

import model.SleepModel;
import model.SleepPerWeek;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SleepPerWeek sleepPerWeek = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySleepLog() {
        JsonReader reader = new JsonReader("./data/testWriterEmptySleepLog.json");
        try {
            SleepPerWeek sleepPerWeek = reader.read();
            assertEquals(0, sleepPerWeek.numEntries());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSleepLog() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralSleepLog.json");
        try {
            SleepPerWeek sleepPerWeek = reader.read();
            List<SleepModel> sleepEntries = sleepPerWeek.getSleepPerWeek();
            assertEquals(2, sleepEntries.size());
            checkSleepModel("Saturday", 5.6, false, sleepEntries.get(0));
            checkSleepModel("Sunday", 7.8, false, sleepEntries.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
