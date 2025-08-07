@echo off
echo Starting StreamVault Application...
echo.

echo Starting Spring Boot API...
cd backend
start "Spring Boot API" cmd /k "mvnw.cmd spring-boot:run"

echo Waiting for API to start...
timeout /t 15

echo Starting React Frontend...
cd ..\frontend
start "React Frontend" cmd /k "npm start"

echo.
echo Both servers should be starting:
echo - Spring Boot API: http://localhost:8080
echo - React Frontend: http://localhost:3000
echo - H2 Database Console: http://localhost:8080/h2-console
echo.
echo API Endpoints available:
echo - GET http://localhost:8080/api/media (All media)
echo - GET http://localhost:8080/api/media/{id} (Specific media)
echo - GET http://localhost:8080/api/movies (All movies)
echo - GET http://localhost:8080/api/tvshows (All TV shows)
echo.
pause
