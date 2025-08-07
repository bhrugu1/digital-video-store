// Details page component - Shows individual movie/TV show information
import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';

const Details = () => {
  // Get the ID from the URL (e.g., /details/5 gives us id = "5")
  const { id } = useParams();
  
  // State for storing the specific media item
  const [media, setMedia] = useState(null);
  
  // State for loading indicator
  const [loading, setLoading] = useState(true);

  // Fetch specific media item when component loads or ID changes
  useEffect(() => {
    const fetchMediaDetails = async () => {
      setLoading(true);
      try {
        console.log(`🎬 Fetching details for media ID: ${id}`);
        
        // Use your comprehensive API endpoint for getting media by ID
        const response = await fetch(`http://localhost:8080/api/media/${id}`);
        
        if (!response.ok) throw new Error("Media not found");
        
        const result = await response.json();
        console.log('Media Details API Response:', result);
        
        // Extract data from ApiResponse wrapper
        const mediaData = result.success ? result.data : null;
        
        if (!mediaData) {
          throw new Error("Media data not found in response");
        }
        
        setMedia(mediaData);
        setLoading(false);
        console.log('✅ Media details loaded successfully');
        
      } catch (error) {
        console.error("❌ Error fetching media details from Spring Boot API:", error);
        console.log('🔧 Make sure Spring Boot backend is running on port 8080');
        
        // Fallback to json-server if Spring Boot is not available
        try {
          const fallbackResponse = await fetch(`http://localhost:3003/media/${id}`);
          if (!fallbackResponse.ok) throw new Error("Not found in fallback");
          const fallbackData = await fallbackResponse.json();
          setMedia(fallbackData);
          setLoading(false);
          console.log('📱 Using fallback json-server data');
        } catch (fallbackErr) {
          console.error("❌ Fallback fetch error:", fallbackErr);
          setMedia(null);
          setLoading(false);
        }
      }
    };
    
    fetchMediaDetails();
  }, [id]);

  // Show loading message while fetching data
  if (loading) {
    return (
      <div className="details-container">
        <div className="loading">Loading...</div>
      </div>
    );
  }

  // Show error message if media item not found
  if (media === null) {
    return (
      <div className="details-container">
        <div className="error-message">
          <h2>Content Not Found</h2>
          <p>Sorry, the requested movie or TV show could not be found.</p>
          <Link to="/listings" className="back-link">← Back to Browse</Link>
        </div>
      </div>
    );
  }

  // Handle rent button click
  const handleRent = () => {
    alert(`Rent functionality for "${media.title}" would be implemented here!`);
  };

  // Handle buy button click
  const handleBuy = () => {
    alert(`Purchase functionality for "${media.title}" would be implemented here!`);
  };

  return (
    <div className="details-container">
      {/* Header with back link and media type */}
      <div className="details-header">
        <Link to="/listings" className="back-link">← Back to Browse</Link>
        <div className="media-type">{media.type || 'Media'}</div>
      </div>
      
      <div className="details-content">
        {/* Left side - Poster images */}
        <div className="poster-section">
          {/* Large poster display */}
          <div className="large-poster-container">
            <img 
              src={media.posterLarge || media.poster} 
              alt={media.title} 
              className="large-poster"
              onError={(e) => {
                // Show placeholder if image fails to load
                e.target.src = 'https://via.placeholder.com/600x800/1c1c1c/ffffff?text=No+Image';
              }}
            />
          </div>
          
          {/* Small poster thumbnail */}
          <div className="small-poster-container">
            <img 
              src={media.poster} 
              alt={media.title} 
              className="small-poster"
              onError={(e) => {
                // Show placeholder if image fails to load
                e.target.src = 'https://via.placeholder.com/150x200/1c1c1c/ffffff?text=No+Image';
              }}
            />
          </div>
        </div>
        
        {/* Right side - Movie/show information */}
        <div className="info-section">
          {/* Title */}
          <h1 className="media-title">{media.title}</h1>
          
          {/* Synopsis/Description */}
          <div className="synopsis-section">
            <h3>Synopsis</h3>
            <p className="synopsis">{media.synopsis || 'No synopsis available.'}</p>
          </div>
          
          {/* Pricing and purchase options */}
          <div className="pricing-section">
            <h3>Available Options</h3>
            <div className="pricing-options">
              {/* Rent option */}
              <div className="price-option">
                <div className="price-info">
                  <span className="price-label">Rent</span>
                  <span className="price-amount">${media.rent?.toFixed(2) || '0.00'}</span>
                </div>
                <button onClick={handleRent} className="rent-button">
                  🎬 Rent Now
                </button>
              </div>
              
              {/* Buy option */}
              <div className="price-option">
                <div className="price-info">
                  <span className="price-label">Buy</span>
                  <span className="price-amount">${media.buy?.toFixed(2) || '0.00'}</span>
                </div>
                <button onClick={handleBuy} className="buy-button">
                  💰 Buy Now
                </button>
              </div>
            </div>
          </div>
          
          {/* Additional information */}
          <div className="additional-info">
            <div className="info-item">
              <strong>Type:</strong> {media.type || 'N/A'}
            </div>
            <div className="info-item">
              <strong>Quality:</strong> HD, 4K Available
            </div>
            <div className="info-item">
              <strong>Languages:</strong> English, Spanish, French
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Details;
