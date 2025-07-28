/**
 * Experiment 1.2: Java program to generate the Fibonacci series up to 'n' numbers
 */
import java.util.Scanner;

public class FibonacciSeries {
    
    public static void generateFibonacci(int n) {
        if (n <= 0) {
            System.out.println("Please enter a positive number.");
            return;
        }
        
        System.out.println("Fibonacci series up to " + n + " numbers:");
        
        if (n >= 1) {
            System.out.print("0 ");
        }
        if (n >= 2) {
            System.out.print("1 ");
        }
        
        int first = 0, second = 1;
        
        for (int i = 3; i <= n; i++) {
            int next = first + second;
            System.out.print(next + " ");
            first = second;
            second = next;
        }
        System.out.println();
    }
    
    public static void generateFibonacciRecursive(int n) {
        System.out.println("\nFibonacci series using recursion:");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciRecursive(i) + " ");
        }
        System.out.println();
    }
    
    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of terms for Fibonacci series: ");
        int n = scanner.nextInt();
        
        // Iterative approach
        generateFibonacci(n);
        
        // Recursive approach (for smaller numbers due to performance)
        if (n <= 20) {
            generateFibonacciRecursive(n);
        } else {
            System.out.println("Recursive approach skipped for performance (n > 20)");
        }
        
        scanner.close();
    }
}
