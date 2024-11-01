import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Controller {
    public static Path logs = Path.of("logs.txt");

    public static BaseDrone createRandomDrone() {
        Random random = new Random();
        return switch (random.nextInt(4)) {
            case 0 ->
                    new QuadCopter(
                            random.nextDouble() * 50 + 10, // range
                            random.nextDouble() * 60 + 10, // flight time
                            random.nextDouble() * 20 + 5,  // speed
                            random.nextDouble() * 30 + 10, // load capacity
                            random.nextDouble() * 5 + 1,   // height
                            random.nextDouble() * 500 + 100, // size
                            "Electric",                    // power type
                            random.nextDouble() * 2 + 0.5, // size
                            "Vertical",                    // takeoff method
                            random.nextBoolean(),          // weather resistant
                            "4K"                           // camera resolution
                    );
            case 1 ->
                    new Plane(
                            random.nextDouble() * 200 + 50, // range
                            random.nextDouble() * 180 + 60, // flight time
                            random.nextDouble() * 300 + 100, // speed
                            random.nextDouble() * 500 + 100, // load capacity
                            random.nextDouble() * 50 + 10,  // height
                            random.nextDouble() * 5000 + 1000, // size
                            "Fuel",                         // power type
                            random.nextDouble() * 15 + 5,  // wing span
                            "Horizontal",                  // takeoff method
                            random.nextBoolean(),          // weather resistant
                            random.nextDouble() * 30 + 5   // wing span
                    );
            case 2 ->
                    new Helicopter(
                            random.nextDouble() * 150 + 30, // range
                            random.nextDouble() * 120 + 40, // flight time
                            random.nextDouble() * 200 + 50, // speed
                            random.nextDouble() * 300 + 50, // load capacity
                            random.nextDouble() * 25 + 5,   // height
                            random.nextDouble() * 4000 + 500, // size
                            "Fuel",                         // power type
                            random.nextDouble() * 5 + 1,   // rotor diameter
                            "Vertical",                    // takeoff method
                            random.nextBoolean(),          // weather resistant
                            random.nextInt(6) + 2          // number of blades
                    );
            case 3 ->
                    new HybridDrone(
                            random.nextDouble() * 100 + 40, // range
                            random.nextDouble() * 90 + 30,  // flight time
                            random.nextDouble() * 250 + 50, // speed
                            random.nextDouble() * 400 + 50, // load capacity
                            random.nextDouble() * 40 + 10,  // height
                            random.nextDouble() * 6000 + 1500, // size
                            "Hybrid (Fuel + Electric)",    // power type
                            random.nextDouble() * 3 + 1,   // battery capacity
                            "Vertical",                    // takeoff method
                            random.nextBoolean(),          // weather resistant
                            random.nextDouble() * 6000 + 1000 // battery capacity
                    );
            default -> throw new IllegalStateException("Unexpected value: " + random.nextInt(4));
        };
    }
    public static void writeDataBase(ArrayList<BaseDrone> droneArrayList, Path path) throws IOException {
        ArrayList<String> droneStrings = new ArrayList<String>();

        for (BaseDrone drone : droneArrayList) {
            droneStrings.add(drone.toString());
        }

        if (Files.exists(path)) {
            Files.write(path, droneStrings, StandardOpenOption.TRUNCATE_EXISTING);
        } else {
            Files.createFile(path);
            Files.write(path, droneStrings);
        }
        Logs.writeTime("DataBase writing successful.", logs);
        System.out.println("DataBase writing successful.");
    }
    public static ArrayList<BaseDrone> readDataBase(Path path) throws IOException {
        ArrayList<BaseDrone> droneArrayList = new ArrayList<>();
        String[] lines = Files.readAllLines(path).toArray(new String[0]);

        for (String line : lines) {
            String[] parts = line.split(",");
            switch (Integer.parseInt(parts[0].split(" ")[0])) {
                case 1 -> droneArrayList.add(new QuadCopter(parts));
                case 2 -> droneArrayList.add(new Plane(parts));
                case 3 -> droneArrayList.add(new Helicopter(parts));
                case 4 -> droneArrayList.add(new HybridDrone(parts));
                default ->
                        throw new IllegalStateException("Unexpected value: " + Integer.parseInt(parts[0].split(" ")[0]));
            }
        }
        Logs.writeTime("DataBase reading successful.", logs);
        System.out.println("DataBase reading successful.");
        return droneArrayList;
    }

    public static String scanString(String desc) {
        Scanner sc = new Scanner(System.in);
        String str;
        System.out.print(desc + " ");
        while (true) {
            try{
                str = sc.nextLine();
                if (str.isEmpty()) {
                    throw new IllegalArgumentException("\nLine is empty. Write something please.");
                }
                if (str.replaceAll("(?U)[\\pP\\s]", "").trim().isEmpty()) {
                    throw new IllegalArgumentException("\nOnly symbols of punctuation.");
                } else { break; }
            } catch (IllegalArgumentException e) {
                ExceptionHandler.handleException(e, logs);
            }

        }
        return str;
    }

    public static void changeDrone(ArrayList<BaseDrone> drones) throws IOException {
        Controller.removeDrone(drones);
        Controller.addDrone(drones);
    }

    public static void mainMenu(ArrayList<BaseDrone> drones, Path settings, Path data, Path exceptionLogs) {
        Random random = new Random();
        int choice = 1;
        Scanner sc = new Scanner(System.in);

        try {
            User user = new User(User.readSettingsFromFile(settings));
            user.auth();
            if (user.getGroup() == "root" && user.isAutotests()) {
                Autotests.testConstructorWithParameters();
                Autotests.testConstructorWithStringArray();
                Autotests.testGetType();
                Autotests.testToString();
                Autotests.testGetPurpose();
                Autotests.writeLogToFile();
            }

            View.printGreeting(user.getLogin());
            switch (user.getGroup()) {
                case "user" -> {
                    while (choice != 0) {
                        View.printMenu(user);
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1 -> drones = Controller.readDataBase(data);
                            case 2 -> Controller.writeDataBase(drones, data);
                            case 3 -> Controller.addDrone(drones);
                            case 4 -> Controller.removeDrone(drones);
                            case 5 -> View.printDrones(drones);
                            case 6 -> Controller.changeDrone(drones);
                        }

                    }
                }
                case "root" -> {
                    while (choice != 0) {
                        View.printMenu(user);
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1 -> drones = Controller.readDataBase(data);
                            case 2 -> Controller.writeDataBase(drones, data);
                            case 3 -> Controller.addDrone(drones);
                            case 4 -> Controller.removeDrone(drones);
                            case 5 -> View.printDrones(drones);
                            case 6 -> Controller.changeDrone(drones);
                            case 7 -> user.setDebugging(!user.isDebugging());
                            case 8 -> user.setAutotests(!user.isAutotests());
                        }
                    }
                }
            }

        } catch (IOException e) {
            ExceptionHandler.handleException(e, exceptionLogs);
        }
    }

    public static double scanDouble(String desc) {
        System.out.println(desc + ": ");
        Scanner sn = new Scanner(System.in);
        String input = new String();
        double num = -1;
        while(true) {
            try {
                input = sn.nextLine();
                num = Double.parseDouble(input);
                if (num < 0) {throw new IllegalArgumentException("Number must be positive number");}
                return num;
            }
            catch (InputMismatchException | NumberFormatException ex) {
                ExceptionHandler.handleException(ex, logs);
            }
        }
    }

    public static int scanInt(String desc, int hlim, int llim) {
        System.out.println(desc + ": ");
        Scanner sn = new Scanner(System.in);
        String input = new String();
        int num = -1;
        while(true) {
            try {
                input = sn.nextLine();
                num = Integer.parseInt(input);
                if (llim > num || num > hlim) {throw new IllegalArgumentException("Number must be >" + llim + "and <" + hlim);}
                return num;
            }
            catch (InputMismatchException | NumberFormatException ex) {
                ExceptionHandler.handleException(ex, logs);
            }
        }
    }

    public static boolean scanBoolean(String desc) {
        System.out.println(desc + ": ");
        Scanner sn = new Scanner(System.in);
        String input = new String();
        boolean num;
        while(true) {
            try {
                input = sn.nextLine();
                num = Boolean.parseBoolean(input);
                return num;
            }
            catch (InputMismatchException | NumberFormatException ex) {
                ExceptionHandler.handleException(ex, logs);
            }
        }
    }

    public static void addDrone(ArrayList<BaseDrone> drones) {
        System.out.println("""
                Choose drone type:
                1. QuadCopter.
                2. Plane.
                3. Helicopter.
                4. HybridDrone.
                """);
        int choice = Controller.scanInt("Write integer", 4, 1);
        switch (choice) {
            case 1 -> drones.add(new QuadCopter(Controller.scanDouble("Write maxRange"),
                    Controller.scanDouble("Write maxFlightTime"),
                    Controller.scanDouble("Write cruiseSpeed"),
                    Controller.scanDouble("Write maxSpeed"),
                    Controller.scanDouble("Write loadCapacity"),
                    Controller.scanDouble("Write maxHeight"),
                    Controller.scanString("Write powerType"),
                    Controller.scanDouble("Write size"),
                    Controller.scanString("Write takeOffMethod"),
                    Controller.scanBoolean("Write is weatherResistant?"),
                    Controller.scanString("Write camera resolution")));
            case 2 -> drones.add(new Plane(Controller.scanDouble("Write maxRange"),
                    Controller.scanDouble("Write maxFlightTime"),
                    Controller.scanDouble("Write cruiseSpeed"),
                    Controller.scanDouble("Write maxSpeed"),
                    Controller.scanDouble("Write loadCapacity"),
                    Controller.scanDouble("Write maxHeight"),
                    Controller.scanString("Write powerType"),
                    Controller.scanDouble("Write size"),
                    Controller.scanString("Write takeOffMethod"),
                    Controller.scanBoolean("Write is weatherResistant?"),
                    Controller.scanDouble("Write camera wingSpan")));
            case 3 -> drones.add(new Helicopter(Controller.scanDouble("Write maxRange"),
                    Controller.scanDouble("Write maxFlightTime"),
                    Controller.scanDouble("Write cruiseSpeed"),
                    Controller.scanDouble("Write maxSpeed"),
                    Controller.scanDouble("Write loadCapacity"),
                    Controller.scanDouble("Write maxHeight"),
                    Controller.scanString("Write powerType"),
                    Controller.scanDouble("Write size"),
                    Controller.scanString("Write takeOffMethod"),
                    Controller.scanBoolean("Write is weatherResistant?"),
                    Controller.scanInt("Write camera numberOfBlades", 64, 2)));
            case 4 -> drones.add(new HybridDrone(Controller.scanDouble("Write maxRange"),
                    Controller.scanDouble("Write maxFlightTime"),
                    Controller.scanDouble("Write cruiseSpeed"),
                    Controller.scanDouble("Write maxSpeed"),
                    Controller.scanDouble("Write loadCapacity"),
                    Controller.scanDouble("Write maxHeight"),
                    Controller.scanString("Write powerType"),
                    Controller.scanDouble("Write size"),
                    Controller.scanString("Write takeOffMethod"),
                    Controller.scanBoolean("Write is weatherResistant?"),
                    Controller.scanDouble("Write camera batteryCapacity")));
        }
    }

    public static void removeDrone(ArrayList<BaseDrone> drones) throws IOException {
        while (true) {
            View.printDrones(drones);
            int id = Controller.scanInt("Write ID of drone: ", drones.size(), 0);
            if (id > drones.size() || id < 0) {
                throw new IOException("No such drone in array.");
            }
            Logs.writeTime("Deleting drone by ID: " + id, logs);
            drones.remove(id);
            break;
        }
    }

    public static void autotestsStart() throws IOException {
        Autotests.testConstructorWithParameters();
        Autotests.testConstructorWithStringArray();
        Autotests.testGetType();
        Autotests.testToString();
        Autotests.testGetPurpose();
        Autotests.writeLogToFile();
    }
}