/**
 * Experiment 4.3: Write a Java program that demonstrates checked and unchecked exceptions.
 */

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CheckedUncheckedException {
    
    // Utility method to print section headers
    private static void printSection(String title) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(title);
        System.out.println("=".repeat(50));
    }
    
    // CHECKED EXCEPTIONS DEMONSTRATION
    
    /**
     * Demonstrates FileNotFoundException and IOException (Checked Exceptions)
     */
    private static void demonstrateFileExceptions() {
        printSection("CHECKED EXCEPTIONS: File Operations");
        
        // 1. FileNotFoundException - when file doesn't exist
        System.out.println("1. Attempting to read non-existent file:");
        try {
            FileReader file = new FileReader("nonexistent_file.txt");
            BufferedReader br = new BufferedReader(file);
            String line = br.readLine();
            System.out.println("File content: " + line);
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("✗ FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("✗ IOException: " + e.getMessage());
        }
        
        // 2. Successful file operation
        System.out.println("\n2. Creating and reading a temporary file:");
        String fileName = "temp_demo_file.txt";
        
        try {
            // Create and write to file
            FileWriter writer = new FileWriter(fileName);
            writer.write("This is a demonstration of file operations.\n");
            writer.write("Checked exceptions must be handled explicitly.\n");
            writer.close();
            System.out.println("✓ File created and written successfully");
            
            // Read from file
            FileReader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader);
            String line;
            System.out.println("File contents:");
            while ((line = br.readLine()) != null) {
                System.out.println("  " + line);
            }
            br.close();
            
            // Clean up
            File file = new File(fileName);
            if (file.delete()) {
                System.out.println("✓ Temporary file deleted");
            }
            
        } catch (IOException e) {
            System.out.println("✗ IOException during file operations: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates SQLException simulation and other checked exceptions
     */
    private static void demonstrateOtherCheckedExceptions() {
        printSection("CHECKED EXCEPTIONS: Various Types");
        
        // 1. ParseException
        System.out.println("1. ParseException with date parsing:");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String[] dates = {"25-12-2023", "invalid-date", "01-01-2024"};
        
        for (String dateStr : dates) {
            try {
                Date date = sdf.parse(dateStr);
                System.out.println("✓ Parsed date: " + dateStr + " -> " + date);
            } catch (ParseException e) {
                System.out.println("✗ ParseException for '" + dateStr + "': " + e.getMessage());
            }
        }
        
        // 2. ClassNotFoundException simulation
        System.out.println("\n2. ClassNotFoundException:");
        try {
            Class.forName("com.nonexistent.ClassName");
        } catch (ClassNotFoundException e) {
            System.out.println("✗ ClassNotFoundException: " + e.getMessage());
        }
        
        // 3. InterruptedException simulation
        System.out.println("\n3. InterruptedException with Thread.sleep:");
        try {
            System.out.println("Sleeping for 1 second...");
            Thread.sleep(1000);
            System.out.println("✓ Sleep completed successfully");
        } catch (InterruptedException e) {
            System.out.println("✗ InterruptedException: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
        
        // 4. MalformedURLException
        System.out.println("\n4. MalformedURLException:");
        String[] urls = {"https://www.example.com", "invalid-url", "ftp://ftp.example.com"};
        
        for (String urlStr : urls) {
            try {
                URL url = new URL(urlStr);
                System.out.println("✓ Valid URL: " + url);
            } catch (MalformedURLException e) {
                System.out.println("✗ MalformedURLException for '" + urlStr + "': " + e.getMessage());
            }
        }
    }
    
    // UNCHECKED EXCEPTIONS DEMONSTRATION
    
    /**
     * Demonstrates various RuntimeExceptions (Unchecked Exceptions)
     */
    private static void demonstrateUncheckedExceptions() {
        printSection("UNCHECKED EXCEPTIONS: Runtime Exceptions");
        
        // 1. NullPointerException
        System.out.println("1. NullPointerException:");
        String nullString = null;
        try {
            int length = nullString.length(); // This will throw NPE
            System.out.println("String length: " + length);
        } catch (NullPointerException e) {
            System.out.println("✗ NullPointerException: " + e.getMessage());
        }
        
        // 2. ArrayIndexOutOfBoundsException
        System.out.println("\n2. ArrayIndexOutOfBoundsException:");
        int[] array = {1, 2, 3, 4, 5};
        try {
            System.out.println("Accessing valid index [2]: " + array[2]);
            System.out.println("Accessing invalid index [10]: " + array[10]); // This will throw exception
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("✗ ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
        
        // 3. ArithmeticException
        System.out.println("\n3. ArithmeticException:");
        try {
            int result1 = 10 / 2;
            System.out.println("✓ 10 / 2 = " + result1);
            
            int result2 = 10 / 0; // This will throw ArithmeticException
            System.out.println("10 / 0 = " + result2);
        } catch (ArithmeticException e) {
            System.out.println("✗ ArithmeticException: " + e.getMessage());
        }
        
        // 4. NumberFormatException
        System.out.println("\n4. NumberFormatException:");
        String[] numbers = {"123", "45.67", "not-a-number", "999"};
        for (String numStr : numbers) {
            try {
                int num = Integer.parseInt(numStr);
                System.out.println("✓ Parsed '" + numStr + "' as: " + num);
            } catch (NumberFormatException e) {
                System.out.println("✗ NumberFormatException for '" + numStr + "': " + e.getMessage());
            }
        }
        
        // 5. IllegalArgumentException
        System.out.println("\n5. IllegalArgumentException:");
        try {
            Thread thread = new Thread();
            thread.setPriority(11); // Valid priority is 1-10, this will throw exception
        } catch (IllegalArgumentException e) {
            System.out.println("✗ IllegalArgumentException: " + e.getMessage());
        }
        
        // 6. StringIndexOutOfBoundsException
        System.out.println("\n6. StringIndexOutOfBoundsException:");
        String text = "Hello";
        try {
            char validChar = text.charAt(1);
            System.out.println("✓ Character at index 1: " + validChar);
            
            char invalidChar = text.charAt(10); // This will throw exception
            System.out.println("Character at index 10: " + invalidChar);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("✗ StringIndexOutOfBoundsException: " + e.getMessage());
        }
        
        // 7. ClassCastException
        System.out.println("\n7. ClassCastException:");
        try {
            Object obj = "This is a string";
            String str = (String) obj; // Valid cast
            System.out.println("✓ Valid cast to String: " + str);
            
            Integer num = (Integer) obj; // This will throw ClassCastException
            System.out.println("Invalid cast to Integer: " + num);
        } catch (ClassCastException e) {
            System.out.println("✗ ClassCastException: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates collection-related unchecked exceptions
     */
    private static void demonstrateCollectionExceptions() {
        printSection("UNCHECKED EXCEPTIONS: Collection Operations");
        
        // 1. ConcurrentModificationException
        System.out.println("1. ConcurrentModificationException:");
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        
        try {
            System.out.println("Original list: " + list);
            // This is safe - no concurrent modification
            List<String> copyList = new ArrayList<>(list);
            for (String item : copyList) {
                if (item.equals("Banana")) {
                    list.remove(item); // Modifying original list while iterating copy
                }
            }
            System.out.println("✓ Modified list safely: " + list);
            
            // This will cause ConcurrentModificationException
            list.add("Date");
            for (String item : list) {
                if (item.equals("Date")) {
                    list.remove(item); // Modifying list while iterating it directly
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("✗ ConcurrentModificationException: " + e.getMessage());
        }
        
        // 2. NoSuchElementException
        System.out.println("\n2. NoSuchElementException:");
        Stack<Integer> stack = new Stack<>();
        try {
            stack.push(1);
            stack.push(2);
            System.out.println("✓ Popped: " + stack.pop());
            System.out.println("✓ Popped: " + stack.pop());
            System.out.println("Attempting to pop from empty stack:");
            stack.pop(); // This will throw NoSuchElementException
        } catch (NoSuchElementException e) {
            System.out.println("✗ NoSuchElementException: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates the difference between checked and unchecked exceptions
     */
    private static void demonstrateDifferences() {
        printSection("CHECKED vs UNCHECKED EXCEPTIONS: Key Differences");
        
        System.out.println("CHECKED EXCEPTIONS:");
        System.out.println("- Must be declared in method signature or handled with try-catch");
        System.out.println("- Compile-time checking");
        System.out.println("- Examples: IOException, ClassNotFoundException, ParseException");
        System.out.println("- Represent recoverable conditions");
        
        System.out.println("\nUNCHECKED EXCEPTIONS:");
        System.out.println("- No need to declare or handle explicitly");
        System.out.println("- Runtime checking");
        System.out.println("- Examples: RuntimeException and its subclasses");
        System.out.println("- Represent programming errors");
        
        // Demonstrate method signature differences
        System.out.println("\n--- Method Signature Examples ---");
        
        System.out.println("// Method with checked exception - MUST declare 'throws'");
        System.out.println("public void readFile() throws IOException { ... }");
        
        System.out.println("\n// Method with unchecked exception - MAY declare 'throws'");
        System.out.println("public void divideNumbers(int a, int b) { ... } // Can throw ArithmeticException");
        
        // Demonstrate compilation differences
        demonstrateCompilationDifference();
    }
    
    /**
     * Shows how checked exceptions affect compilation
     */
    private static void demonstrateCompilationDifference() {
        System.out.println("\n--- Compilation Behavior ---");
        
        System.out.println("1. Checked exception handling:");
        // This MUST be in try-catch or method must declare throws
        try {
            Thread.sleep(100);
            System.out.println("✓ Checked exception handled properly");
        } catch (InterruptedException e) {
            System.out.println("Handled InterruptedException");
        }
        
        System.out.println("\n2. Unchecked exception - no forced handling:");
        // This CAN be in try-catch but not required for compilation
        try {
            int result = Integer.parseInt("123");
            System.out.println("✓ Parsed successfully: " + result);
            
            // We can choose to handle or not handle unchecked exceptions
            int division = 10 / 2;
            System.out.println("✓ Division result: " + division);
        } catch (NumberFormatException e) {
            System.out.println("Optionally handled NumberFormatException");
        }
        // Note: We didn't handle ArithmeticException, but code still compiles
    }
    
    /**
     * Creates custom checked and unchecked exceptions for demonstration
     */
    private static void demonstrateCustomExceptions() {
        printSection("CUSTOM CHECKED AND UNCHECKED EXCEPTIONS");
        
        // Custom checked exception
        class CustomCheckedException extends Exception {
            public CustomCheckedException(String message) {
                super(message);
            }
        }
        
        // Custom unchecked exception
        class CustomUncheckedException extends RuntimeException {
            public CustomUncheckedException(String message) {
                super(message);
            }
        }
        
        // Method that throws checked exception
        class CheckedExceptionDemo {
            public void methodWithCheckedException() throws CustomCheckedException {
                throw new CustomCheckedException("This is a custom checked exception");
            }
        }
        
        // Method that throws unchecked exception
        class UncheckedExceptionDemo {
            public void methodWithUncheckedException() {
                throw new CustomUncheckedException("This is a custom unchecked exception");
            }
        }
        
        System.out.println("1. Custom Checked Exception:");
        CheckedExceptionDemo checkedDemo = new CheckedExceptionDemo();
        try {
            checkedDemo.methodWithCheckedException(); // MUST be in try-catch
        } catch (CustomCheckedException e) {
            System.out.println("✗ Caught custom checked exception: " + e.getMessage());
        }
        
        System.out.println("\n2. Custom Unchecked Exception:");
        UncheckedExceptionDemo uncheckedDemo = new UncheckedExceptionDemo();
        try {
            uncheckedDemo.methodWithUncheckedException(); // MAY be in try-catch
        } catch (CustomUncheckedException e) {
            System.out.println("✗ Caught custom unchecked exception: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates exception hierarchy
     */
    private static void demonstrateExceptionHierarchy() {
        printSection("EXCEPTION HIERARCHY");
        
        System.out.println("java.lang.Throwable");
        System.out.println("├── java.lang.Error (Unchecked)");
        System.out.println("│   ├── OutOfMemoryError");
        System.out.println("│   ├── StackOverflowError");
        System.out.println("│   └── ...");
        System.out.println("└── java.lang.Exception");
        System.out.println("    ├── java.lang.RuntimeException (Unchecked)");
        System.out.println("    │   ├── NullPointerException");
        System.out.println("    │   ├── ArrayIndexOutOfBoundsException");
        System.out.println("    │   ├── ArithmeticException");
        System.out.println("    │   ├── NumberFormatException");
        System.out.println("    │   └── ...");
        System.out.println("    └── Other Exceptions (Checked)");
        System.out.println("        ├── IOException");
        System.out.println("        ├── ClassNotFoundException");
        System.out.println("        ├── ParseException");
        System.out.println("        └── ...");
        
        System.out.println("\nKey Rules:");
        System.out.println("• All RuntimeException subclasses are UNCHECKED");
        System.out.println("• All other Exception subclasses are CHECKED");
        System.out.println("• Error classes are UNCHECKED (but shouldn't be caught)");
    }
    
    public static void main(String[] args) {
        System.out.println("JAVA CHECKED AND UNCHECKED EXCEPTIONS DEMONSTRATION");
        System.out.println("This program shows the differences between checked and unchecked exceptions");
        
        try {
            // Demonstrate checked exceptions
            demonstrateFileExceptions();
            demonstrateOtherCheckedExceptions();
            
            // Demonstrate unchecked exceptions
            demonstrateUncheckedExceptions();
            demonstrateCollectionExceptions();
            
            // Show differences
            demonstrateDifferences();
            demonstrateCustomExceptions();
            demonstrateExceptionHierarchy();
            
        } catch (Exception e) {
            System.out.println("Unexpected error in main method: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("DEMONSTRATION COMPLETED");
            System.out.println("=".repeat(60));
            
            System.out.println("\nSummary:");
            System.out.println("✓ Checked exceptions: Must be handled or declared");
            System.out.println("✓ Unchecked exceptions: Optional to handle");
            System.out.println("✓ Both types serve different purposes in Java programming");
        }
    }
}
