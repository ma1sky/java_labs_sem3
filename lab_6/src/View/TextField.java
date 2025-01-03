package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextField extends JTextField implements FocusListener {
    private String example;

    private final CompoundBorder borderIdle = new CompoundBorder(
            new LineBorder(Colors.DARK30, 2),
            new EmptyBorder(10, 10, 10, 10)
    );

    private final CompoundBorder borderActive = new CompoundBorder(
            new LineBorder(Colors.ACCENT50, 2),
            new EmptyBorder(10, 10, 10, 10)
    );

    public TextField(String example) {
        this.example = example;
        this.setPreferredSize(new Dimension(190, 50));
        this.setMinimumSize(new Dimension(190, 50));
        this.setMaximumSize(new Dimension(190, 50));
        this.setText(example);
        this.setFont(Fonts.SPAN);
        this.setBackground(Colors.DARK10);
        this.setForeground(Colors.DARK30);
        this.setBorder(borderIdle);
        this.setCaretColor(Colors.WHITE100);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.setBorder(borderActive);
        if (isExampleText()) {
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

    private boolean isExampleText() {
        return this.getText().equals(example);
    }

    private boolean isEmpty() {
        return this.getText().isEmpty();
    }

    private void clearField() {
        this.setText("");
        this.setForeground(Colors.WHITE100);
    }

    public void resetField() {
        this.setForeground(Colors.DARK30);
        this.setText(example);
    }

    public void setExample(String example) {
        this.setForeground(Colors.DARK30);
        this.example = example;
    }
}