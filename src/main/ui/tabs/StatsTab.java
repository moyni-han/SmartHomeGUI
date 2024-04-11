package ui.tabs;

import model.Room;
import model.SmartHome;
import ui.HouseOrganizerGUI;
import ui.tabs.popouts.StatsSheet;

import javax.swing.*;
import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.GridLayout;


//EFFECTS: creates a stats tab with buttons and a greeting
public class StatsTab extends Tab {

    private static final String INIT_GREETING = "Click to see house statistics";
    private JLabel greeting;

    SmartHome house;

    //EFFECTS: constructs a home tab for console with buttons and a greeting
    public StatsTab(HouseOrganizerGUI controller) {
        super(controller);



        setLayout(new GridLayout(3, 1));

        placeGreeting();
        placeSeeStatsButton();


    }

    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }


    private void displayStats() {

        greeting = new JLabel("---");
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);


    }

    //EFFECTS: creates Save and load buttons that save and load when clicked
    private void placeSeeStatsButton() {
        JButton save = new JButton("House Statistics");




        JPanel buttonRow = formatButtonRow(save);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        save.addActionListener(e -> {
            house = getController().getHouse();
            ArrayList<Room> roomList = house.getRooms();
            StatsSheet sheet = new StatsSheet(roomList);
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    sheet.createAndShowGUI(roomList);
                }
            });
        });

        this.add(buttonRow);
    }


}
