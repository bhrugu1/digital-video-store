package com.bhrugu.api.restapi.controller;

import com.bhrugu.api.restapi.model.Customer;
import com.bhrugu.api.restapi.service.CustomerService;
import com.bhrugu.api.restapi.dto.CustomerRegistrationRequest;
import com.bhrugu.api.restapi.dto.CustomerLoginRequest;
import com.bhrugu.api.restapi.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Authentication operations
 * Provides RESTful API endpoints for user login and registration
 * Works with CustomerService for database operations
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private CustomerService customerService;
    
    /**
     * POST /api/auth/register - Register a new customer
     * Accepts JSON data from frontend registration form
     * 
     * @param registrationRequest The registration data
     * @return ApiResponse with success/error message and customer data
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Customer>> register(
            @RequestBody CustomerRegistrationRequest registrationRequest) {
        
        try {
            // Delegate to customer service
            ApiResponse<Customer> response = customerService.registerCustomer(registrationRequest);
            
            // Return appropriate HTTP status based on response
            if (response.isSuccess()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                if (response.getStatusCode() == 409) {
                    status = HttpStatus.CONFLICT; // Email already exists
                } else if (response.getStatusCode() == 500) {
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                }
                return ResponseEntity.status(status).body(response);
            }
            
        } catch (Exception e) {
            ApiResponse<Customer> errorResponse = ApiResponse.error(
                    "Registration failed: " + e.getMessage(), 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    /**
     * POST /api/auth/login - Authenticate customer login
     * Accepts JSON data from frontend login form
     * 
     * @param loginRequest The login data (username/email, password)
     * @return ApiResponse with success/error message and customer data
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Customer>> login(
            @RequestBody CustomerLoginRequest loginRequest) {
        
        try {
            // Delegate to customer service
            ApiResponse<Customer> response = customerService.authenticateCustomer(loginRequest);
            
            // Return appropriate HTTP status based on response
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                if (response.getStatusCode() == 401) {
                    status = HttpStatus.UNAUTHORIZED; // Invalid credentials
                } else if (response.getStatusCode() == 500) {
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                }
                return ResponseEntity.status(status).body(response);
            }
            
        } catch (Exception e) {
            ApiResponse<Customer> errorResponse = ApiResponse.error(
                    "Login failed: " + e.getMessage(), 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    /**
     * GET /api/auth/health - Health check endpoint
     * Simple endpoint to verify auth service is working
     * 
     * @return Success message with timestamp
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> healthCheck() {
        ApiResponse<String> response = ApiResponse.success(
                "Authentication service is running", "OK", 200);
        return ResponseEntity.ok(response);
    }
}
