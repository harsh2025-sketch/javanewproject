package com.nimbus.frs.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Booking entity representing a flight booking
 */
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String bookingId;
    private String passengerId;
    private String flightId;
    private String seatNumber;
    private String travelClass; // ECONOMY or BUSINESS
    private double fare;
    private double taxes;
    private double totalAmount;
    private LocalDateTime bookingDate;
    private String status; // CONFIRMED, CANCELLED, PENDING
    private String paymentId;
    private String paymentStatus; // PAID, PENDING, FAILED, REFUNDED
    
    // Additional passenger details for display
    private Passenger passenger;
    private Flight flight;
    
    public Booking() {
    }
    
    public Booking(String bookingId, String passengerId, String flightId, 
                   String seatNumber, String travelClass, double fare) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.travelClass = travelClass;
        this.fare = fare;
        this.taxes = fare * 0.12; // 12% tax
        this.totalAmount = fare + taxes;
        this.bookingDate = LocalDateTime.now();
        this.status = "PENDING";
        this.paymentStatus = "PENDING";
    }
    
    // Getters and Setters
    public String getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
    
    public String getPassengerId() {
        return passengerId;
    }
    
    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }
    
    public String getFlightId() {
        return flightId;
    }
    
    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }
    
    public String getSeatNumber() {
        return seatNumber;
    }
    
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    
    public String getTravelClass() {
        return travelClass;
    }
    
    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }
    
    public double getFare() {
        return fare;
    }
    
    public void setFare(double fare) {
        this.fare = fare;
    }
    
    public double getTaxes() {
        return taxes;
    }
    
    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public LocalDateTime getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public String getPaymentStatus() {
        return paymentStatus;
    }
    
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public Passenger getPassenger() {
        return passenger;
    }
    
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    
    public Flight getFlight() {
        return flight;
    }
    
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    
    public String getFormattedBookingDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return bookingDate.format(formatter);
    }
    
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", passengerId='" + passengerId + '\'' +
                ", flightId='" + flightId + '\'' +
                ", travelClass='" + travelClass + '\'' +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
