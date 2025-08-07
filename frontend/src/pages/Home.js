// Home page component - Main landing page with hero slider and featured content
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import HeroSlider from '../components/HeroSlider';
import config from '../config/api';

const Home = () => {
  // State to store all movies and TV shows from the API
  const [media, setMedia] = useState([]);
  
  // Separate state for featured content
  const [featuredMovies, setFeaturedMovies] = useState([]);
  const [featuredShows, setFeaturedShows] = useState([]);

  // Fetch data from API when component loads
  useEffect(() => {
    const fetchAllData = async () => {
      try {
        console.log('🎬 Fetching data from Spring Boot API...');
        
        // Fetch general data and use first few items as featured content
        const [moviesResponse, tvShowsResponse] = await Promise.all([
          fetch(`${config.API_BASE_URL}/movies`),
          fetch(`${config.API_BASE_URL}/tvshows`)
        ]);
        
        const moviesResult = await moviesResponse.json();
        const tvShowsResult = await tvShowsResponse.json();
        
        console.log('Movies API Response:', moviesResult);
        console.log('TV Shows API Response:', tvShowsResult);
        
        // Extract data from ApiResponse wrapper
        const movies = moviesResult.success ? moviesResult.data : [];
        const tvShows = tvShowsResult.success ? tvShowsResult.data : [];
        
        // Use first 4 items as "featured" content
        const featuredMoviesData = movies.slice(0, 4);
        const featuredTVData = tvShows.slice(0, 4);
        
        // Combine all media for hero slider
        const allMedia = [...movies, ...tvShows];
        console.log('✅ Total media loaded:', allMedia.length);
        console.log('🌟 Featured movies loaded:', featuredMoviesData.length);
        console.log('🌟 Featured TV shows loaded:', featuredTVData.length);
        
        setMedia(allMedia);
        setFeaturedMovies(featuredMoviesData);
        setFeaturedShows(featuredTVData);
        
      } catch (error) {
        console.error('❌ Error fetching data from Spring Boot API:', error);
        console.log('🔧 Make sure Spring Boot backend is running');
        
        // Fallback to json-server if Spring Boot is not available
        try {
          const fallbackResponse = await fetch(`${config.FALLBACK_API_URL}/media`);
          const fallbackData = await fallbackResponse.json();
          
          // Use simple filter for featured content with fallback data
          const fallbackMovies = fallbackData.filter(item => item.type === 'Movie').slice(0, 4);
          const fallbackShows = fallbackData.filter(item => item.type === 'TV Show').slice(0, 4);
          
          setMedia(fallbackData);
          setFeaturedMovies(fallbackMovies);
          setFeaturedShows(fallbackShows);
          console.log('📱 Using fallback json-server data with filtered featured content');
        } catch (fallbackError) {
          console.error('❌ Fallback error:', fallbackError);
        }
      }
    };
    
    fetchAllData();
  }, []);

  return (
    <div className="home-page">
      {/* Hero banner with rotating slideshow */}
      <HeroSlider />

      {/* Featured Movies Section */}
      <section className="featured-section">
        <h3>Featured Movies</h3>
        <div className="grid">
          {featuredMovies.map(item => (
            <Link key={item.id} to={`/details/${item.id}`}>
              <div className="card">
                <img 
                  src={item.poster} 
                  alt={item.title}
                  onError={(e) => {
                    // Show placeholder if image fails to load
                    e.target.src = 'https://via.placeholder.com/300x400/1c1c1c/ffffff?text=No+Image';
                  }}
                />
                <h4>{item.title}</h4>
              </div>
            </Link>
          ))}
        </div>
      </section>

      {/* Featured TV Shows Section */}
      <section className="featured-section">
        <h3>Featured TV Shows</h3>
        <div className="grid">
          {featuredShows.map(item => (
            <Link key={item.id} to={`/details/${item.id}`}>
              <div className="card">
                <img 
                  src={item.poster} 
                  alt={item.title}
                  onError={(e) => {
                    // Show placeholder if image fails to load
                    e.target.src = 'https://via.placeholder.com/300x400/1c1c1c/ffffff?text=No+Image';
                  }}
                />
                <h4>{item.title}</h4>
              </div>
            </Link>
          ))}
        </div>
      </section>

      {/* Platform Features Section */}
      <section className="content-section">
        <div className="content-container">
          <h2>Experience Entertainment Like Never Before</h2>
          <p>Discover thousands of movies and TV shows at your fingertips. From blockbuster hits to indie gems, from binge-worthy series to timeless classics - StreamVault has it all.</p>
          
          {/* Feature highlights grid */}
          <div className="features-grid">
            <div className="feature-card">
              <div className="feature-icon">🎯</div>
              <h3>Instant Access</h3>
              <p>Rent or buy your favorite content and start watching immediately on any device.</p>
            </div>
            
            <div className="feature-card">
              <div className="feature-icon">📱</div>
              <h3>Multi-Device Support</h3>
              <p>Watch on your TV, laptop, tablet, or smartphone - seamlessly across all platforms.</p>
            </div>
            
            <div className="feature-card">
              <div className="feature-icon">🔥</div>
              <h3>Latest Releases</h3>
              <p>Be the first to watch new releases and trending content from Hollywood and beyond.</p>
            </div>
            
            <div className="feature-card">
              <div className="feature-icon">💎</div>
              <h3>4K Ultra HD</h3>
              <p>Enjoy cinema-quality viewing with stunning 4K resolution and immersive sound.</p>
            </div>
          </div>
        </div>
      </section>

      {/* Hollywood Highlights Section */}
      <section className="hollywood-section">
        <div className="content-container">
          <h2>Hollywood's Finest</h2>
          <div className="hollywood-content">
            {/* Text content */}
            <div className="hollywood-text">
              <h3>Award-Winning Content</h3>
              <p>From Oscar winners to Emmy champions, dive into the world's most celebrated films and series. Our collection features the best of Hollywood, including exclusive premieres, director's cuts, and behind-the-scenes content.</p>
              
              <h3>Star-Studded Performances</h3>
              <p>Watch your favorite actors and discover new talent in our extensive library. From action-packed blockbusters to intimate dramas, experience storytelling at its finest.</p>
              
              {/* Call-to-action buttons */}
              <div className="cta-buttons">
                <Link to="/listings" className="cta-primary">Explore Library</Link>
                <Link to="/register" className="cta-secondary">Join Now</Link>
              </div>
            </div>
            
            {/* Featured image */}
            <div className="hollywood-image">
              <img src="https://images.unsplash.com/photo-1489599511072-b27dbc006adc?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80" alt="Hollywood" />
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Home;
