#!/bin/bash
set -e

echo "🔨 Building Spring Boot application..."
./gradlew clean build -x test

echo "✅ Build completed successfully!"
echo "📦 JAR file: build/libs/quan-ly-diem-1.0.0.jar"
