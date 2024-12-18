package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Controller.WorkPanelController;
import Model.BaseDrone;

public class WorkPanel extends JPanel {
    public ArrayList<DroneItem> droneItems = new ArrayList<>();
    Button addButton = new Button("Add drone");

    public WorkPanel() {
        this.setBackground(Colors.DARK10);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder(new EmptyBorder(20,20,20,20));
        addButton.setForeground(Colors.DARK30);
        addButton.setBackground(Colors.DARK20);
        this.add(addButton);
    }

    public void addAddButtonListener(ActionListener listener) {
        this.addButton.addActionListener(listener);
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

    public void addNewDrone(String[][] droneData, WorkPanelController controller) {
        droneItems.add(new DroneItem(droneData, controller, droneItems.size()));
        this.add(droneItems.getLast()); // Добавление последнего элемента
        this.revalidate();
        this.repaint();
    }

    public String showDropDown() {
        String[] selectOptions = {"Helicopter", "QuadCopter", "Hybrid Drone", "Plane"};
        return (String) JOptionPane.showInputDialog(
                this,
                "Select a drone type:",
                "Drone Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                selectOptions,
                selectOptions[0]
        );
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }



    public void removeDroneItem(int id) {
        if (id >= 0 && id < droneItems.size()) {
            this.remove(droneItems.get(id));
            droneItems.remove(id);

            for (int i = id; i < droneItems.size(); i++) {
                droneItems.get(i).setIndex(i);
            }

            this.revalidate();
            this.repaint();
        } else {
            showError("Invalid index. Unable to remove drone.");
        }
    }
}