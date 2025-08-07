package com.bhrugu.api.restapi.controller;

import com.bhrugu.api.restapi.model.Media;
import com.bhrugu.api.restapi.service.MediaService;
import com.bhrugu.api.restapi.repository.MediaRepository;
import com.bhrugu.api.restapi.dto.MediaCreationRequest;
import com.bhrugu.api.restapi.dto.MediaUpdateRequest;
import com.bhrugu.api.restapi.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST Controller for Media operations
 * Provides comprehensive RESTful API endpoints for the digital video store
 * Handles movies and TV shows with full CRUD operations
 * Works with MongoDB through MediaService
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class MediaController {

    @Autowired
    private MediaService mediaService;
    
    @Autowired
    private MediaRepository mediaRepository;

    /**
     * Debug endpoint to check database contents
     */
    @GetMapping("/debug-data")
    public ResponseEntity<ApiResponse<String>> debugData() {
        try {
            long totalCount = mediaService.count();
            List<Media> allMedia = mediaRepository.findAll();
            
            System.out.println("=== DATABASE DEBUG INFO ===");
            System.out.println("Total items in database: " + totalCount);
            
            for (Media media : allMedia) {
                System.out.println("- " + media.getTitle() + " (" + media.getType() + ") - Featured: " + media.getFeatured());
            }
            
            List<Media> movies = mediaService.getAllMovies();
            List<Media> tvShows = mediaService.getAllTVShows();
            List<Media> featuredMovies = mediaService.getFeaturedMovies();
            List<Media> featuredTVShows = mediaService.getFeaturedTVShows();
            
            System.out.println("Movies found: " + movies.size());
            System.out.println("TV Shows found: " + tvShows.size());
            System.out.println("Featured Movies found: " + featuredMovies.size());
            System.out.println("Featured TV Shows found: " + featuredTVShows.size());
            
            String debugInfo = String.format(
                "Total: %d | Movies: %d | TV Shows: %d | Featured Movies: %d | Featured TV Shows: %d",
                totalCount, movies.size(), tvShows.size(), featuredMovies.size(), featuredTVShows.size()
            );
            
            return ResponseEntity.ok(new ApiResponse<>(true, "Debug completed", debugInfo, 200));
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Debug failed: " + e.getMessage(), null, 500));
        }
    }

    /**
     * Manual data initialization endpoint
     */
    @GetMapping("/init-data")
    public ResponseEntity<ApiResponse<String>> initializeSampleData() {
        try {
            System.out.println("🎬 Manually initializing database with sample data...");
            
            // Clear existing data first
            mediaService.deleteAll();
            
            // Add sample movies
            Media inception = new Media("Inception", "Movie", "A thief who steals corporate secrets through dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.", "inception.jpg", "inception_large.jpg", 4.99, 14.99, true);
            mediaService.save(inception);
            
            Media matrix = new Media("The Matrix", "Movie", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.", "matrix.jpg", "matrix_large.jpg", 3.99, 12.99, true);
            mediaService.save(matrix);
            
            Media darkKnight = new Media("The Dark Knight", "Movie", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "dark_knight.jpg", "dark_knight_large.jpg", 4.99, 15.99, true);
            mediaService.save(darkKnight);
            
            Media blackPanther = new Media("Black Panther", "Movie", "T'Challa, heir to the hidden but advanced kingdom of Wakanda, must step forward to lead his people into a new future and must confront a challenger from his country's past.", "black_panther.jpg", "black_panther_large.jpg", 4.99, 14.99, false);
            mediaService.save(blackPanther);
            
            // Add sample TV shows
            Media breakingBad = new Media("Breaking Bad", "TV Show", "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.", "breaking_bad.jpg", "breaking_bad_large.jpg", 2.99, 24.99, true);
            mediaService.save(breakingBad);
            
            Media gameOfThrones = new Media("Game of Thrones", "TV Show", "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.", "game_of_thrones.jpg", "game_of_thrones_large.jpg", 2.99, 29.99, true);
            mediaService.save(gameOfThrones);
            
            Media theOffice = new Media("The Office", "TV Show", "A mockumentary on a group of typical office workers, where the workday consists of ego clashes, inappropriate behavior, and tedium.", "the_office.jpg", "the_office_large.jpg", 1.99, 19.99, false);
            mediaService.save(theOffice);
            
            long count = mediaService.count();
            
            System.out.println("✅ Database initialized successfully!");
            System.out.println("📊 Total media items: " + count);
            
            return ResponseEntity.ok(new ApiResponse<>(true, "Database initialized successfully with " + count + " items", "Success", 200));
            
        } catch (Exception e) {
            System.err.println("❌ Error initializing database: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Failed to initialize database: " + e.getMessage(), null, 500));
        }
    }

    /**
     * Get all movies
     */
    @GetMapping("/movies")
    public ResponseEntity<ApiResponse<List<Media>>> getAllMovies() {
        try {
            List<Media> movies = mediaService.getAllMovies();
            return ResponseEntity.ok(new ApiResponse<>(true, "Movies retrieved successfully", movies, 200));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Failed to retrieve movies: " + e.getMessage(), null, 500));
        }
    }

    /**
     * Get all TV shows
     */
    @GetMapping("/tvshows")
    public ResponseEntity<ApiResponse<List<Media>>> getAllTVShows() {
        try {
            List<Media> tvShows = mediaService.getAllTVShows();
            return ResponseEntity.ok(new ApiResponse<>(true, "TV shows retrieved successfully", tvShows, 200));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Failed to retrieve TV shows: " + e.getMessage(), null, 500));
        }
    }

    /**
     * Get featured movies
     */
    @GetMapping("/featured/movies")
    public ResponseEntity<ApiResponse<List<Media>>> getFeaturedMovies() {
        try {
            List<Media> featuredMovies = mediaService.getFeaturedMovies();
            return ResponseEntity.ok(new ApiResponse<>(true, "Featured movies retrieved successfully", featuredMovies, 200));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Failed to retrieve featured movies: " + e.getMessage(), null, 500));
        }
    }

    /**
     * Get featured TV shows
     */
    @GetMapping("/featured/tvshows")
    public ResponseEntity<ApiResponse<List<Media>>> getFeaturedTVShows() {
        try {
            List<Media> featuredTVShows = mediaService.getFeaturedTVShows();
            return ResponseEntity.ok(new ApiResponse<>(true, "Featured TV shows retrieved successfully", featuredTVShows, 200));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Failed to retrieve featured TV shows: " + e.getMessage(), null, 500));
        }
    }

    /**
     * Search media by title
     */
    @GetMapping("/media/search")
    public ResponseEntity<ApiResponse<List<Media>>> searchMedia(@RequestParam String title) {
        try {
            List<Media> searchResults = mediaService.searchByTitle(title);
            return ResponseEntity.ok(new ApiResponse<>(true, "Search completed successfully", searchResults, 200));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Failed to search media: " + e.getMessage(), null, 500));
        }
    }

    /**
     * Get specific media by ID
     */
    @GetMapping("/media/{id}")
    public ResponseEntity<ApiResponse<Media>> getMediaById(@PathVariable String id) {
        try {
            Media media = mediaService.getById(id);
            if (media != null) {
                return ResponseEntity.ok(new ApiResponse<>(true, "Media found", media, 200));
            } else {
                return ResponseEntity.status(404).body(new ApiResponse<>(false, "Media not found with ID: " + id, null, 404));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Failed to retrieve media: " + e.getMessage(), null, 500));
        }
    }

    /**
     * Create new media
     */
    @PostMapping("/media")
    public ResponseEntity<ApiResponse<Media>> createMedia(@RequestBody MediaCreationRequest request) {
        try {
            Media media = new Media(
                request.getTitle(),
                request.getType(),
                request.getSynopsis(),
                request.getPoster(),
                request.getPosterLarge(),
                request.getRent(),
                request.getBuy(),
                request.getFeatured()
            );
            Media savedMedia = mediaService.save(media);
            return ResponseEntity.status(201).body(new ApiResponse<>(true, "Media created successfully", savedMedia, 201));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Failed to create media: " + e.getMessage(), null, 500));
        }
    }

    /**
     * Update existing media
     */
    @PutMapping("/media/{id}")
    public ResponseEntity<ApiResponse<Media>> updateMedia(@PathVariable String id, @RequestBody MediaUpdateRequest request) {
        try {
            Media updatedMedia = mediaService.update(id, request);
            if (updatedMedia != null) {
                return ResponseEntity.ok(new ApiResponse<>(true, "Media updated successfully", updatedMedia, 200));
            } else {
                return ResponseEntity.status(404).body(new ApiResponse<>(false, "Media not found with ID: " + id, null, 404));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Failed to update media: " + e.getMessage(), null, 500));
        }
    }

    /**
     * Delete media
     */
    @DeleteMapping("/media/{id}")
    public ResponseEntity<ApiResponse<String>> deleteMedia(@PathVariable String id) {
        try {
            boolean deleted = mediaService.delete(id);
            if (deleted) {
                return ResponseEntity.ok(new ApiResponse<>(true, "Media deleted successfully", "Success", 200));
            } else {
                return ResponseEntity.status(404).body(new ApiResponse<>(false, "Media not found with ID: " + id, null, 404));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Failed to delete media: " + e.getMessage(), null, 500));
        }
    }
}