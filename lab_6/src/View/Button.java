package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Button extends JButton{
    Button(String text){
        this.setFont(Fonts.BOLD);
        this.setBackground(Colors.WHITE100);
        this.setForeground(Colors.DARK10);
        this.setText(text);
        this.setFocusable(false);
        this.setPreferredSize(new Dimension(190, 50));
        this.setMinimumSize(new Dimension(100, 30));
        this.setMaximumSize(new Dimension(190, 50));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBorder(new EmptyBorder(1,1,1,1));
    }
}