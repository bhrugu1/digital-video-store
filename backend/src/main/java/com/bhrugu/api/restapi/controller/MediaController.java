package com.bhrugu.api.restapi.controller;

import com.bhrugu.api.restapi.model.Media;
import com.bhrugu.api.restapi.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Media operations
 * Provides RESTful API endpoints for the digital video store
 * Works with MongoDB through MediaService
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "https://digital-video-store-iota.vercel.app"}) // Allow React app to access this API
public class MediaController {
    
    @Autowired
    private MediaService mediaService;
    
    /**
     * GET /api/media - Get all media from MongoDB
     * @return List of all media documents
     */
    @GetMapping("/media")
    public ResponseEntity<List<Media>> getAllMedia() {
        List<Media> mediaList = mediaService.getAllMedia();
        return ResponseEntity.ok(mediaList);
    }
    
    /**
     * GET /api/media/{id} - Get media by MongoDB ObjectId
     * @param id The MongoDB ObjectId as string
     * @return Media document if found, 404 if not found
     */
    @GetMapping("/media/{id}")
    public ResponseEntity<Media> getMediaById(@PathVariable String id) {
        Optional<Media> media = mediaService.getMediaById(id);
        if (media.isPresent()) {
            return ResponseEntity.ok(media.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * GET /api/media/type/{type} - Get media by type (movie or tv-show)
     * @param type The media type to filter by
     * @return List of media matching the type
     */
    @GetMapping("/media/type/{type}")
    public ResponseEntity<List<Media>> getMediaByType(@PathVariable String type) {
        List<Media> mediaList = mediaService.getMediaByType(type);
        return ResponseEntity.ok(mediaList);
    }
    
    /**
     * GET /api/media/search?title=keyword - Search media by title
     * @param title The title keyword to search for
     * @return List of media with matching titles
     */
    @GetMapping("/media/search")
    public ResponseEntity<List<Media>> searchMedia(@RequestParam String title) {
        List<Media> mediaList = mediaService.searchMediaByTitle(title);
        return ResponseEntity.ok(mediaList);
    }
    
    /**
     * GET /api/movies - Get all movies from MongoDB
     * @return List of all movie documents
     */
    @GetMapping("/movies")
    public ResponseEntity<List<Media>> getAllMovies() {
        List<Media> movies = mediaService.getAllMovies();
        return ResponseEntity.ok(movies);
    }
    
    /**
     * GET /api/tvshows - Get all TV shows from MongoDB
     * @return List of all TV show documents
     */
    @GetMapping("/tvshows")
    public ResponseEntity<List<Media>> getAllTVShows() {
        List<Media> tvShows = mediaService.getAllTVShows();
        return ResponseEntity.ok(tvShows);
    }
    
    /**
     * POST /api/media - Create new media in MongoDB
     * @param media The media object to create
     * @return Created media with generated ObjectId
     */
    @PostMapping("/media")
    public ResponseEntity<Media> createMedia(@RequestBody Media media) {
        Media savedMedia = mediaService.saveMedia(media);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedia);
    }
    
    /**
     * PUT /api/media/{id} - Update existing media in MongoDB
     * @param id The MongoDB ObjectId as string
     * @param mediaDetails The updated media details
     * @return Updated media or 404 if not found
     */
    @PutMapping("/media/{id}")
    public ResponseEntity<Media> updateMedia(@PathVariable String id, @RequestBody Media mediaDetails) {
        Media updatedMedia = mediaService.updateMedia(id, mediaDetails);
        if (updatedMedia != null) {
            return ResponseEntity.ok(updatedMedia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * DELETE /api/media/{id} - Delete media from MongoDB
     * @param id The MongoDB ObjectId as string
     * @return 204 No Content if deleted, 404 if not found
     */
    @DeleteMapping("/media/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable String id) {
        boolean deleted = mediaService.deleteMedia(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * GET /api/media/price?min=5.0&max=20.0 - Get media by price range
     * @param minPrice Minimum buy price
     * @param maxPrice Maximum buy price
     * @return List of media within the price range
     */
    @GetMapping("/media/price")
    public ResponseEntity<List<Media>> getMediaByPriceRange(
            @RequestParam Double minPrice, 
            @RequestParam Double maxPrice) {
        List<Media> mediaList = mediaService.getMediaByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(mediaList);
    }
}
