package persistence;

import model.SleepModel;
import model.SleepPerWeek;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
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
        JsonReader reader = new JsonReader("./data/testReaderEmptySleepLog.json");
        try {
            SleepPerWeek sleepPerWeek = reader.read();
            assertEquals(0, sleepPerWeek.numEntries());
            LocalDateTime dateTime = LocalDateTime.now();
            assertEquals(sleepPerWeek.getMonth(), dateTime.getMonth().name());
            //assertEquals();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

//    @Test
//    void testReaderEmptyWorkRoom() {
//        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
//        try {
//            WorkRoom wr = reader.read();
//            assertEquals("My work room", wr.getName());
//            assertEquals(0, wr.numThingies());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }

    @Test
    void testReaderGeneralSleepLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSleepLog.json");
        try {
            SleepPerWeek sleepPerWeek = reader.read();
            List<SleepModel> sleepEntries = sleepPerWeek.getSleepPerWeek();
            assertEquals(2, sleepEntries.size());
            LocalDateTime dateTime = LocalDateTime.now();
            assertEquals(sleepPerWeek.getMonth(), dateTime.getMonth().name());
            checkSleepModel("Saturday", 5.6, false, sleepEntries.get(0));
            checkSleepModel("Sunday", 7.8, false, sleepEntries.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
