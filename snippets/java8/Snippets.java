/*
 * Java official docs: https://docs.oracle.com/en/java/
 * OpenJDK official docs: https://openjdk.org/
 * Run with
 *   $ sh run_snippets.sh
 * Or
 *   $ javac Snippets.java && java Snippets && rm Snippets.class
 * 
 */

import java.util.Arrays;

public class Snippets {
  public static void main(String[] args) {
    
    System.out.println("##################################################");
    System.out.println("# Primitive types - Default values when creating arrays with size");
    System.out.println("##################################################");
    System.out.println("Default value for byte is 0");
    System.out.println("--------------------------------------------------");
    System.out.println(Arrays.toString(new byte[1]));

    System.out.println("\nDefault value for short is 0");
    System.out.println("--------------------------------------------------");
    System.out.println(Arrays.toString(new short[1]));

    System.out.println("\nDefault value for int is 0");
    System.out.println("--------------------------------------------------");
    System.out.println(Arrays.toString(new int[1]));

    System.out.println("\nDefault value for long is 0L");
    System.out.println("--------------------------------------------------");
    System.out.println(Arrays.toString(new long[1]));
    
    System.out.println("\nDefault value for float is 0.0f");
    System.out.println("--------------------------------------------------");
    System.out.println(Arrays.toString(new float[1]));
    
    System.out.println("\nDefault value for double is 0.0d");
    System.out.println("--------------------------------------------------");
    System.out.println(Arrays.toString(new double[1]));
   
    System.out.println("\nDefault value for char is '\u0000', null character for unicode");
    System.out.println("--------------------------------------------------");
    System.out.println(Arrays.toString(new char[1]));
    
    System.out.println("\nDefault value for boolean is false");
    System.out.println("--------------------------------------------------");
    System.out.println(Arrays.toString(new boolean[1]));
    
    System.out.println();
    System.out.println("##################################################");
    System.out.println("# Arrays - Different ways to create one");
    System.out.println("##################################################");
    System.out.println("Declare array using standard Java");
    System.out.println("--------------------------------------------------");
    int[] arr;
    
    System.out.println("\nDeclare array using C-way (NOT RECOMMENDED)");
    System.out.println("--------------------------------------------------");
    int arr2[];

    System.out.println("\nInitialize size of an declared array");
    System.out.println("--------------------------------------------------");
    int arr3[];
    arr3 = new int[10];

    System.out.println("\nDeclare and initialize array size inline");
    System.out.println("--------------------------------------------------");
    int arr4[] = new int[10];


    System.out.println("\nAssigning values to each array position");
    System.out.println("--------------------------------------------------");
    arr4[0] = 262; // First element in every array
    arr4[1] = 860;
    arr4[2] = 56;
    arr4[3] = 69;
    arr4[4] = 112;
    arr4[5] = -425;
    arr4[6] = 303;
    arr4[7] = 377;
    arr4[8] = 181;
    arr4[9] = -65; // Last element in a 10 elements array

    System.out.println("\nArray literals: Declare, initialize and assign values to an array and each of its elements");
    System.out.println("--------------------------------------------------");
    int[] arr5 = {23, 1224, 124, -50, 154, -65, 26, -87, -912, 45};

    System.out.println("\nObtaining array size");
    System.out.println("--------------------------------------------------");
    System.out.println("arr5.length = " + arr5.length);
   
    System.out.println("\nAcessing array first element");
    System.out.println("--------------------------------------------------");
    System.out.println("arr5[0] = " + arr5[0]);

    System.out.println("\nAcessing array last element");
    System.out.println("--------------------------------------------------");
    System.out.println("arr5[arr5.length - 1] = " + arr5[arr5.length - 1]);
    
    // Throws ArrayIndexOutOfBoundsException exception
    // System.out.println("\nAcessing array unexistent element ")
    // System.out.println("--------------------------------------------------")
    // System.out.println("arr5[10] = " + arr5[10]);
    


  }
}