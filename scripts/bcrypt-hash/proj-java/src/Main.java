import java.util.Scanner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.println("--------------------------------------------------");
    System.out.println("BCRYPT PASSWORD HASHER");
    System.out.println("--------------------------------------------------");
    System.out.println("Type the password you want to hash:");
    System.out.println("WARNING: the terminal will show what you type - use with care in shared screens");
    System.out.println("---- SIGINT (CTRL + C) to cancel.");
    String rawPassword = sc.nextLine();

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String result = encoder.encode(rawPassword);
    
    System.out.printf("Your password: %s%n", rawPassword);
    System.out.printf("Result: %s%n", result);
  
  }

}

