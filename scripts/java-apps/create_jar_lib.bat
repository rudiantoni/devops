@ECHO off

@REM --------------------------------------------------
@REM Currently working on: Windows 11 (add more here when working on other OSs)
@REM --------------------------------------------------
@REM
@REM This script compiles and packages a manual Java project into a fat (uber) JAR
@REM that bundles application classes and all dependency JARs from lib\.
@REM
@REM Copy this script to the root of your Java project, where the src, lib and
@REM META-INF directories are located.
@REM The project must contain:
@REM   lib\*.jar          (at least one dependency JAR)
@REM   src\*.java
@REM   src\META-INF\MANIFEST.MF
@REM
@REM By default, source files are read from src\, dependencies are extracted into
@REM out\, application classes are compiled into out\, and the fat JAR is created
@REM in the project root as MyJavaProj.jar.
@REM The out\ directory is removed after the JAR is created.
@REM
@REM Optional arguments:
@REM   --jar-name <name>    JAR output file name (default: MyJavaProj.jar)
@REM   --src-dir <path>     Source directory (default: src)
@REM   --lib-dir <path>     Dependencies directory (default: lib)
@REM   --out-dir <path>     Output directory for the fat JAR (default: out)
@REM   --help               Show usage information
@REM
@REM Run with Windows Command Prompt
@REM > create_jar_lib.bat
@REM > create_jar_lib.bat --jar-name HelloWorld.jar
@REM > create_jar_lib.bat --src-dir src
@REM > create_jar_lib.bat --lib-dir lib
@REM > create_jar_lib.bat --out-dir out
@REM > create_jar_lib.bat --help
@REM

SETLOCAL

SET "CURRENT_DIR=%CD%"
SET "JAR_NAME=MyJavaProj.jar"
SET "SRC_DIR=src"
SET "LIB_DIR=lib"
SET "OUT_DIR=out"

:parseArgs
IF "%~1"=="" GOTO :argsDone
IF /I "%~1"=="--jar-name" GOTO :parseJarName
IF /I "%~1"=="--src-dir" GOTO :parseSrcDir
IF /I "%~1"=="--lib-dir" GOTO :parseLibDir
IF /I "%~1"=="--out-dir" GOTO :parseOutDir
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

:parseLibDir
IF "%~2"=="" GOTO :errorLibDir
CALL :isOption "%~2"
IF NOT ERRORLEVEL 1 GOTO :errorLibDir
SET "LIB_DIR=%~2"
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

:argsDone
ECHO Creating output directory %CURRENT_DIR%\%OUT_DIR%...
IF EXIST "%OUT_DIR%" RMDIR /S /Q "%OUT_DIR%"
MKDIR "%OUT_DIR%"

ECHO.
ECHO Extracting dependency JARs from %CURRENT_DIR%\%LIB_DIR% into %CURRENT_DIR%\%OUT_DIR%...
FOR %%F IN ("%LIB_DIR%\*.jar") DO (
  PUSHD "%OUT_DIR%"
  jar xf "%%~fF"
  IF ERRORLEVEL 1 (
    POPD
    EXIT /B 1
  )
  POPD
)

CALL :stripJarSignatures

ECHO.
ECHO Compiling Java files from %CURRENT_DIR%\%SRC_DIR% into %CURRENT_DIR%\%OUT_DIR%...
javac -cp "%LIB_DIR%\*" -d "%OUT_DIR%" %SRC_DIR%\*.java
IF ERRORLEVEL 1 EXIT /B 1

ECHO.
ECHO Creating fat JAR file %CURRENT_DIR%\%JAR_NAME%...
jar cfm "%JAR_NAME%" "%SRC_DIR%\META-INF\MANIFEST.MF" -C "%OUT_DIR%" .
IF ERRORLEVEL 1 EXIT /B 1

ECHO.
ECHO Removing output directory %CURRENT_DIR%\%OUT_DIR%...
IF EXIST "%OUT_DIR%" RMDIR /S /Q "%OUT_DIR%"

GOTO :mainEnd

:stripJarSignatures
FOR /R "%OUT_DIR%\META-INF" %%F IN (*.SF) DO DEL /F /Q "%%F" 2>NUL
FOR /R "%OUT_DIR%\META-INF" %%F IN (*.DSA) DO DEL /F /Q "%%F" 2>NUL
FOR /R "%OUT_DIR%\META-INF" %%F IN (*.RSA) DO DEL /F /Q "%%F" 2>NUL
EXIT /B 0

:showHelpAndExit
CALL :showHelp
EXIT /B 0

:showHelp
ECHO Usage: create_jar_lib.bat [options]
ECHO.
ECHO Compile and package a Java project with lib\ dependencies into a fat JAR file.
ECHO.
ECHO Options:
ECHO   --jar-name ^<name^>    JAR output file name (default: MyJavaProj.jar)
ECHO   --src-dir ^<path^>     Source directory (default: src)
ECHO   --lib-dir ^<path^>     Dependencies directory (default: lib)
ECHO   --out-dir ^<path^>     Output directory for the fat JAR (default: out)
ECHO   --help                 Show this help message
EXIT /B 0

:errorJarName
ECHO Error: --jar-name requires a value.
EXIT /B 1

:errorSrcDir
ECHO Error: --src-dir requires a value.
EXIT /B 1

:errorLibDir
ECHO Error: --lib-dir requires a value.
EXIT /B 1

:errorOutDir
ECHO Error: --out-dir requires a value.
EXIT /B 1

:isOption
ECHO %~1| FINDSTR /R /B /C:"--" >NUL
IF NOT ERRORLEVEL 1 EXIT /B 0
EXIT /B 1

:mainEnd
ENDLOCAL
EXIT /B 0
