/*
 * Java official docs: https://docs.oracle.com/en/java/
 * OpenJDK official docs: https://openjdk.org/
 * Run with
 *   $ sh run_snippets.sh
 * Or
 *   $ javac Snippets.java && java Snippets && rm Snippets.class
 * 
 * You can open the folder in any preferred IDE, just use Java 8 to compile and run.
 */

import java.util.Arrays;
import java.util.Objects;

public class Snippets {
  public static void main(String[] args) {
    
    /**
     * java.util.Arrays
     * https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html
     * 
     * java.util.Objects
     * https://docs.oracle.com/javase/8/docs/api/java/util/Objects.html
     */

    print("##################################################");
    print("# Primitive types - Default values when creating arrays with size");
    print("##################################################");
    print("Default value for byte is 0");
    print("--------------------------------------------------");
    print(Arrays.toString(new byte[1]));

    print("\nDefault value for short is 0");
    print("--------------------------------------------------");
    print(Arrays.toString(new short[1]));

    print("\nDefault value for int is 0");
    print("--------------------------------------------------");
    print(Arrays.toString(new int[1]));

    print("\nDefault value for long is 0L");
    print("--------------------------------------------------");
    print(Arrays.toString(new long[1]));
    
    print("\nDefault value for float is 0.0f");
    print("--------------------------------------------------");
    print(Arrays.toString(new float[1]));
    
    print("\nDefault value for double is 0.0d");
    print("--------------------------------------------------");
    print(Arrays.toString(new double[1]));
   
    print("\nDefault value for char is '\u0000', null character for unicode");
    print("--------------------------------------------------");
    print(Arrays.toString(new char[1]));
    
    print("\nDefault value for boolean is false");
    print("--------------------------------------------------");
    print(Arrays.toString(new boolean[1]));
    
    print();
    print("##################################################");
    print("# Arrays - Different ways to create one");
    print("##################################################");
    print("Declare array using standard Java");
    print("--------------------------------------------------");
    int[] arr;
    
    print("\nDeclare array using C-way (NOT RECOMMENDED)");
    print("--------------------------------------------------");
    int arr2[];

    print("\nInitialize size of an declared array");
    print("--------------------------------------------------");
    int arr3[];
    arr3 = new int[10];

    print("\nDeclare and initialize array size inline");
    print("--------------------------------------------------");
    int arr4[] = new int[10];


    print("\nAssigning values to each array position");
    print("--------------------------------------------------");
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

    print("\nArray literals: Declare, initialize and assign values to an array and each of its elements");
    print("--------------------------------------------------");
    int[] arr5 = {23, 1224, 124, -50, 154, -65, 26, -87, -912, 45};

    print("\nObtaining array size");
    print("--------------------------------------------------");
    print("arr5.length = " + arr5.length);
   
    print("\nAcessing array first element");
    print("--------------------------------------------------");
    print("arr5[0] = " + arr5[0]);

    print("\nAcessing array last element");
    print("--------------------------------------------------");
    print("arr5[arr5.length - 1] = " + arr5[arr5.length - 1]);
    
    // Throws ArrayIndexOutOfBoundsException
    // print("\nAcessing array unexistent element ")
    // print("--------------------------------------------------")
    // print("arr5[10] = " + arr5[10]);
    
    print();
    print("##################################################");
    print("# Null Checking");
    print("##################################################");
    String objCheckNull = null;
    String objCheckNotNull = "This is a non null String object.";

    print("\nUsing primitive checking");
    print("--------------------------------------------------");
    print(objCheckNull == null);
    print(objCheckNull != null);
    print(objCheckNotNull == null);
    print(objCheckNotNull != null);
    
    print("\nUsing java.util.Objects");
    print("--------------------------------------------------");
        
    print("\nUsing Objects.nonNull(Object o)");
    print(Objects.nonNull(objCheckNull));
    print(Objects.nonNull(objCheckNotNull));
    // Returns true if object is not null, false otherwise

    print("\nUsing Objects.isNull(Object o)");
    print(Objects.isNull(objCheckNull));
    print(Objects.isNull(objCheckNotNull));
    // Returns true if object is null, false otherwise

    print("\nUsing Objects.requireNonNull(Object o)");
    // print(Objects.requireNonNull(objCheckNull)); // Throws java.lang.NullPointerException
    print(Objects.requireNonNull(objCheckNotNull)); 
    // Returns the object if is not null, throw exception otherwise
   
  }

  private static void print(Object o) {
    System.out.println(o);
  }
  private static void print() {
    System.out.println();
  }

}