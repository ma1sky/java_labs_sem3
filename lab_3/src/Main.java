import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        ArrayList<BaseDrone> DroneArrayList = DroneUtils.createDroneArrayList();
        int[] itemsC = {10, 100, 1000, 10000};
        for(int item : itemsC) {
            Logs.writeArrayListLogs(DroneArrayList, item);
        }
    }
}