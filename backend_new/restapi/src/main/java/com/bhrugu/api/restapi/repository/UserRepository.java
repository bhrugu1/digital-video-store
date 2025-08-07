package com.bhrugu.api.restapi.repository;

import com.bhrugu.api.restapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for User operations with MongoDB
 * Provides database operations for user authentication system
 * Extends MongoRepository for basic CRUD operations
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    /**
     * Find user by username
     * @param username The username to search for
     * @return Optional containing the user if found
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Find user by email address
     * @param email The email to search for
     * @return Optional containing the user if found
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Find user by either username or email
     * Used for authentication where user can login with either
     * @param username The username to search for
     * @param email The email to search for
     * @return Optional containing the user if found
     */
    @Query("{'$or': [{'username': ?0}, {'email': ?1}]}")
    Optional<User> findByUsernameOrEmail(String username, String email);
    
    /**
     * Check if username already exists
     * @param username The username to check
     * @return true if username exists, false otherwise
     */
    boolean existsByUsername(String username);
    
    /**
     * Check if email already exists
     * @param email The email to check
     * @return true if email exists, false otherwise
     */
    boolean existsByEmail(String email);
}
