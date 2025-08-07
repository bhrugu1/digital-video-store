package com.bhrugu.api.restapi.repository;

import com.bhrugu.api.restapi.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * MongoDB Repository for Customer Documents
 * Extends MongoRepository for MongoDB operations
 * 
 * Collection: customers
 * Database: streamvault-db
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    
    /**
     * Find customer by email address
     * Used for login and registration validation
     * @param email The customer's email address
     * @return Optional Customer if found
     */
    Optional<Customer> findByEmail(String email);
    
    /**
     * Check if email already exists in database
     * Used for registration validation
     * @param email The email to check
     * @return true if email exists, false otherwise
     */
    boolean existsByEmail(String email);
    
    /**
     * Find customers by status
     * @param status The customer status (active, suspended, etc.)
     * @return List of customers with the specified status
     */
    @Query("{'status': ?0}")
    java.util.List<Customer> findByStatus(String status);
    
    /**
     * Find customers by name (case-insensitive search)
     * @param fullName The name to search for
     * @return List of customers with matching names
     */
    @Query("{'fullName': {$regex: ?0, $options: 'i'}}")
    java.util.List<Customer> findByFullNameContainingIgnoreCase(String fullName);
}
