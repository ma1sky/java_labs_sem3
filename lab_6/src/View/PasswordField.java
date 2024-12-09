package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PasswordField extends JPasswordField implements FocusListener {
    private final String example;

    private final CompoundBorder borderIdle = new CompoundBorder(
            new LineBorder(Colors.DARK30, 2),
            new EmptyBorder(10, 10, 10, 10)
    );

    private final CompoundBorder borderActive = new CompoundBorder(
            new LineBorder(Colors.ACCENT50, 2),
            new EmptyBorder(10, 10, 10, 10)
    );

    public PasswordField(String example) {
        this.example = example;
        configureField();
    }

    private void configureField() {
        this.setPreferredSize(new Dimension(190, 50));
        this.setMinimumSize(new Dimension(190, 50));
        this.setMaximumSize(new Dimension(190, 50));
        this.setText(example);
        this.setFont(Fonts.SPAN);
        this.setBackground(Colors.DARK10);
        this.setForeground(Colors.DARK30);
        this.setBorder(borderIdle);
        this.setEchoChar('*');
        this.setCaretColor(Colors.WHITE100);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.setBorder(borderActive);
        if (isExamplePassword()) {
            clearField();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        this.setBorder(borderIdle);
        if (isEmpty()) {
            resetField();
        }
    }

    private boolean isExamplePassword() {
        return new String(this.getPassword()).equals(example);
    }

    private boolean isEmpty() {
        return this.getPassword().length == 0;
    }

    private void clearField() {
        this.setText("");
        this.setForeground(Colors.WHITE100);
    }

    private void resetField() {
        this.setForeground(Colors.DARK30);
        this.setText(example);
    }
}