import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException {
        //ArrayList<BaseDrone> DroneArrayList = DroneUtils.createDroneArrayList();
        HashMap<Integer, BaseDrone> droneMap = DroneUtils.createDroneHashMap();
        //DroneUtils.fillDroneList(DroneArrayList, 1000);
        Path path = Path.of("file.txt");
        //Logs.writeDataBase(DroneArrayList, path);
        ArrayList<BaseDrone> DroneArrayList = DroneUtils.readDataBase(path);
        for (BaseDrone drone : DroneArrayList) {
            System.out.println(drone.toString());
        }
    }
}