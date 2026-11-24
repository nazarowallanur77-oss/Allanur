import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.security.spec.MGF1ParameterSpec;
import java.util.Base64;

public class RSA {
    public static void main(String[] args) throws Exception {

        KeyPairGenerator generator =
            KeyPairGenerator.getInstance("RSA");

        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();

        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        String message = "Salem Allanur";

        Cipher cipher = Cipher.getInstance(
            "RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

        OAEPParameterSpec spec = new OAEPParameterSpec(
            "SHA-256", "MGF1",
            MGF1ParameterSpec.SHA256,
            PSource.PSpecified.DEFAULT);

        cipher.init(Cipher.ENCRYPT_MODE, publicKey, spec);

        byte[] encrypted = cipher.doFinal(
            message.getBytes("UTF-8"));

        System.out.println("Encrypted: " +
            Base64.getEncoder().encodeToString(encrypted));

        cipher.init(Cipher.DECRYPT_MODE, privateKey, spec);

        byte[] decrypted = cipher.doFinal(encrypted);

        System.out.println("Decrypted: " +
            new String(decrypted, "UTF-8"));
    }
}
