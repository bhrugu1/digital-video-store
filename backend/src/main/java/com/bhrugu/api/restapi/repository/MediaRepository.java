package com.bhrugu.api.restapi.repository;

import com.bhrugu.api.restapi.model.Media;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * MongoDB Repository for Media Documents
 * Extends MongoRepository for MongoDB operations
 * 
 * Collection: media-catalog
 * Database: streamvault-db
 */
@Repository
public interface MediaRepository extends MongoRepository<Media, String> {
    
    /**
     * Find all media by type (movie or tv-show)
     * @param type The media type to search for
     * @return List of media matching the type
     */
    List<Media> findByType(String type);
    
    /**
     * Find media by title containing a keyword (case-insensitive)
     * @param title The title keyword to search for
     * @return List of media with matching titles
     */
    List<Media> findByTitleContainingIgnoreCase(String title);
    
    /**
     * Find all movies using MongoDB query
     * @return List of all movie documents
     */
    @Query("{ 'type': 'movie' }")
    List<Media> findAllMovies();
    
    /**
     * Find all TV shows using MongoDB query
     * @return List of all TV show documents
     */
    @Query("{ 'type': 'tv-show' }")
    List<Media> findAllTVShows();
    
    /**
     * Find media by price range
     * @param minPrice Minimum price
     * @param maxPrice Maximum price
     * @return List of media within price range
     */
    @Query("{ 'buy': { $gte: ?0, $lte: ?1 } }")
    List<Media> findByPriceRange(Double minPrice, Double maxPrice);
}
