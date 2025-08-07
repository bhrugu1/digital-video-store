# Railway Deployment Guide for Digital Video Store Backend

## Problem Solution
The Railway deployment was failing because Nixpacks couldn't detect the Spring Boot application in the `backend/` subdirectory. This has been fixed with the following configuration files:

### Files Added:
1. `railway.toml` - Tells Railway to build and deploy from the backend directory
2. `Procfile` - Alternative deployment configuration
3. Updated `backend/src/main/resources/application.properties` - Railway-compatible configuration

## Deployment Steps

### 1. Push Updated Configuration
```bash
git add .
git commit -m "Add Railway deployment configuration"
git push origin main
```

### 2. Railway Setup
1. Go to [Railway.app](https://railway.app/)
2. Login with GitHub
3. Click "New Project" → "Deploy from GitHub repo"
4. Select your `digital-video-store` repository
5. Railway should now detect the Spring Boot application correctly

### 3. Environment Variables (if needed)
If Railway needs additional configuration, add these environment variables in Railway dashboard:
- `SPRING_PROFILES_ACTIVE=production`
- `PORT=8080` (Railway usually sets this automatically)

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
