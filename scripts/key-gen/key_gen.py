#
# Requires Python 3+
# --------------------------------------------------
# Run with
# $ python3/python/py key_gen.py
# --------------------------------------------------
#
# This script generates a cryptographically secure random key and prints it
# as a Base64 encoded string.
#
# When prompted, enter the desired key size in bytes (for example, 32).
# The script uses secrets.token_bytes to fill a byte array of that size and then
# encodes the result in Base64.
#
# Example output:
# Result: <base64-encoded-string>
#
# Use SIGINT (CTRL + C) to cancel.
#

import base64
import secrets


def main():
    print("--------------------------------------------------")
    print("KEY GENERATOR")
    print("--------------------------------------------------")
    print("Type the size of the key you want to generate:")
    print("---- SIGINT (CTRL + C) to cancel.")
    expected_size = input("")

    size = int(expected_size)

    print(f"Generating Base64 encoded string for a {size} byte array")
    result = base64.b64encode(secrets.token_bytes(size)).decode("ascii")

    print(f"Result: {result}")


main()
