
// Footer component - Bottom section that appears on every page
import React from 'react';
import { Link } from 'react-router-dom';

const Footer = () => (
  <footer className="footer">
    <div className="footer-container">
      {/* Company/Brand information */}
      <div className="footer-section">
        <h3>StreamVault</h3>
        <p>Your premier destination for digital entertainment</p>
      </div>
      
      {/* Quick navigation links */}
      <div className="footer-section">
        <h4>Quick Links</h4>
        <div className="footer-links">
          <Link to="/">Home</Link>
          <Link to="/listings">Movies & TV Shows</Link>
          <Link to="/register">Sign Up</Link>
          <Link to="/login">Login</Link>
        </div>
      </div>
      
      {/* Content categories */}
      <div className="footer-section">
        <h4>Categories</h4>
        <div className="footer-links">
          <a href="#movies">Movies</a>
          <a href="#tv-shows">TV Shows</a>
          <a href="#new-releases">New Releases</a>
          <a href="#trending">Trending</a>
        </div>
      </div>
      
      {/* Social media links */}
      <div className="footer-section">
        <h4>Follow Us</h4>
        <div className="social-links">
          <a href="#" className="social-link">📘 Facebook</a>
          <a href="#" className="social-link">🐦 Twitter</a>
          <a href="#" className="social-link">📷 Instagram</a>
          <a href="#" className="social-link">📺 YouTube</a>
        </div>
      </div>
    </div>
    
    {/* Copyright and legal information */}
    <div className="footer-bottom">
      <p>&copy; 2025 StreamVault. All rights reserved. | Privacy Policy | Terms of Service</p>
    </div>
  </footer>
);

export default Footer;
