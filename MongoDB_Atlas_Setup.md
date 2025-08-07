# MongoDB Atlas Integration Setup Guide

## ✅ Completed Configuration

### 1. MongoDB Atlas Connection
- **Connection String**: `mongodb+srv://braval5:E1cVluARNzmXZaw2@streamvault.o1obulk.mongodb.net/streamvault-db`
- **Database**: `streamvault-db`
- **Collection**: `media-catalog`

### 2. Updated Files
- ✅ `pom.xml` - Added MongoDB dependency
- ✅ `application.properties` - MongoDB Atlas connection
- ✅ `Media.java` - MongoDB document entity
- ✅ `MediaRepository.java` - MongoDB repository
- ✅ `MediaService.java` - Updated for MongoDB
- ✅ `MediaController.java` - Updated API endpoints
- ✅ `MongoDataInitializer.java` - Sample data loader
- ✅ React frontend pages - Updated to use Spring Boot API

## 🔧 Required Java Setup (One-time only)

### Option 1: Install Java 17+ (Recommended)
1. Download Java 17 from: https://adoptium.net/
2. Install and add to PATH
3. Verify: `java -version`

### Option 2: Use Existing Java Installation
If you have Java installed but Maven wrapper can't find it:
1. Find your Java installation path
2. Set JAVA_HOME environment variable
3. Add to PATH if needed

## 🚀 Testing the Integration

### Step 1: Start Spring Boot Backend
```bash
cd backend
.\mvnw.cmd spring-boot:run
```

**Expected Output:**
```
Started StreamVaultApiApplication in X.XXX seconds
Connected to MongoDB Atlas successfully
Initialized MongoDB Atlas with 24 media items
```

### Step 2: Test API Endpoints
Once backend is running, test these URLs in your browser:

1. **All Media**: http://localhost:8080/api/media
2. **Movies Only**: http://localhost:8080/api/movies
3. **TV Shows Only**: http://localhost:8080/api/tvshows
4. **Single Item**: http://localhost:8080/api/media/{id}

### Step 3: Start React Frontend
```bash
cd frontend
npm start
```

The React app will automatically:
1. Try Spring Boot API (port 8080) first
2. Fallback to json-server (port 3001) if Spring Boot is not available

## 📊 MongoDB Atlas Dashboard

### View Your Data
1. Go to https://cloud.mongodb.com/
2. Sign in with your account
3. Navigate to your StreamVault cluster
4. Click "Browse Collections"
5. You should see:
   - **Database**: `streamvault-db`
   - **Collection**: `media-catalog`
   - **Documents**: 24 movies and TV shows

### Sample Document Structure
```json
{
  "_id": "ObjectId(...)",
  "title": "The Matrix",
  "type": "movie",
  "synopsis": "A computer hacker learns...",
  "poster": "https://image.tmdb.org/t/p/w500/...",
  "poster_large": "https://image.tmdb.org/t/p/w780/...",
  "rent": 3.99,
  "buy": 12.99
}
```

## 🎯 API Endpoints Reference

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/media` | Get all media items |
| GET | `/api/media/{id}` | Get specific media by ID |
| GET | `/api/movies` | Get all movies |
| GET | `/api/tvshows` | Get all TV shows |
| GET | `/api/media/type/{type}` | Get media by type |
| GET | `/api/media/search?title=keyword` | Search by title |
| GET | `/api/media/price?min=5&max=20` | Filter by price range |
| POST | `/api/media` | Create new media |
| PUT | `/api/media/{id}` | Update media |
| DELETE | `/api/media/{id}` | Delete media |

## 🔍 Troubleshooting

### Java Issues
- **Error**: "JAVA_HOME not defined"
  - **Solution**: Install Java or set JAVA_HOME environment variable

### MongoDB Connection Issues
- **Error**: "Failed to connect to MongoDB"
  - **Solution**: Check internet connection and MongoDB Atlas credentials

### API Issues
- **Error**: "Failed to fetch from Spring Boot API"
  - **Solution**: Ensure backend is running on port 8080
  - **Fallback**: Frontend will use json-server on port 3001

### Port Conflicts
- **Spring Boot**: Default port 8080
- **React**: Default port 3000
- **json-server**: Port 3001 (fallback)

## 🎉 Success Indicators

✅ **Backend Started Successfully**
- Console shows: "Started StreamVaultApiApplication"
- MongoDB connection established
- Sample data loaded (24 items)

✅ **Frontend Connected**
- Home page loads with movies and TV shows
- Filtering works on Listings page
- Detail pages load individual items

✅ **MongoDB Atlas Working**
- Data visible in Atlas dashboard
- API endpoints return JSON data
- CRUD operations work

## 🚀 Production Considerations

### Security
- Move connection string to environment variables
- Add authentication and authorization
- Enable MongoDB Atlas IP whitelist

### Performance
- Add connection pooling settings
- Implement caching for frequently accessed data
- Add pagination for large datasets

### Monitoring
- Enable MongoDB Atlas monitoring
- Add application logging
- Set up health checks
