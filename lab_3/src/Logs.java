import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.time.*;
import java.util.Collections;
import java.util.Random;


public class Logs {
    public static void writeArrayListLogs(ArrayList<BaseDrone> droneArrayList, int count) {
        String fileName = "Array_List_Logs_" + count + ".txt";
        ArrayList<Long> durationsListAdds = new ArrayList<Long>();
        ArrayList<Long> durationsListRemoves = new ArrayList<Long>();

        Random random = new Random();
        int addCounter = 0;
        int removeCounter = 0;
        int removes = count / 10;
        double addTimer = 0;
        double removeTimer = 0;
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Start program: " + LocalDateTime.now() + '\n');
            writer.write("ArrayList with " + count + " items:" + '\n');
            for (int i = 0; i < count; i++) {
                long timeStart = System.nanoTime();
                DroneUtils.addDrone(droneArrayList);
                long timeEnd = System.nanoTime();
                durationsListAdds.add(timeEnd - timeStart);
                writer.write("Add, ID: " + i + " , Duration: " + durationsListAdds.get(i) + "ns" + '\n');
                addCounter++;
                addTimer += durationsListAdds.get(i);
            }
            writer.write("AddTotalCount: " + addCounter + '\n');
            writer.write("AddMedianTime: " + calcMedian(durationsListAdds) + '\n');
            writer.write("AddTotalTime: " + addTimer + '\n');

            for (int i = 0; i < removes; i++) {
                long timeStart = System.nanoTime();
                DroneUtils.removeDrone(droneArrayList, random.nextInt(droneArrayList.size()));
                long timeEnd = System.nanoTime();
                durationsListRemoves.add(timeEnd - timeStart);

                writer.write("Remove, ID: " + i + " , Duration: " + durationsListRemoves.get(i) + "ns" + '\n');
                removeCounter++;
                removeTimer += durationsListRemoves.get(i);
            }
            writer.write("RemoveTotalCount: " + removeCounter + '\n');
            writer.write("RemoveMedianTime: " + calcMedian(durationsListRemoves) + '\n');
            writer.write("RemoveTotalTime: " + removeTimer + '\n');
            writer.write("Finish program: " + LocalDateTime.now());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static double calcMedian(ArrayList<Long> list) {
        Collections.sort(list);

        int size = list.size();
        if (size % 2 == 1) {
            return list.get(size / 2);
        } else {
            double middle1 = list.get((size / 2) - 1);
            double middle2 = list.get(size / 2);
            return (middle1 + middle2) / 2.0;
        }
    }
}
