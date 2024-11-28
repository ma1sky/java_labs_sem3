package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignInFrame {
    ImageIcon icon = new ImageIcon("./images/icon.png");
    JLabel iconCopter = new JLabel(icon);

    public Frame frame = new Frame();
    JPanel panel = new JPanel();

    Text title = new Text(Fonts.H1, "Welcome back!", Colors.WHITE100);
    TextField loginField = new TextField("Login");
    TextField passwordField = new TextField("Password");

    Button submitButton = new Button("Sign in");
    Text messageLabel = new Text(Fonts.SPAN, "", Colors.WHITE100);

    public SignInFrame() {

        iconCopter.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.setBackground(Colors.DARK10);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalGlue());
        iconCopter.setHorizontalAlignment(JLabel.CENTER);
        panel.add(iconCopter);
        panel.add(Spacings.create(Spacings.L));

        panel.add(title);
        panel.add(Spacings.create(Spacings.L));

        panel.add(loginField);
        panel.add(Spacings.create(Spacings.S));

        panel.add(passwordField);
        panel.add(Spacings.create(Spacings.M));

        panel.add(submitButton);

        panel.add(Spacings.create(Spacings.M));
        messageLabel.setForeground(Colors.RED);
        panel.add(messageLabel);

        panel.add(Box.createVerticalGlue());

        frame.add(panel);

        frame.setVisible(true);
    }

    public String getLogin() {
        return loginField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    public void setMessageColor(Color color) {
        this.messageLabel.setForeground(color);
    }

    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
}