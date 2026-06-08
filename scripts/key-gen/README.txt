##################################################
key_gen.jar
##################################################
/**
 * Requires Java 8+
 * --------------------------------------------------
 * Run with
 * $ java -jar key_gen.jar
 * --------------------------------------------------
 *
 * This application generates a cryptographically secure random key and prints it
 * as a Base64 encoded string.
 *
 * When prompted, enter the desired key size in bytes (for example, 32).
 * The application uses SecureRandom to fill a byte array of that size and then
 * encodes the result in Base64.
 *
 * Example output:
 * Result: <base64-encoded-string>
 *
 * Use SIGINT (CTRL + C) to cancel.
 */
