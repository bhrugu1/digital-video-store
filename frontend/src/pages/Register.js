// Register page component - New user registration form
// Handles user registration with backend API integration  
// On successful registration: automatically logs in user and navigates to dashboard
// On failed registration: displays error message
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Auth.css';
import config from '../config/api';

const Register = () => {
  // Navigation hook for programmatic routing after registration
  const navigate = useNavigate();
  
  // Form data state - stores all registration form input values
  const [formData, setFormData] = useState({
    fullName: '',
    email: '',
    password: '',
    confirmPassword: ''
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
   * Handles form submission and registration process
   * - Validates form inputs (password match, required fields)
   * - Calls backend registration API
   * - Automatically logs in user on successful registration
   * - Updates header component via custom event
   * - Navigates to dashboard on success
   */
  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent page reload on form submission
    
    // Client-side validation before API call
    if (formData.password !== formData.confirmPassword) {
      setError('Passwords do not match!');
      return;
    }
    
    if (!formData.fullName || !formData.email || !formData.password) {
      setError('All fields are required!');
      return;
    }

    setIsLoading(true);
    setError('');
    
    try {
      console.log('👤 Attempting registration with Assignment 2 API...');
      
      // Call backend registration endpoint
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
        // Success! Auto-login user and store authentication data
        localStorage.setItem('user', JSON.stringify(result.data));
        localStorage.setItem('isLoggedIn', 'true');
        
        // Trigger custom event to update header component immediately
        window.dispatchEvent(new Event('authStateChanged'));
        
        alert('✅ Registration successful! Welcome to StreamVault!');
        console.log('✅ User registered and logged in successfully:', result.data);
        navigate('/dashboard'); // Redirect to user dashboard
      } else {
        // Registration failed - show error message
        setError(result.message || 'Registration failed. Please try again.');
      }

    } catch (error) {
      console.error('❌ Registration error:', error);
      setError('Network error. Please check your connection and try again.');
      
      // Development fallback message
      alert('⚠️ Registration endpoint not available. Make sure Spring Boot backend is running on port 8080.');
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
