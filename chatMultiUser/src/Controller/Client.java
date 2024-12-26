package Controller;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import javax.swing.*;

public class Client {
    private static final String SERVER_ADDRESS = "localhost"; // Адрес сервера
    private static final int SERVER_PORT = 12345; // Порт сервера
    private static Socket socket;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static JTextArea chatArea;
    private static String nickname;

    public static void main(String[] args) {
        // Установка графического интерфейса
        JFrame frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        frame.add(chatScrollPane, BorderLayout.CENTER);

        JTextField messageField = new JTextField();
        frame.add(messageField, BorderLayout.SOUTH);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                sendMessage(message);
                messageField.setText(""); // Очищаем поле ввода
            }
        });

        frame.add(sendButton, BorderLayout.EAST);

        frame.setVisible(true);

        // Запрашиваем никнейм у пользователя
        nickname = JOptionPane.showInputDialog(frame, "Enter your nickname: ");
        if (nickname == null || nickname.trim().isEmpty()) {
            nickname = "Anonymous"; // Установка никнейма по умолчанию
        }

        // Запуск клиента
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            // Отправляем никнейм на сервер
            out.write(nickname);
            out.newLine();
            out.flush();

            // Запуск потока для чтения сообщений от сервера
            new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        chatArea.append(message + "\n"); // Добавляем сообщение в текстовую область
                        chatArea.setCaretPosition(chatArea.getDocument().getLength()); // Прокручиваем к последнему сообщению
                    }
                } catch (IOException e) {
                    e.fillInStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private static void sendMessage(String message) {
        try {
            out.write(message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}