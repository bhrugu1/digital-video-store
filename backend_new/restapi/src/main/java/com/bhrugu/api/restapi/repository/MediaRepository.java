package com.bhrugu.api.restapi.repository;

import com.bhrugu.api.restapi.model.Media;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MediaRepository extends MongoRepository<Media, String> {
    
    List<Media> findByType(String type);
    List<Media> findByFeatured(boolean featured);
    List<Media> findByTypeAndFeatured(String type, boolean featured);
    List<Media> findByTitleContainingIgnoreCase(String title);
}