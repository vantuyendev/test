@echo off

REM Compile Java files
echo Compiling Java files...
if not exist bin mkdir bin
javac -d bin -sourcepath src\main\java %cd%\src\main\java\*.java 2>nul
javac -d bin -sourcepath src\main\java %cd%\src\main\java\models\*.java 2>nul
javac -d bin -sourcepath src\main\java %cd%\src\main\java\ui\*.java 2>nul
javac -d bin -sourcepath src\main\java %cd%\src\main\java\utils\*.java 2>nul

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
) else (
    echo Compilation failed!
    exit /b 1
)

REM Run the application
echo Starting application...
cd bin
java -cp . ui.MainFrame
cd ..
