/**
 * Experiment 2.2: Java program to accept n numbers from command-line arguments,
 * store them in an array, and find the second largest number
 */
import java.util.Arrays;

public class SecondLargestCommandLine {
    
    public static int findSecondLargest(int[] numbers) {
        if (numbers.length < 2) {
            throw new IllegalArgumentException("At least 2 numbers are required");
        }
        
        // Sort the array in descending order
        Arrays.sort(numbers);
        
        // Find the second largest (distinct) number
        int largest = numbers[numbers.length - 1];
        
        for (int i = numbers.length - 2; i >= 0; i--) {
            if (numbers[i] != largest) {
                return numbers[i];
            }
        }
        
        throw new IllegalArgumentException("All numbers are identical");
    }
    
    public static int findSecondLargestOptimal(int[] numbers) {
        if (numbers.length < 2) {
            throw new IllegalArgumentException("At least 2 numbers are required");
        }
        
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        
        for (int num : numbers) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }
        
        if (secondLargest == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("All numbers are identical");
        }
        
        return secondLargest;
    }
    
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java SecondLargestCommandLine <num1> <num2> ... <numN>");
            System.out.println("Example: java SecondLargestCommandLine 5 8 3 9 1 7");
            System.out.println("Note: Please provide at least 2 numbers");
            return;
        }
        
        try {
            // Convert command line arguments to integer array
            int[] numbers = new int[args.length];
            
            System.out.println("Input numbers:");
            for (int i = 0; i < args.length; i++) {
                numbers[i] = Integer.parseInt(args[i]);
                System.out.print(numbers[i] + " ");
            }
            System.out.println();
            
            // Find second largest using both methods
            int secondLargest1 = findSecondLargest(numbers.clone());
            int secondLargest2 = findSecondLargestOptimal(numbers);
            
            System.out.println("\nArray: " + Arrays.toString(numbers));
            System.out.println("Second largest number (sorting method): " + secondLargest1);
            System.out.println("Second largest number (optimal method): " + secondLargest2);
            
            // Display sorted array for reference
            int[] sortedNumbers = numbers.clone();
            Arrays.sort(sortedNumbers);
            System.out.println("Sorted array (ascending): " + Arrays.toString(sortedNumbers));
            
        } catch (NumberFormatException e) {
            System.out.println("Error: All arguments must be valid integers.");
            System.out.println("Usage: java SecondLargestCommandLine <num1> <num2> ... <numN>");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
