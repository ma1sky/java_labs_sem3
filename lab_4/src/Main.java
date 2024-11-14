import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Path logs = Path.of("logs.txt");
        Path data= Path.of("dataBase.bin");
        Path settings = Path.of("settings.txt");
        ArrayList<BaseDrone> drones = new ArrayList<>();
        try {
            Logs.writeTime("Program started at: ", logs);
            Controller.mainMenu(drones, settings, data, logs);
            Logs.writeTime("Program end: ", logs);
        } catch (IOException ex) {
            ExceptionHandler.handleException(ex, logs);
        }
    }
}