# 🚀 Digital Video Store - Vercel Deployment Guide

This guide will help you deploy your Digital Video Store to production.

## 📋 Prerequisites

1. **GitHub Account** - To store your code
2. **Vercel Account** - For frontend deployment (free tier available)
3. **Railway/Render Account** - For backend deployment (free tier available)
4. **MongoDB Atlas** - Your database (already set up)

## 🏗️ Deployment Architecture

```
Frontend (React) → Vercel
Backend (Spring Boot) → Railway/Render  
Database → MongoDB Atlas ✅ (Already configured)
```

## 📂 Step 1: Prepare Your Repository

### 1.1 Initialize Git Repository (if not done)
```bash
cd c:\Users\bhrug\DigitalVideoStore
git init
git add .
git commit -m "Initial commit - Digital Video Store"
```

### 1.2 Push to GitHub
```bash
# Create a new repository on GitHub first, then:
git remote add origin https://github.com/yourusername/digital-video-store.git
git branch -M main
git push -u origin main
```

## 🖥️ Step 2: Deploy Backend (Spring Boot)

### Option A: Railway (Recommended)

1. **Sign up at [Railway.app](https://railway.app)**
2. **Connect your GitHub account**
3. **Deploy from GitHub:**
   - Click "New Project"
   - Select "Deploy from GitHub repo"
   - Choose your repository
   - Railway will auto-detect it's a Spring Boot app
4. **Environment Variables:**
   Add these in Railway dashboard:
   ```
   SPRING_DATA_MONGODB_URI=your_mongodb_connection_string
   SPRING_DATA_MONGODB_DATABASE=DigitalVideoStore
   SERVER_PORT=8080
   ```
5. **Custom Start Command:** (if needed)
   ```
   java -jar target/restapi-0.0.1-SNAPSHOT.jar
   ```

### Option B: Render

1. **Sign up at [Render.com](https://render.com)**
2. **Create a new Web Service**
3. **Connect your GitHub repo**
4. **Build Command:**
   ```
   cd backend_new/restapi && ./mvnw clean package -DskipTests
   ```
5. **Start Command:**
   ```
   java -jar backend_new/restapi/target/restapi-0.0.1-SNAPSHOT.jar
   ```

## 🌐 Step 3: Deploy Frontend (Vercel)

### 3.1 Update Environment Variables

After your backend is deployed, update `.env.production`:

```env
# Replace with your actual backend URL
REACT_APP_API_URL=https://your-backend-app.railway.app/api
REACT_APP_FALLBACK_API_URL=https://api.jsonbin.io/v3/your-backup-data
```

### 3.2 Vercel Deployment

1. **Sign up at [Vercel.com](https://vercel.com)**
2. **Import Project:**
   - Click "New Project"
   - Import from GitHub
   - Select your repository
3. **Configure Build Settings:**
   - **Framework Preset:** Create React App
   - **Root Directory:** `frontend`
   - **Build Command:** `npm run build`
   - **Output Directory:** `build`
4. **Environment Variables:**
   Add in Vercel dashboard:
   ```
   REACT_APP_API_URL=https://your-backend-url.railway.app/api
   ```
5. **Deploy!**

## 🔧 Step 4: Update CORS Settings

Update your Spring Boot backend to allow your Vercel domain:

```java
// In MediaController.java (and other controllers)
@CrossOrigin(origins = {
    "http://localhost:3000", 
    "http://localhost:3001",
    "https://your-app-name.vercel.app"  // Add your Vercel URL
})
```

## ✅ Step 5: Test Your Deployment

1. **Visit your Vercel URL**
2. **Check browser console** for any API errors
3. **Test all functionality:**
   - Homepage loads with content
   - Search works
   - Movie/TV show details load
   - Featured content displays

## 🔄 Step 6: Continuous Deployment

Both Vercel and Railway support automatic deployments:
- **Push to main branch** → Automatic deployment
- **Preview deployments** for feature branches
- **Rollback capabilities** if needed

## 🛠️ Common Issues & Solutions

### CORS Errors
- Update `@CrossOrigin` annotations in backend
- Ensure your Vercel URL is in allowed origins

### Environment Variables Not Working
- Check exact variable names (REACT_APP_ prefix required)
- Redeploy after adding environment variables

### API Not Found (404)
- Verify backend URL in environment variables
- Check backend is running and healthy

### Build Failures
- Ensure `package.json` is in frontend directory
- Check for any TypeScript errors
- Verify all dependencies are listed

## 📊 Monitoring & Analytics

- **Vercel Analytics** - For frontend performance
- **Railway Metrics** - For backend monitoring  
- **MongoDB Atlas Monitoring** - For database performance

## 💰 Cost Considerations

**Free Tier Limits:**
- **Vercel:** 100GB bandwidth, unlimited sites
- **Railway:** $5 credit/month, 500 hours runtime
- **Render:** 750 hours/month, sleeps after 15min inactivity

## 🚀 Next Steps After Deployment

1. **Custom Domain** (Optional)
2. **SSL Certificate** (Auto-enabled)
3. **SEO Optimization**
4. **Performance Monitoring**
5. **Error Tracking** (Sentry integration)

## 📞 Support

If you encounter issues:
1. Check the deployment logs
2. Verify environment variables
3. Test API endpoints directly
4. Check CORS settings

Your Digital Video Store will be live and accessible worldwide! 🎬🌍
