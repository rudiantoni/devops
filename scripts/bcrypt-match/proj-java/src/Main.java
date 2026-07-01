import java.util.Scanner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.println("--------------------------------------------------");
    System.out.println("BCRYPT PASSWORD MATCHER");
    System.out.println("--------------------------------------------------");
    System.out.println("Type the password:");
    System.out.println("WARNING: the terminal will show what you type - use with care in shared screens");
    System.out.println("---- SIGINT (CTRL + C) to cancel.");
    String rawPassword = sc.nextLine();

    System.out.println("Type the hashed password:");
    String hashedPassword = sc.nextLine();

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    boolean result = encoder.matches(rawPassword, hashedPassword);
    
    System.out.printf("Password: %s%n", rawPassword);
    System.out.printf("Hashed password: %s%n", hashedPassword);
    System.out.printf("Matches: %s%n", result);
  
  }

}

