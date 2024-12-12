package View;

import Controller.WorkPanelController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

public class DroneItem extends JPanel {
    private final WorkPanelController controller; // Поле для хранения ссылки на контроллер
    private final Button editButton;
    private final Button deleteButton;
    private final Button saveButton;
    private final Button cancelButton;
    private boolean editMode = false;
    private final ArrayList<TextField> fields = new ArrayList<>();
    private final TextField idField;
    protected ArrayList<JPanel> rows = new ArrayList<>();

    public DroneItem(String[][] droneData, WorkPanelController controller, int id) {
        this.controller = controller;
        this.setBackground(Colors.DARK20);
        JPanel idRow = new JPanel();
        idRow.setLayout(new BoxLayout(idRow, BoxLayout.X_AXIS));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Text idText = new Text(Fonts.SPAN, "ID", Colors.WHITE100);
        idText.setBorder(new EmptyBorder(4,8,4,8));
        idField = new TextField(String.valueOf(id));
        idField.setEnabled(false);
        idField.setEditable(false);

        idRow.add(idText);
        idRow.add(Box.createHorizontalGlue());
        idRow.add(idField);
        idRow.setBackground(Colors.DARK20);
        rows.add(idRow);

        for (int i = 0; i < droneData.length; i++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setBackground(Colors.DARK20);
            rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));

            String[] row = droneData[i];
            Text text = new Text(Fonts.SPAN, row[0], Colors.WHITE100);
            text.setBorder(new EmptyBorder(4, 8, 4, 8));
            rowPanel.add(text);
            fields.add(new TextField(row[1]));
            fields.get(i).setEditable(false);
            fields.get(i).setEnabled(false);
            rowPanel.add(Box.createHorizontalGlue());
            rowPanel.add(fields.get(i));

            rows.add(rowPanel);
        }

        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");

        editButton.addActionListener(e -> toggleEditMode());
        deleteButton.addActionListener(e -> deleteField());
        saveButton.addActionListener(e -> changeInfo());
        cancelButton.addActionListener(e -> cancelEdit());

        rows.add(new JPanel());
        rows.getLast().add(editButton);
        rows.getLast().add(deleteButton);
        rows.getLast().setBackground(Colors.DARK20);
        for (JPanel row : rows) {
            this.add(row);
        }
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

        fields.getFirst().setEnabled(false);
        fields.getFirst().setEditable(false);

        if (!editMode) {
            rows.getLast().add(saveButton);
            rows.getLast().add(cancelButton);
            rows.getLast().remove(editButton);
            rows.getLast().remove(deleteButton);
        } else {
            rows.getLast().remove(saveButton);
            rows.getLast().remove(cancelButton);
            rows.getLast().add(editButton);
            rows.getLast().add(deleteButton);
        }

        editMode = !editMode;

        DroneItem.this.getParent().revalidate();
        DroneItem.this.getParent().repaint();
    }

    public void changeInfo() {
        String[] newData = new String[fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            newData[i] = fields.get(i).getText();
        }
        if (!controller.updateDroneData(Integer.parseInt(idField.getText()), newData)) {
            cancelEdit();
        } else {
            this.toggleEditMode();
        }
    }

    public void cancelEdit() {
        for (TextField field: fields) {
            field.resetField();
        }

        this.toggleEditMode();
    }


    public void deleteField() {
        controller.deleteItem(Integer.parseInt(idField.getText()));
    }
}
