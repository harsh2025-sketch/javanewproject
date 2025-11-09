package com.nimbus.frs.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * User entity for authentication and authorization
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String userId;
    private String email;
    private String password; // Hashed password
    private String firstName;
    private String lastName;
    private String phone;
    private String role; // CUSTOMER or ADMIN
    private LocalDateTime registrationDate;
    private boolean active;
    
    public User() {
    }
    
    public User(String userId, String email, String password, String firstName, 
                String lastName, String role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.registrationDate = LocalDateTime.now();
        this.active = true;
    }
    
    // Getters and Setters
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }
    
    public boolean isCustomer() {
        return "CUSTOMER".equalsIgnoreCase(role);
    }
    
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
