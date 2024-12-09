package Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.time.*;
import java.util.Random;


public class Logs {
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