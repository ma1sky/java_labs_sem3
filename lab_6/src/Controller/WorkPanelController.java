package Controller;

import Model.Model;
import View.WorkPanel;

public class WorkPanelController {
    private final Model model;
    private final WorkPanel view;
    private final GeneralController callback;

    public WorkPanelController(Model model, WorkPanel view, GeneralController callback) {
        this.model = model;
        this.view = view;
        this.callback = callback;

        initializeDroneTables();
    }

    private void initializeDroneTables() {
        view.addDroneTables(model.data);
    }

    static public void changeInfo(int id, String[] newData) {

    }
}