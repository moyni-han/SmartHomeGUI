

package ui.tabs.popouts;

/*
 * SimpleTableDemo.java requires no other files.
 */

import model.Appliance;
import model.Room;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

public class StatsSheet extends JPanel {


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public StatsSheet(ArrayList<Room> roomList) {
        super(new GridLayout(1,0));





        Object[][] data = {
                {"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},
                {"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},
                {"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},
                {"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},
                {"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"},{"_","_"}};


        System.out.println(roomList);
        int counter = 0;
        for (Room r: roomList) {

            ArrayList<Appliance> applianceList = r.getAppliances();
            for (Appliance a: applianceList) {
                counter++;
                data[applianceList.indexOf(a) + counter][0] = a.getApplianceName();
                data[applianceList.indexOf(a) + counter][1] = a.getEnergyUsage();
                //applianceList.indexOf(a);

            }
        }


        String[] columnNames = {
                "Appliance",
                "Energy Usage"};



        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));

        table.setFillsViewportHeight(true);



        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI(ArrayList<Room> roomList) {
        //Create and set up the window.
        JFrame frame = new JFrame("SimpleTableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        StatsSheet newContentPane = new StatsSheet(roomList);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


}
