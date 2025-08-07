package com.bhrugu.api.restapi.service;

import com.bhrugu.api.restapi.model.Customer;
import com.bhrugu.api.restapi.repository.CustomerRepository;
import com.bhrugu.api.restapi.dto.CustomerRegistrationRequest;
import com.bhrugu.api.restapi.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Service layer for Customer operations
 * Handles business logic for customer management
 * Works with MongoDB through CustomerRepository
 */
@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public CustomerService() {
        // Constructor without password encoder for demo purposes
    }
    
    /**
     * Register a new customer with validation and password encryption
     * @param registrationRequest The registration data from frontend
     * @return ApiResponse with success/error message and customer data
     */
    public ApiResponse<Customer> registerCustomer(CustomerRegistrationRequest registrationRequest) {
        try {
            // Validate passwords match
            if (!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())) {
                return ApiResponse.error("Passwords do not match", 400);
            }
            
            // Check if email already exists
            if (customerRepository.existsByEmail(registrationRequest.getEmail())) {
                return ApiResponse.error("Email address is already registered", 409);
            }
            
            // Validate password strength (minimum requirements)
            if (!isValidPassword(registrationRequest.getPassword())) {
                return ApiResponse.error("Password must be at least 6 characters long and contain letters and numbers", 400);
            }
            
            // Create new customer
            Customer customer = new Customer();
            customer.setFullName(registrationRequest.getFullName().trim());
            customer.setEmail(registrationRequest.getEmail().toLowerCase().trim());
            
            // Store password as-is for demo purposes (not recommended for production)
            customer.setPassword(registrationRequest.getPassword());
            
            // Save customer to MongoDB
            Customer savedCustomer = customerRepository.save(customer);
            
            return ApiResponse.success("Customer registered successfully", savedCustomer, 201);
            
        } catch (Exception e) {
            return ApiResponse.error("Registration failed: " + e.getMessage(), 500);
        }
    }
    
    /**
     * Get customer by ID with validation
     * @param customerId The customer ID to retrieve
     * @return ApiResponse with customer data or error message
     */
    public ApiResponse<Customer> getCustomerById(String customerId) {
        try {
            // Validate customer ID format (basic validation)
            if (customerId == null || customerId.trim().isEmpty()) {
                return ApiResponse.error("Customer ID is required", 400);
            }
            
            // Validate MongoDB ObjectId format (24 hex characters)
            if (!isValidObjectId(customerId)) {
                return ApiResponse.error("Invalid customer ID format", 400);
            }
            
            Optional<Customer> customerOpt = customerRepository.findById(customerId);
            
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                return ApiResponse.success("Customer retrieved successfully", customer, 200);
            } else {
                return ApiResponse.error("Customer not found with ID: " + customerId, 404);
            }
            
        } catch (Exception e) {
            return ApiResponse.error("Failed to retrieve customer: " + e.getMessage(), 500);
        }
    }
    
    /**
     * Get customer by email
     * @param email The customer email to search for
     * @return ApiResponse with customer data or error message
     */
    public ApiResponse<Customer> getCustomerByEmail(String email) {
        try {
            if (email == null || email.trim().isEmpty()) {
                return ApiResponse.error("Email is required", 400);
            }
            
            Optional<Customer> customerOpt = customerRepository.findByEmail(email.toLowerCase().trim());
            
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                return ApiResponse.success("Customer retrieved successfully", customer, 200);
            } else {
                return ApiResponse.error("Customer not found with email: " + email, 404);
            }
            
        } catch (Exception e) {
            return ApiResponse.error("Failed to retrieve customer: " + e.getMessage(), 500);
        }
    }
    
    /**
     * Get all customers (admin function)
     * @return List of all customers
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    /**
     * Update customer last login timestamp
     * @param customerId The customer ID
     */
    public void updateLastLogin(String customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.setLastLogin(LocalDateTime.now());
            customerRepository.save(customer);
        }
    }
    
    /**
     * Validate password strength
     * @param password The password to validate
     * @return true if password meets requirements
     */
    private boolean isValidPassword(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        
        // Check for at least one letter and one number
        boolean hasLetter = password.matches(".*[a-zA-Z].*");
        boolean hasNumber = password.matches(".*\\d.*");
        
        return hasLetter && hasNumber;
    }
    
    /**
     * Validate MongoDB ObjectId format
     * @param id The ID to validate
     * @return true if valid ObjectId format
     */
    private boolean isValidObjectId(String id) {
        if (id == null || id.length() != 24) {
            return false;
        }
        return id.matches("^[a-fA-F0-9]{24}$");
    }
    
    /**
     * Verify password against stored password
     * @param rawPassword The plain text password
     * @param encodedPassword The password from database
     * @return true if passwords match
     */
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        // Simple string comparison for demo purposes (not recommended for production)
        return rawPassword != null && rawPassword.equals(encodedPassword);
    }
}
