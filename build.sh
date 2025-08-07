#!/bin/bash
echo "Building Spring Boot application..."
cd backend
chmod +x mvnw
./mvnw clean package -DskipTests
echo "Build completed successfully!"
