package com.frame.template.common.utils.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;

public class AesEncryptionUtil {

    private static final int AES_BLOCK_SIZE = 16;

    public static void main(String[] args) throws Exception {
        // data to encrypt
        //String plaintext = "Hello, world!";
        //System.out.println("Plaintext: " + plaintext);

        // key
        String secretKeyString = "3EDD438238C66CDED5E408C518C51B99";

        // encrypted data
        //String ciphertext = encrypt(plaintext, secretKeyString);
        String ciphertext = "OEyO+s+orVaTsTPB6ajIEn1JR5oDKaO4+GsoAnEXCXktgMtydXgg45Z2srDQI6izOBsin9mays0IOAuhbVa/9TPfmzvWIkHCWZDEynyO+2tvnDCgr038B7nRMdHTb5BZ";
        System.out.println("Ciphertext: " + ciphertext);

        // decrypt data
        String decrypted = decrypt(ciphertext, secretKeyString);
        System.out.println("Decrypted: " + decrypted);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Convert string to LocalDate type
        LocalDateTime date = LocalDateTime.parse("2023-03-24 11:15:00", formatter);
        // get the current date
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        System.out.println("now.isAfter(date) = " + now.isAfter(date));
        // Get the current timestamp
        Instant instant = Instant.now();
        // Convert Timestamp to ZonedDateTime object
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        System.out.println("localDateTime = " + localDateTime);
    }

    // Encrypt data using AES algorithm
    public static String encrypt(String plaintext, String secretKeyString) throws Exception {
        SecretKeySpec secretKey = generateSecretKey(secretKeyString);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // Calculate the number of bytes that need to be filled
        int padding = AES_BLOCK_SIZE - (plaintext.length() % AES_BLOCK_SIZE);
        // Create a filled data array, set the filling bytes to the number of bytes that need to be filled
        byte[] padded = Arrays.copyOf(plaintext.getBytes(StandardCharsets.UTF_8), plaintext.length() + padding);
        Arrays.fill(padded, plaintext.length(), padded.length, (byte) padding);
        // encrypt the data and return
        byte[] encrypted = cipher.doFinal(padded);
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Decrypt data using AES algorithm
    public static String decrypt(String ciphertext, String secretKeyString) throws Exception {
        SecretKeySpec secretKey = generateSecretKey(secretKeyString);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // Decode Base64 string
        byte[] encrypted = Base64.getDecoder().decode(ciphertext);
        // decrypt data
        byte[] decrypted = cipher.doFinal(encrypted);
        // unpopulate and return
        int padding = decrypted[decrypted.length - 1];
        return new String(Arrays.copyOfRange(decrypted, 0, decrypted.length - padding), StandardCharsets.UTF_8);
    }

    // Generate an AES key for the specified string
    private static SecretKeySpec generateSecretKey(String secretKeyString) throws Exception {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha256.digest(secretKeyString.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(keyBytes, "AES");
    }

}
