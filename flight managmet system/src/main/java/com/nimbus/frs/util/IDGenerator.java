package com.nimbus.frs.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Utility for generating unique IDs
 */
public class IDGenerator {
    
    /**
     * Generate unique booking ID
     * Format: BKG-YYYYMMDDHHMMSS-XXXXX
     */
    public static String generateBookingId() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String random = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        return "BKG-" + timestamp + "-" + random;
    }
    
    /**
     * Generate unique flight ID
     * Format: FLT-XXXXX
     */
    public static String generateFlightId() {
        String random = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return "FLT-" + random;
    }
    
    /**
     * Generate unique passenger ID
     * Format: PSG-XXXXX
     */
    public static String generatePassengerId() {
        String random = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return "PSG-" + random;
    }
    
    /**
     * Generate unique user ID
     * Format: USR-XXXXX
     */
    public static String generateUserId() {
        String random = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return "USR-" + random;
    }
    
    /**
     * Generate unique payment ID
     * Format: PAY-YYYYMMDDHHMMSS-XXXXX
     */
    public static String generatePaymentId() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String random = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        return "PAY-" + timestamp + "-" + random;
    }
    
    /**
     * Generate transaction ID for payment gateway
     * Format: TXN-XXXXXXXXXXXXX
     */
    public static String generateTransactionId() {
        String random = UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
        return "TXN-" + random;
    }
}
