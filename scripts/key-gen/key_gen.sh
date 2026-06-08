#!/bin/bash

#
# --------------------------------------------------
# Currently working on: Linux, Mac and Windows with Git Bash
# --------------------------------------------------
#
# This script generates a cryptographically secure random key and prints it
# as a Base64 encoded string.
#
# When prompted, enter the desired key size in bytes (for example, 32).
# The script uses openssl rand or /dev/urandom to fill a byte array of that size
# and then encodes the result in Base64.
#
# Example output:
# Result: <base64-encoded-string>
#
# Use SIGINT (CTRL + C) to cancel.
#
# Run with:
# sh key_gen.sh
#

echo "--------------------------------------------------"
echo "KEY GENERATOR"
echo "--------------------------------------------------"
echo "Type the size of the key you want to generate:"
echo "---- SIGINT (CTRL + C) to cancel."
read EXPECTED_SIZE

SIZE="${EXPECTED_SIZE//$'\r'/}"

echo "Generating Base64 encoded string for a $SIZE byte array"

if command -v openssl >/dev/null 2>&1; then
  RESULT=$(openssl rand -base64 "$SIZE" | tr -d '\n\r')
else
  RESULT=$(head -c "$SIZE" /dev/urandom | base64 | tr -d '\n\r')
fi

echo "Result: $RESULT"
