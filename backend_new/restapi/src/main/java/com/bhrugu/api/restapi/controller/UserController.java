package com.bhrugu.api.restapi.controller;

import com.bhrugu.api.restapi.model.User;
import com.bhrugu.api.restapi.service.UserService;
import com.bhrugu.api.restapi.dto.UserRegistrationRequest;
import com.bhrugu.api.restapi.dto.UserAuthenticationRequest;
import com.bhrugu.api.restapi.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for User Authentication operations
 * Provides user registration and authentication endpoints
 * Handles secure user account management for the digital video store
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // Allow React app to access this API
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 1. POST /api/auth/register - Create new user account
     * @param request User registration request with all required fields
     * @return ApiResponse with registered user data
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> registerUser(@RequestBody UserRegistrationRequest request) {
        ApiResponse<User> response = userService.registerUser(request);
        HttpStatus status = response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
    
    /**
     * 2. POST /api/auth/login - Authenticate user login
     * @param request Authentication request with username/email and password
     * @return ApiResponse with authenticated user data
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> authenticateUser(@RequestBody UserAuthenticationRequest request) {
        ApiResponse<User> response = userService.authenticateUser(request);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(response);
    }
    
    /**
     * GET /api/auth/user/{id} - Get user by ID (for profile retrieval)
     * @param id User ID
     * @return ApiResponse with user data
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable String id) {
        ApiResponse<User> response = userService.getUserById(id);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}
