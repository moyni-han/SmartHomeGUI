package persistence;

import model.Room;
import model.Appliance;
import model.SmartHome;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SmartHome read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSmartHome(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses SmartHome from JSON object and returns it
    private SmartHome parseSmartHome(JSONObject jsonObject) {
        String name = jsonObject.getString("homeName");
        SmartHome wr = new SmartHome(name);
        addRooms(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses rooms from JSON object and adds them to workroom
    private void addRooms(SmartHome wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("rooms");
        for (Object json : jsonArray) {
            JSONObject nextRoom = (JSONObject) json;
            addRoom(wr, nextRoom);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses room from JSON object and adds it to workroom
    private void addRoom(SmartHome wr, JSONObject jsonObject) {
        String name = jsonObject.getString("roomName");
        int lightbulbs = jsonObject.getInt("numLightBulbs");
        Room room = new Room(lightbulbs,name);
        addAppliances(room, jsonObject);
        wr.addRoom(room);
    }

    private void addAppliances(Room room, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("appliances");
        for (Object json : jsonArray) {
            JSONObject nextAppliance = (JSONObject) json;
            addAppliance(room, nextAppliance);
        }
    }



    // MODIFIES: wr
    //EFFECTS parses appliances from JSON object and adds them to a room/SmartHome
    private void addAppliance(Room room, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        // boolean onOff = jsonObject.getBoolean("onOff");
        int energyUsage = jsonObject.getInt("energyUsage");
        room.addAppliance(energyUsage,name);

//        ArrayList<Appliance> appList = room.getAppliances();

//        if (appList.get(appList.size() - 1).getEnergyUsage() == 0 && onOff == true) {
//            appList.get(appList.size() - 1).turnOnOff();
//        } else if (appList.get(appList.size() - 1).getEnergyUsage() > 0 && onOff == false) {
//            appList.get(appList.size() - 1).turnOnOff();
//        }


    }
}
