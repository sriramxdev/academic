/**
 * Experiment 6.1: Implement Lambda Expressions and Method References in Java.
 */

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

// Functional interfaces for demonstration
@FunctionalInterface
interface Calculator {
    double calculate(double a, double b);
}

@FunctionalInterface
interface StringProcessor {
    String process(String input);
}

@FunctionalInterface
interface NumberValidator {
    boolean validate(int number);
}

// Custom class for method reference examples
class MathUtils {
    public static double add(double a, double b) {
        return a + b;
    }
    
    public static double multiply(double a, double b) {
        return a * b;
    }
    
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;
        
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    
    public double subtract(double a, double b) {
        return a - b;
    }
    
    public double divide(double a, double b) {
        if (b == 0) throw new ArithmeticException("Division by zero");
        return a / b;
    }
}

// Student class for lambda demonstrations
class Student {
    private String name;
    private int age;
    private double gpa;
    private String major;
    
    public Student(String name, int age, double gpa, String major) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        this.major = major;
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGpa() { return gpa; }
    public String getMajor() { return major; }
    
    @Override
    public String toString() {
        return String.format("Student{name='%s', age=%d, gpa=%.2f, major='%s'}", 
                           name, age, gpa, major);
    }
}

public class LambdaMethodReferenceDemo {
    
