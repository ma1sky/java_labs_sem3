import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.time.*;
import java.util.Collections;
import java.util.Random;


public class Logs {
    static Random random = new Random();
    static int addCounter = 0;
    static int removeCounter = 0;
    static double addTimer = 0;
    static double removeTimer = 0;
    static long timeStart = 0;
    static long timeEnd = 0;

    public static void writeArrayListLogs(ArrayList<BaseDrone> droneArrayList, int count) {
        removeCounter = 0;
        addTimer = 0;
        String fileName = "Array_List_Logs_" + count + ".txt";
        ArrayList<Long> durationsListAdds = new ArrayList<Long>();
        ArrayList<Long> durationsListRemoves = new ArrayList<Long>();
        int removes = count / 10;

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Start program: " + LocalDateTime.now() + '\n');
            writer.write("ArrayList with " + count + " items:" + '\n');
            for (int i = 0; i < count; i++) {
                timeStart = System.nanoTime();
                droneArrayList.add(Controller.createRandomDrone());
                timeEnd = System.nanoTime();
                durationsListAdds.add(timeEnd - timeStart);
                writer.write("Add, ID: " + i + " , Duration: " + durationsListAdds.get(i) + "ns" + '\n');
                addCounter++;
                addTimer += durationsListAdds.get(i);
            }
            writer.write("AddTotalCount: " + addCounter + '\n');
            writer.write("AddMedianTime: " + calcMedian(durationsListAdds) + '\n');
            writer.write("AddTotalTime: " + addTimer + '\n');

            for (int i = 0; i < removes; i++) {
                timeStart = System.nanoTime();
                droneArrayList.remove(random.nextInt(droneArrayList.size()));
                timeEnd = System.nanoTime();
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
            ExceptionHandler.logException(e, Path.of(fileName));
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
    public static void writeTime(String text, Path logs) throws IOException {
        if (Files.exists(logs)) {
            Files.writeString(logs, text + LocalDateTime.now().toString() + '\n', StandardOpenOption.APPEND);
        }
        else {
            Files.createFile(logs);
            Files.writeString(logs, text + LocalDateTime.now().toString() + '\n');
        }
    }
}
