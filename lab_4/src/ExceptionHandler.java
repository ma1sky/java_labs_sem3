import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class ExceptionHandler {
    public static void logException(Exception e, Path fileName) {
        try (FileWriter writer = new FileWriter(String.valueOf(fileName), true)) {
            writer.write("Exception occurred at: " + LocalDateTime.now() + '\n');
            writer.write("Exception type: " + e.getClass().getName() + '\n');
            writer.write("Message: " + e.getMessage() + '\n');
            writer.write("Stack trace: " + '\n');
            for (StackTraceElement element : e.getStackTrace()) {
                writer.write("\tat " + element.toString() + '\n');
            }
            writer.write("---------------------------------------" + '\n');
        } catch (IOException ioException) {
            System.err.println("Error writing to log file: " + ioException.getMessage());
        }
    }

    public static void handleException(Exception e, Path fileName) {
        System.err.println("An exception occurred: " + e.getClass().getName());
        System.err.println("Message: " + e.getMessage());
        e.printStackTrace();
        logException(e, fileName);
    }
}