package View;

import javax.swing.*;
import java.awt.*;

public class Auth extends JFrame {
    JTextField nicknameField;
    JTextField ipField;
    JButton connectButton;

    Auth() {
        nicknameField.setText("Your nickname");
        ipField.setText("Your IP");
        connectButton.setText("Connect");
        this.setTitle("UAVs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(new Dimension(1500, 920));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
    }
}
