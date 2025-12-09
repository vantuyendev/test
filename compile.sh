#!/bin/bash
# Compile script for Linux/Mac with better error handling

set -e  # Exit on error

echo "╔════════════════════════════════════════════════════╗"
echo "║  Hệ Thống Quản Lý Điểm Học Tập THCS - Java Build  ║"
echo "╚════════════════════════════════════════════════════╝"

# Check JDK version
echo "📋 Checking JDK version..."
java_version=$(java -version 2>&1 | head -n 1 | grep -oP '(?<=version ")[^"]+' || echo "Unknown")
echo "   JDK Version: $java_version"

# Create bin directory
echo "📁 Creating bin directory..."
mkdir -p bin

# Compile Java files
echo "🔨 Compiling Java files..."
echo "   - Compiling models..."
javac -d bin -sourcepath src/main/java src/main/java/models/*.java 2>&1 || {
    echo "   ❌ Error compiling models!"
    exit 1
}

echo "   - Compiling utils..."
javac -d bin -sourcepath src/main/java src/main/java/utils/*.java 2>&1 || {
    echo "   ❌ Error compiling utils!"
    exit 1
}

echo "   - Compiling ui..."
javac -d bin -sourcepath src/main/java src/main/java/ui/*.java 2>&1 || {
    echo "   ❌ Error compiling ui!"
    exit 1
}

echo ""
echo "✅ Compilation successful!"
echo ""
echo "📊 Project Statistics:"
echo "   - Models: $(ls -1 src/main/java/models/*.java | wc -l) classes"
echo "   - UI: $(ls -1 src/main/java/ui/*.java | wc -l) panels"
echo "   - Utils: $(ls -1 src/main/java/utils/*.java | wc -l) utilities"
echo ""
echo "🚀 Ready to run!"
echo ""
echo "To start the application, run:"
echo "   cd bin && java -cp . ui.MainFrame"
echo ""
