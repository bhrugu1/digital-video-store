package com.bhrugu.api.restapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Media Document Entity for MongoDB
 * Represents movies and TV shows in the StreamVault digital video store
 * 
 * MongoDB Document Structure:
 * - Collection: media-catalog
 * - Database: streamvault-db
 */
@Document(collection = "media-catalog")
public class Media {
    
    /**
     * Unique identifier for each media item
     * MongoDB ObjectId as string
     */
    @Id
    private String id;
    
    /**
     * Title of the movie or TV show
     */
    @Field("title")
    private String title;
    
    /**
     * Type of media content ("movie" or "tv-show")
     */
    @Field("type")
    private String type;
    
    /**
     * Brief description/synopsis of the content
     */
    @Field("synopsis")
    private String synopsis;
    
    /**
     * URL to the poster image
     */
    @Field("poster")
    private String poster;
    
    /**
     * URL to the large poster image
     */
    @Field("poster_large")
    private String posterLarge;
    
    /**
     * Rental price for the media
     */
    @Field("rent")
    private Double rent;
    
    /**
     * Purchase price for the media
     */
    @Field("buy")
    private Double buy;
    
    // Default constructor required by MongoDB
    public Media() {}
    
    // Constructor with parameters
    public Media(String title, String type, String synopsis, String poster, String posterLarge, Double rent, Double buy) {
        this.title = title;
        this.type = type;
        this.synopsis = synopsis;
        this.poster = poster;
        this.posterLarge = posterLarge;
        this.rent = rent;
        this.buy = buy;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    
    public String getPosterLarge() { return posterLarge; }
    public void setPosterLarge(String posterLarge) { this.posterLarge = posterLarge; }
    
    public Double getRent() { return rent; }
    public void setRent(Double rent) { this.rent = rent; }
    
    public Double getBuy() { return buy; }
    public void setBuy(Double buy) { this.buy = buy; }
}
