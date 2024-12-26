import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

public static void main(String[] args) {
    String webPageUrl = "https://ru.wikipedia.org/wiki/Список_королей_Шотландии";
    String localFilePath = "downloaded_page.html";

    try {
        URL url = new URL(webPageUrl);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        FileWriter fileWriter = new FileWriter(localFilePath);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            fileWriter.write(inputLine + "\n");
        }

        in.close();
        fileWriter.close();
        System.out.println("Page downloaded in " + localFilePath);
    } catch (Exception e) {
        e.fillInStackTrace();
    }
}