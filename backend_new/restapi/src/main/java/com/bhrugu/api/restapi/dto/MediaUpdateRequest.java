package com.bhrugu.api.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object for updating media content
 * Used for partial updates of existing media items
 */
public class MediaUpdateRequest {
    
    @JsonProperty("title")
    private String title;
    
    @JsonProperty("synopsis")
    private String synopsis;
    
    @JsonProperty("poster")
    private String poster;
    
    @JsonProperty("posterLarge")
    private String posterLarge;
    
    @JsonProperty("rent")
    private Double rent;
    
    @JsonProperty("buy")
    private Double buy;
    
    @JsonProperty("featured")
    private Boolean featured;
    
    // Default constructor
    public MediaUpdateRequest() {}
    
    // Getters and setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
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
        return "MediaUpdateRequest{" +
                "title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", rent=" + rent +
                ", buy=" + buy +
                ", featured=" + featured +
                '}';
    }
}
