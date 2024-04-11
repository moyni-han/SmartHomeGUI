/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package ui.tabs;

import model.Room;
import ui.HouseOrganizerGUI;
import ui.tabs.popouts.SpringUtilities;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;




public class MiscRoomTab extends Tab
                                    implements ActionListener,
                                                FocusListener {
    static final int GAP = 10;
    JTextField applianceName;
    JTextField energyUsage;


    private String name;
    private Room miscRoom;
    //EFFECTS: creates a room tab for console with buttons and a greeting

    public MiscRoomTab(HouseOrganizerGUI controller, Room room) {
        super(controller);

        miscRoom = room;
        name = miscRoom.getRoomName();


        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

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
        // add(createAddressDisplay());

    }

    public String getName() {
        return name;
    }





    //EFFECTS:  creates buttons for the user

    protected JComponent createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        JButton button = new JButton("Add Appliance");
        button.addActionListener(this);
        panel.add(button);



        return panel;
    }






    /**
     * Called when the user clicks the button or presses
     * Enter in a text field.
     */
    public void actionPerformed(ActionEvent e) {
        String appName = applianceName.getText();
        int appEnergy = Integer.valueOf(energyUsage.getText());
        miscRoom.addAppliance(appEnergy,appName);
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
                "Name: ",
                "Energy Usage (kWh): "
        };

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        //Create the text field and set it up.
        applianceName = new JTextField();
        applianceName.setColumns(20);
        fields[fieldNum++] = applianceName;

        energyUsage = new JTextField();
        energyUsage.setColumns(20);
        fields[fieldNum++] = energyUsage;


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


