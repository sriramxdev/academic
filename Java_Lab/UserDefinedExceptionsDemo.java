/**
 * Experiment 4.2: Implement user-defined exceptions along with try-catch-finally blocks in Java.
 */

import java.util.Scanner;

// Custom Exception Classes

// Exception for invalid age
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Exception for insufficient balance
class InsufficientBalanceException extends Exception {
    private double availableBalance;
    
    public InsufficientBalanceException(String message, double availableBalance) {
        super(message);
        this.availableBalance = availableBalance;
    }
    
    public double getAvailableBalance() {
        return availableBalance;
    }
}

// Exception for invalid email format
class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}

// Exception for grade validation
class InvalidGradeException extends Exception {
    public InvalidGradeException(String message) {
        super(message);
    }
}

// Runtime exception for negative numbers
class NegativeNumberException extends RuntimeException {
    public NegativeNumberException(String message) {
        super(message);
    }
}

// Classes that use custom exceptions

class Student {
    private String name;
    private int age;
    private String email;
    private double grade;
    
    public Student(String name, int age, String email, double grade) 
            throws InvalidAgeException, InvalidEmailException, InvalidGradeException {
        setName(name);
        setAge(age);
        setEmail(email);
        setGrade(grade);
    }
    
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }
    
    public void setAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative. Provided: " + age);
        }
        if (age < 16) {
            throw new InvalidAgeException("Student must be at least 16 years old. Provided: " + age);
        }
        if (age > 100) {
            throw new InvalidAgeException("Age seems unrealistic. Provided: " + age);
        }
        this.age = age;
    }
    
    public void setEmail(String email) throws InvalidEmailException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidEmailException("Email cannot be null or empty");
        }
        
        email = email.trim();
        
        // Basic email validation
        if (!email.contains("@") || !email.contains(".")) {
            throw new InvalidEmailException("Invalid email format: " + email);
        }
        
        if (email.indexOf("@") != email.lastIndexOf("@")) {
            throw new InvalidEmailException("Email cannot contain multiple @ symbols: " + email);
        }
        
        this.email = email;
    }
    
    public void setGrade(double grade) throws InvalidGradeException {
        if (grade < 0.0 || grade > 100.0) {
            throw new InvalidGradeException("Grade must be between 0 and 100. Provided: " + grade);
        }
        this.grade = grade;
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public double getGrade() { return grade; }
    
    public char getLetterGrade() {
        if (grade >= 90) return 'A';
        else if (grade >= 80) return 'B';
        else if (grade >= 70) return 'C';
        else if (grade >= 60) return 'D';
        else return 'F';
    }
    
    @Override
    public String toString() {
        return String.format("Student{name='%s', age=%d, email='%s', grade=%.2f (%c)}", 
                           name, age, email, grade, getLetterGrade());
    }
}

class BankAccount {
    private String accountNumber;
    private double balance;
    private String holderName;
    
    public BankAccount(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
    }
    
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Insufficient balance. Attempted to withdraw ₹" + amount + 
                " but only ₹" + balance + " available.", balance);
        }
        
        balance -= amount;
        System.out.println("Successfully withdrew ₹" + amount + ". New balance: ₹" + balance);
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        System.out.println("Successfully deposited ₹" + amount + ". New balance: ₹" + balance);
    }
    
    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
}

class Calculator {
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }
    
    public static double squareRoot(double number) throws NegativeNumberException {
        if (number < 0) {
            throw new NegativeNumberException("Cannot calculate square root of negative number: " + number);
        }
        return Math.sqrt(number);
    }
    
    public static int factorial(int n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers: " + n);
        }
        if (n > 20) {
            throw new IllegalArgumentException("Factorial calculation would cause overflow for: " + n);
        }
        
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

public class UserDefinedExceptionsDemo {
    
