#!/bin/bash

# Compile Java files
echo "Compiling Java files..."
mkdir -p bin
javac -d bin -sourcepath src/main/java $(find src/main/java -name "*.java")

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
else
    echo "✗ Compilation failed!"
    exit 1
fi

# Run the application
echo "Starting application..."
cd bin
java -cp . ui.MainFrame
