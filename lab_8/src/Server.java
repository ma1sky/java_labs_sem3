import Model.BaseDrone;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.ArrayList;

private static Socket clientSocket;
private static ServerSocket server;
private static ObjectInputStream in;
private static ObjectOutputStream out;
private static ArrayList<BaseDrone> data = new ArrayList<>();

public static void main() {
    try {
        server = new ServerSocket(1984);
        System.out.println("Server started!");

        clientSocket = server.accept();
        in = new ObjectInputStream(clientSocket.getInputStream());
        out = new ObjectOutputStream(clientSocket.getOutputStream());

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        out.writeObject(publicKey);
        out.flush();
        byte[] encryptedSecretKey = (byte[]) in.readObject();
        SecretKey secretKey = decryptAESKey(encryptedSecretKey, privateKey);

        receiveData(secretKey);

    } catch (IOException e) {
        e.fillInStackTrace();
    } catch (Exception e) {
        throw new RuntimeException(e);
    } finally {
        try {
            if (clientSocket != null) clientSocket.close();
            if (in != null) in.close();
            if (out != null) out.close();
            if (server != null) server.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}

private static void receiveData(SecretKey secretKey) throws IOException {
    try {
        int totalParts = in.readInt(); // Читаем количество частей
        for (int i = 0; i < totalParts; i++) {
            byte[] encryptedData = (byte[]) in.readObject(); // Читаем зашифрованные данные
            byte[] checksum = (byte[]) in.readObject(); // Читаем контрольную сумму

            // Дешифрование
            byte[] decryptedData = decrypt(encryptedData, secretKey);

            // Проверка контрольной суммы
            if (MessageDigest.isEqual(checksum, computeChecksum(decryptedData))) {
                ArrayList<BaseDrone> drones = (ArrayList<BaseDrone>) deserialize(decryptedData);
                data.addAll(drones);
                System.out.println("Drones received and verified!");
            } else {
                System.out.println("Checksum mismatch for part " + i);
            }
        }
        for (int j = 0; j < data.size(); j++) {
            System.out.println(j);
            System.out.println(data.get(j).toString());
        }
    } catch (ClassNotFoundException e) {
        e.fillInStackTrace();
    }
}

private static byte[] decrypt(byte[] data, SecretKey secretKey) throws IOException {
    try {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    } catch (Exception e) {
        throw new IOException("Decryption failed", e);
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

private static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
    try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
         ObjectInputStream in = new ObjectInputStream(bis)) {
        return in.readObject();
    }
}

private static SecretKey decryptAESKey(byte[] encryptedAESKey, PrivateKey privateKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] decryptedKeyBytes = cipher.doFinal(encryptedAESKey);
    return new javax.crypto.spec.SecretKeySpec(decryptedKeyBytes, "AES");
}