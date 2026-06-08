@ECHO OFF
@REM --------------------------------------------------
@REM Currently working on: Windows 11 (add more here when working on other OSs)
@REM --------------------------------------------------
@REM
@REM This script generates a cryptographically secure random key and prints it
@REM as a Base64 encoded string.
@REM
@REM When prompted, enter the desired key size in bytes (for example, 32).
@REM The script uses RandomNumberGenerator to fill a byte array of that size and then
@REM encodes the result in Base64.
@REM
@REM Example output:
@REM Result: <base64-encoded-string>
@REM
@REM Use SIGINT (CTRL + C) to cancel.
@REM
@REM Run with Windows Command Prompt
@REM > key_gen.bat
@REM

SETLOCAL

ECHO --------------------------------------------------
ECHO KEY GENERATOR
ECHO --------------------------------------------------
ECHO Type the size of the key you want to generate:
ECHO ---- SIGINT (CTRL + C) to cancel.

SET /P SIZE=""

ECHO Generating Base64 encoded string for a %SIZE% byte array

FOR /F "usebackq delims=" %%R IN (`powershell -NoProfile -Command "$bytes = New-Object byte[] %SIZE%; [System.Security.Cryptography.RandomNumberGenerator]::Create().GetBytes($bytes); [Convert]::ToBase64String($bytes)"`) DO SET "RESULT=%%R"

ECHO Result: %RESULT%
ECHO.

ENDLOCAL
