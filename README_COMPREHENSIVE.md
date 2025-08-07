# 🎬 StreamVault - Digital Video Store

A full-stack web application for a digital video store featuring movie and TV show streaming, user authentication, and content management.

## 🌐 Live Deployment

- **Frontend**: https://digital-video-store-iota.vercel.app (Vercel)
- **Backend API**: https://digital-video-store-production.up.railway.app (Railway)  
- **Database**: MongoDB Atlas (Cloud)

## 📋 Assignment Requirements ✅

This project successfully combines Assignment 1 (Frontend) and Assignment 2 (Backend):

### ✅ Frontend Features (Assignment 1)
- **Home Page**: Featured movies and TV shows with hero slider
- **Listings Page**: Browse all content with search functionality
- **Details Page**: Individual movie/show information with rent/buy options
- **User Authentication**: Login and registration forms
- **Responsive Design**: Works on desktop and mobile devices

### ✅ Backend Features (Assignment 2)  
- **REST API**: Complete CRUD operations for media management
- **User Management**: Registration and login endpoints
- **Database Integration**: MongoDB Atlas for data persistence
- **CORS Configuration**: Enables frontend-backend communication

### ✅ Integration Features
- **Authentication Flow**: Login/registration with session management
- **API Integration**: Frontend consumes backend REST APIs
- **State Management**: User authentication state across components
- **Production Deployment**: Both frontend and backend deployed to cloud

## 🏗️ Architecture

```
Frontend (React) ----HTTP/HTTPS----> Backend (Spring Boot) -----> MongoDB Atlas
     ↓                                      ↓                           ↓
   Vercel                               Railway                    Cloud Database
```

## 🛠️ Technology Stack

### Frontend
- **Framework**: React.js 18
- **Routing**: React Router DOM
- **Styling**: CSS3 with modern animations
- **HTTP Client**: Fetch API
- **Deployment**: Vercel

### Backend  
- **Framework**: Spring Boot 3
- **Database**: MongoDB with Spring Data
- **Security**: BCrypt password hashing
- **CORS**: Global configuration for frontend integration
- **Deployment**: Railway

### Database
- **Type**: NoSQL (MongoDB Atlas)
- **Collections**: `media` (movies/TV shows), `customers` (users)
- **Features**: Text search, indexing, cloud hosting

## 📁 Project Structure

```
DigitalVideoStore/
├── frontend/                    # React.js frontend application
│   ├── public/                 # Static assets
│   ├── src/
│   │   ├── components/         # Reusable UI components
│   │   │   ├── Header.js       # Navigation with user dropdown
│   │   │   ├── Footer.js       # Site footer
│   │   │   └── HeroSlider.js   # Homepage image carousel
│   │   ├── pages/              # Page components
│   │   │   ├── Home.js         # Homepage with featured content
│   │   │   ├── Listings.js     # Browse all movies/shows
│   │   │   ├── Details.js      # Individual content details
│   │   │   ├── Login.js        # User login form
│   │   │   ├── Register.js     # User registration form
│   │   │   └── Dashboard.js    # User dashboard
│   │   ├── styles/             # CSS styling
│   │   │   └── global.css      # Global styles and themes
│   │   └── config/
│   │       └── api.js          # API configuration
│   └── package.json            # Dependencies and scripts
└── backend/                     # Spring Boot backend application
    ├── src/main/java/com/bhrugu/api/restapi/
    │   ├── controller/         # REST API endpoints
    │   │   ├── MediaController.java      # Media CRUD operations
    │   │   └── AuthController.java       # Authentication endpoints
    │   ├── service/            # Business logic layer
    │   │   ├── MediaService.java         # Media business logic
    │   │   └── CustomerService.java      # User management logic
    │   ├── model/              # Data models
    │   │   ├── Media.java                # Movie/TV show entity
    │   │   └── Customer.java             # User entity
    │   ├── repository/         # Data access layer
    │   │   ├── MediaRepository.java      # Media database operations
    │   │   └── CustomerRepository.java   # User database operations
    │   └── dto/                # Data transfer objects
    │       ├── ApiResponse.java          # Standard API response wrapper
    │       ├── CustomerLoginRequest.java
    │       └── CustomerRegistrationRequest.java
    └── pom.xml                 # Maven dependencies
```

## 🚀 Features

### 🎭 Content Management
- **Browse Movies & TV Shows**: Complete catalog with search functionality
- **Detailed Information**: Synopsis, pricing, high-quality posters
- **Search**: Find content by title with real-time results
- **Categories**: Filter by movies or TV shows

### 👤 User Authentication  
- **Registration**: Create new accounts with email validation
- **Login**: Secure authentication with session management
- **User Dashboard**: Personalized user area
- **Profile Management**: User information and settings

### 🎨 User Interface
- **Modern Design**: Dark theme with gradient backgrounds
- **Responsive**: Mobile-friendly responsive design
- **Interactive**: Hover effects, smooth animations
- **User Dropdown**: Professional authentication UI with user's name

### 🔧 Admin Features (via Postman/API)
- **Content Management**: Add, edit, delete movies and TV shows
- **User Management**: View registered users
- **Database Operations**: Direct API access for content updates

