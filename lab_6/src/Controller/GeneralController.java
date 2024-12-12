package Controller;

import Model.Model;
import View.GeneralView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import Model.QuadCopter;
import Model.Plane;
import Model.Helicopter;
import Model.HybridDrone;

import Model.BaseDrone;

import static Model.Model.logs;

public class GeneralController {

    GeneralView view;
    Model model;

    public GeneralController(GeneralView view, Model model) {
        this.view = view;
        this.model = model;
        startSigningIn();
        save();
    }

    public void startSigningIn() {
        new SignInController(this.model, this.view.signInPanel, this);
    }

    public void startWorkPanel() {
        this.view.startWorkPanel();
        new WorkPanelController(this.model, this.view.workPanel, this);
    }

    public void save() {
        this.view.saveOnClose(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    GeneralController.saveDataBase(model.getData(), model.dataPath);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static ArrayList<BaseDrone> readDataBase(Path path) throws IOException, ClassNotFoundException {
        ArrayList<BaseDrone> droneArrayList = new ArrayList<>();

        if (Files.exists(path)) {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
                while (true) {
                    try {
                        BaseDrone drone = (BaseDrone) ois.readObject();
                        droneArrayList.add(drone);
                    } catch (EOFException e) {
                        break;
                    }
                }
            }
        } else {
            throw new IOException("File doesnt exist.");
        }

        return droneArrayList;
    }
    public static void saveDataBase(ArrayList<BaseDrone> droneArrayList, Path path) throws IOException {
        if (Files.exists(path)) {
            Files.delete(path);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            for (BaseDrone drone : droneArrayList) {
                oos.writeObject(drone);
            }
        }

        Logs.writeTime("DataBase writing successful.", logs);
    }
    public static String[] readSettings(Path path) throws IOException {
        return Files.readAllLines(path).toArray(new String[0])[0].split(" ");
    }
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

    public static boolean validateBool(String data) {
        if (data == null) {
            return false;
        }
        String trimmedData = data.trim();
        return trimmedData.equalsIgnoreCase("true") || trimmedData.equalsIgnoreCase("false");
    }
    public static boolean validateString(String data, int minLength, int maxLength) {
        if (data == null || data.trim().isEmpty()) {
            return false;
        }
        String trimmedData = data.trim();
        for (char c : trimmedData.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }
    public static boolean validateDouble(String data) {
        if (data == null) {
            return false;
        }

        try {
            Double.parseDouble(data.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean validateInt(String data) {
        if (data == null) {
            return false;
        }

        try {
            Integer.parseInt(data.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
