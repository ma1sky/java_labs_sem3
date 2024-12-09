package View;

import javax.swing.*;
import java.awt.*;

public class GeneralView extends JFrame {
    static public ImageIcon icon = new ImageIcon("./images/icon.png");
    CardLayout layout = new CardLayout();
    public SignInPanel signInPanel = new SignInPanel();
    public WorkPanel workPanel = new WorkPanel();

    public GeneralView() {
        this.setTitle("UAVs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setIconImage(icon.getImage());
        this.setSize(new Dimension(1200, 720));
        this.setBackground(Colors.DARK10);
        this.setLocationRelativeTo(null);

        this.setLayout(layout);
        this.add(signInPanel,"SignInPanel");
        JScrollPane scrollWorkPane = new JScrollPane(workPanel);
        scrollWorkPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollWorkPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollWorkPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollWorkPane, "workPanel");

        this.setVisible(true);
    }

    public void startWorkPanel() {
        layout.show(this.getContentPane(), "workPanel");
    }
}
