@ECHO off

@REM --------------------------------------------------
@REM Currently working on: Windows 11 (add more here when working on other OSs)
@REM --------------------------------------------------
@REM
@REM This script compiles and packages a manual Java project into an executable JAR file.
@REM It is intended for small standalone projects with no external dependencies (JDK only).
@REM
@REM Copy this script to the root of your Java project, where the src and META-INF directories are located.
@REM The project must contain:
@REM   src\*.java
@REM   src\META-INF\MANIFEST.MF
@REM
@REM By default, source files are read from src\, compiled classes are written to out\,
@REM and the JAR is created in the project root as MyJavaProj.jar.
@REM The out\ directory is removed after the JAR is created.
@REM
@REM Optional arguments:
@REM   --jar-name <name>    Change the output JAR file name (default: MyJavaProj.jar)
@REM   --help               Show usage information
@REM
@REM Run with Windows Command Prompt
@REM > create_jar.bat
@REM > create_jar.bat --jar-name HelloWorld.jar
@REM > create_jar.bat --help
@REM

SETLOCAL

SET "CURRENT_DIR=%CD%"
SET "JAR_NAME=MyJavaProj.jar"
SET "SRC_DIR=src"
SET "OUT_DIR=out"

:parseArgs
IF "%~1"=="" GOTO :argsDone
IF /I "%~1"=="--jar-name" GOTO :parseJarName
IF /I "%~1"=="--help" GOTO :showHelpAndExit
ECHO Unknown option: %~1
ECHO.
CALL :showHelp
EXIT /B 1

:parseJarName
IF "%~2"=="" GOTO :errorJarName
CALL :isOption "%~2"
IF NOT ERRORLEVEL 1 GOTO :errorJarName
SET "JAR_NAME=%~2"
SHIFT
SHIFT
GOTO :parseArgs

:argsDone
ECHO Creating output directory %CURRENT_DIR%\%OUT_DIR%...
IF NOT EXIST "%OUT_DIR%" MKDIR "%OUT_DIR%"

ECHO.
ECHO Compiling Java files from %CURRENT_DIR%\%SRC_DIR% to %CURRENT_DIR%\%OUT_DIR%...
javac -d "%OUT_DIR%" %SRC_DIR%\*.java
IF ERRORLEVEL 1 EXIT /B 1

ECHO.
ECHO Creating JAR file %CURRENT_DIR%\%JAR_NAME%...
jar cfm "%JAR_NAME%" "%SRC_DIR%\META-INF\MANIFEST.MF" -C "%OUT_DIR%" .
IF ERRORLEVEL 1 EXIT /B 1

ECHO.
ECHO Removing output directory %CURRENT_DIR%\%OUT_DIR%...
IF EXIST "%OUT_DIR%" RMDIR /S /Q "%OUT_DIR%"

GOTO :mainEnd

:showHelpAndExit
CALL :showHelp
EXIT /B 0

:showHelp
ECHO Usage: create_jar.bat [options]
ECHO.
ECHO Compile and package a manual Java project into a JAR file.
ECHO.
ECHO Options:
ECHO   --jar-name ^<name^>    JAR output file name (default: MyJavaProj.jar)
ECHO   --help                 Show this help message
EXIT /B 0

:errorJarName
ECHO Error: --jar-name requires a value.
EXIT /B 1

:isOption
ECHO %~1| FINDSTR /R /B /C:"--" >NUL
IF NOT ERRORLEVEL 1 EXIT /B 0
EXIT /B 1

:mainEnd
ENDLOCAL
EXIT /B 0
