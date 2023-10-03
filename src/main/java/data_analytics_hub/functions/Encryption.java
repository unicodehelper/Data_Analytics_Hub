package data_analytics_hub.functions;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class Encryption implements IEncryption {

    private static Encryption instance = null;

    public Encryption() {
    }

    public static Encryption getInstance() {
        if (instance == null) {
            instance = new Encryption();
        }
        return instance;
    }

    @Override
    public String AESEncrypt(String str) {
        byte[] decodedKey = java.util.Base64.getDecoder().decode(key);
        SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, originalKey);
            byte[] cipherText = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while encrypting data", e);
        }
    }

    @Override
    public String AESDecrypt(String str) {
        byte[] decodedKey = java.util.Base64.getDecoder().decode(key);
        SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, originalKey);
            byte[] cipherText = cipher.doFinal(Base64.getDecoder().decode(str));
            return new String(cipherText, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while decrypting data", e);
        }
    }
}
