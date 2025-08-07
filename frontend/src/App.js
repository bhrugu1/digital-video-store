
// Main App component - Sets up routing for the entire application
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

// Import all page components
import Home from './pages/Home';
import Listings from './pages/Listings';
import Details from './pages/Details';
import Login from './pages/Login';
import Register from './pages/Register';
import Dashboard from './pages/Dashboard';

// Import layout components that appear on every page
import Header from './components/Header';
import Footer from './components/Footer';

// Import global styles
import './styles/global.css';

function App() {
  return (
    <div className="App">
      {/* Router wraps the entire app to enable navigation */}
      <Router>
        {/* Header appears on every page */}
        <Header />
        
        {/* Main content area where pages are displayed */}
        <main>
          <Routes>
            {/* Define all the routes/pages in our app */}
            <Route path="/" element={<Home />} />                    {/* Homepage */}
            <Route path="/listings" element={<Listings />} />        {/* All movies/shows */}
            <Route path="/details/:id" element={<Details />} />      {/* Individual item details */}
            <Route path="/login" element={<Login />} />              {/* User login */}
            <Route path="/register" element={<Register />} />        {/* User registration */}
            <Route path="/dashboard" element={<Dashboard />} />      {/* User dashboard - Assignment 2 */}
          </Routes>
        </main>
        
        {/* Footer appears on every page */}
        <Footer />
      </Router>
    </div>
  );
}

export default App;
