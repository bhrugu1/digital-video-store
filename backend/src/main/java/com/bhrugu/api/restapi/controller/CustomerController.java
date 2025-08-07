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

import java.util.List;

/**
 * REST Controller for Customer operations
 * Provides RESTful API endpoints for customer management
 * Works with MongoDB through CustomerService
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    /**
     * POST /api/customers/register - Register a new customer
     * Accepts JSON data from frontend registration form
     * Validates data and encrypts password before storing in MongoDB
     * 
     * @param registrationRequest The registration data (fullName, email, password, confirmPassword)
     * @return ApiResponse with success/error message and status code
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Customer>> registerCustomer(
            @RequestBody CustomerRegistrationRequest registrationRequest) {
        
        try {
            // Basic validation
            if (!registrationRequest.isValid()) {
                ApiResponse<Customer> errorResponse = ApiResponse.error("Invalid registration data. Please check all fields.", 400);
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            // Register customer through service layer
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
     * POST /api/customers/login - Authenticate customer login
     * Accepts JSON data from frontend login form
     * Validates credentials against MongoDB database
     * 
     * @param loginRequest The login data (username/email, password)
     * @return ApiResponse with success/error message and customer data
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Customer>> loginCustomer(
            @RequestBody CustomerLoginRequest loginRequest) {
        
        try {
            // Basic validation
            if (!loginRequest.isValid()) {
                ApiResponse<Customer> errorResponse = ApiResponse.error("Username and password are required", 400);
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            // Authenticate customer through service layer
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
     * GET /api/customers/{id} - Get customer by ID
     * Retrieves a specific customer and all associated information
     * Provides validation for invalid customer IDs
     * 
     * @param id The customer ID (MongoDB ObjectId)
     * @return ApiResponse with customer data or error message
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> getCustomerById(@PathVariable String id) {
        
        try {
            // Get customer through service layer (includes validation)
            ApiResponse<Customer> response = customerService.getCustomerById(id);
            
            // Return appropriate HTTP status based on response
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                if (response.getStatusCode() == 404) {
                    status = HttpStatus.NOT_FOUND; // Customer not found
                } else if (response.getStatusCode() == 500) {
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                }
                return ResponseEntity.status(status).body(response);
            }
            
        } catch (Exception e) {
            ApiResponse<Customer> errorResponse = ApiResponse.error(
                    "Failed to retrieve customer: " + e.getMessage(), 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    /**
     * GET /api/customers/email/{email} - Get customer by email
     * Alternative endpoint to retrieve customer by email address
     * 
     * @param email The customer email address
     * @return ApiResponse with customer data or error message
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<Customer>> getCustomerByEmail(@PathVariable String email) {
        
        try {
            ApiResponse<Customer> response = customerService.getCustomerByEmail(email);
            
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                if (response.getStatusCode() == 404) {
                    status = HttpStatus.NOT_FOUND;
                } else if (response.getStatusCode() == 500) {
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                }
                return ResponseEntity.status(status).body(response);
            }
            
        } catch (Exception e) {
            ApiResponse<Customer> errorResponse = ApiResponse.error(
                    "Failed to retrieve customer: " + e.getMessage(), 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    /**
     * GET /api/customers - Get all customers (Admin endpoint)
     * Returns list of all registered customers
     * 
     * @return List of customers
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {
        
        try {
            List<Customer> customers = customerService.getAllCustomers();
            ApiResponse<List<Customer>> response = ApiResponse.success(
                    "Retrieved " + customers.size() + " customers", customers, 200);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            ApiResponse<List<Customer>> errorResponse = ApiResponse.error(
                    "Failed to retrieve customers: " + e.getMessage(), 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    /**
     * GET /api/customers/health - Health check endpoint
     * Simple endpoint to verify customer service is working
     * 
     * @return Success message with timestamp
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> healthCheck() {
        ApiResponse<String> response = ApiResponse.success(
                "Customer service is running", "OK", 200);
        return ResponseEntity.ok(response);
    }
}
