package com.bhrugu.api.restapi.service;

import com.bhrugu.api.restapi.model.Media;
import com.bhrugu.api.restapi.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for Media operations
 * Handles business logic for media management
 * Works with MongoDB through MediaRepository
 * 
 * This service acts as an intermediary between the REST controller and database
 * - Encapsulates business logic and validation
 * - Provides clean interface for data operations
 * - Handles error cases and data transformations
 */
@Service
public class MediaService {
    
    // Repository injection - Spring Data MongoDB automatically implements CRUD operations
    @Autowired
    private MediaRepository mediaRepository;
    
    /**
     * Get all media items from MongoDB
     * @return List of all media documents
     */
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }
    
    /**
     * Get media by ID (MongoDB ObjectId as String)
     * @param id The MongoDB ObjectId as string
     * @return Optional containing the media if found
     */
    public Optional<Media> getMediaById(String id) {
        return mediaRepository.findById(id);
    }
    
    /**
     * Get media by type (movie or tv-show)
     * @param type The media type to filter by
     * @return List of media matching the type
     */
    public List<Media> getMediaByType(String type) {
        return mediaRepository.findByType(type);
    }
    
    /**
     * Search media by title (case-insensitive)
     * @param title The title keyword to search for
     * @return List of media with matching titles
     */
    public List<Media> searchMediaByTitle(String title) {
        return mediaRepository.findByTitleContainingIgnoreCase(title);
    }
    
    /**
     * Get all movies from MongoDB
     * @return List of all movie documents
     */
    public List<Media> getAllMovies() {
        return mediaRepository.findAllMovies();
    }
    
    /**
     * Get all TV shows from MongoDB
     * @return List of all TV show documents
     */
    public List<Media> getAllTVShows() {
        return mediaRepository.findAllTVShows();
    }
    
    /**
     * Save new media to MongoDB
     * @param media The media object to save
     * @return The saved media with generated ID
     */
    public Media saveMedia(Media media) {
        return mediaRepository.save(media);
    }
    
    /**
     * Update existing media in MongoDB
     * @param id The MongoDB ObjectId as string
     * @param mediaDetails The updated media details
     * @return The updated media or null if not found
     */
    public Media updateMedia(String id, Media mediaDetails) {
        Optional<Media> optionalMedia = mediaRepository.findById(id);
        if (optionalMedia.isPresent()) {
            Media media = optionalMedia.get();
            media.setTitle(mediaDetails.getTitle());
            media.setType(mediaDetails.getType());
            media.setSynopsis(mediaDetails.getSynopsis());
            media.setPoster(mediaDetails.getPoster());
            media.setPosterLarge(mediaDetails.getPosterLarge());
            media.setRent(mediaDetails.getRent());
            media.setBuy(mediaDetails.getBuy());
            return mediaRepository.save(media);
        }
        return null;
    }
    
    /**
     * Delete media from MongoDB
     * Used by DELETE /api/media/{id} endpoint
     * 
     * @param id The MongoDB ObjectId as string
     * @return true if media was found and deleted, false if media doesn't exist
     * 
     * Implementation:
     * 1. Check if media exists in database
     * 2. If exists: delete it and return true
     * 3. If not found: return false (controller returns 404)
     */
    public boolean deleteMedia(String id) {
        if (mediaRepository.existsById(id)) {
            mediaRepository.deleteById(id);
            return true; // Successfully deleted
        }
        return false; // Media not found
    }
    
    /**
     * Find media by price range
     * @param minPrice Minimum buy price
     * @param maxPrice Maximum buy price
     * @return List of media within the price range
     */
    public List<Media> getMediaByPriceRange(Double minPrice, Double maxPrice) {
        return mediaRepository.findByPriceRange(minPrice, maxPrice);
    }
}
