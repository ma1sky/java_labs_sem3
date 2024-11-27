package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class TextField extends JTextField implements FocusListener {
    private final String example;

    CompoundBorder borderIdle = new CompoundBorder(
            new LineBorder(Colors.DARK30, 2),
            new EmptyBorder(10,10,10,10)
    );

    CompoundBorder borderActive = new CompoundBorder(
            new LineBorder(Colors.ACCENT50, 2),
            new EmptyBorder(10,10,10,10)
    );

    TextField(String example) {
        this.example = example;
        this.setPreferredSize(new Dimension(190, 50));
        this.setMinimumSize(new Dimension(190, 50));
        this.setMaximumSize(new Dimension(190, 50));
        this.setText(example);
        this.setFont(Fonts.SPAN);
        this.setBackground(Colors.DARK10);
        this.setForeground(Colors.DARK30);
        this.addFocusListener(this);
        this.setBorder(borderIdle);
        this.setCaretColor(Colors.WHITE100);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.setBorder(borderActive);
        if (this.getText().equals(example)) {
            this.setText("");
            this.setForeground(Colors.WHITE100);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        this.setBorder(borderIdle);
        if (this.getText().isEmpty()) {
            this.setForeground(Colors.DARK30);
            this.setText(example);
        }
    }
}


