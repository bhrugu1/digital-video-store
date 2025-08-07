// Listings page component - Shows all movies and TV shows with filtering and search
import React, { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import config from '../config/api';

const Listings = () => {
  // State for storing all media items
  const [media, setMedia] = useState([]);
  
  // State for loading indicator
  const [loading, setLoading] = useState(true);
  
  // State for current filter (all, Movie, or TV Show)
  const [filter, setFilter] = useState('all');
  
  // State for search functionality
  const [searchResults, setSearchResults] = useState([]);
  const [isSearching, setIsSearching] = useState(false);
  
  // Get search query from URL parameters
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const searchQuery = searchParams.get('search');

  // Fetch search results using the dedicated search endpoint
  const fetchSearchResults = async (query) => {
    setIsSearching(true);
    setLoading(true);
    try {
      console.log(`🔍 Searching for: "${query}"`);
      
      // Use the dedicated search endpoint from Assignment 2
      const response = await fetch(`${config.API_BASE_URL}/media/search?title=${encodeURIComponent(query)}`);
      const result = await response.json();
      
      console.log('🔍 Search API Response:', result);
      
      // API returns direct array, not wrapped in ApiResponse
      const searchData = Array.isArray(result) ? result : [];
      console.log(`✅ Search results found: ${searchData.length} items`);
      
      setSearchResults(searchData);
      setLoading(false);
      
    } catch (error) {
      console.error('❌ Error searching media from Spring Boot API:', error);
      console.log('🔧 Make sure Spring Boot backend is running on port 8080');
      
      // Fallback to json-server search
      try {
        const fallbackResponse = await fetch(`http://localhost:3003/media?title_like=${encodeURIComponent(query)}`);
        const fallbackData = await fallbackResponse.json();
        setSearchResults(fallbackData);
        setLoading(false);
        console.log('📱 Using fallback json-server search');
      } catch (fallbackError) {
        console.error('❌ Fallback search error:', fallbackError);
        setLoading(false);
      }
    } finally {
      setIsSearching(false);
    }
  };

  // Fetch all media from API when component loads or search when query present
  useEffect(() => {
    if (searchQuery) {
      // If there's a search query, perform search
      fetchSearchResults(searchQuery);
    } else {
      // Otherwise, fetch all media
      fetchAllMedia();
    }
  }, [searchQuery]); // Re-run when search query changes

  const fetchAllMedia = async () => {
    setLoading(true);
    setIsSearching(false);
    try {
      console.log('🎬 Fetching all movies and TV shows for listings page...');
      
      // Fetch movies and TV shows separately using your comprehensive API
      const [moviesResponse, tvShowsResponse] = await Promise.all([
        fetch(`${config.API_BASE_URL}/movies`),
        fetch(`${config.API_BASE_URL}/tvshows`)
      ]);
      
      const moviesResult = await moviesResponse.json();
      const tvShowsResult = await tvShowsResponse.json();
      
      console.log('Movies API Response:', moviesResult);
      console.log('TV Shows API Response:', tvShowsResult);
      
      // API returns direct arrays, not wrapped in ApiResponse
      const movies = Array.isArray(moviesResult) ? moviesResult : [];
      const tvShows = Array.isArray(tvShowsResult) ? tvShowsResult : [];
      
      // Combine both arrays
      const allMedia = [...movies, ...tvShows];
      console.log('✅ Total media loaded for listings:', allMedia.length);
      
      setMedia(allMedia);
      setSearchResults([]); // Clear search results when loading all media
      setLoading(false);
      
    } catch (error) {
      console.error('❌ Error fetching media from Spring Boot API:', error);
      console.log('🔧 Make sure Spring Boot backend is running on port 8080');
      
      // Fallback to json-server if Spring Boot is not available
      try {
        const fallbackResponse = await fetch('http://localhost:3003/media');
        const fallbackData = await fallbackResponse.json();
        setMedia(fallbackData);
        setLoading(false);
        console.log('📱 Using fallback json-server data');
      } catch (fallbackErr) {
        console.error('❌ Fallback error:', fallbackErr);
        setLoading(false);
      }
    }
  };

  // Determine which data set to use and filter accordingly
  const currentDataSet = searchQuery ? searchResults : media;
  
  // Filter media based on selected filter - supports both MongoDB and json-server formats
  const filteredMedia = filter === 'all' 
    ? currentDataSet 
    : currentDataSet.filter(item => {
        if (filter === 'Movie') {
          return item.type === 'Movie' || item.type === 'movie';
        } else if (filter === 'TV Show') {
          return item.type === 'TV Show' || item.type === 'tv-show';
        }
        return item.type === filter;
      });

  // Helper function to count items by type (supports both formats)
  const countByType = (type) => {
    const dataToCount = searchQuery ? searchResults : media;
    if (type === 'Movie') {
      return dataToCount.filter(item => item.type === 'Movie' || item.type === 'movie').length;
    } else if (type === 'TV Show') {
      return dataToCount.filter(item => item.type === 'TV Show' || item.type === 'tv-show').length;
    }
    return 0;
  };

  // Show loading message while fetching data
  if (loading) {
    return (
      <div className="listings-page">
        <div className="loading">Loading content...</div>
      </div>
    );
  }

  return (
    <div className="listings-page">
      {/* Page header with title and filter buttons */}
      <div className="listings-header">
        {searchQuery ? (
          <div className="search-header">
            <h1>Search Results</h1>
            <p>Showing results for: "<strong>{searchQuery}</strong>"</p>
            <p>{searchResults.length} {searchResults.length === 1 ? 'result' : 'results'} found</p>
            <Link to="/listings" className="clear-search-btn">← Back to All Movies & TV Shows</Link>
          </div>
        ) : (
          <>
            <h1>Movies & TV Shows</h1>
            <p>Discover thousands of movies and TV shows available for rent or purchase</p>
          </>
        )}
        
        {/* Filter buttons section */}
        <div className="filter-section">
          <h3>Filter by Category:</h3>
          <div className="filter-buttons">
            {/* Show All button */}
            <button 
              className={filter === 'all' ? 'filter-btn active' : 'filter-btn'}
              onClick={() => setFilter('all')}
            >
              All ({currentDataSet.length})
            </button>
            
            {/* Movies only button */}
            <button 
              className={filter === 'Movie' ? 'filter-btn active' : 'filter-btn'}
              onClick={() => setFilter('Movie')}
            >
              Movies ({countByType('Movie')})
            </button>
            
            {/* TV Shows only button */}
            <button 
              className={filter === 'TV Show' ? 'filter-btn active' : 'filter-btn'}
              onClick={() => setFilter('TV Show')}
            >
              TV Shows ({countByType('TV Show')})
            </button>
          </div>
        </div>
      </div>
      
      {/* Grid of media items */}
      <div className="listings-grid">
        {filteredMedia.length > 0 ? (
          // Show each media item as a clickable card
          filteredMedia.map(item => (
            <Link key={item.id} to={`/details/${item.id}`} className="listing-link">
              <div className="listing-card">
                {/* Poster image with hover overlay */}
                <div className="poster-container">
                  <img 
                    src={item.poster} 
                    alt={item.title}
                    onError={(e) => {
                      // Show placeholder if image fails to load
                      e.target.src = 'https://via.placeholder.com/300x400/1c1c1c/ffffff?text=No+Image';
                    }}
                  />
                  
                  {/* Overlay that appears on hover */}
                  <div className="overlay">
                    <div className="overlay-content">
                      <p className="media-type">{item.type || 'Media'}</p>
                      <p className="rent-price">Rent: ${item.rent?.toFixed(2) || '0.00'}</p>
                      <p className="buy-price">Buy: ${item.buy?.toFixed(2) || '0.00'}</p>
                    </div>
                  </div>
                </div>
                
                {/* Title and type info below image */}
                <div className="listing-info">
                  <h3 className="listing-title">{item.title}</h3>
                  <p className="listing-type">{item.type}</p>
                </div>
              </div>
            </Link>
          ))
        ) : (
          // Show message when no items match the filter
          <div className="no-results">
            <h3>No content found</h3>
            <p>Try adjusting your filter or check back later for new releases.</p>
          </div>
        )}
      </div>
      
      {/* Footer showing count of filtered items */}
      {filteredMedia.length > 0 && (
        <div className="listings-footer">
          <p>Showing {filteredMedia.length} {filter === 'all' ? 'items' : filter === 'Movie' ? 'movies' : 'TV shows'}</p>
        </div>
      )}
    </div>
  );
};

export default Listings;
