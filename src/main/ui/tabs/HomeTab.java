package ui.tabs;

import model.Room;
import model.SmartHome;
import ui.HouseOrganizerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import ui.tabs.popouts.SpringUtilities;

public class HomeTab extends Tab implements ActionListener, FocusListener {


    private JTabbedPane insideBar;
    private SmartHome house;
    private ArrayList<Room> usedRooms;
    static final int GAP = 10;
    JTextField roomName;
    JTextField numLightBulbs;


    //EFFECTS: constructs a home tab for console with buttons and a greeting
    public HomeTab(HouseOrganizerGUI controller) {
        super(controller);
        usedRooms = new ArrayList<>();
        loadRoomTabs(usedRooms); /// put here to fix null pointer error with adding fresh room


        setLayout(new GridLayout(3, 1));



        insideBar = new JTabbedPane();
        insideBar.setTabPlacement(JTabbedPane.RIGHT);
        insideBar.setSize(WIDTH, HEIGHT / 2);


        add(insideBar);
        placeCheckRoomsTab();
        setVisible(true);

        JPanel leftHalf = new JPanel() {
            //Don't allow us to stretch vertically.
            public Dimension getMaximumSize() {
                Dimension pref = getPreferredSize();
                return new Dimension(Integer.MAX_VALUE,
                        pref.height);
            }
        };
        leftHalf.setLayout(new BoxLayout(leftHalf,
                BoxLayout.PAGE_AXIS));
        leftHalf.add(createEntryFields());
        leftHalf.add(createButtons());

        add(leftHalf);

    }

    //EFFECTS: places the rooms in the right hand tab bar
    private void  placeCheckRoomsTab() {
        JButton checkForRooms = new JButton("Check for Newly Loaded Rooms");

        JPanel buttonRow = formatButtonRow(checkForRooms);



        checkForRooms.addActionListener(e -> {

            loadRoomTabs(usedRooms); /// something to do with this shit

        });
        this.add(buttonRow);

    }
    //EFFECTS:  loads the rooms from the right hand tab bar

    private void loadRoomTabs(ArrayList<Room> usedRooms) {
        ArrayList<Room> internalUsedRooms = usedRooms;

//

        ArrayList<MiscRoomTab> roomTabs = new ArrayList<>();
        house = getController().getHouse();
        ArrayList<Room> roomList = house.getRooms();
        System.out.println(roomList);

        for (Room r : roomList) {
            System.out.println(roomList.indexOf(r));
            if (!internalUsedRooms.contains(r)) {

                System.out.println(r.getRoomName());
                roomTabs.add(new MiscRoomTab(this.getController(), r));
                internalUsedRooms.add(r);

            }

        }

        for (MiscRoomTab r : roomTabs) {
            insideBar.add(r, roomTabs.indexOf(r));
            insideBar.setTitleAt(roomTabs.indexOf(r), r.getName());

        }

        this.usedRooms =  internalUsedRooms;

    }
    //EFFECTS: creates buttons for the user

    protected JComponent createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        JButton button = new JButton("Add Room");
        button.addActionListener(this);
        panel.add(button);



        return panel;
    }

    /**
     * Called when the user clicks the button or presses
     * Enter in a text field.
     */
    public void actionPerformed(ActionEvent e) {
        String appName = roomName.getText();
        int numLights = Integer.valueOf(numLightBulbs.getText());
        house.addRoom(new Room(numLights, appName));
    }



    /**
     * Called when one of the fields gets the focus so that
     * we can select the focused field.
     */
    public void focusGained(FocusEvent e) {
        Component c = e.getComponent();
        if (c instanceof JFormattedTextField) {
            selectItLater(c);
        } else if (c instanceof JTextField) {
            ((JTextField)c).selectAll();
        }
    }

    //Workaround for formatted text field focus side effects.
    protected void selectItLater(Component c) {
        if (c instanceof JFormattedTextField) {
            final JFormattedTextField ftf = (JFormattedTextField)c;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ftf.selectAll();
                }
            });
        }
    }

    //Needed for FocusListener interface.
    public void focusLost(FocusEvent e) {

    } //ignore

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    protected JComponent createEntryFields() {
        JPanel panel = new JPanel(new SpringLayout());

        String[] labelStrings = {
                "Name of Room: ",
                "Number of LightBulbs: "
        };

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        //Create the text field and set it up.
        roomName = new JTextField();
        roomName.setColumns(20);
        fields[fieldNum++] = roomName;

        numLightBulbs = new JTextField();
        numLightBulbs.setColumns(20);
        fields[fieldNum++] = numLightBulbs;


        //Associate label/field pairs, add everything,
        //and lay it out.
        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i],
                    JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);

            //Add listeners to each field.
            JTextField tf = null;

            tf = (JTextField)fields[i];
            tf.addActionListener(this);
            tf.addFocusListener(this);
        }
        SpringUtilities.makeCompactGrid(panel,
                labelStrings.length, 2,
                GAP, GAP, //init x,y
                GAP, GAP / 2);//xpad, ypad
        return panel;
    }


}
