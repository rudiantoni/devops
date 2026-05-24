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
 *
 * java.lang.StringBuilder
 * https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html
 *
 * java.lang.StringBuffer
 * https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuffer.html
 *
 * java.math.BigDecimal
 * https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Snippets {

  public static void main(String[] args) {

    print("##################################################");
    print("# Variables");
    print("##################################################");
    print("Default non initialized variables/properties (primitive, reference)");
    print("--------------------------------------------------");
    print("Primitive types (only works when a non-empty array of that type is created)");
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
    Teste refTypeTest = new Teste();
    print(refTypeTest.checkRefTypeNull); // Output: null
    // Only works when loading from another object, local access in a non initialized variable throws a compile error

    print("\nBigDecimal (java.math.BigDecimal)");
    print("--------------------------------------------------");
    print("From String (preferred for money and other exact decimal literals)");
    BigDecimal bdFromString = new BigDecimal("19.99");
    print(bdFromString); // Output: 19.99

    print("\nFrom int or long");
    BigDecimal bdFromInt = new BigDecimal(42);
    BigDecimal bdFromLong = new BigDecimal(12345L);
    print(bdFromInt); // Output: 42
    print(bdFromLong); // Output: 12345

    print("\nUsing BigDecimal.valueOf(long) or BigDecimal.valueOf(double)");
    BigDecimal bdValueOfLong = BigDecimal.valueOf(999L);
    BigDecimal bdValueOfDouble = BigDecimal.valueOf(3.14);
    print(bdValueOfLong); // Output: 999
    print(bdValueOfDouble); // Output: 3.14

    print("\nBuilt-in constants");
    BigDecimal bdZero = BigDecimal.ZERO;
    BigDecimal bdOne = BigDecimal.ONE;
    BigDecimal bdTen = BigDecimal.TEN;
    print(bdZero); // Output: 0
    print(bdOne); // Output: 1
    print(bdTen); // Output: 10

    print("\nAvoid new BigDecimal(double) for exact values (inherits double imprecision)");
    BigDecimal bdFromDoubleBad = new BigDecimal(0.1);
    BigDecimal bdFromStringGood = new BigDecimal("0.1");
    print(bdFromDoubleBad); // Output: 0.1000000000000000055511151231257827021181583404541015625
    print(bdFromStringGood); // Output: 0.1

    print("\nBigDecimal arithmetic (methods return new instances; operands are unchanged)");
    BigDecimal bdPrice = new BigDecimal("10.00");
    BigDecimal bdQty = new BigDecimal("3");
    print(bdPrice.add(bdQty)); // Output: 13.00
    print(bdPrice.subtract(new BigDecimal("2.50"))); // Output: 7.50
    print(bdPrice.multiply(bdQty)); // Output: 30.00
    print(bdPrice.divide(new BigDecimal("4"), 2, RoundingMode.HALF_UP)); // Output: 2.50
    print(bdPrice.setScale(4, RoundingMode.HALF_UP)); // Output: 10.0000

    print();
    print("##################################################");
    print("# Data evaluation");
    print("##################################################");
    print("Truthy or false");
    print("--------------------------------------------------");
    print("Happy (or sad if you prefer JS) to inform you, but there's no truthy and falsy values in Java ;D");

    print();
    print("Checking by unset (null, None, undefined, etc.) values");
    print("--------------------------------------------------");
    String objCheckNull = null;
    String objCheckNotNull = "This is a non null String object.";

    print("Using primitive checking");
    print(objCheckNull == null);
    print(objCheckNull != null);
    print(objCheckNotNull == null);
    print(objCheckNotNull != null);

    print("\nUsing java.util.Objects");
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

    print("Returning the required value when original value is not null");
    String checkNullOptOriginalValue = Optional.ofNullable(objCheckNotNull).orElse("DEFAULT STRING VALUE");
    print(checkNullOptOriginalValue); // Output: This is a non null String object.

    print("\nReturning a default value when original value is null");
    String checkNullOptDefaultValue = Optional.ofNullable(objCheckNull).orElse("DEFAULT STRING VALUE");
    print(checkNullOptDefaultValue); // Output: DEFAULT STRING VALUE

    print("\nKeeping a null value when input is null (making optional useless)");
    String checkNullOptKeepNull = Optional.ofNullable(objCheckNull).orElse(null);
    print(checkNullOptKeepNull); // Output: null

    print("\nComparing BigDecimal values");
    print("--------------------------------------------------");
    print("Using .compareTo() — numeric equality, scale ignored (use for ordering and thresholds)");
    BigDecimal bdCompareA = new BigDecimal("0.10");
    BigDecimal bdCompareB = new BigDecimal("0.1");
    print(bdCompareA.compareTo(bdCompareB)); // Output: 0 (equal as numbers)
    print(new BigDecimal("5").compareTo(new BigDecimal("10"))); // Output: -1
    print(new BigDecimal("10").compareTo(new BigDecimal("5"))); // Output: 1

    print("\nUsing .equals() — same numeric value AND same scale");
    print(bdCompareA.equals(bdCompareB)); // Output: false

    print();
    print("##################################################");
    print("# Conditional structures");
    print("##################################################");
    print("if");
    print("--------------------------------------------------");
    print("Using different combinations of if and if-else");
    print("Using normal if, if-else to run functions");
    if (true) {
      print("Entered the if block");
    } // Output: Entered the if block

    print("\nif-else");
    print("--------------------------------------------------");
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

    print("\nif-else...if");
    print("--------------------------------------------------");
    print("Using if-else...if (else if chain)");
    int score = 75;
    if (score >= 90) {
      print("grade A");
    } else if (score >= 70) {
      print("grade B"); // Output: grade B
    } else {
      print("grade C");
    }

    print("\nswitch");
    print("--------------------------------------------------");
    print("Using switch");
    String day = "MON";
    switch (day) {
      case "MON":
        print("Monday"); // Output: Monday
        break;
      case "FRI":
        print("Friday");
        break;
      default:
        print("Other day");
    }

    print("\nternary");
    print("--------------------------------------------------");
    print("Using ternary operator to return values");
    Integer result = 0;
    result = true ? 10 : 20;
    print(result); // Output: 10

    result = false ? 10 : 20;
    print(result); // Output: 20

    print("\nComparing BigDecimal in if (never use == or .equals() for ordering)");
    print("--------------------------------------------------");
    BigDecimal bdOrderTotal = new BigDecimal("150.00");
    BigDecimal bdFreeShippingMin = new BigDecimal("100.00");
    if (bdOrderTotal.compareTo(bdFreeShippingMin) >= 0) {
      print("Free shipping applies"); // Output: Free shipping applies
    }

    print();
    print("##################################################");
    print("# Repetitive structures");
    print("##################################################");
    print("There's some different repetitive structures");

    print("\nLooping using indexes");
    print("--------------------------------------------------");
    print("Looping using indexes - for loop from 1 to 5");
    for (Integer i = 1; i <= 5; i++) {
      print(i);
    } // Output: 1, 2, 3, 4, 5

    print("\nLooping using provided functions");
    print("--------------------------------------------------");
    print("Looping using provided functions - enhanced for and forEach");
    List<Integer> loopStructsList = Arrays.asList(1,2,3,4,5);
    for (Integer it: loopStructsList) {
      print(it);
    } // Output: 1, 2, 3, 4, 5

    print("\nUsing forEach list loop");
    loopStructsList.forEach(it -> {
      print(it);
    });
    // You can't break or continue these loops iterations

    print("\nLooping break");
    print("--------------------------------------------------");
    print("Looping break - exit early from an indexed loop");
    for (int j = 1; j <= 10; j++) {
      if (j == 4) {
        break;
      }
      print(j);
    } // Output: 1, 2, 3

    print();
    print("##################################################");
    print("# String operations");
    print("##################################################");
    String strOps = "This is a sample text."; // Main string
    String strSearchA = "sample"; // Exists on the main string
    String strSearchB = "THIS"; // Don't exists on the main string

    print("Checking string size (String.length())");
    print("--------------------------------------------------");
    print(strOps.length()); // Output: 22

    print("\nChecking string contains text");
    print("--------------------------------------------------");
    print("Using .contains() string function");
    print(strOps.contains(strSearchA)); // Output: true
    print(strOps.contains(strSearchB)); // Output: false

    print("\nUsing .indexOf() string function");
    print(strOps.indexOf(strSearchA) != -1); // Output: true
    print(strOps.indexOf(strSearchB) != -1); // Output: false

    print("\nUsing (regex) .matches() string function");
    print(strOps.matches(".*" + strSearchA + ".*")); // Output: true
    print(strOps.matches(".*" + strSearchB + ".*")); // Output: false

    print("\nSplitting string by character (String.split())");
    print("--------------------------------------------------");
    String csv = "a,b,c";
    print(Arrays.asList(csv.split(","))); // Output: [a, b, c]

    print("\nTransforming string to lowercase (String.toLowerCase())");
    print("--------------------------------------------------");
    print("Hello".toLowerCase()); // Output: hello

    print("\nTransforming string to uppercase (String.toUpperCase())");
    print("--------------------------------------------------");
    print("Hello".toUpperCase()); // Output: HELLO

    print("\nReplacing string with another string");
    print("--------------------------------------------------");
    print("Replace all ocorrences of \'is\' with \'NOT\'");
    print("Using .replaceAll() string function");
    String strOpsReplaceBase = "Hello world, this is a sample string.";
    String strOpsReplaceContent = "NOT";
    print(strOpsReplaceBase.replaceAll("is", strOpsReplaceContent)); // Output: Hello world, thNOT NOT a sample string.

    print("\nUsing (regex) .replaceAll() string function to use instead of .replaceAll()");
    print(strOpsReplaceBase.replaceAll("(?i)is", strOpsReplaceContent)); // Output: Hello world, thNOT NOT a sample string.

    print("\nFormatting BigDecimal as String");
    print("--------------------------------------------------");
    BigDecimal bdFormat = new BigDecimal("1234567890.50");
    print(bdFormat.toPlainString()); // Output: 1234567890.50 (no scientific notation)
    print(bdFormat.toString()); // Output: 1234567890.50
    print(new BigDecimal("10.5000").stripTrailingZeros().toPlainString()); // Output: 10.5

    print();
    print("##################################################");
    print("# Array operations");
    print("##################################################");
    print("Creating new arrays");
    print("--------------------------------------------------");
    print("There are a lot of ways to create arrays in Java");
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

    print("\nFiltering arrays");
    print("--------------------------------------------------");
    int[] arrFiltered = Arrays.stream(arr5).filter(n -> n > 0).toArray();
    print(Arrays.toString(arrFiltered));

    print("\nMapping arrays");
    print("--------------------------------------------------");
    int[] arrMapped = Arrays.stream(arr5).map(n -> n * 2).toArray();
    print(Arrays.toString(arrMapped));

    print("\nFinding first array element matching condition");
    print("--------------------------------------------------");
    print(Arrays.stream(arr5).filter(n -> n > 100).findFirst().getAsInt()); // Output: 1224

    print("\nFinding last array element matching condition");
    print("--------------------------------------------------");
    print(Arrays.stream(arr5).filter(n -> n > 100).reduce((a, b) -> b).getAsInt()); // Output: 154

    print("\nRemoving duplicate from arrays");
    print("--------------------------------------------------");
    int[] arrDupes = {1, 2, 2, 3, 3, 3};
    print(Arrays.toString(Arrays.stream(arrDupes).distinct().toArray())); // Output: [1, 2, 3]

    print("\nCopying arrays");
    print("--------------------------------------------------");
    int[] arrCopy = Arrays.copyOf(arr5, arr5.length);
    print(Arrays.toString(arrCopy));

    print("\nAppending arrays");
    print("--------------------------------------------------");
    int[] tail = {-1, -2};
    int[] arrAppended = IntStream.concat(Arrays.stream(arr5), Arrays.stream(tail)).toArray();
    print(Arrays.toString(arrAppended));

    print();
    print("##################################################");
    print("# List operations");
    print("##################################################");
    print("Creating new lists");
    print("--------------------------------------------------");
    print("Rather than arrays, lists can be easier to deal with for dynamic data");
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

    print("\nObtaining list size");
    print("--------------------------------------------------");
    print(listsFromCollectedStream.size()); // Output: 3

    print("\nAcessing list first element");
    print("--------------------------------------------------");
    print(listsFromCollectedStream.get(0)); // Output: a

    print("\nAcessing list last element");
    print("--------------------------------------------------");
    print(listsFromCollectedStream.get(listsFromCollectedStream.size() - 1)); // Output: c

    print();
    print("Filtering lists");
    print("--------------------------------------------------");
    print("Filtering where age is higher then 30");
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
    print("Mapping lists");
    print("--------------------------------------------------");
    print("Converting DataModelB into DataModelC");
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
    print("Finding first list element matching condition");
    print("--------------------------------------------------");
    print("Find first DataModelA whose ages are higher than 10, 23, 28 and 45.");
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
    print("Finding last list element matching condition");
    print("--------------------------------------------------");
    print("Last DataModelA whose age is higher than 28 (stream encounter order)");
    print(dataFind.stream().filter(it -> it.getAge() > 28).reduce((a, b) -> b).orElse(null)); // Output: DataModelA{name='Eve', age=40}

    print();
    print("Removing first list element matching condition");
    print("--------------------------------------------------");
    print("Remove only the first DataModelA whose age is higher than 10 from a mutable list");
    print("Using Stream API .filter() with .findFirst() and List.remove()");
    List<DataModelA> dataRemoveFirst = new ArrayList<>(Arrays.asList(
      new DataModelA("Alice", 8),
      new DataModelA("Bob", 15),
      new DataModelA("Charlie", 20)
    ));
    print(dataRemoveFirst); // Output: [DataModelA{name='Alice', age=8}, DataModelA{name='Bob', age=15}, DataModelA{name='Charlie', age=20}]
    DataModelA toRemove = dataRemoveFirst.stream()
      .filter(it -> it.getAge() > 10)
      .findFirst()
      .orElse(null);
    if (toRemove != null) {
      dataRemoveFirst.remove(toRemove);
    }
    print(dataRemoveFirst); // Output: [DataModelA{name='Alice', age=8}, DataModelA{name='Charlie', age=20}]

    print();
    print("Using Stream API .filter() with .findFirst() and .ifPresent()");
    List<DataModelA> dataRemoveFirstA = new ArrayList<>(Arrays.asList(
      new DataModelA("Alice", 8),
      new DataModelA("Bob", 15),
      new DataModelA("Charlie", 20)
    ));
    dataRemoveFirstA.stream()
      .filter(it -> it.getAge() > 10)
      .findFirst()
      .ifPresent(it -> dataRemoveFirstA.remove(it));
    print(dataRemoveFirstA); // Output: [DataModelA{name='Alice', age=8}, DataModelA{name='Charlie', age=20}]

    print();
    print("Using Stream API .filter() with .findFirst() and .ifPresent() with method reference");
    List<DataModelA> dataRemoveFirstB = new ArrayList<>(Arrays.asList(
      new DataModelA("Alice", 8),
      new DataModelA("Bob", 15),
      new DataModelA("Charlie", 20)
    ));
    dataRemoveFirstB.stream()
      .filter(it -> it.getAge() > 10)
      .findFirst()
      .ifPresent(dataRemoveFirstB::remove);
    print(dataRemoveFirstB); // Output: [DataModelA{name='Alice', age=8}, DataModelA{name='Charlie', age=20}]

    print();
    print("Removing duplicate from lists");
    print("--------------------------------------------------");
    print("Removing strings apple and banana.");
    print("Using Stream API .distinct()");
    // The .distinct() function is the same as performing the .equals() between the comparing objects
    List<String> uniqueListRepeated = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "date");
    List<String> uniqueList = uniqueListRepeated
      .stream()
      .distinct()
      .collect(Collectors.toList());
    print(uniqueList); // Output: [apple, banana, cherry, date]


    print();
    print("Appending lists");
    print("--------------------------------------------------");
    print("Append all elements of one list to the end of another");
    List<Integer> listAppendBase = new ArrayList<>(Arrays.asList(10, 20));
    listAppendBase.addAll(Arrays.asList(30, 40));
    print(listAppendBase); // Output: [10, 20, 30, 40]

    print();
    print("Inserting a list into another list");
    print("--------------------------------------------------");
    print("We can emulate a list spreading just by adding a list in another list");
    print("Adding list to list");
    List<Integer> listConcatContent = new ArrayList<>(Arrays.asList(1, 2, 3));
    List<Integer> listConcatBase = new ArrayList<>(Arrays.asList(4, 5));
    listConcatBase.addAll(1, listConcatContent);
    print(listConcatBase); // Output: [4, 1, 2, 3, 5]

    print();
    print("Shuffling lists");
    print("--------------------------------------------------");
    print("Shuffle a list, by shuffle I mean re-order the elements in a pseudo-random order");
    List<Integer> listShuffle = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    for (int i = 0; i < 3; i++) {
      Collections.shuffle(listShuffle);
      print(listShuffle); // Output: unknown, but it must be different orderings of the listShuffle list.
    }

    print("\nAggregating BigDecimal values from a list");
    print("--------------------------------------------------");
    List<BigDecimal> listBdAmounts = Arrays.asList(
      new BigDecimal("10.50"),
      new BigDecimal("2.25"),
      new BigDecimal("7.25")
    );
    BigDecimal listBdSum = listBdAmounts.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    print(listBdSum); // Output: 20.00

    print("\nMapping list items to BigDecimal line totals");
    List<BigDecimal> listBdLineTotals = listBdAmounts.stream()
      .map(amount -> amount.multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_UP))
      .collect(Collectors.toList());
    print(listBdLineTotals); // Output: [11.55, 2.48, 7.98]

    print();
    print("##################################################");
    print("# Object operations");
    print("##################################################");
    print("Creating new objects");
    print("--------------------------------------------------");
    DataModelA objectDemo = new DataModelA("Zoe", 31);
    print(objectDemo); // Output: DataModelA{name='Zoe', age=31}

    print();
    print("Checking object contains attributes");
    print("--------------------------------------------------");
    print("Using null-safe field reads (no dynamic attribute map in plain Java)");
    print(objectDemo.getName() != null); // Output: true
    print(objectDemo.getAge() > 0); // Output: true

    print();
    print("##################################################");
    print("# Custom auxiliary functions");
    print("##################################################");
    print("Remove duplicates from a dictionary list");
    print("--------------------------------------------------");
    List<Map<String, String>> dictRows = new ArrayList<>();
    dictRows.add(Collections.singletonMap("id", "1"));
    dictRows.add(Collections.singletonMap("id", "2"));
    dictRows.add(Collections.singletonMap("id", "1"));
    List<Map<String, String>> dictUniqueById = dictRows.stream()
      .collect(Collectors.toMap(m -> m.get("id"), m -> m, (a, b) -> a))
      .values()
      .stream()
      .collect(Collectors.toList());
    print(dictUniqueById); // Output: two maps with ids 1 and 2 (order may vary)

    print();
    print("##################################################");
    print("# Language specific");
    print("##################################################");
    print("Using functional interfaces");
    print("--------------------------------------------------");
    print("Now we gonna see some cool stuff, first let's begin with Function, so we can pass a function as an argument");
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
    print("Printing a String List using lambda and method reference");
    List<String> funcIntStrListNames = Arrays.asList("Alice", "Bob", "Charlie");
    // If you need to pass more parameters, consider using BiConsumer or model a class to do that
    Consumer<String> funcIntPrintWithPrefix = name -> System.out.println("Name: " + name);
    // Usage as lambda
    funcIntStrListNames.forEach( it -> { funcIntPrintWithPrefix.accept(it); } ); // Output: Name: Alice, Name: Bob, Name: Charlie
    // Usage as method reference
    funcIntStrListNames.forEach(funcIntPrintWithPrefix); // Output: Name: Alice, Name: Bob, Name: Charlie

    print("\nNow we'll use Predicate, which is a boolean valued function (returns booleans) of one argument");
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

    print();
    print("Using StringBuilder");
    print("--------------------------------------------------");
    print("StringBuilder is a mutable sequence of characters, designed for fast string composition.");
    print("It avoids the overhead of creating new String objects (Strings are IMMUTABLE in Java) on every concatenation.");
    print("Use StringBuilder when building strings in loops or doing many sequential modifications.");
    print("NOTE: StringBuilder is NOT thread-safe. For multi-threaded scenarios use StringBuffer instead (same API, synchronized).");

    print("\nCreating a StringBuilder - Different ways");
    print("Empty constructor (default initial capacity = 16 chars)");
    StringBuilder sbEmpty = new StringBuilder();
    print("sbEmpty content: \"" + sbEmpty.toString() + "\""); // Output: sbEmpty content: ""
    print("sbEmpty length: " + sbEmpty.length()); // Output: sbEmpty length: 0
    print("sbEmpty capacity: " + sbEmpty.capacity()); // Output: sbEmpty capacity: 16

    print("\nWith a pre-defined initial capacity (recommended when the final size is roughly known)");
    StringBuilder sbCapacity = new StringBuilder(64);
    print("sbCapacity length: " + sbCapacity.length()); // Output: sbCapacity length: 0
    print("sbCapacity capacity: " + sbCapacity.capacity()); // Output: sbCapacity capacity: 64

    print("\nWith an initial String content (capacity = string length + 16)");
    StringBuilder sbFromString = new StringBuilder("Hello");
    print("sbFromString content: \"" + sbFromString.toString() + "\""); // Output: sbFromString content: "Hello"
    print("sbFromString length: " + sbFromString.length()); // Output: sbFromString length: 5
    print("sbFromString capacity: " + sbFromString.capacity()); // Output: sbFromString capacity: 21

    print("\nFrom a CharSequence (any object implementing CharSequence: String, StringBuilder, StringBuffer, ...)");
    CharSequence baseCharSeq = "World";
    StringBuilder sbFromCharSeq = new StringBuilder(baseCharSeq);
    print(sbFromCharSeq); // Output: World

    print();
    print("There's a .append() overload for virtually every type, and ALL of them return the same StringBuilder, enabling chaining.");
    print("Appending different data types one by one");
    StringBuilder sbAppend = new StringBuilder();
    sbAppend.append("Text ");                 // String
    sbAppend.append(42);                      // int
    sbAppend.append(' ');                     // char
    sbAppend.append(3.14);                    // double
    sbAppend.append(' ');
    sbAppend.append(true);                    // boolean
    sbAppend.append(' ');
    sbAppend.append(100L);                    // long
    sbAppend.append(' ');
    sbAppend.append(2.5f);                    // float
    sbAppend.append(' ');
    sbAppend.append(new BigDecimal("99.99"));  // BigDecimal
    sbAppend.append(' ');
    sbAppend.append((Object) null);           // null is appended literally as "null"
    sbAppend.append(' ');
    sbAppend.append(new char[]{'a','b','c'}); // char[]
    print(sbAppend); // Output: Text 42 3.14 true 100 2.5 99.99 null abc

    print("\nMethod chaining - idiomatic StringBuilder usage");
    String chainedResult = new StringBuilder()
      .append("User: ")
      .append("Alice")
      .append(", Age: ")
      .append(30)
      .append(", Active: ")
      .append(true)
      .toString();
    print(chainedResult); // Output: User: Alice, Age: 30, Active: true

    print("\nAppending only a portion of a char array - .append(char[] str, int offset, int len)");
    char[] partialChars = {'H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd'};
    StringBuilder sbAppendPartial = new StringBuilder();
    sbAppendPartial.append(partialChars, 0, 5); // append "Hello" (5 chars starting at index 0)
    print(sbAppendPartial); // Output: Hello

    print("\nAppending only a portion of a CharSequence - .append(CharSequence s, int start, int end)");
    StringBuilder sbAppendCharSeqRange = new StringBuilder();
    sbAppendCharSeqRange.append("Hello World", 6, 11); // append substring [6, 11) = "World"
    print(sbAppendCharSeqRange); // Output: World

    print("\nAppending unicode code points - .appendCodePoint(int codePoint)");
    print("Required when dealing with characters above U+FFFF (encoded as a surrogate pair / 2 chars).");
    StringBuilder sbAppendCodePoint = new StringBuilder();
    sbAppendCodePoint.appendCodePoint(65);      // 'A' (basic char, 1 code unit)
    sbAppendCodePoint.appendCodePoint(0x1F600); // smiling-face emoji (above BMP, encoded as 2 code units)
    print(sbAppendCodePoint); // Output: A (followed by emoji, terminal-dependent rendering)
    print("length (code units): " + sbAppendCodePoint.length()); // Output: length (code units): 3

    print();
    print("Insert overloads exist for the same types as .append() (String, primitives, char[], CharSequence, Object).");
    print("Inserting in the middle");
    StringBuilder sbInsert = new StringBuilder("Hello World");
    sbInsert.insert(5, "---");
    print(sbInsert); // Output: Hello--- World

    print("\nInserting at the beginning (index 0)");
    sbInsert.insert(0, "[START] ");
    print(sbInsert); // Output: [START] Hello--- World

    print("\nInserting at the end (index == length() is equivalent to .append())");
    sbInsert.insert(sbInsert.length(), " [END]");
    print(sbInsert); // Output: [START] Hello--- World [END]

    print();
    print("Range is [start, end), the substring is replaced even if the new string has a different length.");
    print("Replacing with a same-size string");
    StringBuilder sbReplace = new StringBuilder("Hello World");
    sbReplace.replace(6, 11, "Java!");
    print(sbReplace); // Output: Hello Java!

    print("\nReplacing with a longer string (StringBuilder grows)");
    StringBuilder sbReplaceLonger = new StringBuilder("abc xyz");
    sbReplaceLonger.replace(4, 7, "REPLACED");
    print(sbReplaceLonger); // Output: abc REPLACED

    print("\nReplacing with a shorter string (StringBuilder shrinks)");
    StringBuilder sbReplaceShorter = new StringBuilder("Hello World");
    sbReplaceShorter.replace(6, 11, "J");
    print(sbReplaceShorter); // Output: Hello J

    print();
    print("Deleting a single character at a given index - .deleteCharAt(int index)");
    StringBuilder sbDelChar = new StringBuilder("Hello");
    sbDelChar.deleteCharAt(0);
    print(sbDelChar); // Output: ello

    print("\nDeleting a range [start, end) - .delete(int start, int end)");
    StringBuilder sbDelRange = new StringBuilder("Hello World");
    sbDelRange.delete(5, 11);
    print(sbDelRange); // Output: Hello

    print();
    print("There's no .clear() method, but multiple alternatives:");
    print("Using .setLength(0) - MOST EFFICIENT, only resets the length, capacity (buffer) is kept");
    StringBuilder sbClearA = new StringBuilder("Some text");
    sbClearA.setLength(0);
    print("\"" + sbClearA.toString() + "\" (length=" + sbClearA.length() + ")"); // Output: "" (length=0)

    print("\nUsing .delete(0, length()) - same effect, more verbose");
    StringBuilder sbClearB = new StringBuilder("Some text");
    sbClearB.delete(0, sbClearB.length());
    print("\"" + sbClearB.toString() + "\" (length=" + sbClearB.length() + ")"); // Output: "" (length=0)

    print("\nReassigning to a brand new instance - NOT recommended: discards the underlying buffer");
    StringBuilder sbClearC = new StringBuilder("Some text");
    sbClearC = new StringBuilder();
    print("\"" + sbClearC.toString() + "\" (length=" + sbClearC.length() + ")"); // Output: "" (length=0)

    print();
    print("Single in-place call: .reverse()");
    StringBuilder sbReverse = new StringBuilder("Hello World");
    sbReverse.reverse();
    print(sbReverse); // Output: dlroW olleH

    print();
    print("Acessing a character by index - .charAt(int index)");
    StringBuilder sbCharAt = new StringBuilder("Hello");
    print(sbCharAt.charAt(0)); // Output: H
    print(sbCharAt.charAt(4)); // Output: o
    // sbCharAt.charAt(5); // Throws StringIndexOutOfBoundsException

    print("\nReplacing a single character by index - .setCharAt(int index, char ch)");
    StringBuilder sbSetCharAt = new StringBuilder("Hello");
    sbSetCharAt.setCharAt(0, 'J');
    print(sbSetCharAt); // Output: Jello

    print();
    print("Substring extraction returns a NEW String, the original StringBuilder is left UNCHANGED.");
    print("Substring from a given index to the end - .substring(int start)");
    StringBuilder sbSubstring = new StringBuilder("Hello World");
    print(sbSubstring.substring(6)); // Output: World

    print("\nSubstring from a given range [start, end) - .substring(int start, int end)");
    print(sbSubstring.substring(0, 5)); // Output: Hello
    print(sbSubstring); // Output: Hello World (StringBuilder unchanged)

    print("\nSimilar method that returns a CharSequence - .subSequence(int start, int end)");
    print("Equivalent to .substring(start, end) but typed as CharSequence (the returned object is actually a String).");
    CharSequence subSeq = sbSubstring.subSequence(0, 5);
    print(subSeq); // Output: Hello

    print();
    print("Returns the index of the match or -1 if not found.");
    StringBuilder sbSearch = new StringBuilder("Hello World, Hello Java");

    print("Searching for the FIRST ocorrence - .indexOf(String str)");
    print(sbSearch.indexOf("Hello"));  // Output: 0
    print(sbSearch.indexOf("Java"));   // Output: 19
    print(sbSearch.indexOf("Python")); // Output: -1 (not found)

    print("\nSearching for the FIRST ocorrence starting at index - .indexOf(String str, int fromIndex)");
    print(sbSearch.indexOf("Hello", 1)); // Output: 13 (skips the one at index 0)

    print("\nSearching for the LAST ocorrence - .lastIndexOf(String str)");
    print(sbSearch.lastIndexOf("Hello"));  // Output: 13
    print(sbSearch.lastIndexOf("Python")); // Output: -1 (not found)

    print();
    print("length() = number of characters currently stored");
    print("capacity() = total space available in the underlying buffer (auto-grows as needed)");
    print("Getting current length and capacity");
    StringBuilder sbLen = new StringBuilder("Hello");
    print("length: " + sbLen.length());     // Output: length: 5
    print("capacity: " + sbLen.capacity()); // Output: capacity: 21 (5 + 16 default extra)

    print("\nTruncating with .setLength(int newLength) when newLength < current length");
    sbLen.setLength(3);
    print(sbLen); // Output: Hel

    print("\nExtending with .setLength(int newLength) when newLength > current length");
    print("New positions are padded with the null character (\\u0000).");
    StringBuilder sbExtend = new StringBuilder("Hi");
    sbExtend.setLength(5);
    print("length=" + sbExtend.length()); // Output: length=5
    print("chars (as int): " + (int) sbExtend.charAt(0) + ", " + (int) sbExtend.charAt(1) + ", "
      + (int) sbExtend.charAt(2) + ", " + (int) sbExtend.charAt(3) + ", " + (int) sbExtend.charAt(4));
    // Output: chars (as int): 72, 105, 0, 0, 0  (H, i, \u0000, \u0000, \u0000)

    print("\nEnsuring a minimum capacity - .ensureCapacity(int minimum)");
    print("Useful before a known big sequence of appends to avoid multiple buffer reallocations.");
    StringBuilder sbEnsureCap = new StringBuilder();
    print("Before: " + sbEnsureCap.capacity()); // Output: Before: 16
    sbEnsureCap.ensureCapacity(100);
    print("After: " + sbEnsureCap.capacity()); // Output: After: 100

    print("\nReducing the buffer to fit the current length - .trimToSize()");
    StringBuilder sbTrim = new StringBuilder(100);
    sbTrim.append("short");
    print("Before trim: capacity=" + sbTrim.capacity() + ", length=" + sbTrim.length()); // Output: Before trim: capacity=100, length=5
    sbTrim.trimToSize();
    print("After trim: capacity=" + sbTrim.capacity() + ", length=" + sbTrim.length()); // Output: After trim: capacity=5, length=5

    print();
    print(".chars() and .codePoints() return IntStream, enabling functional-style char processing.");
    print(".chars() - each char as an int (UTF-16 code unit, 0-65535)");
    StringBuilder sbChars = new StringBuilder("Abc");
    List<Integer> charsCollected = sbChars.chars()
      .boxed()
      .collect(Collectors.toList());
    print(charsCollected); // Output: [65, 98, 99]

    print("\n.codePoints() - each code point as an int (handles surrogate pairs correctly)");
    List<Integer> codePointsCollected = sbChars.codePoints()
      .boxed()
      .collect(Collectors.toList());
    print(codePointsCollected); // Output: [65, 98, 99]

    print("\nDifference between .chars() and .codePoints() for chars above U+FFFF");
    StringBuilder sbSurrogate = new StringBuilder("A");
    sbSurrogate.appendCodePoint(0x1F600); // emoji, 1 code point but 2 code units
    print("length (code units): " + sbSurrogate.length());           // Output: length (code units): 3
    print(".chars() count: " + sbSurrogate.chars().count());         // Output: .chars() count: 3 (each surrogate half counted)
    print(".codePoints() count: " + sbSurrogate.codePoints().count()); // Output: .codePoints() count: 2 (emoji counted as one)

    print();
    print(".getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)");
    StringBuilder sbGetChars = new StringBuilder("Hello World");
    char[] dstArr = new char[5];
    sbGetChars.getChars(6, 11, dstArr, 0); // copy "World" into dstArr starting at index 0
    print(new String(dstArr)); // Output: World

    print();
    print("Single call: .toString()");
    StringBuilder sbToString = new StringBuilder("Hello World");
    String resultStr = sbToString.toString();
    print(resultStr); // Output: Hello World
    print(resultStr.getClass().getName()); // Output: java.lang.String

    print();
    print("This is the canonical use case for StringBuilder.");
    print("Concatenating a list of strings into a CSV-like result");
    List<String> sbLoopList = Arrays.asList("apple", "banana", "cherry", "date");
    StringBuilder sbLoop = new StringBuilder();
    for (int i = 0; i < sbLoopList.size(); i++) {
      sbLoop.append(sbLoopList.get(i));
      if (i < sbLoopList.size() - 1) {
        sbLoop.append(", ");
      }
    }
    print(sbLoop); // Output: apple, banana, cherry, date

    print("\nWhy avoid the \"+=\" String concatenation in loops");
    print("Each \"a += b\" creates a brand new String object (Strings are IMMUTABLE in Java).");
    print("For N iterations, you allocate O(N) temporary strings and copy O(N^2) characters total.");
    print("StringBuilder reuses the same underlying buffer and is O(N) in both time and memory.");
    print("Modern compilers may rewrite a SINGLE \"a\" + \"b\" + \"c\" expression into a StringBuilder,");
    print("but they CANNOT do that across LOOP iterations - that's where StringBuilder is mandatory.");

    print();
    print("Different idioms for the same outcome");
    List<String> sbAltList = Arrays.asList("a", "b", "c", "d");

    print("Using String.join() - cleanest when you just need a separator (no prefix/suffix)");
    String joinedJoin = String.join(", ", sbAltList);
    print(joinedJoin); // Output: a, b, c, d

    print("\nUsing Stream API with Collectors.joining(separator, prefix, suffix)");
    String joinedStream = sbAltList.stream().collect(Collectors.joining(", ", "[", "]"));
    print(joinedStream); // Output: [a, b, c, d]

    print("\nUsing StringBuilder - most flexible (full control over the building logic)");
    StringBuilder joinedSb = new StringBuilder("[");
    for (int i = 0; i < sbAltList.size(); i++) {
      joinedSb.append(sbAltList.get(i));
      if (i < sbAltList.size() - 1) joinedSb.append(", ");
    }
    joinedSb.append("]");
    print(joinedSb); // Output: [a, b, c, d]

    print();
    print("Both classes share the SAME public API (.append, .insert, .delete, .replace, .reverse, ...).");
    print("StringBuilder (since Java 5): NOT synchronized, FASTER, recommended for single-threaded scenarios.");
    print("StringBuffer (since Java 1.0): SYNCHRONIZED methods (thread-safe), slower.");
    print("Rule of thumb: always use StringBuilder, unless you really need to share the instance between threads.");
    StringBuffer sbBufferDemo = new StringBuffer("Hello").append(" World");
    print(sbBufferDemo); // Output: Hello World


    /*
    TODO: ATUALIZAR COM A UTILIZAÇÃO DE RUNNABLE (VER método de passar função em lambda, parecido com as arrow functions do JS)
    */

  }

  // Functions from content
  
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

  // Helper functions
  
  private static void print(Object o) {
    System.out.println(o);
  }
  private static void print() {
    System.out.println();
  }

}
