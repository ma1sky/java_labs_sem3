package Model;

import Controller.GeneralController;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import static Controller.GeneralController.readSettings;

public class Model {
    public static Path logs = Path.of("logs.txt");
    public Path dataPath = Path.of("dataBase.bin");
    Path settingsPath = Path.of("settings.txt");
    private User user;

    public ArrayList<BaseDrone> data;

    public Model() throws IOException, ClassNotFoundException {

        this.user = new User(readSettings(settingsPath));
        this.data = GeneralController.readDataBase(dataPath);
    }
    public void saveData() {

    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public ArrayList<BaseDrone> getData() {
        return data;
    }
    public void setData(ArrayList<BaseDrone> data) {
        this.data = data;
    }
}
