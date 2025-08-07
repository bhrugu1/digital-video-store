// Dashboard page component - User personal information and account management
import React, { useEffect, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import './Auth.css';

const Dashboard = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Check if user is logged in
    const isLoggedIn = localStorage.getItem('isLoggedIn');
    const userData = localStorage.getItem('user');

    if (!isLoggedIn || !userData) {
      // User not logged in, redirect to login page
      alert('⚠️ Please log in to access your dashboard.');
      navigate('/login');
      return;
    }

    try {
      // Parse user data from localStorage
      const parsedUser = JSON.parse(userData);
      setUser(parsedUser);
      console.log('👤 Dashboard loaded for user:', parsedUser);
    } catch (error) {
      console.error('Error parsing user data:', error);
      localStorage.removeItem('user');
      localStorage.removeItem('isLoggedIn');
      navigate('/login');
    }

    setLoading(false);
  }, [navigate]);

  // Handle logout
  const handleLogout = () => {
    localStorage.removeItem('user');
    localStorage.removeItem('isLoggedIn');
    alert('👋 You have been logged out successfully.');
    navigate('/');
  };

  if (loading) {
    return (
      <div className="auth-page">
        <div className="auth-container">
          <div className="loading">Loading your dashboard...</div>
        </div>
      </div>
    );
  }

  if (!user) {
    return null; // Will redirect to login
  }

  return (
    <div className="auth-page">
      <div className="auth-container">
        <div className="auth-card dashboard-card">
          {/* Dashboard header */}
          <div className="auth-header">
            <h2>Welcome to Your Dashboard</h2>
            <p>Manage your account and view your personal information</p>
          </div>

          {/* User Information Section */}
          <div className="user-info-section">
            <h3>Personal Information</h3>
            <div className="info-grid">
              <div className="info-item">
                <label>Full Name:</label>
                <span>{user.fullName || user.username || 'Not provided'}</span>
              </div>
              <div className="info-item">
                <label>Email:</label>
                <span>{user.email || 'Not provided'}</span>
              </div>
              <div className="info-item">
                <label>Username:</label>
                <span>{user.username || 'Not provided'}</span>
              </div>
              <div className="info-item">
                <label>Account Status:</label>
                <span className="status-active">Active</span>
              </div>
              <div className="info-item">
                <label>Member Since:</label>
                <span>{user.createdAt ? new Date(user.createdAt).toLocaleDateString() : 'Recently'}</span>
              </div>
            </div>
          </div>

          {/* Quick Actions Section */}
          <div className="quick-actions">
            <h3>Quick Actions</h3>
            <div className="action-buttons">
              <Link to="/listings" className="action-btn">
                🎬 Browse Movies & TV Shows
              </Link>
              <Link to="/" className="action-btn">
                🏠 Go to Homepage
              </Link>
              <button onClick={handleLogout} className="action-btn logout-btn">
                🚪 Logout
              </button>
            </div>
          </div>

          {/* Account Details Section */}
          <div className="account-details">
            <h3>Account Details</h3>
            <div className="details-info">
              <p>🛡️ Your account is secured with encrypted password protection.</p>
              <p>📧 All notifications will be sent to your registered email address.</p>
              <p>🎯 You can browse and search our entire catalog of movies and TV shows.</p>
            </div>
          </div>

          {/* Navigation back */}
          <div className="auth-footer">
            <Link to="/" className="auth-link">← Back to Homepage</Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
