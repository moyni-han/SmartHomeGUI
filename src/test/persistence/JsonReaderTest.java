package persistence;

import model.Appliance;
import model.Room;
import model.SmartHome;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SmartHome wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySmartHome() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySmartHome.json");
        try {
            SmartHome wr = reader.read();
            assertEquals("my home", wr.getName());
            assertEquals(wr.getRooms().size(), 0);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSmarHomeNoAppliances() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSmarHomeNoAppliances.json");
        try {
            SmartHome wr = reader.read();
            assertEquals("my home", wr.getName());
            List<Room> rooms = wr.getRooms();
            assertEquals(2, rooms.size());
            checkRoom("needle", 0 ,rooms.get(0));
            checkRoom("saw", 4, rooms.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralSmarHomeAppliances() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSmartHomeAppliances.json");
        try {
            SmartHome wr = reader.read();
            assertEquals("my home", wr.getName());
            List<Room> rooms = wr.getRooms();
            assertEquals(2, rooms.size());
            checkRoom("needle", 0 ,rooms.get(0));
            checkRoom("saw", 4, rooms.get(1));
            checkAppliance("a1", (Appliance) rooms.get(0).getAppliances().get(0),0);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}