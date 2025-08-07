// Login page component - User authentication form
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Auth.css';

const Login = () => {
  const navigate = useNavigate();
  
  // State to store form input values
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  // State for form submission
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');

  // Update form data when user types in input fields
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
    // Clear error when user starts typing
    if (error) setError('');
  };

  // Handle form submission using Assignment 2 login endpoint
  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent page reload
    
    // Validate required fields
    if (!formData.email || !formData.password) {
      setError('Both email and password are required!');
      return;
    }

    setIsLoading(true);
    setError('');
    
    try {
      console.log('🔐 Attempting login with Assignment 2 API...');
      
      // Use the Assignment 2 login endpoint
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: formData.email, // Using email as username
          password: formData.password
        })
      });

      const result = await response.json();
      console.log('Login API Response:', result);

      if (result.success) {
        // Success! Store user data and redirect to dashboard
        localStorage.setItem('user', JSON.stringify(result.data));
        localStorage.setItem('isLoggedIn', 'true');
        
        alert('✅ Login successful! Welcome back!');
        console.log('✅ User logged in successfully:', result.data);
        navigate('/dashboard');
      } else {
        // Login failed
        setError(result.message || 'Invalid email or password. Please try again.');
      }

    } catch (error) {
      console.error('❌ Login error:', error);
      setError('Network error. Please check your connection and try again.');
      
      // For development, show a fallback message
      alert('⚠️ Login endpoint not available. Make sure Spring Boot backend is running on port 8080.');
    } finally {
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
