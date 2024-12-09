package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import Model.BaseDrone;

public class WorkPanel extends JPanel {
    public ArrayList<DroneItem> droneItems = new ArrayList<>();

    public WorkPanel() {
        this.setBackground(Colors.DARK10);
        this.setLayout(new GridLayout(3, 2, 20, 20));
        this.setBorder(new EmptyBorder(50,150,50,150));
    }

    public void addDroneTables(ArrayList<BaseDrone> drones) {
        for (int i = 0; i < drones.size(); i++) {
            String[][] droneData = drones.get(i).toTableData();
            droneItems.add(new DroneItem(droneData, i));
            this.add(droneItems.get(i));
        }
        this.revalidate();
        this.repaint();
    }

    static public void updateInfo(int id, String[] newData) {

    }
}