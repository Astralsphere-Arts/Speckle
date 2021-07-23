package Internal;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author Astralsphere Arts
 */

public class Security {
    public static boolean checkPass(String password) {
        int i; char c;
        boolean hasNum = false, hasCap = false, hasLow = false;
        for (i = 0; i < password.length(); i++) {
            c = password.charAt(i);
            if (Character.isDigit(c))
                hasNum = true;
            if (Character.isUpperCase(c))
                hasCap = true;
            if (Character.isLowerCase(c))
                hasLow = true;
        }
        return hasNum && hasCap && hasLow;
    }
    
    public static String getEncodedString(String inputString) {
       return Base64.getEncoder().encodeToString(inputString.getBytes());
    }
    
    public static String getDecodedString(String encryptedString) {
        return new String(Base64.getDecoder().decode(encryptedString));
    }
    
    public static String generateHash(String inputString) {
        int iterations = 1000;
        byte[] salt = getSalt().getBytes();
        PBEKeySpec spec = new PBEKeySpec(inputString.toCharArray(), salt, iterations, 64 * 8);
        byte[] hash = Hash(spec);
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }
    
    public static boolean validateHash(String inputString, String storedHash) {
        String[] parts = storedHash.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
        PBEKeySpec spec = new PBEKeySpec(inputString.toCharArray(), salt, iterations, hash.length * 8);
        byte[] testHash = Hash(spec);
        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++)
            diff |= hash[i] ^ testHash[i];
        return diff == 0;
    }
    
    private static byte[] Hash(PBEKeySpec spec) {
        byte[] hash = null;
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            hash = skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return hash;
    }

    private static String getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Arrays.toString(salt);
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    private static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length ;i++)
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        return bytes;
    }
}
