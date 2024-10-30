import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Path data = Path.of("file.txt");
        Path exceptionLogs = Path.of("exceptionLogs.txt");
        Scanner sc = new Scanner(System.in);
        ArrayList<BaseDrone> drones = new ArrayList<>();
        int choice = 1;

        try {
            User user = new User(User.readSettingsFromFile(Path.of("settings.txt")));
            user.auth();
            View.printGreeting(user.getLogin());
            while (choice != 0) {
                View.printMenu(user);
                choice = sc.nextInt();
                switch (choice) {
                    case 3 -> user.setDebugging(!user.isDebugging());
                    case 4 -> user.setAutotests(!user.isAutotests());
                    case 5 -> drones = Controller.readDataBase(data);
                    case 6 -> Controller.writeDataBase(drones, data);
                    case 0 -> System.exit(0);
                }
            }
        } catch (IOException e) {
            ExceptionHandler.handleException(e, exceptionLogs);
        }
    }
}