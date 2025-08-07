// Register page component - New user registration form
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Auth.css';
import config from '../config/api';

const Register = () => {
  const navigate = useNavigate();
  
  // State to store all form input values
  const [formData, setFormData] = useState({
    fullName: '',
    email: '',
    password: '',
    confirmPassword: ''
  });

  // State for form submission
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');

  // Update form data when user types in any input field
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
    // Clear error when user starts typing
    if (error) setError('');
  };

  // Handle form submission using Assignment 2 registration endpoint
  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent page reload
    
    // Check if passwords match before proceeding
    if (formData.password !== formData.confirmPassword) {
      setError('Passwords do not match!');
      return;
    }
    
    // Validate required fields
    if (!formData.fullName || !formData.email || !formData.password) {
      setError('All fields are required!');
      return;
    }

    setIsLoading(true);
    setError('');
    
    try {
      console.log('👤 Attempting registration with Assignment 2 API...');
      
      // Use the Assignment 2 registration endpoint
      const response = await fetch(`${config.API_BASE_URL}/auth/register`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          fullName: formData.fullName,
          email: formData.email,
          password: formData.password,
          confirmPassword: formData.confirmPassword
        })
      });

      const result = await response.json();
      console.log('Registration API Response:', result);

      if (result.success) {
        // Success! Alert user and redirect to login
        alert('✅ Registration successful! Please log in with your new account.');
        console.log('✅ User registered successfully:', result.data);
        navigate('/login');
      } else {
        // Registration failed
        setError(result.message || 'Registration failed. Please try again.');
      }

    } catch (error) {
      console.error('❌ Registration error:', error);
      setError('Network error. Please check your connection and try again.');
      
      // For development, show a fallback message
      alert('⚠️ Registration endpoint not available. Make sure Spring Boot backend is running on port 8080.');
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
            <h2>Join StreamVault</h2>
            <p>Create your account and start streaming today</p>
          </div>
          
          {/* Error message display */}
          {error && (
            <div className="error-message">
              {error}
            </div>
          )}
          
          {/* Registration form */}
          <form className="auth-form" onSubmit={handleSubmit}>
            {/* Full name input */}
            <div className="form-group">
              <label htmlFor="fullName">Full Name</label>
              <input 
                type="text" 
                id="fullName"
                name="fullName"
                placeholder="Enter your full name" 
                value={formData.fullName}
                onChange={handleChange}
                required 
              />
            </div>
            
            {/* Email input */}
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
            
            {/* Password input */}
            <div className="form-group">
              <label htmlFor="password">Password</label>
              <input 
                type="password" 
                id="password"
                name="password"
                placeholder="Create a password" 
                value={formData.password}
                onChange={handleChange}
                required 
              />
            </div>
            
            {/* Password confirmation input */}
            <div className="form-group">
              <label htmlFor="confirmPassword">Confirm Password</label>
              <input 
                type="password" 
                id="confirmPassword"
                name="confirmPassword"
                placeholder="Confirm your password" 
                value={formData.confirmPassword}
                onChange={handleChange}
                required 
              />
            </div>
            
            {/* Terms and conditions checkbox */}
            <div className="form-options">
              <label className="checkbox-label">
                <input type="checkbox" required />
                <span>I agree to the Terms of Service and Privacy Policy</span>
              </label>
            </div>
            
            {/* Submit button */}
            <button 
              type="submit" 
              className="auth-button"
              disabled={isLoading}
            >
              {isLoading ? 'Creating Account...' : 'Create Account'}
            </button>
          </form>
          
          {/* Link to login page */}
          <div className="auth-footer">
            <p>Already have an account? <Link to="/login" className="auth-link">Sign in here</Link></p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;
