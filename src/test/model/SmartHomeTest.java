package model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartHomeTest {
    private SmartHome sh1, sh2, sh3, sh4;
    private Room r1, r2, r3;

    @BeforeEach
    void runBefore() {

        // Test Add Room
        sh1 = new SmartHome("SH1");
        r1 = new Room(3, "room 1");
        r2 = new Room(3, "room 1");
        r3 = new Room(3, "room 1");

        //Test Remove Room
        sh2 = new SmartHome("SH2");
        sh2.addRoom(r1);
        sh2.addRoom(r2);

        //Test Get Energy Usage
        sh3 = new SmartHome("SH3");
        sh3.addRoom(r1);
        sh3.addRoom(r2);
        sh3.addRoom(r3);
        sh4 = new SmartHome("SH4");


    }

    @Test
    void TestAddRoom() {
        sh1.addRoom(r1);
        assertEquals(sh1.getRooms().size(),1);
        sh1.addRoom(r1);
        assertEquals(sh1.getRooms().size(), 1);
        sh1.addRoom(r2);
        assertEquals(sh1.getRooms().size(), 2);

    }

    @Test
    void TestRemoveRoom() {

        sh2.removeRoom(r1);
        assertEquals(sh2.getRooms().size(), 1);
        sh2.removeRoom(r2);
        assertEquals(sh2.getRooms().size(), 0);

    }

    @Test
    void TestGetEnergyUsage() {
        assertEquals(sh3.getEnergyUsage(),0.54);
        assertEquals(sh4.getEnergyUsage(),0);

    }
}
