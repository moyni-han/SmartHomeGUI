package model;



import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class ApplianceTest {
    Appliance a1;

    @BeforeEach
    void runBefore() {
        a1 = new Appliance(20,"richard");

    }

    @Test
    void TestGetApplianceName() {
        assertEquals(a1.getApplianceName(),"richard");

    }

    @Test
    void TestGetEnergyUsage() {
        assertEquals(a1.getEnergyUsage(), 20);
        a1.turnOnOff();
        assertEquals(a1.getEnergyUsage(), 0);
    }

    @Test
    void TestTurnOnOff() {
        a1.turnOnOff();
        assertEquals(a1.getEnergyUsage(),0);
        a1.turnOnOff();
        assertEquals(a1.getEnergyUsage(), 20);
    }
}
