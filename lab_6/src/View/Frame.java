package View;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    static public ImageIcon icon = new ImageIcon("./images/icon.png");

    public Frame() {
        this.setTitle("UAVs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setIconImage(icon.getImage());
        this.setSize(new Dimension(1200, 720));
        this.setVisible(true);
        this.setBackground(Colors.DARK10);
        this.setLocationRelativeTo(null);
    }
    public void reload() {
        this.repaint();
        this.revalidate();
    }
}
