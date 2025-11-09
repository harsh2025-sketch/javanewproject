package com.nimbus.frs.util;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Email utility for sending notifications
 */
public class EmailUtil {
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String FROM_EMAIL = "nimbus.airlines@gmail.com";
    private static final String EMAIL_PASSWORD = "your-app-password"; // Use app-specific password
    
    /**
     * Send email notification
     */
    public static boolean sendEmail(String toEmail, String subject, String body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, EMAIL_PASSWORD);
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL, "Nimbus Airlines"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setContent(body, "text/html; charset=utf-8");
            
            Transport.send(message);
            System.out.println("Email sent successfully to: " + toEmail);
            return true;
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Send booking confirmation email
     */
    public static boolean sendBookingConfirmation(String toEmail, String passengerName, 
                                                   String bookingId, String flightNumber, 
                                                   String source, String destination, 
                                                   String departureTime, String seatNumber, 
                                                   String travelClass, double totalAmount) {
        String subject = "Booking Confirmation - " + bookingId;
        String body = buildBookingConfirmationEmail(passengerName, bookingId, flightNumber, 
                                                    source, destination, departureTime, 
                                                    seatNumber, travelClass, totalAmount);
        return sendEmail(toEmail, subject, body);
    }
    
    /**
     * Send cancellation confirmation email
     */
    public static boolean sendCancellationConfirmation(String toEmail, String passengerName, 
                                                        String bookingId, String flightNumber) {
        String subject = "Booking Cancellation - " + bookingId;
        String body = buildCancellationEmail(passengerName, bookingId, flightNumber);
        return sendEmail(toEmail, subject, body);
    }
    
    /**
     * Send flight schedule change notification
     */
    public static boolean sendScheduleChangeNotification(String toEmail, String passengerName, 
                                                          String bookingId, String flightNumber, 
                                                          String changeDetails) {
        String subject = "Flight Schedule Change - " + flightNumber;
        String body = buildScheduleChangeEmail(passengerName, bookingId, flightNumber, changeDetails);
        return sendEmail(toEmail, subject, body);
    }
    
    private static String buildBookingConfirmationEmail(String passengerName, String bookingId, 
                                                        String flightNumber, String source, 
                                                        String destination, String departureTime, 
                                                        String seatNumber, String travelClass, 
                                                        double totalAmount) {
        return "<!DOCTYPE html><html><head><style>" +
               "body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }" +
               ".container { max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; }" +
               ".header { background: #0056b3; color: white; padding: 20px; text-align: center; }" +
               ".content { padding: 20px; }" +
               ".booking-details { background: #f8f9fa; padding: 15px; margin: 15px 0; border-left: 4px solid #0056b3; }" +
               ".footer { text-align: center; padding: 20px; color: #666; font-size: 12px; }" +
               "</style></head><body>" +
               "<div class='container'>" +
               "<div class='header'><h1>✈️ Nimbus Airlines</h1></div>" +
               "<div class='content'>" +
               "<h2>Booking Confirmed!</h2>" +
               "<p>Dear " + passengerName + ",</p>" +
               "<p>Your flight booking has been confirmed. Here are your booking details:</p>" +
               "<div class='booking-details'>" +
               "<p><strong>Booking ID:</strong> " + bookingId + "</p>" +
               "<p><strong>Flight Number:</strong> " + flightNumber + "</p>" +
               "<p><strong>Route:</strong> " + source + " → " + destination + "</p>" +
               "<p><strong>Departure:</strong> " + departureTime + "</p>" +
               "<p><strong>Seat Number:</strong> " + seatNumber + "</p>" +
               "<p><strong>Class:</strong> " + travelClass + "</p>" +
               "<p><strong>Total Amount:</strong> ₹" + String.format("%.2f", totalAmount) + "</p>" +
               "</div>" +
               "<p>Please arrive at the airport at least 2 hours before departure.</p>" +
               "<p>Thank you for choosing Nimbus Airlines!</p>" +
               "</div>" +
               "<div class='footer'>" +
               "<p>This is an automated email. Please do not reply.</p>" +
               "<p>&copy; 2025 Nimbus Airlines. All rights reserved.</p>" +
               "</div></div></body></html>";
    }
    
    private static String buildCancellationEmail(String passengerName, String bookingId, 
                                                 String flightNumber) {
        return "<!DOCTYPE html><html><head><style>" +
               "body { font-family: Arial, sans-serif; }" +
               ".container { max-width: 600px; margin: 0 auto; padding: 20px; }" +
               "</style></head><body>" +
               "<div class='container'>" +
               "<h2>Booking Cancelled</h2>" +
               "<p>Dear " + passengerName + ",</p>" +
               "<p>Your booking (ID: " + bookingId + ") for flight " + flightNumber + 
               " has been cancelled successfully.</p>" +
               "<p>Refund will be processed within 5-7 business days.</p>" +
               "<p>Thank you,<br>Nimbus Airlines</p>" +
               "</div></body></html>";
    }
    
    private static String buildScheduleChangeEmail(String passengerName, String bookingId, 
                                                   String flightNumber, String changeDetails) {
        return "<!DOCTYPE html><html><head><style>" +
               "body { font-family: Arial, sans-serif; }" +
               ".container { max-width: 600px; margin: 0 auto; padding: 20px; }" +
               ".alert { background: #fff3cd; padding: 15px; border-left: 4px solid #ffc107; }" +
               "</style></head><body>" +
               "<div class='container'>" +
               "<h2>Flight Schedule Change</h2>" +
               "<p>Dear " + passengerName + ",</p>" +
               "<div class='alert'>" +
               "<p><strong>Important Notice:</strong> Your flight schedule has been updated.</p>" +
               "</div>" +
               "<p><strong>Booking ID:</strong> " + bookingId + "</p>" +
               "<p><strong>Flight Number:</strong> " + flightNumber + "</p>" +
               "<p><strong>Change Details:</strong> " + changeDetails + "</p>" +
               "<p>We apologize for any inconvenience caused.</p>" +
               "<p>Thank you,<br>Nimbus Airlines</p>" +
               "</div></body></html>";
    }
}
