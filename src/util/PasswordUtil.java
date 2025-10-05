package util;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtil {
    // Genera un salt aleatorio
    public static byte[] generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    // Genera un hash PBKDF2
    public static String hashPassword(String password, byte[] salt) {
        int iterations = 65536;  
        int keyLength = 256;     

        try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();

            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            e.printStackTrace();  
            return null; 
        }
    }


    // Verifica si la contraseña ingresada coincide con el hash almacenado
    public static boolean verifyPassword(String password, String storedHash, byte[] storedSalt) {
        try {
            String newHash = hashPassword(password, storedSalt);
            return newHash.equals(storedHash);
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }


    // Convierte un salt a String (para almacenarlo en JSON, DB, etc.)
    public static String saltToBase64(byte[] salt) {
        return Base64.getEncoder().encodeToString(salt);
    }

    // Convierte un String Base64 a salt (cuando lees de la DB o JSON)
    public static byte[] base64ToSalt(String saltBase64) {
        return Base64.getDecoder().decode(saltBase64);
    }
}
