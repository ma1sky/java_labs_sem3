import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Scanner;

public static void main() {
    String smtpHost = "smtp.yandex.ru";
    final String password = "rboevqtfkauyhwsx";
    final String user = "agutalenco@yandex.ru";
    String imapHost = "imap.yandex.ru";

    Scanner sc = new Scanner(System.in);

    System.out.println("1. Send email. \n2. Read emails. \n3. Exit.");

    while(true) {
        switch (Integer.parseInt(sc.nextLine())) {
            case 1 -> {
                sendEmail(smtpHost, user, password);
            }
            case 2 -> {
                readEmails(imapHost, user, password);
            }
            case 3 -> {
                System.exit(0);
            }
        }
    }

}

private static void sendEmail(String host, String user, String password) {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "587");

    String theme = scanString("Write theme: ");
    String email = scanString("Write recipient: ");
    String text = scanString("Write text: ");

    try {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(user, password);
                    }
                });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject(theme);
        message.setText(text);

        Transport.send(message);
        System.out.println("Success!");

    } catch (MessagingException e) {
        e.fillInStackTrace();
    }
}

private static void readEmails(String host, String user, String password) {
    Properties props = new Properties();
    props.put("mail.imap.ssl.enable", "true");

    try {
        Session session = Session.getInstance(props);
        Store store = session.getStore("imap");
        store.connect(host, user, password);

        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        Message[] messages = folder.getMessages();
        System.out.println("Количество сообщений: " + messages.length);

        for (Message message : messages) {
            System.out.println("Тема: " + message.getSubject());
            System.out.println("От: " + message.getFrom()[0]);
            System.out.println("Дата: " + message.getSentDate());
            System.out.println("---------");
        }

        folder.close(false);
        store.close();
    } catch (Exception e) {
        e.fillInStackTrace();
    }
}
public static String scanString(String desc) {
    Scanner sc = new Scanner(System.in);
    String str;
    System.out.print(desc + " ");
    while (true) {
        try{
            str = sc.nextLine();
            if (str.isEmpty()) {
                throw new IllegalArgumentException("\nLine is empty. Write something please.");
            }
            if (str.replaceAll("(?U)[\\pP\\s]", "").trim().isEmpty()) {
                throw new IllegalArgumentException("\nOnly symbols of punctuation.");
            } else { break; }
        } catch (IllegalArgumentException e) {
            e.fillInStackTrace();
        }

    }
    return str;
}