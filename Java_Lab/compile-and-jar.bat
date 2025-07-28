@echo off
REM Batch script to compile Java packages and create JAR file
REM Experiment 4.1: Package compilation and JAR creation

echo === Java Package Compilation and JAR Creation ===
echo.

echo 1. Compiling Java packages...
javac -d . com/university/math/*.java com/university/utils/*.java PackageDemo.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo.

echo 2. Creating JAR file...
jar cf university-utils.jar com/ PackageDemo.class

if %ERRORLEVEL% NEQ 0 (
    echo JAR creation failed!
    pause
    exit /b 1
)

echo JAR file created successfully!
echo.

echo 3. Listing JAR contents...
jar tf university-utils.jar

echo.
echo 4. Running the program from JAR...
java -cp university-utils.jar PackageDemo

echo.
echo === Process completed ===
pause
