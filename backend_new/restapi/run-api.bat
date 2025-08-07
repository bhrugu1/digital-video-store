@echo off
echo Starting Digital Video Store API...
echo.
echo Setting up Java environment...
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%

echo Java Version:
java -version
echo.

echo Compiling and running Spring Boot application...
echo.

REM Method 1: Try Maven wrapper
if exist mvnw.cmd (
    echo Using Maven wrapper...
    call mvnw.cmd clean spring-boot:run
) else (
    echo Maven wrapper not found, trying alternative method...
    
    REM Method 2: Try installed Maven
    mvn clean spring-boot:run
)

pause
