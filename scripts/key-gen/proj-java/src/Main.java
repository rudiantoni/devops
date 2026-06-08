import java.security.SecureRandom;
import java.util.Base64;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.println("--------------------------------------------------");
    System.out.println("KEY GENERATOR");
    System.out.println("--------------------------------------------------");
    System.out.println("Type the size of the key you want to generate:");
    System.out.println("---- SIGINT (CTRL + C) to cancel.");
    String expectedSize = sc.nextLine();
    
    Integer size = Integer.valueOf(expectedSize);

    System.out.printf("Generating Base64 encoded string for a %d byte array\n", size);
    byte[] bytes = new byte[size];
    new SecureRandom().nextBytes(bytes);
    String result = Base64.getEncoder().encodeToString(bytes);
    
    System.out.printf("Result: %s%n", result);
  }

}

