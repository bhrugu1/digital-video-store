// Login page component - User authentication form
// Handles user login with backend API integration
// On successful login: stores user data in localStorage and navigates to dashboard
// On failed login: displays error message
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Auth.css';
import config from '../config/api';

const Login = () => {
  // Navigation hook for programmatic routing after login
  const navigate = useNavigate();
  
  // Form data state - stores email and password input values
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  // UI state management
  const [isLoading, setIsLoading] = useState(false); // Loading spinner during API call
  const [error, setError] = useState(''); // Error message display

  /**
   * Updates form state when user types in input fields
   * Also clears any existing error messages for better UX
   */
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
    // Clear error when user starts typing - improves user experience
    if (error) setError('');
  };

  /**
   * Handles form submission and authentication process
   * - Validates form inputs
   * - Calls backend authentication API
   * - Stores user data in localStorage on success
   * - Updates header component via custom event
   * - Navigates to dashboard on successful login
   */
  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent page reload on form submission
    
    // Client-side validation before API call
    if (!formData.email || !formData.password) {
      setError('Both email and password are required!');
      return;
    }

    setIsLoading(true);
    setError('');
    
    try {
      console.log('🔐 Attempting login with Assignment 2 API...');
      
      // Call backend authentication endpoint
      const response = await fetch(`${config.API_BASE_URL}/auth/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: formData.email, // Backend expects 'username' field
          password: formData.password
        })
      });

      const result = await response.json();
      console.log('Login API Response:', result);

      if (result.success) {
        // Success! Store authentication data in localStorage
        localStorage.setItem('user', JSON.stringify(result.data));
        localStorage.setItem('isLoggedIn', 'true');
        
        // Trigger custom event to update header component immediately
        window.dispatchEvent(new Event('authStateChanged'));
        
        alert('✅ Login successful! Welcome back!');
        console.log('✅ User logged in successfully:', result.data);
        navigate('/dashboard'); // Redirect to user dashboard
      } else {
        // Authentication failed - show error message
        setError(result.message || 'Invalid email or password. Please try again.');
      }

    } catch (error) {
      console.error('❌ Login error:', error);
      setError('Network error. Please check your connection and try again.');
      
      // Development fallback message
      alert('⚠️ Login endpoint not available. Make sure Spring Boot backend is running on port 8080.');
    } finally {
      // Reset loading state regardless of success/failure
      setIsLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-container">
        <div className="auth-card">
          {/* Page header */}
          <div className="auth-header">
            <h2>Welcome Back</h2>
            <p>Sign in to your StreamVault account</p>
          </div>
          
          {/* Error message display */}
          {error && (
            <div className="error-message">
              {error}
            </div>
          )}
          
          {/* Login form */}
          <form className="auth-form" onSubmit={handleSubmit}>
            {/* Email input field */}
            <div className="form-group">
              <label htmlFor="email">Email Address</label>
              <input 
                type="email" 
                id="email"
                name="email"
                placeholder="Enter your email" 
                value={formData.email}
                onChange={handleChange}
                required 
              />
            </div>
            
            {/* Password input field */}
            <div className="form-group">
              <label htmlFor="password">Password</label>
              <input 
                type="password" 
                id="password"
                name="password"
                placeholder="Enter your password" 
                value={formData.password}
                onChange={handleChange}
                required 
              />
            </div>
            
            {/* Additional options */}
            <div className="form-options">
              <label className="checkbox-label">
                <input type="checkbox" />
                <span>Remember me</span>
              </label>
              <a href="#" className="forgot-link">Forgot password?</a>
            </div>
            
            {/* Submit button */}
            <button 
              type="submit" 
              className="auth-button"
              disabled={isLoading}
            >
              {isLoading ? 'Signing In...' : 'Sign In'}
            </button>
          </form>
          
          {/* Link to registration page */}
          <div className="auth-footer">
            <p>Don't have an account? <Link to="/register" className="auth-link">Sign up here</Link></p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
