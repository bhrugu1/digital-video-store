// API configuration for different environments
const config = {
  API_BASE_URL: process.env.REACT_APP_API_URL || 'http://localhost:8080/api',
  FALLBACK_API_URL: process.env.REACT_APP_FALLBACK_API_URL || 'http://localhost:3003',
};

export default config;
