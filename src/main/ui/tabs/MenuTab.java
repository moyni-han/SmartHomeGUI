package ui.tabs;

import ui.HouseOrganizerGUI;

import javax.swing.*;
import java.awt.*;

public class MenuTab extends Tab {
    private static final String INIT_GREETING = "Do you want to Save or Load your Smart Home?";
    private JLabel greeting;






    //EFFECTS: constructs a menu tab for console with buttons and a greeting
    public MenuTab(HouseOrganizerGUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));


        placeGreeting();
        placeSaveLoadButtons();

    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    //EFFECTS: creates Save and load buttons that save and load when clicked
    private void placeSaveLoadButtons() {
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
//        ImageIcon image1 = new ImageIcon("src/main/ui/tabs/images/smarthome.png");
//        JFrame frame = new JFrame("Internal Frame");
//        frame.add(new JLabel(image1));
//        frame.pack();
//        frame.setVisible(true);


        JPanel buttonRow = formatButtonRow(save);


        buttonRow.add(load);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        save.addActionListener(e -> {
            getController().saveSmartHome();
            greeting.setText("Saved Home Status!");
        });

        load.addActionListener(e -> {
            getController().loadSmartHome();
            greeting.setText("Loaded Home Status!");
        });

        this.add(buttonRow);
    }



}
