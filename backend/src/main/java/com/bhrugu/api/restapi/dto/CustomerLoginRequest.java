package com.bhrugu.api.restapi.dto;

/**
 * Data Transfer Object for customer login requests
 * Used to capture login form data from frontend
 */
public class CustomerLoginRequest {
    
    private String username; // Can be email or username
    private String password;
    
    // Default constructor
    public CustomerLoginRequest() {}
    
    // Constructor with parameters
    public CustomerLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Getters and setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Validates the login request data
     * @return true if all required fields are present and valid
     */
    public boolean isValid() {
        return username != null && !username.trim().isEmpty() &&
               password != null && !password.trim().isEmpty();
    }
    
    @Override
    public String toString() {
        return "CustomerLoginRequest{" +
                "username='" + username + '\'' +
                ", password='[HIDDEN]'" +
                '}';
    }
}
