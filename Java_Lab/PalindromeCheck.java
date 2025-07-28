/**
 * Experiment 1.1: Java program to check whether a given string is a palindrome
 */
import java.util.Scanner;

public class PalindromeCheck {
    
    public static boolean isPalindrome(String str) {
        // Convert to lowercase and remove spaces for accurate checking
        str = str.toLowerCase().replaceAll("\\s", "");
        
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string to check if it's a palindrome: ");
        String input = scanner.nextLine();
        
        if (isPalindrome(input)) {
            System.out.println("'" + input + "' is a palindrome.");
        } else {
            System.out.println("'" + input + "' is not a palindrome.");
        }
        
        // Test with predefined examples
        System.out.println("\nTesting with examples:");
        String[] testStrings = {"racecar", "hello", "A man a plan a canal Panama", "12321", "java"};
        
        for (String test : testStrings) {
            System.out.println("'" + test + "' is " + 
                (isPalindrome(test) ? "" : "not ") + "a palindrome.");
        }
        
        scanner.close();
    }
}
