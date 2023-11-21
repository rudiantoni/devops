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
/**
 * java.util.Arrays
 * https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html
 *
 *
 * java.util.Collections
 * https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html
 *
 * java.util.Function
 * https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html
 *
 * java.util.Function.BiConsumer
 * https://docs.oracle.com/javase/8/docs/api/java/util/function/BiFunction.html
 *
 * java.util.Function.BiFunction
 * https://docs.oracle.com/javase/8/docs/api/java/util/function/BiFunction.html
 *
 * java.util.Function.BiPredicate
 * https://docs.oracle.com/javase/8/docs/api/java/util/function/BiPredicate.html
 *
 * java.util.Function.Consumer
 * https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html
 *
 * java.util.Function.Predicate
 * https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html
 *
 * java.util.List
 * https://docs.oracle.com/javase/8/docs/api/java/util/List.html
 *
 * java.util.Objects
 * https://docs.oracle.com/javase/8/docs/api/java/util/Objects.html
 *
 * java.util.Optional
 * https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
 *
 * java.lang.String
 * https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
 */

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Snippets {

  public static void main(String[] args) {

    print("##################################################");
    print("# Default non initialized variable values");
    print("##################################################");
    print("Primitive types (only works when a non-empty array of that type is created)");
    print("--------------------------------------------------");
    print("byte");
    print((new byte[1])[0]); // Output: 0

    print("\nshort");
    print((new short[1])[0]); // Output: 0

    print("\nint");
    print((new int[1])[0]); // Output: 0

    print("\nlong");
    print((new long[1])[0]); // Output: 0 (0L)

    print("\nfloat");
    print((new float[1])[0]); // Output: 0 (0.0f)

    print("\ndouble");
    print((new double[1])[0]); // Output: 0 (0.0d)

    print("\nchar");
    print((new char[1])[0]); // Output: \u0000 (null character for unicode)

    print("\nboolean");
    print((new boolean[1])[0]); // Output: false

    print("\nReference types");
    print("--------------------------------------------------");
    Teste refTypeTest = new Teste();
    print(refTypeTest.checkRefTypeNull); // Output: null
    // Only works when loading from another object, local access in a non initialized variable throws a compile error

    print();
    print("##################################################");
    print("# Arrays - Different ways to create one");
    print("##################################################");
    print("There are a lot of ways to create arrays in Java");
    print("--------------------------------------------------");
    print("Declare array using standard Java");
    int[] arr;

    print("\nDeclare array using C-way (NOT RECOMMENDED)");
    int arr2[];

    print("\nInitialize size of an declared array");
    int arr3[];
    arr3 = new int[10];

    print("\nDeclare and initialize array size inline");
    int arr4[] = new int[10];

    print("\nConverting a list to an array");
    List<String> arraysFromListBase = Arrays.asList("a", "b", "c");
    String[] arraysFromList = arraysFromListBase.toArray(new String[0]);
    print(Arrays.toString(arraysFromList)); // Output: [a, b, c]

    print("\nAssigning values to each array position");
    arr4[0] = 262; // First element in every array
    arr4[9] = -65; // Last element in a 10 elements array

    print("\nArray literals: Declare, initialize and assign values to an array and each of its elements");
    int[] arr5 = {23, 1224, 124, -50, 154, -65, 26, -87, -912, 45};

    print("\nObtaining array size");
    print("arr5.length = " + arr5.length);

    print("\nAcessing array first element");
    print("arr5[0] = " + arr5[0]);

    print("\nAcessing array last element");
    print("arr5[arr5.length - 1] = " + arr5[arr5.length - 1]);

    // Throws ArrayIndexOutOfBoundsException
    // print("\nAcessing array unexistent element ")
    // print("--------------------------------------------------")
    // print("arr5[10] = " + arr5[10]);

    print();
    print("##################################################");
    print("# Lists - Different ways to create one");
    print("##################################################");
    print("Rather than arrays, you can use lists can be a lot easier to deal with data");
    print("--------------------------------------------------");
    print("Creating empty fixed list");
    List<String> listsFixedEmpty = Collections.emptyList();
    // listsFixedEmpty.add("d"); // Throws UnsupportedOperationException
    print(listsFixedEmpty); // Output: []

    print("\nCreating fixed list converted from array");
    String[] listsFixedFromArrayBase = new String[]{"a", "b", "c"};
    List<String> listsFixedFromArray = Arrays.asList(listsFixedFromArrayBase);
    // listsFixedFromArray.add("d"); // Throws UnsupportedOperationException
    print(listsFixedFromArray); // Output: [a, b, c]

    print("\nCreating fixed list from array data");
    List<String> listsFixedFromArrayData = Arrays.asList("a", "b", "c");
    // listsFixedFromArrayData.add("d"); // Throws UnsupportedOperationException
    print(listsFixedFromArrayData); // Output: [a, b, c]

    print("\nCreating empty mutable list");
    List<String> listsMutableEmpty = new ArrayList<>();
    listsMutableEmpty.add("d");
    print(listsMutableEmpty); // Output: [d]

    print("\nCreating mutable list (arraylist) converted from array");
    String[] listsMutableFromArrayBase = new String[]{"a", "b", "c"};
    List<String> listsMutableFromArray = new ArrayList<>(Arrays.asList(listsMutableFromArrayBase));
    listsMutableFromArray.add("d");
    print(listsMutableFromArray); // Output: [a, b, c, d]

    print("\nCreating mutable list (arraylist) from array data");
    List<String> listsMutableFromArrayData = new ArrayList<>(Arrays.asList("a", "b", "c"));
    listsMutableFromArrayData.add("d");
    print(listsMutableFromArrayData); // Output: [a, b, c, d]

    print("\nUsing Collections.singletonList() for a list (fixed) with a single element");
    List<String> listsSingleElement = Collections.singletonList("a");
    print(listsSingleElement); // Output: [a]

    print("\nUsing Collections.nCopies() for a list (fixed) with multiple copies of the same element");
    List<String> listsCopiesOfSameElement = Collections.nCopies(3, "a"); // 3 copies of "a"
    print(listsCopiesOfSameElement); // Output: [a, a, a]

    print("\nUsing anonymous inner class for complex initializations with code block");
    List<String> listsAnonymousInnerClass = new ArrayList<String>() {{
      add("a");
      add("b");
      add("c");
    }};
    print(listsAnonymousInnerClass); // Output: [a, b, c]

    print("\nUsing Stream API collecting elements");
    List<String> listsFromCollectedStream = Stream.of("a", "b", "c").collect(Collectors.toList());
    print(listsFromCollectedStream); // Output: [a, b, c]

    print();
    print("##################################################");
    print("# Filter list of objects by object property");
    print("##################################################");
    print("Filtering where age is higher then 30");
    print("--------------------------------------------------");
    List<DataModelA> dataFilter = Arrays.asList(
      new DataModelA("Alice", 28),
      new DataModelA("Bob", 35),
      new DataModelA("Charlie", 42),
      new DataModelA("David", 29)
    );

    print("Using Stream API .filter() with a condition");
    List<DataModelA> filteredData = dataFilter
      .stream()
      .filter(it -> it.getAge() > 30)
      .collect(Collectors.toList());
    print(filteredData); // Output: [DataModelA{name='Bob', age=35}, DataModelA{name='Charlie', age=42}]

    print("\nUsing Stream API .filter() with flow control");
    // Each register will be evaluated, when the function returns true, it'll be added to the output
    List<DataModelA> filteredDataFlow = dataFilter
      .stream()
      .filter(it -> {
        if (it.getAge() > 30) {
          return true;
        } else {
          return false;
        }
      })
      .collect(Collectors.toList());
    print(filteredDataFlow); // Output: [DataModelA{name='Bob', age=35}, DataModelA{name='Charlie', age=42}]

    print();
    print("##################################################");
    print("# Map list of objects by object property");
    print("##################################################");
    print("Converting DataModelB into DataModelC");
    print("--------------------------------------------------");
    List<DataModelB> dataMap = Arrays.asList(
      new DataModelB("Alice", 28, "USA"),
      new DataModelB("Bob", 35, "Canada"),
      new DataModelB("Charlie", 42, "France"),
      new DataModelB("David", 29, "Germany")
    );

    print("Using Stream API .map() with object constructor");
    List<DataModelC> mappedData = dataMap
      .stream()
      .map(it -> new DataModelC(it.getName(), it.getCountry()))
      .collect(Collectors.toList());
    print(mappedData);
    /* Output:
    [
      DataModelC{newName='Alice', newCountry='USA'},
      DataModelC{newName='Bob', newCountry='Canada'},
      DataModelC{newName='Charlie', newCountry='France'},
      DataModelC{newName='David', newCountry='Germany'}
    ]
     */

    print("\nUsing Stream API .map() with static method");
    List<DataModelC> mappedDataA = dataMap
      .stream()
      .map(it -> Util.toDataModelC(it))
      .collect(Collectors.toList());
    print(mappedDataA);
    /*
    [
      DataModelC{newName='Alice', newCountry='USA'},
      DataModelC{newName='Bob', newCountry='Canada'},
      DataModelC{newName='Charlie', newCountry='France'},
      DataModelC{newName='David', newCountry='Germany'}
    ]
     */

    print("\nUsing Stream API .map() with method reference");
    // Everytime that the lambda expression is calling only one method, it should be considered to use a method reference
    List<DataModelC> mappedDataB = dataMap
      .stream()
      .map(Util::toDataModelC)
      .collect(Collectors.toList());
    print(mappedDataB);
    /*
    [
      DataModelC{newName='Alice', newCountry='USA'},
      DataModelC{newName='Bob', newCountry='Canada'},
      DataModelC{newName='Charlie', newCountry='France'},
      DataModelC{newName='David', newCountry='Germany'}
    ]
     */

    print("\nBuilding list of by the property 'name'");
    print("--------------------------------------------------");

    print("Using Stream API .map() with .getName() from DataModelB()");
    List<String> dataMapNameList = dataMap
      .stream()
      .map(it -> it.getName())
      .collect(Collectors.toList());
    print(dataMapNameList); // Output: [Alice, Bob, Charlie, David]

    print("\nUsing Stream API .map() with flow control");
    List<String> dataMapNameListFlow = dataMap
      .stream()
      .map(it -> {
        if (it.getName().equalsIgnoreCase(dataMap.get(0).getName())) {
          return "FIRST_NAME";
        } else {
          return it.getName();
        }
      })
      .collect(Collectors.toList());
    print(dataMapNameListFlow); // Output: [FIRST_NAME, Bob, Charlie, David]

    print();
    print("##################################################");
    print("# FindFirst - Find first ocorrence in object list by property or null otherwise");
    print("##################################################");
    print("Find first DataModelA whose ages are higher than 10, 23, 28 and 45.");
    print("--------------------------------------------------");
    List<DataModelA> dataFind = Arrays.asList(
      new DataModelA("Alice", 20),
      new DataModelA("Bob", 25),
      new DataModelA("Charlie", 30),
      new DataModelA("David", 35),
      new DataModelA("Eve", 40)
    );
    DataModelA higherThen10 = dataFind.stream().filter(it -> it.getAge() > 10).findFirst().orElse(null);
    DataModelA higherThen23 = dataFind.stream().filter(it -> it.getAge() > 23).findFirst().orElse(null);
    DataModelA higherThen28 = dataFind.stream().filter(it -> it.getAge() > 28).findFirst().orElse(null);
    DataModelA higherThen45 = dataFind.stream().filter(it -> it.getAge() > 45).findFirst().orElse(null);
    print(higherThen10); // Output: DataModelA{name='Alice', age=20}
    print(higherThen23); // Output: DataModelA{name='Bob', age=25}
    print(higherThen28); // Output: DataModelA{name='Charlie', age=30}
    print(higherThen45); // Output: null

    print();
    print("##################################################");
    print("# Create list with unique values (remove duplicates)");
    print("##################################################");
    print("Removing strings apple and banana.");
    print("--------------------------------------------------");
    print("Using Stream API .distinct()");
    // The .distinct() function is the same as performing the .equals() between the comparing objects
    List<String> uniqueListRepeated = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "date");
    List<String> uniqueList = uniqueListRepeated
      .stream()
      .distinct()
      .collect(Collectors.toList());
    print(uniqueList); // Output: [apple, banana, cherry, date]


    print();
    print("##################################################");
    print("# Adding a list inside another list (JS array spread equivalent)");
    print("##################################################");
    print("We can emulate a list spreading just by adding a list in another list");
    print("--------------------------------------------------");
    print("Adding list to list");
    List<Integer> listConcatContent = new ArrayList<>(Arrays.asList(1, 2, 3));
    List<Integer> listConcatBase = new ArrayList<>(Arrays.asList(4, 5));
    listConcatBase.addAll(1, listConcatContent);
    print(listConcatBase); // Output: [4, 1, 2, 3, 5]

    print();
    print("##################################################");
    print("# String operations");
    print("##################################################");
    print("Check string contains text");
    print("--------------------------------------------------");
    String strOps = "This is a sample text."; // Main string
    String strSearchA = "sample"; // Exists on the main string
    String strSearchB = "THIS"; // Don't exists on the main string

    print("Using .contains() string function");
    print(strOps.contains(strSearchA)); // Output: true
    print(strOps.contains(strSearchB)); // Output: false

    print("\nUsing .indexOf() string function");
    print(strOps.indexOf(strSearchA) != -1); // Output: true
    print(strOps.indexOf(strSearchB) != -1); // Output: false

    print("\nUsing (regex) .matches() string function");
    print(strOps.matches(".*" + strSearchA + ".*")); // Output: true
    print(strOps.matches(".*" + strSearchB + ".*")); // Output: false

    print();
    print("##################################################");
    print("# Truthy and falsy (true and false) values checking");
    print("##################################################");
    print("Happy (or sad if you prefer JS) to inform you, but there's no truthy and falsy values in Java ;D");
    print("--------------------------------------------------");

    print();
    print("##################################################");
    print("# Null Checking");
    print("##################################################");
    String objCheckNull = null;
    String objCheckNotNull = "This is a non null String object.";

    print("Using primitive checking");
    print(objCheckNull == null);
    print(objCheckNull != null);
    print(objCheckNotNull == null);
    print(objCheckNotNull != null);

    print("\nUsing java.util.Objects");
    print("--------------------------------------------------");
    print("Using Objects.nonNull(Object o)");
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

    print("\nUsing java.util.Optional");
    print("--------------------------------------------------");

    print("Returning the required value when original value is not null");
    String checkNullOptOriginalValue = Optional.ofNullable(objCheckNotNull).orElse("DEFAULT STRING VALUE");
    print(checkNullOptOriginalValue); // Output: This is a non null String object.

    print("\nReturning a default value when original value is null");
    String checkNullOptDefaultValue = Optional.ofNullable(objCheckNull).orElse("DEFAULT STRING VALUE");
    print(checkNullOptDefaultValue); // Output: DEFAULT STRING VALUE

    print("\nKeeping a null value when input is null (making optional useless)");
    String checkNullOptKeepNull = Optional.ofNullable(objCheckNull).orElse(null);
    print(checkNullOptKeepNull); // Output: null

    print();
    print("##################################################");
    print("# Replace a string with another string");
    print("##################################################");
    print("Replace all ocorrences of \'is\' with \'NOT\'");
    print("--------------------------------------------------");
    print("Using .replaceAll() string function");
    String strOpsReplaceBase = "Hello world, this is a sample string.";
    String strOpsReplaceContent = "NOT";
    print(strOpsReplaceBase.replaceAll("is", strOpsReplaceContent)); // Output: Hello world, thNOT NOT a sample string.

    print("\nUsing (regex) .replaceAll() string function to use instead of .replaceAll()");
    print(strOpsReplaceBase.replaceAll("(?i)is", strOpsReplaceContent)); // Output: Hello world, thNOT NOT a sample string.

    print();
    print("##################################################");
    print("# Conditional structures");
    print("##################################################");
    print("Using different combinations of if and if-else");
    print("--------------------------------------------------");
    print("Using normal if, if-else to run functions");
    if (true) {
      print("Entered the if block");
    } // Output: Entered the if block

    if (false){
      print("Entered the if block");
    } else {
      print("Entered the else block");
    } // Output: Entered the else block

    print("\nUsing single instruction if, if-else to run functions");
    if (true)
      print("Entered the if block"); // Output: Entered the if block

    if (false)
      print("Entered the if block");
    else
      print("Entered the else block"); // Output: Entered the else block

    print("\nUsing ternary operator to return values");
    Integer result = 0;
    result = true ? 10 : 20;
    print(result); // Output: 10

    result = false ? 10 : 20;
    print(result); // Output: 20

    print();
    print("##################################################");
    print("# Repetitive structures");
    print("##################################################");
    print("There's some different repetitive structures");
    print("--------------------------------------------------");
    print("Using for loop to loop from 1 to 5");
    for (Integer i = 1; i <= 5; i++) {
      print(i);
    } // Output: 1, 2, 3, 4, 5

    print("\nUsing enhanced for loop in a items list");
    List<Integer> loopStructsList = Arrays.asList(1,2,3,4,5);
    for (Integer it: loopStructsList) {
      print(it);
    } // Output: 1, 2, 3, 4, 5

    print("\nUsing forEach list loop");
    loopStructsList.forEach(it -> {
      print(it);
    });
    // You can't break or continue these loops iterations

    print();
    print("##################################################");
    print("# Using functional interfaces");
    print("##################################################");
    print("Now we gonna see some cool stuff, first let's begin with Function, so we can pass a function as an argument");
    print("--------------------------------------------------");
    /* The processMessage function returns an String and takes a String and a function as argument.
    This function need to return a value to make some validation to the String and the result
    will be changed according to that */
    print("Using normal function call");
    // Using overload, don't pass a validation function
    String processedStringNoValidation = processMessage("That's a default message");
    print(processedStringNoValidation); // Output: NO validation - That's a default message

    print("\nUsing function call with a function as argument failing the validation");
    // Function<inputType, returnType>
    // If you need to pass more parameters, consider using BiFunction or model a class to do that
    Function<String, Boolean> validateString = inp -> {
      /* Simply returns false when input String is:
      null, empty, blank or with length higher than 12
      Otherwise returns true */
      if (inp == null || inp.isEmpty() || inp.replaceAll("\\s", "").isEmpty() || inp.length() > 12) {
        return false;
      } else {
        return true;
      }
    };
    // This message will fail the validation
    String processedStringInvalidHigher = processMessage("That's a default message", validateString);
    print(processedStringInvalidHigher); // Output: Validated: ---INVALID---

    print("\nUsing function call with a function as argument succeeding the validation");
    // This message will succeed the validation
    String processedStringValid = processMessage("Is it valid?", validateString);
    print(processedStringValid); // Output: Validated: Is it valid? (VALID)

    print("\nNow let's take a look at Consumer, where we need to perform an action in only one input and without a return value");
    print("--------------------------------------------------");
    print("Printing a String List using lambda and method reference");
    List<String> funcIntStrListNames = Arrays.asList("Alice", "Bob", "Charlie");
    // If you need to pass more parameters, consider using BiConsumer or model a class to do that
    Consumer<String> funcIntPrintWithPrefix = name -> System.out.println("Name: " + name);
    // Usage as lambda
    funcIntStrListNames.forEach( it -> { funcIntPrintWithPrefix.accept(it); } ); // Output: Name: Alice, Name: Bob, Name: Charlie
    // Usage as method reference
    funcIntStrListNames.forEach(funcIntPrintWithPrefix); // Output: Name: Alice, Name: Bob, Name: Charlie

    print("\nNow we'll use Predicate, which is a boolean valued function (returns booleans) of one argument");
    print("--------------------------------------------------");
    print("Filtering a String List, allowing names that starts with J using lambda and method reference");
    List<String> funcIntPredListNames = Arrays.asList("Alice", "Bob", "James", "Julie", "Jenna", "Michael");
    // If you need to pass more parameters, consider using BiPredicate or model a class to do that
    Predicate<String> funcIntStartsWithJ = name -> name.startsWith("J");
    // Usage as lambda
    print(funcIntPredListNames.stream()
      .filter(it -> funcIntStartsWithJ.test(it))
      .collect(Collectors.toList())
    ); // Output: [James, Julie, Jenna]
    // Usage as method reference
    print(funcIntPredListNames.stream()
      .filter(funcIntStartsWithJ::test)
      .collect(Collectors.toList())
    ); // Output: [James, Julie, Jenna]


    /*
    TODO: ATUALIZAR COM A UTILIZAÇÃO DE RUNNABLE (VER método de passar função em lambda, parecido com as arrow functions do JS)
    */

  }

  private static void print(Object o) {
    System.out.println(o);
  }
  private static void print() {
    System.out.println();
  }

  private static String processMessage(String message) {
    return processMessage(message, null);
  }

  private static String processMessage(String message, Function<String, Boolean> validation) {
    String result;
    if (Objects.isNull(validation)) {
      result = "NO validation - " + message;
    } else {
      Boolean valid = validation.apply(message);
      if (valid) {
        result = "Validated: " + message + " (VALID)";
      } else {
        result = "Validated: ---INVALID---";
      }
    }
    return result;
  }

}
