
// Header component - Navigation bar that appears on every page
import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Header = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [user, setUser] = useState(null);
  const [showUserDropdown, setShowUserDropdown] = useState(false);
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
    
    // Listen for custom auth events (when user logs in/out in same tab)
    window.addEventListener('authStateChanged', checkLoginStatus);
    
    return () => {
      window.removeEventListener('storage', checkLoginStatus);
      window.removeEventListener('authStateChanged', checkLoginStatus);
    };
  }, []);

  // Handle logout
  const handleLogout = () => {
    localStorage.removeItem('user');
    localStorage.removeItem('isLoggedIn');
    setIsLoggedIn(false);
    setUser(null);
    setShowUserDropdown(false);
    
    // Trigger custom event to update other components
    window.dispatchEvent(new Event('authStateChanged'));
    
    alert('👋 You have been logged out successfully.');
    navigate('/');
  };

  // Toggle user dropdown
  const toggleUserDropdown = () => {
    setShowUserDropdown(!showUserDropdown);
  };

  // Close dropdown when clicking outside
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (!event.target.closest('.user-dropdown')) {
        setShowUserDropdown(false);
      }
    };

    if (showUserDropdown) {
      document.addEventListener('click', handleClickOutside);
    }

    return () => {
      document.removeEventListener('click', handleClickOutside);
    };
  }, [showUserDropdown]);

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
              // Show when user is logged in - User dropdown menu
              <div className="user-dropdown">
                <button 
                  onClick={toggleUserDropdown} 
                  className="user-dropdown-trigger"
                >
                  👤 {user?.fullName || user?.name || 'User'} ▼
                </button>
                {showUserDropdown && (
                  <div className="user-dropdown-menu">
                    <Link 
                      to="/dashboard" 
                      className="dropdown-item"
                      onClick={() => setShowUserDropdown(false)}
                    >
                      📊 Dashboard
                    </Link>
                    <Link 
                      to="/profile" 
                      className="dropdown-item"
                      onClick={() => setShowUserDropdown(false)}
                    >
                      ⚙️ Profile Settings
                    </Link>
                    <hr className="dropdown-divider" />
                    <button 
                      onClick={handleLogout} 
                      className="dropdown-item logout-item"
                    >
                      🚪 Logout
                    </button>
                  </div>
                )}
              </div>
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
