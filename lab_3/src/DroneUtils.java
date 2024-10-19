import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DroneUtils {
    public static ArrayList<BaseDrone> createDroneArrayList() {
        return new ArrayList<BaseDrone>();
    }

    public static HashMap<Integer, BaseDrone> createDroneHashMap() {
        return new HashMap<Integer, BaseDrone>();
    }

    public static void fillDroneList(ArrayList<BaseDrone> droneList, int numberOfDrones) {
        Random random = new Random();

        for (int i = 0; i < numberOfDrones; i++) {
            droneList.add(createRandomDrone());
        }
    }

    public static void addDrone(ArrayList<BaseDrone> droneList){
        DroneUtils.fillDroneList(droneList, 1);
    }

    public static void removeDrone(ArrayList<BaseDrone> droneArrayList, int index) {
        droneArrayList.remove(index);
    }

    public static void fillDroneMap(HashMap<Integer, BaseDrone> droneMap, int numberOfDrones) {
        for (int i = 0; i < numberOfDrones; i++) {
            droneMap.put(i, createRandomDrone());
        }
    }

    public static void addHashDrone(HashMap<Integer, BaseDrone> droneMap, int id) {
        BaseDrone drone = createRandomDrone();
        droneMap.put(id, drone);
    }

    private static BaseDrone createRandomDrone() {
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

    public static void removeDrone(HashMap<Integer, BaseDrone> droneMap, int key) {
        droneMap.remove(key);
    }

}
