#!/bin/bash
set -e
echo "🔧 Building Spring Boot application..."
cd backend
echo "📁 Current directory: $(pwd)"
echo "📋 Making mvnw executable..."
chmod +x mvnw
echo "🏗️  Running Maven build..."
./mvnw clean package -DskipTests -q
echo "✅ Build completed successfully!"
echo "📦 JAR files created:"
ls -la target/*.jar
