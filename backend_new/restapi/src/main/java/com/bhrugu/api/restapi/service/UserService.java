package com.bhrugu.api.restapi.service;

import com.bhrugu.api.restapi.model.User;
import com.bhrugu.api.restapi.repository.UserRepository;
import com.bhrugu.api.restapi.dto.UserRegistrationRequest;
import com.bhrugu.api.restapi.dto.UserAuthenticationRequest;
import com.bhrugu.api.restapi.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service layer for User Authentication operations
 * Handles business logic for user registration and authentication
 * Provides secure password handling with BCrypt encryption
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    /**
     * Register a new user account
     * @param request User registration request with all required fields
     * @return ApiResponse with success/error message and user data
     */
    public ApiResponse<User> registerUser(UserRegistrationRequest request) {
        try {
            // Validate required fields
            if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
                return ApiResponse.error("Username is required");
            }
            
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                return ApiResponse.error("Email is required");
            }
            
            if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
                return ApiResponse.error("Password is required");
            }
            
            if (request.getFirstName() == null || request.getFirstName().trim().isEmpty()) {
                return ApiResponse.error("First name is required");
            }
            
            if (request.getLastName() == null || request.getLastName().trim().isEmpty()) {
                return ApiResponse.error("Last name is required");
            }
            
            // Validate username format (alphanumeric, underscore, hyphen only)
            String username = request.getUsername().trim();
            if (!username.matches("^[a-zA-Z0-9_-]+$")) {
                return ApiResponse.error("Username can only contain letters, numbers, underscores, and hyphens");
            }
            
            if (username.length() < 3 || username.length() > 20) {
                return ApiResponse.error("Username must be between 3 and 20 characters");
            }
            
            // Validate email format
            String email = request.getEmail().trim().toLowerCase();
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return ApiResponse.error("Invalid email format");
            }
            
            // Validate password strength
            String password = request.getPassword();
            if (password.length() < 6) {
                return ApiResponse.error("Password must be at least 6 characters long");
            }
            
            // Check if username already exists
            if (userRepository.existsByUsername(username)) {
                return ApiResponse.error("Username already exists");
            }
            
            // Check if email already exists
            if (userRepository.existsByEmail(email)) {
                return ApiResponse.error("Email already exists");
            }
            
            // Encrypt password
            String encryptedPassword = passwordEncoder.encode(password);
            
            // Create new user
            User user = new User(
                username,
                email,
                encryptedPassword,
                request.getFirstName().trim(),
                request.getLastName().trim()
            );
            
            // Save to database
            User savedUser = userRepository.save(user);
            
            return ApiResponse.success("User registered successfully", savedUser);
            
        } catch (Exception e) {
            return ApiResponse.error("Failed to register user: " + e.getMessage());
        }
    }
    
    /**
     * Authenticate user login
     * @param request Authentication request with username/email and password
     * @return ApiResponse with success/error message and user data
     */
    public ApiResponse<User> authenticateUser(UserAuthenticationRequest request) {
        try {
            // Validate required fields
            if (request.getUsernameOrEmail() == null || request.getUsernameOrEmail().trim().isEmpty()) {
                return ApiResponse.error("Username or email is required");
            }
            
            if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
                return ApiResponse.error("Password is required");
            }
            
            String usernameOrEmail = request.getUsernameOrEmail().trim();
            String password = request.getPassword();
            
            // Try to find user by username or email
            Optional<User> userOptional = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
            
            if (!userOptional.isPresent()) {
                return ApiResponse.error("Invalid username/email or password");
            }
            
            User user = userOptional.get();
            
            // Verify password
            if (!passwordEncoder.matches(password, user.getPassword())) {
                return ApiResponse.error("Invalid username/email or password");
            }
            
            return ApiResponse.success("Authentication successful", user);
            
        } catch (Exception e) {
            return ApiResponse.error("Failed to authenticate user: " + e.getMessage());
        }
    }
    
    /**
     * Find user by ID
     * @param id User ID
     * @return ApiResponse with user data or error message
     */
    public ApiResponse<User> getUserById(String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                return ApiResponse.error("User ID is required");
            }
            
            Optional<User> user = userRepository.findById(id.trim());
            if (user.isPresent()) {
                return ApiResponse.success("User found successfully", user.get());
            } else {
                return ApiResponse.error("User not found with ID: " + id);
            }
        } catch (Exception e) {
            return ApiResponse.error("Failed to retrieve user: " + e.getMessage());
        }
    }
}
