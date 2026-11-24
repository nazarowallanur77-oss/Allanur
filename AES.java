import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AES {
    public static void main(String[] args) throws Exception {

        KeyGenerator generator =
            KeyGenerator.getInstance("AES");
        generator.init(256);

        SecretKey key = generator.generateKey();

        byte[] nonce = new byte[12];
        new SecureRandom().nextBytes(nonce);

        Cipher cipher =
            Cipher.getInstance("AES/GCM/NoPadding");

        cipher.init(Cipher.ENCRYPT_MODE, key,
            new GCMParameterSpec(128, nonce));

        String message = "Salem Allanur";

        byte[] encrypted = cipher.doFinal(
            message.getBytes("UTF-8"));

        System.out.println("Encrypted: " +
            Base64.getEncoder().encodeToString(encrypted));

        cipher.init(Cipher.DECRYPT_MODE, key,
            new GCMParameterSpec(128, nonce));

        byte[] decrypted = cipher.doFinal(encrypted);

        System.out.println("Decrypted: " +
            new String(decrypted, "UTF-8"));
    }
}
