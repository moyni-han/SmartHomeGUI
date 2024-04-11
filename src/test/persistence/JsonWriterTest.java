package persistence;

import model.Appliance;
import model.Room;
import model.SmartHome;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            SmartHome wr = new SmartHome("my home");
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
            SmartHome wr = new SmartHome("my home");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            wr = reader.read();
            assertEquals("my home", wr.getName());
            assertEquals(0, wr.getRooms().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            SmartHome wr = new SmartHome("my home");
            wr.addRoom(new Room(0, "kitchen"));
            wr.addRoom(new Room(0, "fuck"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            wr = reader.read();
            assertEquals("my home", wr.getName());
            List<Room> rooms = wr.getRooms();
            assertEquals(2, rooms.size());
            checkRoom("kitchen", 0, rooms.get(0));
            checkRoom("fuck", 0, rooms.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralSmartHomeAppliances() {
        try {
            SmartHome wr = new SmartHome("my home");
            wr.addRoom(new Room(0, "kitchen"));
            wr.addRoom(new Room(0, "fuck"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSmartHomeAppliance.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSmartHomeAppliance.json");
            wr = reader.read();
            assertEquals("my home", wr.getName());
            List<Room> rooms = wr.getRooms();
            rooms.get(0).addAppliance(0,"tv");
            rooms.get(0).addAppliance(0,"vt");
            rooms.get(1).addAppliance(0,"smartSink");
            rooms.get(1).addAppliance(0,"dumbSink");



            List<Appliance> appliances_r1 = rooms.get(0).getAppliances();
            List<Appliance> appliances_r2 = rooms.get(1).getAppliances();


            assertEquals(2, rooms.size());
            checkRoom("kitchen", 0, rooms.get(0));
            checkRoom("fuck", 0, rooms.get(1));
            checkAppliance("tv",appliances_r1.get(0),0);
            checkAppliance("vt",appliances_r1.get(1),0);
            checkAppliance("smartsink",appliances_r2.get(0),0);
            checkAppliance("dumbsink",appliances_r2.get(1),0);


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}