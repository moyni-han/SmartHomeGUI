package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

import java.util.List;




//represents a SmartHome in the program
//home has total energy usage, and control over every room's switch
public class SmartHome implements Writable {
    private ArrayList<Room> roomList; // list of rooms in house
    private String homeName;


    public SmartHome(String homeName) {
        this.roomList = new ArrayList<Room>();
        this.homeName = homeName.toLowerCase();

    }


    public String getName() {
        return homeName;
    }


    //EFFECTS: adds a room to the list of rooms
    public void addRoom(Room room) {

        if (!roomList.contains(room)) {
            EventLog.getInstance().logEvent(new Event("Added room: " + room.getRoomName()));
            roomList.add(room);

        }


    }


    //removes a room from the list of rooms
    public void removeRoom(Room room) {
        EventLog.getInstance().logEvent(new Event("Removed room: " + room.getRoomName()));
        roomList.remove(room);


    }



    public ArrayList getRooms() {
        return roomList;
    }


    //MODIFIES: this
    //EFFECTS returns a double of the energy usage in the house
    public double getEnergyUsage() {
        double energy = 0;
        for (Room r : roomList) {
            energy += r.totalEnergyUsage();
        }
        return energy;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("homeName", homeName);
        json.put("rooms", roomsToJson());
        EventLog.getInstance().logEvent(new Event("Saved Rooms, and Appliances to: " + homeName));
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray roomsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Room r : roomList) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }
}
