package com.bhrugu.api.restapi.service;

import com.bhrugu.api.restapi.model.Media;
import com.bhrugu.api.restapi.repository.MediaRepository;
import com.bhrugu.api.restapi.dto.MediaUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    public List<Media> getAllMovies() {
        return mediaRepository.findByType("Movie");
    }

    public List<Media> getAllTVShows() {
        return mediaRepository.findByType("TV Show");
    }

    public List<Media> getFeaturedMovies() {
        return mediaRepository.findByTypeAndFeatured("Movie", true);
    }

    public List<Media> getFeaturedTVShows() {
        return mediaRepository.findByTypeAndFeatured("TV Show", true);
    }

    public List<Media> searchByTitle(String title) {
        return mediaRepository.findByTitleContainingIgnoreCase(title);
    }

    public Media getById(String id) {
        Optional<Media> media = mediaRepository.findById(id);
        return media.orElse(null);
    }

    public Media save(Media media) {
        return mediaRepository.save(media);
    }

    public Media update(String id, MediaUpdateRequest request) {
        Optional<Media> existingMedia = mediaRepository.findById(id);
        if (existingMedia.isPresent()) {
            Media media = existingMedia.get();
            media.setTitle(request.getTitle());
            media.setSynopsis(request.getSynopsis());
            media.setPoster(request.getPoster());
            media.setPosterLarge(request.getPosterLarge());
            media.setRent(request.getRent());
            media.setBuy(request.getBuy());
            media.setFeatured(request.getFeatured());
            return mediaRepository.save(media);
        }
        return null;
    }

    public boolean delete(String id) {
        if (mediaRepository.existsById(id)) {
            mediaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAll() {
        mediaRepository.deleteAll();
    }

    public long count() {
        return mediaRepository.count();
    }

    public List<Media> findByFeatured(boolean featured) {
        return mediaRepository.findByFeatured(featured);
    }
}