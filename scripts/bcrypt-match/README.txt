##################################################
bcrypt_match.jar
##################################################
/**
 * Requires Java 8+
 * --------------------------------------------------
 * Run with
 * $ java -jar bcrypt_match.jar
 * --------------------------------------------------
 *
 * This application checks whether a plain-text password matches a BCrypt hash.
 * It uses Spring Security's BCryptPasswordEncoder (compatible with Spring Boot 2.7).
 *
 * When prompted, type the plain-text password and then the BCrypt hash to compare.
 * WARNING: the terminal will show what you type - use with care on shared screens.
 *
 * Example output:
 * Password: <password>
 * Hashed password: $2a$10$<bcrypt-hash>
 * Matches: true
 *
 * Use SIGINT (CTRL + C) to cancel.
 */
