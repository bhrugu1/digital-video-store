
// Header component - Navigation bar that appears on every page
import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Header = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  // Check login status on component mount
  useEffect(() => {
    const checkLoginStatus = () => {
      const loginStatus = localStorage.getItem('isLoggedIn');
      const userData = localStorage.getItem('user');
      
      if (loginStatus && userData) {
        setIsLoggedIn(true);
        try {
          setUser(JSON.parse(userData));
        } catch (error) {
          console.error('Error parsing user data:', error);
        }
      } else {
        setIsLoggedIn(false);
        setUser(null);
      }
    };

    checkLoginStatus();
    
    // Listen for storage changes (when user logs in/out in another tab)
    window.addEventListener('storage', checkLoginStatus);
    
    return () => {
      window.removeEventListener('storage', checkLoginStatus);
    };
  }, []);

  // Handle logout
  const handleLogout = () => {
    localStorage.removeItem('user');
    localStorage.removeItem('isLoggedIn');
    setIsLoggedIn(false);
    setUser(null);
    alert('👋 You have been logged out successfully.');
    navigate('/');
  };

  // Handle search form submission
  const handleSearch = (e) => {
    e.preventDefault();
    if (searchQuery.trim()) {
      // Navigate to listings page with search query
      navigate(`/listings?search=${encodeURIComponent(searchQuery.trim())}`);
      setSearchQuery('');
    }
  };

  return (
    <header className="header">
      <div className="header-container">
        {/* Logo/Brand name - clicking takes user to homepage */}
        <div className="logo">
          <Link to="/">
            <h1>🎬 StreamVault</h1>
          </Link>
        </div>
        
        {/* Search Box */}
        <div className="search-container">
          <form onSubmit={handleSearch} className="search-form">
            <input
              type="text"
              placeholder="Search movies & TV shows..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="search-input"
            />
            <button type="submit" className="search-btn">🔍</button>
          </form>
        </div>
        
        {/* Navigation menu */}
        <nav className="nav-bar">
          {/* Main navigation links */}
          <Link to="/" className="nav-link">Home</Link>
          <Link to="/listings" className="nav-link">Movies & TV Shows</Link>
          
          {/* User authentication links - show different options based on login status */}
          <div className="auth-links">
            {isLoggedIn ? (
              // Show when user is logged in
              <>
                <Link to="/dashboard" className="nav-link dashboard-btn">
                  👤 Dashboard
                </Link>
                <button onClick={handleLogout} className="nav-link logout-btn">
                  Logout
                </button>
              </>
            ) : (
              // Show when user is not logged in
              <>
                <Link to="/login" className="nav-link login-btn">Login</Link>
                <Link to="/register" className="nav-link signup-btn">Sign Up</Link>
              </>
            )}
          </div>
        </nav>
      </div>
    </header>
  );
};

export default Header;
