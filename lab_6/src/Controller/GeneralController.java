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

    public static void autotestsStart() throws IOException {
        Autotests.testConstructorWithParameters();
        Autotests.testConstructorWithStringArray();
        Autotests.testGetType();
        Autotests.testToString();
        Autotests.testGetPurpose();
        Autotests.writeLogToFile();
    }
    public void startSigningIn() {
        new SignInController(this.model, this.view.signInPanel, this);
    }
    public void startWorkPanel() throws IOException, ClassNotFoundException {
        this.view.startWorkPanel();
        new WorkPanelController(this.model, this.view.workPanel, this);
        model.setData(readDataBase(model.dataPath));
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
