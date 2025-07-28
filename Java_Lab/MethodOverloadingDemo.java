/**
 * Experiment 3.2: Create a class MathOperations with overloaded add() methods to sum:
 * 1. Two integers
 * 2. Two doubles
 * 3. Three integers
 * In main(), call all add() methods with different arguments.
 */

class MathOperations {
    
    // Method to add two integers
    public int add(int a, int b) {
        int result = a + b;
        System.out.println("Adding two integers: " + a + " + " + b + " = " + result);
        return result;
    }
    
    // Method to add two doubles
    public double add(double a, double b) {
        double result = a + b;
        System.out.println("Adding two doubles: " + a + " + " + b + " = " + result);
        return result;
    }
    
    // Method to add three integers
    public int add(int a, int b, int c) {
        int result = a + b + c;
        System.out.println("Adding three integers: " + a + " + " + b + " + " + c + " = " + result);
        return result;
    }
    
    // Additional overloaded methods for demonstration
    
    // Method to add array of integers
    public int add(int[] numbers) {
        int sum = 0;
        System.out.print("Adding array of integers: ");
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
            System.out.print(numbers[i]);
            if (i < numbers.length - 1) {
                System.out.print(" + ");
            }
        }
        System.out.println(" = " + sum);
        return sum;
    }
    
    // Method to add two floats
    public float add(float a, float b) {
        float result = a + b;
        System.out.println("Adding two floats: " + a + " + " + b + " = " + result);
        return result;
    }
    
    // Method to add four integers
    public int add(int a, int b, int c, int d) {
        int result = a + b + c + d;
        System.out.println("Adding four integers: " + a + " + " + b + " + " + c + " + " + d + " = " + result);
        return result;
    }
    
    // Method to demonstrate method with same number of parameters but different types
    public String add(String a, String b) {
        String result = a + b;
        System.out.println("Concatenating two strings: \"" + a + "\" + \"" + b + "\" = \"" + result + "\"");
        return result;
    }
}

public class MethodOverloadingDemo {
    public static void main(String[] args) {
        System.out.println("=== Method Overloading Demonstration ===\n");
        
        // Create an instance of MathOperations
        MathOperations math = new MathOperations();
        
        System.out.println("--- Required Overloaded Methods ---");
        
        // 1. Call add() method with two integers
        math.add(10, 20);
        
        // 2. Call add() method with two doubles
        math.add(15.5, 25.7);
        
        // 3. Call add() method with three integers
        math.add(5, 10, 15);
        
        System.out.println("\n--- Additional Overloaded Methods ---");
        
        // Additional demonstrations
        math.add(12.3f, 45.6f);  // Two floats
        
        math.add(1, 2, 3, 4);    // Four integers
        
        // Array of integers
        int[] numbers = {1, 2, 3, 4, 5};
        math.add(numbers);
        
        // String concatenation
        math.add("Hello", "World");
        
        // Demonstrate return values
        System.out.println("\n--- Using Return Values ---");
        
        int sum1 = math.add(100, 200);
        double sum2 = math.add(10.5, 20.5);
        int sum3 = math.add(1, 2, 3);
        
        System.out.println("\nStored results:");
        System.out.println("Sum of two integers: " + sum1);
        System.out.println("Sum of two doubles: " + sum2);
        System.out.println("Sum of three integers: " + sum3);
        System.out.println("Total of all sums: " + (sum1 + sum2 + sum3));
        
        // Demonstrate method resolution at compile time
        System.out.println("\n--- Method Resolution Examples ---");
        
        // Compiler chooses appropriate method based on arguments
        math.add(10, 20);           // Calls add(int, int)
        math.add(10.0, 20.0);       // Calls add(double, double)
        math.add(10, 20, 30);       // Calls add(int, int, int)
        math.add(10.5f, 20.5f);     // Calls add(float, float)
    }
}
