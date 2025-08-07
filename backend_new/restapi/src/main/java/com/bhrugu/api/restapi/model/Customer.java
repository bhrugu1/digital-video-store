package com.bhrugu.api.restapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

/**
 * Customer Document Entity for MongoDB
 * Represents registered users in the StreamVault digital video store
 * 
 * MongoDB Document Structure:
 * - Collection: customers
 * - Database: streamvault-db
 */
@Document(collection = "customers")
public class Customer {
    
    /**
     * Unique identifier for each customer
     * MongoDB ObjectId as string
     */
    @Id
    private String id;
    
    /**
     * Full name of the customer
     * Required field from registration form
     */
    @Field("fullName")
    private String fullName;
    
    /**
     * Email address of the customer
     * Must be unique and valid email format
     * Used for login and communication
     */
    @Field("email")
    @Indexed(unique = true)
    private String email;
    
    /**
     * Encrypted password using BCrypt
     * Never returned in API responses (JsonIgnore)
     */
    @Field("password")
    @JsonIgnore
    private String password;
    
    /**
     * Account creation timestamp
     */
    @Field("createdAt")
    private LocalDateTime createdAt;
    
    /**
     * Last login timestamp
     */
    @Field("lastLogin")
    private LocalDateTime lastLogin;
    
    /**
     * Account status (active, suspended, etc.)
     */
    @Field("status")
    private String status;
    
    // Default constructor
    public Customer() {
        this.createdAt = LocalDateTime.now();
        this.status = "active";
    }
    
    // Constructor with required fields
    public Customer(String fullName, String email, String password) {
        this();
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
    
    // Getters and setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
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
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", status='" + status + '\'' +
                '}';
    }
}
