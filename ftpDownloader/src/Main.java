import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public static void main() {
    String ftpServer = "ftp.mozilla.org";
    String user = "anonymous";
    String password = "user@example.com";
    String remoteFilePath = "/pub/artwork/eps_files_3.zip";
    String localFilePath = "downloaded_readme.txt";

    FTPClient ftpClient = new FTPClient();
    try {
        ftpClient.connect(ftpServer);
        ftpClient.login(user, password);
        //ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        OutputStream outputStream = new FileOutputStream(localFilePath);

        boolean success = ftpClient.retrieveFile(remoteFilePath, outputStream);
        outputStream.close();

        if (success) {
            System.out.println("File downloaded successfull in" + localFilePath);
        } else {
            System.out.println("Error.");
        }

        ftpClient.logout();
    } catch (IOException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}