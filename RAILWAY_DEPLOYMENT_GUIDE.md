# Railway Deployment Guide for Digital Video Store Backend

## Problem Solution
The Railway deployment was failing because:
1. Railway was trying to use an empty `pom.xml` in the root directory
2. Nixpacks couldn't properly detect the Spring Boot app in the subdirectory

This has been fixed with multiple configuration approaches:

### Files Added/Updated:
1. `railway.toml` - Railway configuration with explicit build commands
2. `nixpacks.toml` - Nixpacks-specific configuration
3. `Procfile` - Heroku-style process file
4. `build.sh` - Build script for Railway
5. Removed empty `pom.xml` from root directory
6. Updated `backend/src/main/resources/application.properties` - Railway-compatible configuration

## Deployment Steps

### 1. Push Updated Configuration
```bash
git add .
git commit -m "Fix Railway deployment with multiple configuration approaches"
git push origin main
```

### 2. Railway Setup
1. Go to [Railway.app](https://railway.app/)
2. Login with GitHub
3. If you already have a project, delete it and create a new one
4. Click "New Project" → "Deploy from GitHub repo"
5. Select your `digital-video-store` repository
6. Railway should now detect and build the Spring Boot application

### 3. Manual Override (if needed)
If Railway still has issues, manually set these in Railway dashboard:
- **Build Command**: `cd backend && ./mvnw clean package -DskipTests`
- **Start Command**: `cd backend && java -Dserver.port=$PORT -jar target/*.jar`
- **Root Directory**: Leave empty

### 4. Environment Variables
Railway should automatically detect these, but you can add manually if needed:
- `SPRING_PROFILES_ACTIVE=production`
- `JAVA_VERSION=17`

### 4. Expected Railway Behavior
- Railway will detect the Spring Boot app using our configuration
- It will run `./mvnw clean spring-boot:run` from the backend directory
- The app will be accessible on Railway's generated URL

## Verification Steps
1. Check Railway deployment logs for successful startup
2. Test the API endpoints:
   - GET `https://your-railway-url/api/media/movies`
   - GET `https://your-railway-url/api/media/tv-shows`
3. Update frontend environment variables to use Railway URL

## Troubleshooting
If Railway still has issues:
1. Check the build logs in Railway dashboard
2. Ensure all Maven dependencies are properly specified in pom.xml
3. Consider using Railway's "Custom Start Command": `cd backend && ./mvnw spring-boot:run`

## Alternative: Manual Railway Configuration
If automatic detection still fails, you can manually configure in Railway dashboard:
- Build Command: `cd backend && ./mvnw clean package -DskipTests`
- Start Command: `cd backend && java -jar target/*.jar`
- Root Directory: Leave empty (we handle it in commands)

## Next Steps After Backend Deployment
1. Get the Railway backend URL
2. Update frontend environment variables to use production backend URL
3. Redeploy frontend to Vercel
4. Test full-stack application in production
