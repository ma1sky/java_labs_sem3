package View;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;

public class Fonts {
    static public Font SPAN = loadFont(Path.of("./fonts/Prompt-Regular.ttf"), Font.PLAIN, 16);
    static public Font BOLD = loadFont(Path.of("./fonts/Prompt-Bold.ttf"), Font.BOLD, 16);
    static public Font H1 = loadFont(Path.of("./fonts/Prompt-Bold.ttf"), Font.BOLD, 36);
    static public Font H2 = loadFont(Path.of("./fonts/Prompt-Bold.ttf"), Font.BOLD, 32);
    static public Font H3 = loadFont(Path.of("./fonts/Prompt-Bold.ttf"), Font.BOLD, 24);
    static public Font H4 = loadFont(Path.of("./fonts/Prompt-Bold.ttf"), Font.BOLD, 20);




    static public Font loadFont(Path path, int style, int size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, path.toFile()).deriveFont(style, (float) size);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("Arial", style, size);
        }
    }
}