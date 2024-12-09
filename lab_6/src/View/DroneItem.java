package View;

import Controller.WorkPanelController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class DroneItem extends JPanel {
    private final Button editButton;
    private final Button deleteButton;
    private final Button saveButton;
    private final Button cancelButton;
    private boolean editMode = false;
    private final ArrayList<TextField> fields = new ArrayList<>();
    private TextField idField;

    public DroneItem(String[][] droneData, int id) {
        this.setBackground(Colors.DARK20);
        this.setLayout(new GridLayout(droneData.length + 2, 2, 5, 5));
        Text idText = new Text(Fonts.SPAN, "ID", Colors.WHITE100);
        idText.setBorder(new EmptyBorder(5,10,5,10));
        this.add(idText);
        idField = new TextField(String.valueOf(id));
        idField.setEnabled(false);
        idField.setEditable(false);
        this.add(idField);

        for (int i = 0; i < droneData.length; i++) {
            String[] row = droneData[i];
            Text text = new Text(Fonts.SPAN, row[0], Colors.WHITE100);
            text.setBorder(new EmptyBorder(5, 10, 5, 10));
            this.add(text);

            fields.add(new TextField(row[1]));
            fields.get(i).setEditable(false);
            fields.get(i).setEnabled(false);
            this.add(fields.get(i));
        }

        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");

        editButton.addActionListener(e -> toggleEditMode());
        deleteButton.addActionListener(e -> deleteField());
        saveButton.addActionListener(e -> saveInfo());
        cancelButton.addActionListener(e -> cancelEdit());

        this.add(editButton);
        this.add(deleteButton);
    }

    public void toggleEditMode() {
        editButton.setVisible(editMode);
        editButton.setEnabled(editMode);
        saveButton.setEnabled(!editMode);
        saveButton.setVisible(!editMode);
        cancelButton.setEnabled(!editMode);
        cancelButton.setVisible(!editMode);

        for (TextField field : fields) {
            field.setEditable(!editMode);
            field.setEnabled(!editMode);
        }

        if (!editMode) {
            this.add(saveButton);
            this.add(cancelButton);
            this.remove(editButton);
            this.remove(deleteButton);
        } else {
            this.remove(saveButton);
            this.remove(cancelButton);
            this.add(editButton);
            this.add(deleteButton);
        }

        editMode = !editMode;

        DroneItem.this.getParent().revalidate();
        DroneItem.this.getParent().repaint();
    }

    public void saveInfo() {
        String[] newData = new String[fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            newData[i] = fields.get(i).getText();
        }
        WorkPanelController.changeInfo(Integer.parseInt(idField.getText()), newData);
        this.toggleEditMode();
    }

    public void cancelEdit() {
        for (TextField field: fields) {
            field.resetField();
        }

        this.toggleEditMode();
    }

    public void deleteField() {
        Container parent = DroneItem.this.getParent();
        if (parent != null) {
            parent.remove(DroneItem.this);
            parent.revalidate();
            parent.repaint();
        }
    }
}