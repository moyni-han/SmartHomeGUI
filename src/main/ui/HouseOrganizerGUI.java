package ui;

import model.Appliance;
import model.EventLog;
import model.Room;
import model.SmartHome;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.HomeTab;
import ui.tabs.MenuTab;
import ui.tabs.StatsTab;
import ui.tabs.popouts.SplashScreen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import model.EventLog;
import model.Event;

public class HouseOrganizerGUI extends JFrame {



    private static final String JSON_STORE = "./data/house.json";
    private SmartHome house;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static final int MENU_TAB_INDEX = 0;
    public static final int HOME_TAB_INDEX = 1;
    public static final int STATS_TAB_INDEX = 2;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private JTabbedPane sidebar;

    public static void main(String[] args) {
        new HouseOrganizerGUI();
    }


    //MODIFIES: this
    //EFFECTS: creates SmartHomeGUI, displays sidebar and tabs
    private HouseOrganizerGUI() {
        super("SmartHome Console");

        house = new SmartHome("My Home");  //
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        splashScreen();
        setIcon();

        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog(EventLog.getInstance());

                //THEN you can exit the program
                System.exit(0);
            }
        });


    }

    private void setIcon() {
        ArrayList<Image> icons  = new ArrayList();
        icons.add(new ImageIcon("src/main/ui/tabs/images/smarthome.png").getImage());
        this.setIconImages((java.util.List<? extends Image>) icons);

    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }






    private void splashScreen() {
        SplashScreen splash = new SplashScreen();
        splash.show(2000);

        // Initializing...

        splash.hide();
    }
    //MODIFIES: this
    //EFFECTS: adds home tab, settings tab and report tab to this UI

    private void loadTabs() {


        JPanel menuTab = new MenuTab(this);
        JPanel statsTab = new StatsTab(this);



        sidebar.add(menuTab, MENU_TAB_INDEX);
        sidebar.setTitleAt(MENU_TAB_INDEX, "Menu");
        JPanel homeTab = new HomeTab(this);
        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "My Home");
        sidebar.add(statsTab, STATS_TAB_INDEX);
        sidebar.setTitleAt(STATS_TAB_INDEX, "House Stats");
    }

    //EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }


    public SmartHome getHouse() {
        return house;
    }

    // EFFECTS: saves the workroom to file
    public void saveSmartHome() {
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
    public void loadSmartHome() {
        try {
            house = jsonReader.read();
            System.out.println("Loaded " + house.getName() + " from " + JSON_STORE);
            ArrayList<Room> roomList = house.getRooms();
            for (Room r: roomList) {
                ArrayList<Appliance> applianceList = r.getAppliances();
                for (Appliance a: applianceList) {
                    System.out.println("       * " + a.getApplianceName() + "|| Energy Usage: " + a.getEnergyUsage());
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }






}
