package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignInPanel extends JPanel{
    ImageIcon icon = new ImageIcon("./images/icon.png");
    JLabel iconCopter = new JLabel(icon);
    
    Text title = new Text(Fonts.H1, "Welcome back!", Colors.WHITE100);
    TextField loginField = new TextField("Login");
    PasswordField passwordField = new PasswordField("Password");

    Button submitButton = new Button("Sign in");
    Text messageLabel = new Text(Fonts.SPAN, "", Colors.WHITE100);

    public SignInPanel() {

        iconCopter.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setBackground(Colors.DARK10);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createVerticalGlue());
        iconCopter.setHorizontalAlignment(JLabel.CENTER);
        this.add(iconCopter);
        this.add(Spacings.create(Spacings.L));

        this.add(title);
        this.add(Spacings.create(Spacings.L));

        this.add(loginField);
        this.add(Spacings.create(Spacings.S));

        this.add(passwordField);
        this.add(Spacings.create(Spacings.M));

        this.add(submitButton);

        this.add(Spacings.create(Spacings.M));
        messageLabel.setForeground(Colors.RED);
        this.add(messageLabel);

        this.add(Box.createVerticalGlue());

        this.setVisible(true);
    }

    public String getLogin() {
        return loginField.getText();
    }

    public char[] getPassword() {
        return passwordField.getPassword();
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