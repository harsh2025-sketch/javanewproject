package com.nimbus.frs.util;

import java.security.SecureRandom;
import java.util.Base64;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Security utility for password hashing and validation
 */
public class SecurityUtil {
    
    /**
     * Hash password using SHA-256
     */
    public static String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }
    
    /**
     * Validate password against hashed password
     */
    public static boolean validatePassword(String password, String hashedPassword) {
        String inputHash = hashPassword(password);
        return inputHash.equals(hashedPassword);
    }
    
    /**
     * Generate random token for session management
     */
    public static String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
    
    /**
     * Sanitize input to prevent XSS
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        return input.replaceAll("<", "&lt;")
                   .replaceAll(">", "&gt;")
                   .replaceAll("\"", "&quot;")
                   .replaceAll("'", "&#x27;")
                   .replaceAll("/", "&#x2F;");
    }
    
    /**
     * Mask card number (show only last 4 digits)
     */
    public static String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        int length = cardNumber.length();
        return "**** **** **** " + cardNumber.substring(length - 4);
    }
}
