/**
 * Experiment 2.1: Java program to compute the factorial of a number using command-line arguments
 */
public class FactorialCommandLine {
    
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    public static long factorialRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java FactorialCommandLine <number>");
            System.out.println("Example: java FactorialCommandLine 5");
            return;
        }
        
        try {
            int number = Integer.parseInt(args[0]);
            
            System.out.println("Computing factorial of " + number);
            
            // Iterative approach
            long result1 = factorial(number);
            System.out.println("Factorial (iterative): " + number + "! = " + result1);
            
            // Recursive approach
            long result2 = factorialRecursive(number);
            System.out.println("Factorial (recursive): " + number + "! = " + result2);
            
            // Display step-by-step calculation
            System.out.print("Step-by-step: " + number + "! = ");
            if (number <= 0) {
                System.out.println("1");
            } else {
                for (int i = number; i >= 1; i--) {
                    System.out.print(i);
                    if (i > 1) System.out.print(" × ");
                }
                System.out.println(" = " + result1);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Please provide a valid integer number.");
            System.out.println("Usage: java FactorialCommandLine <number>");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
