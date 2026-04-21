@ECHO off

@REM Watch Docker containers status
@REM Use Ctrl+C to stop then confirm and press Enter
@REM
@REM Run with Windows Command Prompt
@REM > .\docker-ps-watch.bat
@REM
@REM Run with Windows PowerShell
@REM > .\docker-ps-watch.bat

SETLOCAL

:loop
cls
ECHO Updated at date: %date% time: %time%
ECHO.
docker ps -a
TIMEOUT /t 1 /nobreak >nul
GOTO :loop
