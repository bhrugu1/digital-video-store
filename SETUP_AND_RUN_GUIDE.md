# 🚀 Digital Video Store API - Setup & Run Guide

## 📋 Prerequisites Installation

### 1. Install Java Development Kit (JDK)
```bash
# Download and install JDK 17 or later from:
# https://adoptium.net/temurin/releases/
# or
# https://www.oracle.com/java/technologies/downloads/

# After installation, verify:
java -version
javac -version
```

### 2. Install Maven (Optional - project includes Maven Wrapper)
```bash
# Download from: https://maven.apache.org/download.cgi
# Or use Chocolatey (if installed):
choco install maven

# Verify installation:
mvn -version
```

## 🏃‍♂️ Running the Project

### Option 1: Using Maven Wrapper (Recommended)
```powershell
# Navigate to the project directory
cd "C:\Users\bhrug\DigitalVideoStore\backend_new\restapi"

# Run using Maven Wrapper (Windows)
.\mvnw.cmd clean spring-boot:run

# Or for PowerShell
cmd /c "mvnw.cmd clean spring-boot:run"
```

### Option 2: Using Installed Maven
```powershell
# Navigate to the project directory
cd "C:\Users\bhrug\DigitalVideoStore\backend_new\restapi"

# Run the application
mvn clean spring-boot:run
```

### Option 3: Using IDE (IntelliJ IDEA / Eclipse)
1. Import the project as a Maven project
2. Wait for dependencies to download
3. Run the main class: `RestapiApplication.java`
4. Or use the IDE's Spring Boot run configuration

## 🔧 Environment Setup

### Set JAVA_HOME (if needed)
```powershell
# Find Java installation path
$javaPath = (Get-Command java -ErrorAction SilentlyContinue).Source
if ($javaPath) {
    $javaHome = Split-Path (Split-Path $javaPath)
    [Environment]::SetEnvironmentVariable("JAVA_HOME", $javaHome, "User")
    echo "JAVA_HOME set to: $javaHome"
}
```

## 📊 Application Information

### Server Details
- **Port**: 8080
- **Base URL**: http://localhost:8080
- **Database**: MongoDB Atlas (configured in application.properties)

### API Endpoints Available

#### 🎬 Media Management (9 endpoints)
1. `POST /api/media` - Create movie/TV show
2. `GET /api/movies` - Get all movies
3. `GET /api/tvshows` - Get all TV shows
4. `GET /api/media/search?title=keyword` - Search by title
5. `GET /api/featured/movies` - Get featured movies
6. `GET /api/featured/tvshows` - Get featured TV shows
7. `GET /api/media/{id}` - Get specific media
8. `PUT /api/media/{id}` - Update media
9. `DELETE /api/media/{id}` - Delete media

#### 🔐 User Authentication (2 endpoints)
10. `POST /api/auth/register` - User registration
11. `POST /api/auth/login` - User authentication

#### 👥 Customer Management (2 endpoints)
12. `POST /api/customers/register` - Customer registration
13. `GET /api/customers/{id}` - Get customer by ID

## 🧪 Testing the API

### Using curl (after starting the server)
```bash
# Test server is running
curl http://localhost:8080/api/movies

# Create a movie
curl -X POST http://localhost:8080/api/media \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Matrix",
    "type": "movie",
    "synopsis": "A hacker discovers reality is a simulation",
    "rent": 4.99,
    "buy": 14.99,
    "featured": true
  }'

# Register a user
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "securePass123",
    "firstName": "John",
    "lastName": "Doe"
  }'
```

### Using Postman or Insomnia
Import the endpoints and test with the JSON payloads shown above.

## 🐛 Troubleshooting

### Common Issues

1. **"JAVA_HOME not set"**
   - Install JDK and set JAVA_HOME environment variable
   - Restart terminal/IDE after setting

2. **"Port 8080 already in use"**
   - Kill process using port 8080: `netstat -ano | findstr :8080`
   - Or change port in `application.properties`: `server.port=8081`

3. **MongoDB Connection Issues**
   - Check internet connection
   - Verify MongoDB Atlas credentials in `application.properties`

4. **Dependencies not downloading**
   - Clear Maven cache: `mvn dependency:purge-local-repository`
   - Check internet connection for Maven Central

## ✅ Success Indicators

When the application starts successfully, you should see:
```
Started RestapiApplication in X.XXX seconds
Tomcat started on port(s): 8080 (http)
```

The API will be available at: **http://localhost:8080**

## 📁 Project Structure

```
backend_new/restapi/
├── src/main/java/com/bhrugu/api/restapi/
│   ├── model/          # Customer, Media, User entities
│   ├── repository/     # Data access layers
│   ├── service/        # Business logic
│   ├── controller/     # REST endpoints
│   ├── dto/           # Data transfer objects
│   └── RestapiApplication.java
├── src/main/resources/
│   └── application.properties
├── pom.xml
└── mvnw.cmd           # Maven wrapper
```

Your comprehensive Digital Video Store API is ready to run! 🎉
