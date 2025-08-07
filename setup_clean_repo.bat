@echo off
echo 🚀 Digital Video Store - New Repository Setup
echo =============================================

REM Check if we're in the right directory
if not exist "DigitalVideoStore" (
    echo ❌ Please run this script from the directory containing DigitalVideoStore
    pause
    exit /b 1
)

echo 📁 Creating clean project structure...

REM Create new directory for the clean repository
mkdir digital-video-store-clean
cd digital-video-store-clean

echo 📂 Copying Digital Video Store files...

REM Copy backend
xcopy /E /I /H ..\DigitalVideoStore\backend_new\restapi backend
echo ✅ Backend copied

REM Copy frontend  
xcopy /E /I /H ..\DigitalVideoStore\frontend frontend
echo ✅ Frontend copied

REM Copy configuration and documentation files
copy ..\DigitalVideoStore\vercel.json .
copy ..\DigitalVideoStore\VERCEL_DEPLOYMENT_GUIDE.md .
copy ..\DigitalVideoStore\deploy.bat .
copy ..\DigitalVideoStore\deploy.sh .
copy ..\DigitalVideoStore\.gitignore .
copy ..\DigitalVideoStore\add_more_content.py .
copy ..\DigitalVideoStore\remove_duplicates.py .

echo ✅ Configuration files copied

REM Create a clean README
echo # 🎬 Digital Video Store > README.md
echo. >> README.md
echo A modern full-stack web application for streaming digital movies and TV shows. >> README.md
echo. >> README.md
echo ## 🏗️ Architecture >> README.md
echo. >> README.md
echo - **Frontend**: React.js with modern UI components >> README.md
echo - **Backend**: Spring Boot REST API >> README.md
echo - **Database**: MongoDB Atlas >> README.md
echo - **Deployment**: Vercel (Frontend) + Railway (Backend) >> README.md
echo. >> README.md
echo ## 🚀 Quick Start >> README.md
echo. >> README.md
echo ### Development >> README.md
echo. >> README.md
echo 1. **Backend (Spring Boot)**: >> README.md
echo    ```bash >> README.md
echo    cd backend >> README.md
echo    ./mvnw spring-boot:run >> README.md
echo    ``` >> README.md
echo. >> README.md
echo 2. **Frontend (React)**: >> README.md
echo    ```bash >> README.md
echo    cd frontend >> README.md
echo    npm install >> README.md
echo    npm start >> README.md
echo    ``` >> README.md
echo. >> README.md
echo See VERCEL_DEPLOYMENT_GUIDE.md for complete deployment instructions. >> README.md

echo ✅ Clean README.md created

REM Initialize git repository
echo 🔧 Initializing Git repository...
git init
git add .
git commit -m "Initial commit: Digital Video Store - React frontend with responsive design - Spring Boot backend with REST API - MongoDB Atlas integration - Vercel deployment ready - Clean project structure"

echo.
echo 🎉 Clean repository created successfully!
echo.
echo 📋 Next steps:
echo 1. Create new repository on GitHub named 'digital-video-store'
echo 2. Run these commands:
echo    git remote add origin https://github.com/yourusername/digital-video-store.git
echo    git branch -M main
echo    git push -u origin main
echo.
echo 3. Deploy to Vercel and Railway using the deployment guide
echo.
echo 📁 Your clean project is in: %cd%
pause
