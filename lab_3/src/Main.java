import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        ArrayList<BaseDrone> DroneArrayList = DroneUtils.createDroneArrayList();
        HashMap<Integer, BaseDrone> droneMap = DroneUtils.createDroneHashMap();
        int[] itemsC = {10, 100, 1000, 10000};
        for (int item : itemsC) {
            Logs.writeArrayListLogs(DroneArrayList, item);
            Logs.writeHashMapLogs(droneMap, item);
        }
    }
}