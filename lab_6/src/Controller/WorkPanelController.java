package Controller;

import Model.Model;
import View.WorkPanel;
import Model.Helicopter;
import Model.QuadCopter;
import Model.HybridDrone;
import Model.BaseDrone;
import Model.Plane;
import java.awt.*;
import java.util.ArrayList;

public class WorkPanelController extends Component {
    private final Model model;
    private final WorkPanel view;
    private final GeneralController callback;

    public WorkPanelController(Model model, WorkPanel view, GeneralController callback) {
        this.model = model;
        this.view = view;
        this.callback = callback;

        initializeDroneTables();
        addDrone();
    }

    private void initializeDroneTables() {
        view.addDroneTables(model.data, this);
    }

    public void addDrone() {
        view.addAddButtonListener(e -> {
            String selectedItem = view.showDropDown();

            if (selectedItem != null) {
                BaseDrone newDrone = null;

                switch (selectedItem) {
                    case "Helicopter" -> newDrone = new Helicopter();
                    case "QuadCopter" -> newDrone = new QuadCopter();
                    case "Hybrid Drone" -> newDrone = new HybridDrone();
                    case "Plane" -> newDrone = new Plane();
                    default -> {
                        view.showError("Unknown drone type.");
                        return;
                    }
                }

                model.data.add(newDrone);
                view.addNewDrone(newDrone.toTableData(), this);
            }
        });
    }

    public boolean updateDroneData(int id, String[] data) {
        BaseDrone drone = model.data.get(id);
        if (data.length != 12) {
            view.showError("There is not enough data to update the drone.");
            return false;
        }

        return switch (data[0]) {
            case "Helicopter" -> {
                if (!validateHelicopterData(data)) {
                    yield false;
                }
                yield ((Helicopter) drone).changeData(
                        Double.parseDouble(data[1]),
                        Double.parseDouble(data[2]),
                        Double.parseDouble(data[3]),
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Double.parseDouble(data[6]),
                        data[7].replace("'", "").trim(),
                        Double.parseDouble(data[8]),
                        data[9].replace("'", "").trim(),
                        Boolean.parseBoolean(data[10]),
                        Integer.parseInt(data[11])
                );
            }
            case "QuadCopter" -> {
                if (!validateQuadCopterData(data)) {
                    yield false;
                }
                yield ((QuadCopter) drone).changeData(
                        Double.parseDouble(data[1]),
                        Double.parseDouble(data[2]),
                        Double.parseDouble(data[3]),
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Double.parseDouble(data[6]),
                        data[7].replace("'", "").trim(),
                        Double.parseDouble(data[8]),
                        data[9].replace("'", "").trim(),
                        Boolean.parseBoolean(data[10]),
                        data[11].replace("'", "").trim()
                );
            }
            case "Hybrid Drone" -> {
                if (!validateHybridDroneData(data)) {
                    yield false;
                }
                yield ((HybridDrone) drone).changeData(
                        Double.parseDouble(data[1]),
                        Double.parseDouble(data[2]),
                        Double.parseDouble(data[3]),
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Double.parseDouble(data[6]),
                        data[7].replace("'", "").trim(),
                        Double.parseDouble(data[8]),
                        data[9].replace("'", "").trim(),
                        Boolean.parseBoolean(data[10]),
                        Double.parseDouble(data[11]) // battery capacity
                );
            }
            case "Plane" -> {
                if (!validatePlaneData(data)) {
                    yield false;
                }
                yield ((Plane) drone).changeData(
                        Double.parseDouble(data[1]),
                        Double.parseDouble(data[2]),
                        Double.parseDouble(data[3]),
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Double.parseDouble(data[6]),
                        data[7].replace("'", "").trim(),
                        Double.parseDouble(data[8]),
                        data[9].replace("'", "").trim(),
                        Boolean.parseBoolean(data[10]),
                        Double.parseDouble(data[11])
                );
            }
            default -> {
                view.showError("Incorrect input data.");
                yield false;
            }
        };
    }
    private boolean validateHelicopterData(String[] data) {
        return GeneralController.validateDouble(data[1]) &&
                GeneralController.validateDouble(data[2]) &&
                GeneralController.validateDouble(data[3]) &&
                GeneralController.validateDouble(data[4]) &&
                GeneralController.validateDouble(data[5]) &&
                GeneralController.validateDouble(data[6]) &&
                GeneralController.validateString(data[7], 1, 50) &&
                GeneralController.validateDouble(data[8]) &&
                GeneralController.validateString(data[9], 1, 50) &&
                GeneralController.validateBool(data[10]) &&
                GeneralController.validateInt(data[11]);
    }
    private boolean validateQuadCopterData(String[] data) {
        return GeneralController.validateDouble(data[1]) &&
                GeneralController.validateDouble(data[2]) &&
                GeneralController.validateDouble(data[3]) &&
                GeneralController.validateDouble(data[4]) &&
                GeneralController.validateDouble(data[5]) &&
                GeneralController.validateDouble(data[6]) &&
                GeneralController.validateString(data[7], 1, 50) &&
                GeneralController.validateDouble(data[8]) &&
                GeneralController.validateString(data[9], 1, 50) &&
                GeneralController.validateBool(data[10]) &&
                GeneralController.validateString(data[11], 1, 50);
    }
    private boolean validateHybridDroneData(String[] data) {
        return GeneralController.validateDouble(data[1]) &&
                GeneralController.validateDouble(data[2]) &&
                GeneralController.validateDouble(data[3]) &&
                GeneralController.validateDouble(data[4]) &&
                GeneralController.validateDouble(data[5]) &&
                GeneralController.validateDouble(data[6]) &&
                GeneralController.validateString(data[7], 1, 50) &&
                GeneralController.validateDouble(data[8]) &&
                GeneralController.validateString(data[9], 1, 50) &&
                GeneralController.validateBool(data[10]) &&
                GeneralController.validateDouble(data[11]);
    }
    private boolean validatePlaneData(String[] data) {
        return GeneralController.validateDouble(data[1]) &&
                GeneralController.validateDouble(data[2]) &&
                GeneralController.validateDouble(data[3]) &&
                GeneralController.validateDouble(data[4]) &&
                GeneralController.validateDouble(data[5]) &&
                GeneralController.validateDouble(data[6]) &&
                GeneralController.validateString(data[7], 1, 50) &&
                GeneralController.validateDouble(data[8]) &&
                GeneralController.validateString(data[9], 1, 50) &&
                GeneralController.validateBool(data[10]) &&
                GeneralController.validateDouble(data[11]);
    }
    public void deleteItem(int id) {
        if (id >= 0 && id < model.data.size()) {
            model.data.remove(id);
        }

        view.removeDroneItem(id);
    }
}