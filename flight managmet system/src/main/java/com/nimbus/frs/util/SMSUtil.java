package com.nimbus.frs.util;

/**
 * SMS utility for sending notifications (Mock implementation)
 * In production, integrate with SMS gateway like Twilio, AWS SNS, etc.
 */
public class SMSUtil {
    
    /**
     * Send SMS notification
     */
    public static boolean sendSMS(String phoneNumber, String message) {
        // Mock implementation - In production, integrate with SMS gateway
        System.out.println("SMS sent to " + phoneNumber + ": " + message);
        
        // Simulated SMS gateway call
        try {
            // Add your SMS gateway integration here
            // Example: Twilio, AWS SNS, or other provider
            return true;
        } catch (Exception e) {
            System.err.println("Failed to send SMS: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Send booking confirmation SMS
     */
    public static boolean sendBookingConfirmationSMS(String phoneNumber, String passengerName, 
                                                     String bookingId, String flightNumber, 
                                                     String departureTime) {
        String message = String.format(
            "Dear %s, Your booking %s for flight %s on %s is confirmed. Thank you! - Nimbus Airlines",
            passengerName, bookingId, flightNumber, departureTime
        );
        return sendSMS(phoneNumber, message);
    }
    
    /**
     * Send cancellation SMS
     */
    public static boolean sendCancellationSMS(String phoneNumber, String bookingId) {
        String message = String.format(
            "Your booking %s has been cancelled. Refund will be processed in 5-7 days. - Nimbus Airlines",
            bookingId
        );
        return sendSMS(phoneNumber, message);
    }
    
    /**
     * Send flight schedule change SMS
     */
    public static boolean sendScheduleChangeSMS(String phoneNumber, String flightNumber, 
                                                String changeDetails) {
        String message = String.format(
            "Flight %s schedule changed: %s. Check your email for details. - Nimbus Airlines",
            flightNumber, changeDetails
        );
        return sendSMS(phoneNumber, message);
    }
    
    /**
     * Send OTP for verification
     */
    public static boolean sendOTP(String phoneNumber, String otp) {
        String message = String.format(
            "Your OTP for Nimbus Airlines is: %s. Valid for 10 minutes. Do not share with anyone.",
            otp
        );
        return sendSMS(phoneNumber, message);
    }
}
