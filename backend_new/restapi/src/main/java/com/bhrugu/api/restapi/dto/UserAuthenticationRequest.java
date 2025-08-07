package com.bhrugu.api.restapi.dto;

/**
 * DTO for user authentication (login) requests
 * Contains username/email and password for authentication
 */
public class UserAuthenticationRequest {
    
    private String usernameOrEmail; // Can be either username or email
    private String password;
    
    // Default constructor
    public UserAuthenticationRequest() {}
    
    // Constructor
    public UserAuthenticationRequest(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }
    
    // Getters and Setters
    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }
    
    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
