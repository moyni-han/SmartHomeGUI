package model;



import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    Room r1, r2, r3, r4, r5;
    Appliance a1;


    @BeforeEach
    void runBefore(){
        r1 = new Room(0, "r1");
        r1.addLight();
        r2 = new Room(0,"r2");
        r3 = new Room(0,"r3");


    }

    @Test
    void TestGetName(){
        assertEquals(r1.getRoomName(),"r1");

    }

    @Test
    void TestAddAppliance() {
        r1.addAppliance(0, "microwave");
        assertEquals(r1.getAppliances().size(),1);




    }

    @Test
    void TestAddLight() {
        r1.addLight();
        assertEquals(r1.getNumLights(),2);

    }

    @Test
    void TestRemoveLight() {
        r1.removeLight();
        assertEquals(r1.getNumLights(),0);
        r1.removeLight();
        assertEquals(r1.getNumLights(), 0);
    }

    @Test
    void TestSwitchLights() {
        r2.addLight();
        r2.addLight();
        r2.switchLights();
        assertEquals(r2.totalEnergyUsage(),0);
        r2.switchLights();
        assertEquals(r2.totalEnergyUsage(), 0.12);


    }

    @Test
    void TestTotalEnergyUsage() {
        assertEquals(r3.totalEnergyUsage(),0);
        r3.addLight();
        assertEquals(r3.totalEnergyUsage(),0.06);
        r3.addAppliance(1,"testApp");
        assertEquals(r3.totalEnergyUsage(),1.06);

    }



}