    private static void printSection(String title) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println(title);
        System.out.println("=".repeat(70));
    }
    
    /**
     * Demonstrates basic lambda expressions
     */
    private static void demonstrateBasicLambdas() {
        printSection("BASIC LAMBDA EXPRESSIONS");
        
        System.out.println("1. Lambda vs Anonymous Classes:");
        
        // Traditional anonymous class
        Calculator traditionalCalc = new Calculator() {
            @Override
            public double calculate(double a, double b) {
                return a + b;
            }
        };
        
        // Lambda expression
        Calculator lambdaCalc = (a, b) -> a + b;
        
        System.out.println("Traditional: 5 + 3 = " + traditionalCalc.calculate(5, 3));
        System.out.println("Lambda: 5 + 3 = " + lambdaCalc.calculate(5, 3));
        
        System.out.println("\n2. Different lambda syntaxes:");
        
        // Various lambda syntaxes
        Calculator addition = (a, b) -> a + b;                    // Expression lambda
        Calculator subtraction = (a, b) -> { return a - b; };     // Statement lambda
        Calculator multiplication = (double a, double b) -> a * b; // With type declaration
        
        double x = 10, y = 3;
        System.out.println("Addition: " + x + " + " + y + " = " + addition.calculate(x, y));
        System.out.println("Subtraction: " + x + " - " + y + " = " + subtraction.calculate(x, y));
        System.out.println("Multiplication: " + x + " * " + y + " = " + multiplication.calculate(x, y));
        
        System.out.println("\n3. Single parameter lambdas:");
        
        StringProcessor uppercase = s -> s.toUpperCase();
        StringProcessor reverse = s -> new StringBuilder(s).reverse().toString();
        StringProcessor addPrefix = s -> "Processed: " + s;
        
        String text = "Hello World";
        System.out.println("Original: " + text);
        System.out.println("Uppercase: " + uppercase.process(text));
        System.out.println("Reverse: " + reverse.process(text));
        System.out.println("Add prefix: " + addPrefix.process(text));
        
        System.out.println("\n4. Zero parameter lambdas:");
        
        Supplier<String> greeting = () -> "Hello from Lambda!";
        Supplier<Double> randomNumber = () -> Math.random();
        Runnable task = () -> System.out.println("Task executed by lambda");
        
        System.out.println("Greeting: " + greeting.get());
        System.out.println("Random number: " + randomNumber.get());
        System.out.print("Running task: ");
        task.run();
    }
    
    /**
     * Demonstrates method references
     */
    private static void demonstrateMethodReferences() {
        printSection("METHOD REFERENCES");
        
        System.out.println("1. Static Method References:");
        
        // Static method reference
        Calculator staticAdd = MathUtils::add;
        Calculator staticMultiply = MathUtils::multiply;
        
        System.out.println("Static add: 8 + 4 = " + staticAdd.calculate(8, 4));
        System.out.println("Static multiply: 8 * 4 = " + staticMultiply.calculate(8, 4));
        
        // Built-in static method references
        Function<String, Integer> parseInt = Integer::parseInt;
        Function<Double, String> doubleToString = String::valueOf;
        
        System.out.println("Parse int: '123' -> " + parseInt.apply("123"));
        System.out.println("Double to string: 45.67 -> '" + doubleToString.apply(45.67) + "'");
        
        System.out.println("\n2. Instance Method References:");
        
        MathUtils mathInstance = new MathUtils();
        Calculator instanceSubtract = mathInstance::subtract;
        Calculator instanceDivide = mathInstance::divide;
        
        System.out.println("Instance subtract: 15 - 6 = " + instanceSubtract.calculate(15, 6));
        System.out.println("Instance divide: 15 / 3 = " + instanceDivide.calculate(15, 3));
        
        // String instance method references
        String sampleText = "Hello Lambda World";
        Supplier<String> toLower = sampleText::toLowerCase;
        Supplier<Integer> getLength = sampleText::length;
        
        System.out.println("Original: " + sampleText);
        System.out.println("To lower: " + toLower.get());
        System.out.println("Length: " + getLength.get());
        
        System.out.println("\n3. Instance Method References on Arbitrary Objects:");
        
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<String, Integer> stringLength = String::length;
        Comparator<String> stringComparator = String::compareToIgnoreCase;
        
        System.out.println("Arbitrary object method:");
        System.out.println("'hello'::toUpperCase -> " + toUpperCase.apply("hello"));
        System.out.println("'world'::length -> " + stringLength.apply("world"));
        System.out.println("Compare 'Apple' and 'banana': " + stringComparator.compare("Apple", "banana"));
        
        System.out.println("\n4. Constructor References:");
        
        Supplier<List<String>> listSupplier = ArrayList::new;
        Function<Integer, List<String>> listWithCapacity = ArrayList::new;
        Function<String, StringBuilder> stringBuilderFromString = StringBuilder::new;
        
        List<String> newList = listSupplier.get();
        newList.add("Item 1");
        System.out.println("New list: " + newList);
        
        List<String> capacityList = listWithCapacity.apply(10);
        System.out.println("List with capacity 10 created: " + capacityList.getClass().getSimpleName());
        
        StringBuilder sb = stringBuilderFromString.apply("Initial text");
        System.out.println("StringBuilder from constructor: " + sb.toString());
    }
    
    /**
     * Demonstrates built-in functional interfaces
     */
    private static void demonstrateBuiltInFunctionalInterfaces() {
        printSection("BUILT-IN FUNCTIONAL INTERFACES");
        
        System.out.println("1. Predicate<T> - boolean test(T t):");
        
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<String> isLongString = s -> s.length() > 5;
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Numbers: " + numbers);
        System.out.println("Even numbers: " + numbers.stream().filter(isEven).collect(Collectors.toList()));
        System.out.println("Positive numbers: " + numbers.stream().filter(isPositive).collect(Collectors.toList()));
        
        // Predicate composition
        Predicate<Integer> evenAndPositive = isEven.and(isPositive);
        System.out.println("Even AND positive: " + numbers.stream().filter(evenAndPositive).collect(Collectors.toList()));
        
        System.out.println("\n2. Function<T, R> - R apply(T t):");
        
        Function<Integer, String> intToString = Object::toString;
        Function<String, Integer> stringToLength = String::length;
        Function<Integer, Integer> square = n -> n * n;
        
        System.out.println("Function examples:");
        System.out.println("42 -> '" + intToString.apply(42) + "'");
        System.out.println("'Hello World' -> " + stringToLength.apply("Hello World"));
        System.out.println("5 squared = " + square.apply(5));
        
        // Function composition
        Function<Integer, String> squareToString = square.andThen(intToString);
        System.out.println("Square then toString: 6 -> '" + squareToString.apply(6) + "'");
        
        System.out.println("\n3. Consumer<T> - void accept(T t):");
        
        Consumer<String> printWithPrefix = s -> System.out.println(">>> " + s);
        Consumer<String> printLength = s -> System.out.println("Length: " + s.length());
        Consumer<Integer> printSquare = n -> System.out.println(n + "² = " + (n * n));
        
        System.out.println("Consumer examples:");
        printWithPrefix.accept("Hello Consumer");
        printLength.accept("Test String");
        printSquare.accept(7);
        
        // Consumer chaining
        Consumer<String> combinedConsumer = printWithPrefix.andThen(printLength);
        System.out.println("Combined consumer:");
        combinedConsumer.accept("Chained operations");
        
        System.out.println("\n4. Supplier<T> - T get():");
        
        Supplier<String> dateSupplier = () -> new Date().toString();
        Supplier<Integer> randomInt = () -> new Random().nextInt(100);
        Supplier<List<String>> emptyList = ArrayList::new;
        
        System.out.println("Supplier examples:");
        System.out.println("Current date: " + dateSupplier.get());
        System.out.println("Random number: " + randomInt.get());
        System.out.println("Empty list: " + emptyList.get());
        
        System.out.println("\n5. BiFunction<T, U, R> - R apply(T t, U u):");
        
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<String, String, String> concat = (s1, s2) -> s1 + " " + s2;
        BiFunction<Integer, Integer, String> compare = (a, b) -> a > b ? "First is larger" : 
                                                                a < b ? "Second is larger" : "Equal";
        
        System.out.println("BiFunction examples:");
        System.out.println("Add 15 + 25 = " + add.apply(15, 25));
        System.out.println("Concat 'Hello' + 'World' = '" + concat.apply("Hello", "World") + "'");
        System.out.println("Compare 10 and 20: " + compare.apply(10, 20));
    }
    
    /**
     * Demonstrates lambda expressions with collections
     */
    private static void demonstrateLambdasWithCollections() {
        printSection("LAMBDAS WITH COLLECTIONS");
        
        // Create sample data
        List<Student> students = Arrays.asList(
            new Student("Alice Johnson", 20, 3.8, "Computer Science"),
            new Student("Bob Smith", 22, 3.2, "Mathematics"),
            new Student("Charlie Brown", 19, 3.9, "Computer Science"),
            new Student("Diana Wilson", 21, 3.5, "Physics"),
            new Student("Eve Davis", 23, 3.7, "Mathematics"),
            new Student("Frank Miller", 20, 3.1, "Physics")
        );
        
        System.out.println("Original student list:");
        students.forEach(System.out::println);
        
        System.out.println("\n1. Filtering with Predicate:");
        
        // Filter CS students
        List<Student> csStudents = students.stream()
            .filter(s -> "Computer Science".equals(s.getMajor()))
            .collect(Collectors.toList());
        
        System.out.println("Computer Science students:");
        csStudents.forEach(System.out::println);
        
        // Filter high GPA students
        List<Student> highGpaStudents = students.stream()
            .filter(s -> s.getGpa() >= 3.5)
            .collect(Collectors.toList());
        
        System.out.println("\nStudents with GPA >= 3.5:");
        highGpaStudents.forEach(System.out::println);
        
        System.out.println("\n2. Sorting with Comparator:");
        
        // Sort by GPA descending
        List<Student> sortedByGpa = students.stream()
            .sorted((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()))
            .collect(Collectors.toList());
        
        System.out.println("Students sorted by GPA (descending):");
        sortedByGpa.forEach(System.out::println);
        
        // Sort by name using method reference
        List<Student> sortedByName = students.stream()
            .sorted(Comparator.comparing(Student::getName))
            .collect(Collectors.toList());
        
        System.out.println("\nStudents sorted by name:");
        sortedByName.forEach(System.out::println);
        
        System.out.println("\n3. Mapping with Function:");
        
        // Extract names
        List<String> studentNames = students.stream()
            .map(Student::getName)
            .collect(Collectors.toList());
        
        System.out.println("Student names: " + studentNames);
        
        // Create summary strings
        List<String> summaries = students.stream()
            .map(s -> s.getName() + " (" + s.getMajor() + ", GPA: " + s.getGpa() + ")")
            .collect(Collectors.toList());
        
        System.out.println("\nStudent summaries:");
        summaries.forEach(System.out::println);
        
        System.out.println("\n4. Reducing operations:");
        
        // Average GPA
        double avgGpa = students.stream()
            .mapToDouble(Student::getGpa)
            .average()
            .orElse(0.0);
        
        System.out.println("Average GPA: " + String.format("%.2f", avgGpa));
        
        // Highest GPA
        Optional<Student> topStudent = students.stream()
            .max(Comparator.comparing(Student::getGpa));
        
        topStudent.ifPresent(s -> System.out.println("Top student: " + s));
        
        // Count by major
        Map<String, Long> countByMajor = students.stream()
            .collect(Collectors.groupingBy(Student::getMajor, Collectors.counting()));
        
        System.out.println("Count by major: " + countByMajor);
    }
    
    /**
     * Demonstrates advanced lambda patterns
     */
    private static void demonstrateAdvancedLambdaPatterns() {
        printSection("ADVANCED LAMBDA PATTERNS");
        
        System.out.println("1. Custom Functional Interfaces:");
        
        // Number validators using lambdas
        NumberValidator isEven = n -> n % 2 == 0;
        NumberValidator isPrime = MathUtils::isPrime;
        NumberValidator isPositive = n -> n > 0;
        
        List<Integer> testNumbers = Arrays.asList(-5, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        
        System.out.println("Numbers: " + testNumbers);
        
        System.out.print("Even numbers: ");
        testNumbers.stream().filter(isEven::validate).forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        System.out.print("Prime numbers: ");
        testNumbers.stream().filter(isPrime::validate).forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        System.out.print("Positive numbers: ");
        testNumbers.stream().filter(isPositive::validate).forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        System.out.println("\n2. Lambda with Exception Handling:");
        
        Function<String, Integer> safeParseInt = s -> {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Warning: Could not parse '" + s + "' as integer");
                return 0;
            }
        };
        
        List<String> stringNumbers = Arrays.asList("123", "456", "invalid", "789", "not-a-number", "999");
        System.out.println("String numbers: " + stringNumbers);
        
        List<Integer> parsedNumbers = stringNumbers.stream()
            .map(safeParseInt)
            .collect(Collectors.toList());
        
        System.out.println("Parsed numbers: " + parsedNumbers);
        
        System.out.println("\n3. Currying with Lambdas:");
        
        // Curried function: f(a)(b) instead of f(a, b)
        Function<Integer, Function<Integer, Integer>> curriedAdd = a -> b -> a + b;
        Function<Integer, Function<Integer, Integer>> curriedMultiply = a -> b -> a * b;
        
        Function<Integer, Integer> add5 = curriedAdd.apply(5);
        Function<Integer, Integer> multiply3 = curriedMultiply.apply(3);
        
        System.out.println("Curried addition - add 5 to 10: " + add5.apply(10));
        System.out.println("Curried multiplication - multiply 7 by 3: " + multiply3.apply(7));
        
        System.out.println("\n4. Higher-Order Functions:");
        
        // Function that returns a function
        Function<String, Consumer<String>> createPrinter = prefix -> 
            message -> System.out.println(prefix + ": " + message);
        
        Consumer<String> infoPrinter = createPrinter.apply("INFO");
        Consumer<String> errorPrinter = createPrinter.apply("ERROR");
        Consumer<String> debugPrinter = createPrinter.apply("DEBUG");
        
        infoPrinter.accept("Application started successfully");
        errorPrinter.accept("File not found");
        debugPrinter.accept("Variable value is 42");
        
        System.out.println("\n5. Lazy Evaluation with Supplier:");
        
        Supplier<String> expensiveComputation = () -> {
            System.out.println("Performing expensive computation...");
            try {
                Thread.sleep(100); // Simulate expensive operation
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Computation result";
        };
        
        System.out.println("Created expensive computation supplier (not executed yet)");
        
        boolean needResult = true;
        if (needResult) {
            String result = expensiveComputation.get();
            System.out.println("Got result: " + result);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("JAVA LAMBDA EXPRESSIONS AND METHOD REFERENCES DEMONSTRATION");
        System.out.println("This program showcases lambda expressions and method references introduced in Java 8");
        
        try {
            demonstrateBasicLambdas();
            demonstrateMethodReferences();
            demonstrateBuiltInFunctionalInterfaces();
            demonstrateLambdasWithCollections();
            demonstrateAdvancedLambdaPatterns();
            
        } catch (Exception e) {
            System.err.println("Error during demonstration: " + e.getMessage());
        } finally {
            printSection("LAMBDA AND METHOD REFERENCE DEMO COMPLETED");
            
            System.out.println("Key concepts demonstrated:");
            System.out.println("✓ Basic lambda syntax and variations");
            System.out.println("✓ Method references (static, instance, constructor)");
            System.out.println("✓ Built-in functional interfaces (Predicate, Function, Consumer, Supplier)");
            System.out.println("✓ Lambda expressions with collections and streams");
            System.out.println("✓ Advanced patterns (currying, higher-order functions, lazy evaluation)");
            System.out.println("✓ Exception handling in lambdas");
            System.out.println("✓ Function composition and chaining");
            
            System.out.println("\nBenefits of Lambda Expressions:");
            System.out.println("• Reduced boilerplate code");
            System.out.println("• More readable and concise code");
            System.out.println("• Better support for functional programming");
            System.out.println("• Enhanced collections processing with streams");
            System.out.println("• Improved performance with lazy evaluation");
        }
    }
}