## 🚦 API Endpoints

### Authentication Endpoints
```
POST /api/auth/register    # User registration
POST /api/auth/login       # User login  
GET  /api/auth/health      # Service health check
```

### Media Management Endpoints
```
GET    /api/media          # Get all media
GET    /api/movies         # Get all movies
GET    /api/tvshows        # Get all TV shows
GET    /api/media/{id}     # Get specific media by ID
GET    /api/media/search?title={query}  # Search media by title
POST   /api/media          # Create new media
PUT    /api/media/{id}     # Update existing media
DELETE /api/media/{id}     # Delete media
```

## 🗄️ Database Schema

### Media Collection
```javascript
{
  "_id": "ObjectId",
  "title": "String",
  "type": "movie | tv-show", 
  "synopsis": "String",
  "poster": "String (URL)",
  "posterLarge": "String (URL)",
  "rent": "Number",
  "buy": "Number"
}
```

### Customer Collection
```javascript
{
  "_id": "ObjectId",
  "fullName": "String",
  "email": "String (unique)",
  "password": "String (BCrypt hashed)",
  "registrationDate": "DateTime"
}
```

## 🔧 Local Development Setup

### Prerequisites
- Node.js 16+ and npm
- Java 17+
- Maven 3.6+
- Git

### Frontend Setup
```bash
cd frontend
npm install
npm start  # Runs on http://localhost:3000
```

### Backend Setup  
```bash
cd backend
./mvnw spring-boot:run  # Runs on http://localhost:8080
```

### Environment Variables
Create `.env` files for configuration:

**Frontend (.env)**:
```
REACT_APP_API_URL=https://digital-video-store-production.up.railway.app/api
```

**Backend (application.properties)**:
```
spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/streamvault-db
```

## 🚀 Deployment Process

### Frontend (Vercel)
1. Connected to GitHub repository
2. Automatic deployments on push to main branch
3. Environment variables configured in Vercel dashboard
4. Custom domain configured

### Backend (Railway)  
1. Connected to GitHub repository
2. Automatic deployments on push to main branch
3. Environment variables configured in Railway dashboard
4. Custom domain provided by Railway

### Database (MongoDB Atlas)
1. Cloud-hosted MongoDB cluster
2. Network access configured for Railway and development
3. Database users and permissions set up
4. Indexes created for optimal performance

## 🧪 Testing

### API Testing (Postman)
Complete Postman collection available for testing all endpoints:
- Authentication testing (login/register)
- Media CRUD operations  
- Search functionality
- Error handling verification

### Manual Testing Scenarios
1. **User Registration Flow**: Create account → Auto login → Dashboard redirect
2. **User Login Flow**: Login → Header updates → Dashboard access
3. **Content Browsing**: Home → Listings → Search → Details
4. **Responsive Testing**: Mobile and desktop layouts
5. **Authentication Persistence**: Page refresh, browser restart

## 🐛 Troubleshooting

### Common Issues and Solutions

**CORS Errors**:
- Verify backend CORS configuration includes frontend domain
- Check that API URLs match between environments

**Authentication Not Persisting**:  
- Check localStorage for `user` and `isLoggedIn` keys
- Verify custom events are being triggered and listened to

**API 404 Errors**:
- Ensure correct endpoint paths (include `/api` prefix)
- Verify backend is running and accessible
- Check MongoDB ObjectId format (24 characters)

**Build/Deployment Issues**:
- Check environment variables in deployment platforms
- Verify all dependencies are included in package.json/pom.xml
- Review deployment logs for specific errors

## 🔮 Future Enhancements

### Planned Features
- **Payment Integration**: Stripe/PayPal for actual purchases
- **User Reviews**: Rating and review system
- **Recommendations**: AI-powered content suggestions  
- **Favorites**: User watchlists and favorites
- **Admin Dashboard**: Web-based content management
- **Video Streaming**: Actual video playback functionality

### Technical Improvements
- **JWT Authentication**: Token-based auth instead of localStorage
- **Caching**: Redis for improved performance
- **CDN Integration**: Cloudflare for asset delivery
- **Monitoring**: Application performance monitoring
- **Testing**: Unit and integration test suites

## 👨‍💻 Development Team

- **Developer**: Bhrugu Patel
- **GitHub**: [@bhrugu1](https://github.com/bhrugu1)
- **Repository**: [profassign](https://github.com/bhrugu1/profassign)

## 📄 License

This project is developed for educational purposes as part of a web development assignment.

---

## 🎯 Assignment Completion Status

✅ **Frontend-Backend Integration**: Successfully connected React frontend to Spring Boot backend
✅ **User Authentication**: Complete login/registration system with session management  
✅ **Content Management**: Full CRUD operations for movies and TV shows
✅ **Database Integration**: MongoDB Atlas with proper data modeling
✅ **Production Deployment**: Both frontend and backend deployed to cloud platforms
✅ **Professional UI**: Modern, responsive design with user dropdown authentication
✅ **API Documentation**: Complete endpoint documentation and testing setup
✅ **Code Quality**: Comprehensive commenting and documentation

**Result**: All assignment requirements successfully implemented and deployed! 🎉
