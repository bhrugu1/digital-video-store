# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the backend directory
COPY backend ./backend

# Set working directory to backend
WORKDIR /app/backend

# Make maven wrapper executable
RUN chmod +x ./mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Find the JAR file and copy it to a predictable location
RUN cp target/*.jar app.jar

# Expose port (Railway will set PORT environment variable)
EXPOSE $PORT

# Set environment variable for production
ENV SPRING_PROFILES_ACTIVE=production

# Run the application
CMD java -Dserver.port=${PORT:-8080} -jar app.jar
