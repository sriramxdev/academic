/**
 * Experiment 4.1: Create and use Java packages in a program, compile them, and create a JAR file.
 * Main class that demonstrates the use of custom packages
 */

// Import statements for using our custom packages
import com.university.math.Calculator;
import com.university.math.GeometryCalculator;
import com.university.utils.StringUtilities;

public class PackageDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Java Packages Demonstration ===\n");
        
        // Display package information
        System.out.println("--- Package Information ---");
        Calculator.displayInfo();
        GeometryCalculator.displayInfo();
        StringUtilities.displayInfo();
        
        System.out.println("\n--- Math Package Demo ---");
        
        // Using Calculator class
        System.out.println("Calculator Operations:");
        System.out.println("10 + 5 = " + Calculator.add(10, 5));
        System.out.println("10 - 5 = " + Calculator.subtract(10, 5));
        System.out.println("10 * 5 = " + Calculator.multiply(10, 5));
        
        try {
            System.out.println("10 / 5 = " + Calculator.divide(10, 5));
            System.out.println("10 / 0 = " + Calculator.divide(10, 0));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("2^3 = " + Calculator.power(2, 3));
        System.out.println("√16 = " + Calculator.squareRoot(16));
        System.out.println("5! = " + Calculator.factorial(5));
        System.out.println("Is 17 prime? " + Calculator.isPrime(17));
        
        // Using GeometryCalculator class
        System.out.println("\nGeometry Calculations:");
        double radius = 5.0;
        System.out.println("Circle (radius=" + radius + "):");
        System.out.println("  Area = " + String.format("%.2f", GeometryCalculator.circleArea(radius)));
        System.out.println("  Circumference = " + String.format("%.2f", GeometryCalculator.circleCircumference(radius)));
        
        double length = 8.0, width = 6.0;
        System.out.println("Rectangle (" + length + "x" + width + "):");
        System.out.println("  Area = " + GeometryCalculator.rectangleArea(length, width));
        System.out.println("  Perimeter = " + GeometryCalculator.rectanglePerimeter(length, width));
        
        double sphereRadius = 3.0;
        System.out.println("Sphere (radius=" + sphereRadius + "):");
        System.out.println("  Volume = " + String.format("%.2f", GeometryCalculator.sphereVolume(sphereRadius)));
        System.out.println("  Surface Area = " + String.format("%.2f", GeometryCalculator.sphereSurfaceArea(sphereRadius)));
        
        System.out.println("\n--- Utils Package Demo ---");
        
        // Using StringUtilities class
        String testString = "Hello World";
        System.out.println("String Utilities with: \"" + testString + "\"");
        System.out.println("Reversed: \"" + StringUtilities.reverse(testString) + "\"");
        System.out.println("Word count: " + StringUtilities.countWords(testString));
        System.out.println("Vowel count: " + StringUtilities.countVowels(testString));
        System.out.println("Capitalized: \"" + StringUtilities.capitalizeWords(testString.toLowerCase()) + "\"");
        System.out.println("Without spaces: \"" + StringUtilities.removeSpaces(testString) + "\"");
        
        // Palindrome test
        String palindrome = "A man a plan a canal Panama";
        System.out.println("\"" + palindrome + "\" is palindrome: " + StringUtilities.isPalindrome(palindrome));
        
        // Anagram test
        String word1 = "listen";
        String word2 = "silent";
        System.out.println("\"" + word1 + "\" and \"" + word2 + "\" are anagrams: " + 
                          StringUtilities.isAnagram(word1, word2));
        
        System.out.println("\n--- Error Handling Demo ---");
        
        // Demonstrate exception handling with packages
        try {
            Calculator.squareRoot(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Calculator error: " + e.getMessage());
        }
        
        try {
            GeometryCalculator.circleArea(-3);
        } catch (IllegalArgumentException e) {
            System.out.println("Geometry error: " + e.getMessage());
        }
        
        try {
            GeometryCalculator.triangleAreaHeron(1, 2, 5); // Invalid triangle
        } catch (IllegalArgumentException e) {
            System.out.println("Triangle error: " + e.getMessage());
        }
        
        System.out.println("\n--- Package Structure ---");
        System.out.println("com.university.math.Calculator");
        System.out.println("com.university.math.GeometryCalculator");
        System.out.println("com.university.utils.StringUtilities");
        
        System.out.println("\n=== Compilation and JAR Instructions ===");
        System.out.println("To compile and create JAR file:");
        System.out.println("1. Compile: javac -d . com/university/math/*.java com/university/utils/*.java PackageDemo.java");
        System.out.println("2. Create JAR: jar cf university-utils.jar com/ PackageDemo.class");
        System.out.println("3. Run from JAR: java -cp university-utils.jar PackageDemo");
        System.out.println("4. List JAR contents: jar tf university-utils.jar");
    }
}
