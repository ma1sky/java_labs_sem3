import java.io.IOException;
import java.nio.file.Path;

import Controller.ExceptionHandler;
import Controller.GeneralController;
import Controller.Logs;
import Model.Model;
import View.GeneralView;

public class Main {
    public static void main(String[] args) throws IOException {
        Path logs = Path.of("logs.txt");

        try {
            Logs.writeTime("Program started at: ", logs);

            GeneralView view = new GeneralView();
            Model model = new Model();
            new GeneralController(view, model);

            Logs.writeTime("Program end: ", logs);

        } catch (IOException | ClassNotFoundException ex) {
            ExceptionHandler.handleException(ex, logs);
        }
    }
}