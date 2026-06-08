@ECHO off

@REM --------------------------------------------------
@REM Currently working on: Windows 11 (add more here when working on other OSs)
@REM --------------------------------------------------
@REM
@REM This script compiles, packages, runs and cleans up a manual Java project.
@REM It is intended for small standalone projects with no external dependencies (JDK only).
@REM
@REM Copy this script to the root of your Java project, where the src and META-INF directories are located.
@REM The project must contain:
@REM   src\*.java
@REM   src\META-INF\MANIFEST.MF
@REM
@REM Workflow:
@REM   1. Compile Java files from src\ into out\
@REM   2. Create an executable JAR in the project root
@REM   3. Run the application with java -jar
@REM   4. Remove out\ and the JAR file by default
@REM
@REM Optional arguments:
@REM   --jar-name <name>    JAR output file name (default: MyJavaProj.jar)
@REM   --src-dir <path>     Source directory (default: src)
@REM   --out-dir <path>     Compiled output directory (default: out)
@REM   --keep-jar           Keep the JAR file after execution
@REM   --keep-out-dir       Keep the compiled output directory after execution
@REM   --silent             Suppress script messages (application output is still shown)
@REM   --help               Show usage information
@REM
@REM Run with Windows Command Prompt
@REM > run_full.bat
@REM > run_full.bat --jar-name HelloWorld.jar
@REM > run_full.bat --src-dir src
@REM > run_full.bat --out-dir out
@REM > run_full.bat --keep-jar
@REM > run_full.bat --keep-out-dir
@REM > run_full.bat --silent
@REM > run_full.bat --help
@REM

SETLOCAL

SET "CURRENT_DIR=%CD%"
SET "JAR_NAME=MyJavaProj.jar"
SET "KEEP_JAR=false"
SET "KEEP_OUT_DIR=false"
SET "SILENT=false"
SET "SRC_DIR=src"
SET "OUT_DIR=out"

:parseArgs
IF "%~1"=="" GOTO :argsDone
IF /I "%~1"=="--jar-name" GOTO :parseJarName
IF /I "%~1"=="--src-dir" GOTO :parseSrcDir
IF /I "%~1"=="--out-dir" GOTO :parseOutDir
IF /I "%~1"=="--keep-jar" GOTO :parseKeepJar
IF /I "%~1"=="--keep-out-dir" GOTO :parseKeepOutDir
IF /I "%~1"=="--silent" GOTO :parseSilent
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

:parseSrcDir
IF "%~2"=="" GOTO :errorSrcDir
CALL :isOption "%~2"
IF NOT ERRORLEVEL 1 GOTO :errorSrcDir
SET "SRC_DIR=%~2"
SHIFT
SHIFT
GOTO :parseArgs

:parseOutDir
IF "%~2"=="" GOTO :errorOutDir
CALL :isOption "%~2"
IF NOT ERRORLEVEL 1 GOTO :errorOutDir
SET "OUT_DIR=%~2"
SHIFT
SHIFT
GOTO :parseArgs

:parseKeepJar
SET "KEEP_JAR=true"
SHIFT
GOTO :parseArgs

:parseKeepOutDir
SET "KEEP_OUT_DIR=true"
SHIFT
GOTO :parseArgs

:parseSilent
SET "SILENT=true"
SHIFT
GOTO :parseArgs

:argsDone
CALL :log "Creating output directory %CURRENT_DIR%\%OUT_DIR%..."
IF NOT EXIST "%OUT_DIR%" MKDIR "%OUT_DIR%"

CALL :logBlank
CALL :log "Compiling Java files from %CURRENT_DIR%\%SRC_DIR% to %CURRENT_DIR%\%OUT_DIR%..."
javac -d "%OUT_DIR%" %SRC_DIR%\*.java
IF ERRORLEVEL 1 EXIT /B 1

CALL :logBlank
CALL :log "Creating JAR file %CURRENT_DIR%\%JAR_NAME%..."
jar cfm "%JAR_NAME%" "%SRC_DIR%\META-INF\MANIFEST.MF" -C "%OUT_DIR%" .
IF ERRORLEVEL 1 EXIT /B 1

CALL :logBlank
CALL :log "Running Java application from %CURRENT_DIR%\%JAR_NAME%..."
CALL :log "----- APPLICATION OUTPUT START -----"
java -jar "%JAR_NAME%"
IF ERRORLEVEL 1 EXIT /B 1
CALL :log "----- APPLICATION OUTPUT END -----"

IF /I "%KEEP_OUT_DIR%"=="false" (
  CALL :logBlank
  CALL :log "Removing output directory %CURRENT_DIR%\%OUT_DIR%..."
  IF EXIST "%OUT_DIR%" RMDIR /S /Q "%OUT_DIR%"
)

IF /I "%KEEP_JAR%"=="false" (
  CALL :logBlank
  CALL :log "Removing JAR file %CURRENT_DIR%\%JAR_NAME%..."
  IF EXIST "%JAR_NAME%" DEL /F /Q "%JAR_NAME%"
)

GOTO :mainEnd

:showHelpAndExit
CALL :showHelp
EXIT /B 0

:showHelp
ECHO Usage: run_full.bat [options]
ECHO.
ECHO Compile, package and run a manual Java project.
ECHO.
ECHO Options:
ECHO   --jar-name ^<name^>    JAR output file name (default: MyJavaProj.jar)
ECHO   --src-dir ^<path^>     Source directory (default: src)
ECHO   --out-dir ^<path^>     Compiled output directory (default: out)
ECHO   --keep-jar             Keep the JAR file after execution
ECHO   --keep-out-dir         Keep the compiled output directory after execution
ECHO   --silent               Suppress script messages (application output is still shown)
ECHO   --help                 Show this help message
EXIT /B 0

:errorJarName
ECHO Error: --jar-name requires a value.
EXIT /B 1

:errorSrcDir
ECHO Error: --src-dir requires a value.
EXIT /B 1

:errorOutDir
ECHO Error: --out-dir requires a value.
EXIT /B 1

:isOption
ECHO %~1| FINDSTR /R /B /C:"--" >NUL
IF NOT ERRORLEVEL 1 EXIT /B 0
EXIT /B 1

:log
IF /I "%SILENT%"=="true" EXIT /B 0
IF "%~1"=="" (
  ECHO.
) ELSE (
  ECHO %~1
)
EXIT /B 0

:logBlank
IF /I "%SILENT%"=="true" EXIT /B 0
ECHO.
EXIT /B 0

:mainEnd
ENDLOCAL
EXIT /B 0