    private static void demonstrateStudentExceptions() {
        System.out.println("=== Student Registration Demo ===");
        
        String[][] studentData = {
            {"John Doe", "20", "john@email.com", "85.5"},
            {"Jane Smith", "15", "jane@email.com", "92.0"}, // Invalid age
            {"Bob Wilson", "25", "invalid-email", "78.0"}, // Invalid email
            {"Alice Brown", "22", "alice@email.com", "105.0"}, // Invalid grade
            {"Charlie Davis", "-5", "charlie@email.com", "90.0"} // Negative age
        };
        
        for (String[] data : studentData) {
            System.out.println("\nTrying to create student: " + data[0]);
            
            try {
                Student student = new Student(
                    data[0], 
                    Integer.parseInt(data[1]), 
                    data[2], 
                    Double.parseDouble(data[3])
                );
                System.out.println("✓ Student created successfully: " + student);
                
            } catch (InvalidAgeException e) {
                System.out.println("✗ Age Error: " + e.getMessage());
            } catch (InvalidEmailException e) {
                System.out.println("✗ Email Error: " + e.getMessage());
            } catch (InvalidGradeException e) {
                System.out.println("✗ Grade Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("✗ Number Format Error: Invalid number in input data");
            } catch (Exception e) {
                System.out.println("✗ Unexpected Error: " + e.getMessage());
            } finally {
                System.out.println("  Finished processing student registration attempt.");
            }
        }
    }
    
    private static void demonstrateBankAccountExceptions() {
        System.out.println("\n=== Bank Account Demo ===");
        
        BankAccount account = new BankAccount("ACC001", "John Doe", 5000.0);
        System.out.println("Account created: " + account.getHolderName() + 
                          " with balance ₹" + account.getBalance());
        
        double[] withdrawalAmounts = {1000.0, 2000.0, 3000.0, 1500.0}; // Last one will fail
        
        for (double amount : withdrawalAmounts) {
            System.out.println("\nAttempting to withdraw ₹" + amount);
            
            try {
                account.withdraw(amount);
                
            } catch (InsufficientBalanceException e) {
                System.out.println("✗ Withdrawal failed: " + e.getMessage());
                System.out.println("  Available balance: ₹" + e.getAvailableBalance());
                
                // Offer to withdraw available amount
                if (e.getAvailableBalance() > 0) {
                    System.out.println("  Withdrawing available amount instead...");
                    try {
                        account.withdraw(e.getAvailableBalance());
                    } catch (InsufficientBalanceException ex) {
                        System.out.println("  This shouldn't happen!");
                    }
                }
                
            } catch (IllegalArgumentException e) {
                System.out.println("✗ Invalid amount: " + e.getMessage());
            } finally {
                System.out.println("  Current balance: ₹" + account.getBalance());
            }
        }
    }
    
    private static void demonstrateCalculatorExceptions() {
        System.out.println("\n=== Calculator Demo ===");
        
        // Test division by zero
        System.out.println("\nTesting division:");
        double[] dividends = {10.0, 15.0, 20.0};
        double[] divisors = {2.0, 0.0, 3.0}; // Middle one will cause exception
        
        for (int i = 0; i < dividends.length; i++) {
            System.out.println("Calculating " + dividends[i] + " ÷ " + divisors[i]);
            
            try {
                double result = Calculator.divide(dividends[i], divisors[i]);
                System.out.println("✓ Result: " + result);
                
            } catch (ArithmeticException e) {
                System.out.println("✗ Math Error: " + e.getMessage());
            } finally {
                System.out.println("  Division operation completed.");
            }
        }
        
        // Test square root of negative number
        System.out.println("\nTesting square root:");
        double[] numbers = {16.0, 25.0, -9.0, 36.0}; // Third one will cause exception
        
        for (double num : numbers) {
            System.out.println("Calculating √" + num);
            
            try {
                double result = Calculator.squareRoot(num);
                System.out.println("✓ Result: " + result);
                
            } catch (NegativeNumberException e) {
                System.out.println("✗ Input Error: " + e.getMessage());
                System.out.println("  Suggestion: Use absolute value √" + Math.abs(num) + " = " + 
                                 Calculator.squareRoot(Math.abs(num)));
            } finally {
                System.out.println("  Square root operation completed.");
            }
        }
        
        // Test factorial with invalid input
        System.out.println("\nTesting factorial:");
        int[] factorialInputs = {5, 10, -3, 25}; // Third and fourth will cause exceptions
        
        for (int num : factorialInputs) {
            System.out.println("Calculating " + num + "!");
            
            try {
                int result = Calculator.factorial(num);
                System.out.println("✓ Result: " + result);
                
            } catch (IllegalArgumentException e) {
                System.out.println("✗ Input Error: " + e.getMessage());
            } finally {
                System.out.println("  Factorial operation completed.");
            }
        }
    }
    
    private static void demonstrateNestedExceptions() {
        System.out.println("\n=== Nested Exception Handling Demo ===");
        
        try {
            System.out.println("Outer try block started");
            
            try {
                System.out.println("Inner try block started");
                
                // This will cause an exception
                Calculator.squareRoot(-16);
                
                System.out.println("This line won't execute");
                
            } catch (NegativeNumberException e) {
                System.out.println("Inner catch: Handled negative number exception");
                
                // Now cause another exception in the catch block
                Calculator.divide(10, 0);
                
            } finally {
                System.out.println("Inner finally: This always executes");
            }
            
            System.out.println("This line won't execute due to exception in inner catch");
            
        } catch (ArithmeticException e) {
            System.out.println("Outer catch: Handled arithmetic exception from inner catch block");
        } finally {
            System.out.println("Outer finally: This always executes");
        }
        
        System.out.println("Continued execution after nested exception handling");
    }
    
    private static void interactiveExceptionDemo() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== Interactive Exception Demo ===");
        System.out.println("Enter student details (type 'quit' to exit):");
        
        while (true) {
            try {
                System.out.print("\nStudent name (or 'quit'): ");
                String name = scanner.nextLine();
                
                if ("quit".equalsIgnoreCase(name.trim())) {
                    break;
                }
                
                System.out.print("Age: ");
                int age = Integer.parseInt(scanner.nextLine());
                
                System.out.print("Email: ");
                String email = scanner.nextLine();
                
                System.out.print("Grade (0-100): ");
                double grade = Double.parseDouble(scanner.nextLine());
                
                // Try to create student
                Student student = new Student(name, age, email, grade);
                System.out.println("✓ Student created: " + student);
                
            } catch (InvalidAgeException | InvalidEmailException | InvalidGradeException e) {
                System.out.println("✗ Validation Error: " + e.getMessage());
                System.out.println("  Please try again with correct values.");
                
            } catch (NumberFormatException e) {
                System.out.println("✗ Input Error: Please enter valid numbers for age and grade.");
                
            } catch (Exception e) {
                System.out.println("✗ Unexpected Error: " + e.getMessage());
                
            } finally {
                System.out.println("  Input processing completed.");
            }
        }
        
        System.out.println("Interactive demo ended.");
    }
    
    public static void main(String[] args) {
        System.out.println("=== User-Defined Exceptions with Try-Catch-Finally Demo ===\n");
        
        try {
            demonstrateStudentExceptions();
            demonstrateBankAccountExceptions();
            demonstrateCalculatorExceptions();
            demonstrateNestedExceptions();
            
            // Uncomment the line below for interactive demo
            // interactiveExceptionDemo();
            
        } catch (Exception e) {
            System.out.println("Unexpected error in main: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("\n=== All exception demonstrations completed ===");
        }
        
        System.out.println("\nKey points demonstrated:");
        System.out.println("1. Custom checked exceptions (InvalidAgeException, etc.)");
        System.out.println("2. Custom unchecked exceptions (NegativeNumberException)");
        System.out.println("3. Try-catch-finally blocks");
        System.out.println("4. Multiple catch blocks");
        System.out.println("5. Nested exception handling");
        System.out.println("6. Exception chaining and re-throwing");
        System.out.println("7. Resource cleanup in finally blocks");
    }
}
