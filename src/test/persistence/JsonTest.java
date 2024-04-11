package persistence;

import model.Appliance;

import model.SmartHome;

import model.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {
    protected void checkAppliance(String name, Appliance appliance, int energyUsage) {
        assertEquals(name, appliance.getApplianceName());
        assertEquals(energyUsage, appliance.getEnergyUsage());
    }

    protected  void checkRoom(String name, int numLightBulbs, Room room) {
        assertEquals(name, room.getRoomName());
        assertEquals(numLightBulbs, room.getNumLights());
    }

}
