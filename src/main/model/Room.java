package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.lang.reflect.Array;

import java.util.ArrayList;

import java.util.List;

//represents a room in the home
// can control each individual appliance, adding them
// can control whether the lights are on or off, adding lights
// can see total energy usage of room
public class Room implements Writable {
    private int numLightBulbs;                      // number of lights in the room
    private double lightBulbEfficiency;         // efficiency of the lightbulbs being used
    private ArrayList<Appliance> appliances;    // list of all the appliances
    private String roomName;                    // name of the room
    private boolean lightsOnOff;              // if the lights are on or off


    // requires numLightBulbs >= 0;
    // creates a new room with an empty appliance list and no lights
    public Room(int numLightBulbs, String roomName) {
        appliances = new ArrayList<Appliance>();

        this.numLightBulbs = numLightBulbs;
        this.lightBulbEfficiency = 0.06; // average kwH of a lightbulb
        this.roomName = roomName.toLowerCase();
        this.lightsOnOff = true;
        EventLog.getInstance().logEvent(new Event("Added " + getNumLights()
                +  " lightbulbs to room: " + getRoomName()));

    }



    public String getRoomName() {
        return roomName;
    }


    // EFFECTS: adds a new appliance to a specific room
    public void addAppliance(int energyUsage, String applianceName) {
        Appliance newApp = new Appliance(energyUsage, applianceName);
        EventLog.getInstance().logEvent(
                new Event("Added appliance: " + newApp.getApplianceName() +  " to room: " + getRoomName()));

        appliances.add(newApp);
    }

    public ArrayList getAppliances() {
        return appliances;
    }

    // EFFECTS: adds a new light bulb to the room
    public void addLight() {
        this.numLightBulbs++;
        EventLog.getInstance().logEvent(new Event("Added a lightbulb to room: " + getRoomName()));

    }



    //EFFECTS: removes one light
    public void removeLight() {
        if (numLightBulbs > 0) {
            this.numLightBulbs--;
            EventLog.getInstance().logEvent(new Event("Removed a lightbulb from room: " + getRoomName()));


        }
    }



    public int getNumLights() {
        return numLightBulbs;

    }


    //EFFECTS: turns the lights on/ off
    public void switchLights() {
        lightsOnOff = !lightsOnOff;
        EventLog.getInstance().logEvent(new Event("Turned lights off in room: " + getRoomName()));

    }



    // MODIFIES: this
    // EFFECTS: returns the total energy usage for the room
    public double totalEnergyUsage() {
        double lightBulbEnergy;
        if (this.lightsOnOff) {
            lightBulbEnergy = this.numLightBulbs * lightBulbEfficiency;
        } else {
            lightBulbEnergy = 0;
        }


        double applianceEnergy = 0;

        for (Appliance a: appliances) {
            applianceEnergy += a.getEnergyUsage();
        }
        return lightBulbEnergy + applianceEnergy;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("roomName", roomName);
        json.put("numLightBulbs", numLightBulbs);
        json.put("appliances", appliancesToJson());
        return json;
    }

    public JSONArray appliancesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Appliance app : appliances) {
            jsonArray.put(app.toJson());
        }
        return jsonArray;
    }
}
