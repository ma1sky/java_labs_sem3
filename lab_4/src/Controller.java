import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
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

        return droneArrayList;
    }

}
