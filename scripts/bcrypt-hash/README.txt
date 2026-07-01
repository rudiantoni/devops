##################################################
bcrypt_hash.jar
##################################################
/**
 * Requires Java 8+
 * --------------------------------------------------
 * Run with
 * $ java -jar bcrypt_hash.jar
 * --------------------------------------------------
 *
 * This application hashes a password with BCrypt and prints the result.
 * It uses Spring Security's BCryptPasswordEncoder (compatible with Spring Boot 2.7).
 *
 * When prompted, type the password you want to hash.
 * WARNING: the terminal will show what you type - use with care on shared screens.
 *
 * Example output:
 * Your password: <password>
 * Result: $2a$10$<bcrypt-hash>
 *
 * Use SIGINT (CTRL + C) to cancel.
 */
