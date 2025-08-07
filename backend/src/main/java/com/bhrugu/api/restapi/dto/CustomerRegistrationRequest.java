package com.bhrugu.api.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object for customer registration
 * Matches the frontend registration form structure
 */
public class CustomerRegistrationRequest {
    
    @JsonProperty("fullName")
    private String fullName;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("confirmPassword")
    private String confirmPassword;
    
    // Default constructor
    public CustomerRegistrationRequest() {}
    
    // Constructor with all fields
    public CustomerRegistrationRequest(String fullName, String email, String password, String confirmPassword) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
    
    // Validation methods
    public boolean isValid() {
        return fullName != null && !fullName.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() && isValidEmail(email) &&
               password != null && password.length() >= 6 &&
               confirmPassword != null && password.equals(confirmPassword);
    }
    
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
    
    // Getters and setters
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    @Override
    public String toString() {
        return "CustomerRegistrationRequest{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", confirmPassword='[PROTECTED]'" +
                '}';
    }
}
