import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public static void main() {
    String host = "smtp.yandex.ru";
    final String user = "agutalenco@yandex.ru";
    final String password = "rcbeehpunosioqdn";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(user, password);
                }
            });

    try {
        String recipient = scanString("Write address of recipient: ");
        String theme = scanString("Write theme of message: ");
        String messageText = scanString("Write your message: ");

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(theme);
        message.setText(messageText);

        Transport.send(message);

        System.out.println("Success!");
    } catch (MessagingException e) {
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