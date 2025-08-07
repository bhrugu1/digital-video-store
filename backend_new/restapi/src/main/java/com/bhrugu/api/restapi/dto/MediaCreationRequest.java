package com.bhrugu.api.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object for creating media content
 * Matches the frontend media creation form structure
 */
public class MediaCreationRequest {
    
    @JsonProperty("title")
    private String title;
    
    @JsonProperty("type")
    private String type; // "movie" or "tv-show"
    
    @JsonProperty("synopsis")
    private String synopsis;
    
    @JsonProperty("poster")
    private String poster; // Small poster image path
    
    @JsonProperty("posterLarge")
    private String posterLarge; // Large poster image path
    
    @JsonProperty("rent")
    private Double rent; // Rental price
    
    @JsonProperty("buy")
    private Double buy; // Purchase price
    
    @JsonProperty("featured")
    private Boolean featured; // Is featured content
    
    // Default constructor
    public MediaCreationRequest() {}
    
    // Constructor with all parameters
    public MediaCreationRequest(String title, String type, String synopsis, String poster, 
                              String posterLarge, Double rent, Double buy, Boolean featured) {
        this.title = title;
        this.type = type;
        this.synopsis = synopsis;
        this.poster = poster;
        this.posterLarge = posterLarge;
        this.rent = rent;
        this.buy = buy;
        this.featured = featured;
    }
    
    // Getters and setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getSynopsis() {
        return synopsis;
    }
    
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    
    public String getPoster() {
        return poster;
    }
    
    public void setPoster(String poster) {
        this.poster = poster;
    }
    
    public String getPosterLarge() {
        return posterLarge;
    }
    
    public void setPosterLarge(String posterLarge) {
        this.posterLarge = posterLarge;
    }
    
    public Double getRent() {
        return rent;
    }
    
    public void setRent(Double rent) {
        this.rent = rent;
    }
    
    public Double getBuy() {
        return buy;
    }
    
    public void setBuy(Double buy) {
        this.buy = buy;
    }
    
    public Boolean getFeatured() {
        return featured;
    }
    
    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }
    
    @Override
    public String toString() {
        return "MediaCreationRequest{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", rent=" + rent +
                ", buy=" + buy +
                ", featured=" + featured +
                '}';
    }
}
