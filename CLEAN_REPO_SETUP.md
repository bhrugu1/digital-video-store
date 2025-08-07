# 🏗️ Clean Repository Setup Guide

## 🎯 Recommended: Create New Repository

### Why Create a New Repository?

1. **Current Issues:**
   - Repository name "profassign" is not descriptive
   - Contains multiple unrelated projects (Lego + Video Store)
   - Mixed structure confuses deployment tools

2. **Benefits of New Repo:**
   - Professional naming (`digital-video-store`)
   - Clean structure for deployment
   - Focus on single project
   - Better organization

## 📋 Step-by-Step Setup

### Option 1: Automated Setup (Recommended)

1. **Run the setup script:**
   ```bash
   # From c:\Users\bhrug\
   cd DigitalVideoStore
   setup_clean_repo.bat
   ```

2. **Follow the prompts** - it will create a clean project structure

### Option 2: Manual Setup

1. **Create new directory:**
   ```bash
   mkdir digital-video-store-clean
   cd digital-video-store-clean
   ```

2. **Copy necessary files:**
   ```
   Copy these from DigitalVideoStore/:
   ├── backend_new/restapi/ → backend/
   ├── frontend/ → frontend/
   ├── vercel.json
   ├── VERCEL_DEPLOYMENT_GUIDE.md
   ├── deploy.bat
   ├── .gitignore
   ├── add_more_content.py
   └── remove_duplicates.py
   ```

3. **Create clean README.md** (see template below)

4. **Initialize Git:**
   ```bash
   git init
   git add .
   git commit -m "Initial commit: Digital Video Store"
   ```

## 🌐 GitHub Repository Creation

1. **Go to GitHub.com**
2. **Create New Repository:**
   - Name: `digital-video-store`
   - Description: "Full-stack digital movie and TV show streaming platform"
   - Public/Private: Your choice
   - Don't initialize with README (we have our own)

3. **Connect local repository:**
   ```bash
   git remote add origin https://github.com/yourusername/digital-video-store.git
   git branch -M main
   git push -u origin main
   ```

## 📁 Clean Project Structure

After setup, your new repository will have:

```
digital-video-store/
├── backend/                    # Spring Boot API
│   ├── src/
│   ├── pom.xml
│   └── mvnw.cmd
├── frontend/                   # React app
│   ├── src/
│   ├── public/
│   ├── package.json
│   └── build/
├── vercel.json                # Vercel config
├── deploy.bat                 # Deployment helper
├── VERCEL_DEPLOYMENT_GUIDE.md # Complete guide
├── .gitignore                 # Git ignore rules
├── README.md                  # Professional README
└── utility scripts...
```

## 🎬 Professional README Template

Your new README.md will include:
- Project description
- Architecture overview
- Quick start instructions
- Tech stack details
- Deployment information
- Contributing guidelines

## ✅ Benefits After Setup

1. **Professional appearance** for potential employers/clients
2. **Deployment-ready** structure for Vercel/Railway
3. **Clean git history** starting fresh
4. **Focused project** without unrelated code
5. **Better documentation** with comprehensive README

## 🚀 Next Steps After Clean Repo

1. **Push to GitHub** ✅
2. **Deploy backend** to Railway/Render
3. **Deploy frontend** to Vercel
4. **Update environment variables**
5. **Test production deployment**

## ⚠️ What About Current Repo?

**Keep the current `profassign` repository** for your school assignments (WEB700-A3 Lego project), but use the new clean repository for your Digital Video Store portfolio project.

This separation keeps your academic work separate from your professional portfolio project.

---

**Recommendation:** Go with the automated setup using `setup_clean_repo.bat` - it handles everything for you! 🎯
