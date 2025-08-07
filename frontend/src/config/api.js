/**
 * API configuration for different environments
 * 
 * Production Configuration:
 * - Frontend: https://digital-video-store-iota.vercel.app (Vercel)
 * - Backend: https://digital-video-store-production.up.railway.app (Railway)
 * - Database: MongoDB Atlas
 * 
 * Development Configuration:
 * - Frontend: http://localhost:3000
 * - Backend: http://localhost:8080
 * - Database: MongoDB Atlas (same as production)
 * 
 * Environment Variables:
 * - REACT_APP_API_URL: Primary backend API URL
 * - REACT_APP_FALLBACK_API_URL: Fallback URL for development
 */
const config = {
  // Primary API base URL - automatically switches between development and production
  API_BASE_URL: process.env.REACT_APP_API_URL || 'http://localhost:8080/api',
  
  // Fallback API URL for development testing
  FALLBACK_API_URL: process.env.REACT_APP_FALLBACK_API_URL || 'http://localhost:3003',
};

export default config;
