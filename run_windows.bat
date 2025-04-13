@echo off
echo Furniture Production System
echo --------------------------

rem Create bin directory if it doesn't exist
if not exist bin mkdir bin

rem Clean previous build
echo Cleaning previous build...
if exist bin\* del /Q bin\*

rem Compile the Java files
echo Compiling Java files...
javac -d bin src\main\java\com\furniture\**\*.java

rem Check if compilation was successful
if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    
    rem Generate furniture images
    echo Generating furniture images...
    java -cp bin com.furniture.utils.ImageGenerator
    
    rem Copy resources to bin directory
    echo Copying resources...
    if not exist bin\images mkdir bin\images
    xcopy resources\images bin\images /E /I /Y
    
    rem Run the application
    echo Running application...
    java -cp bin com.furniture.ui.FurnitureDesignerApp
) else (
    echo Compilation failed!
    pause
) 