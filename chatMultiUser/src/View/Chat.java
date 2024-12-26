package View;

import javax.swing.*;

public class Chat extends JPanel {
    public JTextField textField;
    public JButton button;
    public JScrollPane messagePane;

    Chat() {
        textField = new JTextField();
        button = new JButton("Send");
        messagePane = new JScrollPane();
        messagePane.createVerticalScrollBar();
        messagePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        messagePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public void addMessage(String text) {
        messagePane.add(new JLabel("text"));
    }
}
