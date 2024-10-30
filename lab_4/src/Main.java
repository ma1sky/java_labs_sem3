import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Path logs = Path.of("logs.txt");
        Path data= Path.of("dataBase.txt");
        Path settings = Path.of("settings.txt");
        ArrayList<BaseDrone> drones = new ArrayList<>();

        Logs.writeTime("Program started at: ", logs);
        String privet = Controller.scanString();

        Controller.mainMenu(drones, settings, data, logs);
        Logs.writeTime("Program end: ", logs);
    }
}