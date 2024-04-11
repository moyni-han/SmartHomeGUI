package ui.tabs;

import ui.HouseOrganizerGUI;

import javax.swing.*;
import java.awt.*;

public abstract class Tab extends JPanel {

    private final HouseOrganizerGUI controller;

    //REQUIRES: SmartHomeUI controller that holds this tab
    public Tab(HouseOrganizerGUI controller) {
        this.controller = controller;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    //EFFECTS: returns the SmartHomeUI controller for this tab
    public HouseOrganizerGUI getController() {
        return controller;
    }

}
