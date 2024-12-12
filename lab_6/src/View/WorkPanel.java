package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import Controller.WorkPanelController;
import Model.BaseDrone;

public class WorkPanel extends JPanel {
    public ArrayList<DroneItem> droneItems = new ArrayList<>();

    public WorkPanel() {
        this.setBackground(Colors.DARK10);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder(new EmptyBorder(20,20,20,20));
        Button addButton = new Button("Add drone");
        addButton.setForeground(Colors.DARK30);
        addButton.setBackground(Colors.DARK20);
        this.add(addButton);
    }

    public void addDroneTables(ArrayList<BaseDrone> drones, WorkPanelController controller) {
        for (int i = 0; i < drones.size(); i++) {
            String[][] droneData = drones.get(i).toTableData();
            droneItems.add(new DroneItem(droneData,controller, i));
            this.add(droneItems.get(i));
        }
        this.revalidate();
        this.repaint();
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void removeDroneItem(int id) {
        if (id >= 0 && id < droneItems.size()) {
            this.remove(droneItems.get(id));
            droneItems.remove(id);
            this.revalidate();
            this.repaint();
        }
    }
}