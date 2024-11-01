import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class User {
    private String login;
    private String password;
    private String group;
    private boolean debugging;
    private boolean autotests;

    public String getLogin() {
        return login;
    }
    public String getGroup() {
        return group;
    }
    public boolean isDebugging() {
        return debugging;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public void setDebugging(boolean debugging) {
        this.debugging = debugging;
    }
    public void setAutotests(boolean autotests) {
        this.autotests = autotests;
    }
    public boolean isAutotests() {
        return autotests;
    }

    public User(String[] settings) {
        this.login = settings[0].split("=")[1].trim();
        this.password = settings[1].split("=")[1].trim();
        this.group = settings[2].split("=")[1].trim();
        this.debugging = Boolean.parseBoolean(settings[3].split("=")[1].trim());
        this.autotests = Boolean.parseBoolean(settings[4].split("=")[1].trim());
    }

    public static String[] readSettingsFromFile(Path path) throws IOException {
        return Files.readAllLines(path).toArray(new String[0])[0].split(" ");
    }

    public void auth() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Login: ");
            if (this.login.equals(sc.nextLine())) {
                System.out.print("Password: ");
                if (this.password.equals(sc.nextLine())) {
                    break;
                } else {
                    System.out.println("Incorrect password. Try again.");
                }
            } else {
                System.out.println("Login doesnt exist. Try again.");
            }
        }
    }
}