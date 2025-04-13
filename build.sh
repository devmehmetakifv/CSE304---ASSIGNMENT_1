#!/bin/bash

# Create bin directory if it doesn't exist
mkdir -p bin

# Clean previous build
rm -rf bin/*

# Compile the Java files
echo "Compiling Java files..."
javac -d bin src/main/java/com/furniture/**/*.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    
    # Generate furniture images
    echo "Generating furniture images..."
    java -cp bin com.furniture.utils.ImageGenerator
    
    # Copy resources to bin directory
    echo "Copying resources..."
    mkdir -p bin/images
    cp -r resources/images bin/
    
    # Run the application
    echo "Running application..."
    java -cp bin com.furniture.ui.FurnitureDesignerApp
else
    echo "Compilation failed!"
fi 