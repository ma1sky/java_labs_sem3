package Controller;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Server extends Thread {
    private static final List<Server> clients = new ArrayList<>();
    private final Socket socket;
    private final BufferedReader in;
    private final BufferedWriter out;
    private String nickname;
    private static JTextArea chatArea;
    private static DefaultListModel<String> userListModel;

    public Server(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        start();
    }

    @Override
    public void run() {
        try {
            // Получаем никнейм клиента
            nickname = in.readLine();
            addClient(this);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received: " + message);
                // Добавляем сообщение в чат сервера с никнеймом
                appendChatMessage(nickname + ": " + message);
                sendMessageToAllClients(nickname + ": " + message);
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        } finally {
            removeClient(this);
            try {
                socket.close();
            } catch (IOException e) {
                e.fillInStackTrace();
            }
        }
    }

    private void addClient(Server client) {
        synchronized (clients) {
            clients.add(client);
            userListModel.addElement(nickname);
            appendChatMessage(nickname + " has joined the chat.");
        }
    }

    private void removeClient(Server client) {
        synchronized (clients) {
            clients.remove(client);
            userListModel.removeElement(nickname);
            appendChatMessage(nickname + " has left the chat.");
        }
    }

    private void sendMessageToAllClients(String message) {
        synchronized (clients) {
            for (Server client : clients) {
                try {
                    client.out.write(message);
                    client.out.newLine();
                    client.out.flush();
                } catch (IOException e) {
                    e.fillInStackTrace();
                }
            }
        }
    }

    public void disconnectClient() {
        removeClient(this);
        try {
            socket.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static void appendChatMessage(String message) {
        chatArea.append(message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);

        userListModel = new DefaultListModel<>();
        JList<String> userList = new JList<>(userListModel);
        JScrollPane userScrollPane = new JScrollPane(userList);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, chatScrollPane, userScrollPane);
        splitPane.setDividerLocation(400);
        frame.add(splitPane, BorderLayout.CENTER);

        JButton removeUserButton = new JButton("Remove User");
        removeUserButton.addActionListener(e -> {
            String selectedUser = userList.getSelectedValue();
            if (selectedUser != null) {
                // Находим клиента и отключаем его
                for (Server client : clients) {
                    if (client.nickname.equals(selectedUser)) {
                        client.disconnectClient(); // Отключаем клиента
                        break;
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeUserButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        // Запускаем сервер
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(12345)) {
                System.out.println("Server is listening on port 12345");
                while (true) {
                    new Server(serverSocket.accept());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}