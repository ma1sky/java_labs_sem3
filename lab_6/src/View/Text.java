package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Text extends JLabel {
    public Text(Font font, String text, Color color){
        this.setFont(font);
        this.setText(text);
        this.setForeground(color);
        this.setBorder(new EmptyBorder(5,15,5,15));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}