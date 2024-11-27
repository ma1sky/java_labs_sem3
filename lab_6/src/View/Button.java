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
        this.setMinimumSize(new Dimension(190, 50));
        this.setMaximumSize(new Dimension(190, 50));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBorder(new EmptyBorder(1,1,1,1));
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Multiple Panels with CardLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        JPanel panel1 = createFirstPanel(cardLayout, cardPanel);
        JPanel panel2 = createSecondPanel(cardLayout, cardPanel);
        JPanel panel3 = createThirdPanel(cardLayout, cardPanel);

        cardPanel.add(panel1, "Panel 1");
        cardPanel.add(panel2, "Panel 2");
        cardPanel.add(panel3, "Panel 3");

        frame.add(cardPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createFirstPanel(CardLayout cardLayout, JPanel cardPanel) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(new JLabel("This is Panel 1"));
        JButton switchButton = new JButton("Go to Panel 2");
        switchButton.addActionListener(e -> cardLayout.show(cardPanel, "Panel 2"));
        panel.add(switchButton);
        return panel;
    }

    private static JPanel createSecondPanel(CardLayout cardLayout, JPanel cardPanel) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.add(new JLabel("This is Panel 2"));
        JButton switchButton1 = new JButton("Go to Panel 1");
        switchButton1.addActionListener(e -> cardLayout.show(cardPanel, "Panel 1")); // Переключаем на Панель 1
        JButton switchButton2 = new JButton("Go to Panel 3");
        switchButton2.addActionListener(e -> cardLayout.show(cardPanel, "Panel 3")); // Переключаем на Панель 3
        panel.add(switchButton1);
        panel.add(switchButton2);
        return panel;
    }

    private static JPanel createThirdPanel(CardLayout cardLayout, JPanel cardPanel) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.MAGENTA);
        panel.add(new JLabel("This is Panel 3"));
        JButton switchButton = new JButton("Go to Panel 1");
        switchButton.addActionListener(e -> cardLayout.show(cardPanel, "Panel 1")); // Переключаем на Панель 1
        panel.add(switchButton);
        return panel;
    }
}