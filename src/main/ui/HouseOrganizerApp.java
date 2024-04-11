package ui;

import model.Appliance;
import model.Room;
import model.SmartHome;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HouseOrganizerApp {
    private static final String JSON_STORE = "./data/house.json";
    private SmartHome house;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    //Effect: runs the smart home app
    public HouseOrganizerApp() {
        house = new SmartHome("My Home");  //
        input = new Scanner(System.in);              // ALL INSIDE INIT() earlier
        input.useDelimiter("\n");             //
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runSmartHome();


    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runSmartHome() {
        boolean keepGoing = true;
        String command = null;


        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {

                keepGoing = false;

            } else {
                mainMenuCommand(command);
            }
        }
        System.out.println("See you later!");

    }

    //EFFECTS: processes users' command
    private void mainMenuCommand(String command) {
        if (command.equals("r")) {
            openRoom();
        } else if (command.equals("h")) {
            houseStats();
        } else if (command.equals("a")) {
            addARoom();
        } else if (command.equals("s")) {
            saveSmartHome();
        } else if (command.equals("l")) {
            loadSmartHome();
        }  else {
            System.out.println("Input not valid...");
        }

    }





    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tr -> select room");
        System.out.println("\th -> see house stats");
        System.out.println("\ta -> add a room");
        System.out.println("\ts -> save the house to file");
        System.out.println("\tl -> load a house from file");
        System.out.println("\tq -> quit");

    }

    //MODIFIES: this
    //EFFECTS: processes user input and adds rooms
    private void addARoom() {
        System.out.println("Do you want to add a Room? y or n");
        String command = input.next().toLowerCase();
        if (command.equals("y")) {
            System.out.println("What would you like to name it?");
            String name = input.next().toLowerCase();
            System.out.println("How many lights would you like in the room?");
            String bulbs = input.next().toLowerCase();
            int bulbsInt = Integer.valueOf(bulbs);
            Room room = new Room(bulbsInt, name);
            house.addRoom(room);



        }

    }

    //prints statistics of house to the screen
    private void houseStats() {
        System.out.println("Your House's Statistics:");
        System.out.println("Total Energy Usage: " + house.getEnergyUsage() + "kWh");
        System.out.println("All Appliances Turned On: ");
        ArrayList<Room> roomList = house.getRooms();
        for (Room r: roomList) {
            ArrayList<Appliance> applianceList = r.getAppliances();
            for (Appliance a: applianceList) {
                System.out.println("       * " + a.getApplianceName() + "|| Energy Usage: " + a.getEnergyUsage());
            }
        }


    }

    //prompts the user to select a room to modift
    private void openRoom() {
        System.out.println("What room would you like to open?");
        boolean keepGoing = true;
        ArrayList<Room> roomList = house.getRooms();
        for (Room r : roomList) {
            System.out.println(roomList.indexOf(r) + 1 + ") " + r.getRoomName());
        }
        System.out.println("q) quit");
        while (keepGoing) {
            String command = input.next().toLowerCase();
            Room room = null;
            System.out.println(roomList.size());
            if (Integer.valueOf(command) <= roomList.size()) {
                roomMenu();
                room = roomList.get(Integer.valueOf(command) - 1);
            } else {
                System.out.println("Invalid Input");
            }
            if (room != null) {
                command = input.next().toLowerCase();
                keepGoing = roomControl(command, keepGoing, room);

            }
        }

    }


    // EFFECTS: displays menu of options to user
    private void roomMenu() {
        System.out.println("What do you want to do?");
        System.out.println("l) Add Lights");
        System.out.println("r) Remove Lights");
        System.out.println("a) Add an appliance");
    }


    //MODIFIES: this
    //EFFECTS: prompts user input, and modifies ROOM class
    private boolean roomControl(String command, Boolean keepGoing, Room room) {
        if (command.equals("l")) {
            System.out.println("How many to add?");
            command = input.next();
            for (int i = 0; i < Integer.valueOf(command); i++) {
                room.addLight();
            }
            keepGoing = false;

        } else if (command.equals("r")) {
            System.out.println("How many to remove?");
            for (int i = 0; i < Integer.valueOf(command); i++) {
                room.removeLight();
            }
            keepGoing = false;

        } else if (command.equals("a")) {
            return applianceMenu(command, keepGoing, room);

        } else {
            System.out.println("Input is Invalid");


        }
        return keepGoing;
    }

    // EFFECTS: displays menu of options to user
    private boolean applianceMenu(String command, Boolean keepGoing, Room room) {
        System.out.println("What to name the appliance?");
        command = input.next().toLowerCase();
        String name = command;
        System.out.println("How much energy does it use?");
        command = input.next().toLowerCase();
        int energy = Integer.valueOf(command);
        room.addAppliance(energy,name);
        keepGoing = false;
        return keepGoing;
    }

    // EFFECTS: saves the workroom to file
    private void saveSmartHome() {
        try {
            jsonWriter.open();
            jsonWriter.write(house);
            jsonWriter.close();
            System.out.println("Saved " + house.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads smarthome from file
    private void loadSmartHome() {
        try {
            house = jsonReader.read();
            System.out.println("Loaded " + house.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



}
