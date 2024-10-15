import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class EncryptUtil {
    private static final String AES = "AES";
    private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";
    private static final int IV_LENGTH = 12;
    private static final int T_LEN = 96;
    private final Key key;

    public EncryptUtil(String pwdKey) {
        final byte[] decodedPwd = Base64.getDecoder().decode(pwdKey);
        this.key = new SecretKeySpec(decodedPwd, AES);
    }

    public byte[] encrypt(String cardNumber) {
        try {
            byte[] iv = new byte[IV_LENGTH];
            (new SecureRandom()).nextBytes(iv);

            Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
            GCMParameterSpec ivSpec = new GCMParameterSpec(T_LEN, iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

            byte[] ciphertext = cipher.doFinal(cardNumber.getBytes(StandardCharsets.UTF_8));
            byte[] encrypted = new byte[iv.length + ciphertext.length];
            System.arraycopy(iv, 0, encrypted, 0, iv.length);
            System.arraycopy(ciphertext, 0, encrypted, iv.length, ciphertext.length);

            return Base64.getEncoder().encode(encrypted);
        } catch (Exception e) {
            throw new CustomException("Error trying to encrypt credit card number", e);
        }
    }

    public String decrypt(byte[] encriptedCardNumber) {
        try {
            byte[] decoded = Base64.getDecoder().decode(encriptedCardNumber);

            byte[] iv = Arrays.copyOfRange(decoded ,0 , IV_LENGTH);

            Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
            GCMParameterSpec ivSpec = new GCMParameterSpec(T_LEN, iv);
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

            byte[] decryptedText = cipher.doFinal(Arrays.copyOfRange(decoded, IV_LENGTH, decoded.length));

            return new String(decryptedText, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new CustomException("Error trying to decode credit card number", e);
        }
    }
}
