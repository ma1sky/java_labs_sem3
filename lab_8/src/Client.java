import Model.BaseDrone;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.util.ArrayList;

private static final int num = 50;
private static Socket socket;
static ArrayList<BaseDrone> data = new ArrayList<>();
private static ObjectOutputStream out;
private static ObjectInputStream in;
private static SecretKey secretKey = null;
private static int PORT = 1984;
private static String IP = "127.0.0.1";

public static void main() {
    try {
        Thread.sleep(2000);
        socket = new Socket(InetAddress.getByName(IP), PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        PublicKey publicKey = (PublicKey) in.readObject();
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        secretKey = keyGen.generateKey();

        byte[] encryptedSecretKey = encryptAESKey(secretKey, publicKey);
        out.writeObject(encryptedSecretKey);
        out.flush();

        for (int i = 0; i < num; i++) {
            data.add(BaseDrone.createRandomDrone());
        }

        int totalParts = 50;
        int partSize = data.size() / totalParts;

        out.writeInt(totalParts);

        for (int i = 0; i < totalParts; i++) {
            int start = i * partSize;
            int end = (i == totalParts - 1) ? data.size() : start + partSize;
            ArrayList<BaseDrone> partData = new ArrayList<>(data.subList(start, end));

            byte[] serializedData = serialize(partData);
            byte[] checksum = computeChecksum(serializedData);
            byte[] encryptedData = encrypt(serializedData);

            out.writeObject(encryptedData);
            out.writeObject(checksum);
            out.flush();
        }

        out.close();
        socket.close();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

private static byte[] encrypt(byte[] data) throws IOException {
    try {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    } catch (Exception e) {
        throw new IOException("Encryption failed", e);
    }
}

private static byte[] computeChecksum(byte[] data) throws IOException {
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data);
    } catch (Exception e) {
        throw new IOException("Checksum computation failed", e);
    }
}

private static byte[] serialize(Object obj) throws IOException {
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
         ObjectOutputStream out = new ObjectOutputStream(bos)) {
        out.writeObject(obj);
        return bos.toByteArray();
    }
}

private static byte[] encryptAESKey(SecretKey secretKey, PublicKey publicKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return cipher.doFinal(secretKey.getEncoded());
}