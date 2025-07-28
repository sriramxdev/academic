/**
 * Experiment 3.4: Create a BankAccount class with deposit(), withdraw(), and displayBalance() methods.
 * • SavingsAccount: Prevents withdrawal if the balance is below ₹100.
 * • CurrentAccount: Allows overdraft up to ₹5000.
 * Create instances, perform transactions, and display balances.
 */

// Base class BankAccount
class BankAccount {
    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;
    
    // Constructor
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }
    
    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }
    
    // Method to withdraw money (can be overridden)
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
            return false;
        }
        
        if (amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
            return true;
        } else {
            System.out.println("Insufficient balance. Available balance: ₹" + balance);
            return false;
        }
    }
    
    // Method to display balance
    public void displayBalance() {
        System.out.println("Account: " + accountNumber + " | Holder: " + accountHolderName + 
                          " | Balance: ₹" + balance);
    }
    
    // Getter methods
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    public double getBalance() {
        return balance;
    }
    
    // Method to display account details
    public void displayAccountDetails() {
        System.out.println("=== Account Details ===");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Type: " + this.getClass().getSimpleName());
        System.out.println("Current Balance: ₹" + balance);
    }
}

// SavingsAccount class - prevents withdrawal if balance goes below ₹100
class SavingsAccount extends BankAccount {
    private static final double MINIMUM_BALANCE = 100.0;
    private double interestRate;
    
    public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
        this.interestRate = 4.5; // 4.5% annual interest rate
    }
    
    public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance, double interestRate) {
        super(accountNumber, accountHolderName, initialBalance);
        this.interestRate = interestRate;
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
            return false;
        }
        
        if ((balance - amount) >= MINIMUM_BALANCE) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully from Savings Account.");
            return true;
        } else {
            System.out.println("Withdrawal denied! Minimum balance of ₹" + MINIMUM_BALANCE + 
                             " must be maintained. Available for withdrawal: ₹" + 
                             Math.max(0, balance - MINIMUM_BALANCE));
            return false;
        }
    }
    
    // Method to calculate and add interest
    public void addInterest() {
        double interest = balance * (interestRate / 100) / 12; // Monthly interest
        balance += interest;
        System.out.println("Monthly interest of ₹" + String.format("%.2f", interest) + 
                          " added to your account.");
    }
    
    public double getInterestRate() {
        return interestRate;
    }
    
    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.println("Minimum Balance: ₹" + MINIMUM_BALANCE);
        System.out.println("Interest Rate: " + interestRate + "% per annum");
    }
}

// CurrentAccount class - allows overdraft up to ₹5000
class CurrentAccount extends BankAccount {
    private static final double OVERDRAFT_LIMIT = 5000.0;
    private double overdraftUsed;
    
    public CurrentAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
        this.overdraftUsed = 0.0;
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
            return false;
        }
        
        double availableAmount = balance + (OVERDRAFT_LIMIT - overdraftUsed);
        
        if (amount <= availableAmount) {
            if (amount <= balance) {
                // Normal withdrawal
                balance -= amount;
                System.out.println("₹" + amount + " withdrawn successfully from Current Account.");
            } else {
                // Overdraft withdrawal
                double overdraftNeeded = amount - balance;
                overdraftUsed += overdraftNeeded;
                balance = 0;
                System.out.println("₹" + amount + " withdrawn (₹" + overdraftNeeded + 
                                 " from overdraft facility).");
            }
            return true;
        } else {
            System.out.println("Withdrawal denied! Maximum overdraft limit exceeded.");
            System.out.println("Available amount: ₹" + availableAmount);
            return false;
        }
    }
    
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            if (overdraftUsed > 0) {
                if (amount >= overdraftUsed) {
                    // Pay off overdraft first
                    amount -= overdraftUsed;
                    System.out.println("₹" + overdraftUsed + " used to clear overdraft.");
                    overdraftUsed = 0;
                    balance += amount;
                    if (amount > 0) {
                        System.out.println("₹" + amount + " deposited to account.");
                    }
                } else {
                    // Partially pay off overdraft
                    overdraftUsed -= amount;
                    System.out.println("₹" + amount + " used to reduce overdraft.");
                }
            } else {
                balance += amount;
                System.out.println("₹" + amount + " deposited successfully.");
            }
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }
    
    @Override
    public void displayBalance() {
        super.displayBalance();
        if (overdraftUsed > 0) {
            System.out.println("Overdraft Used: ₹" + overdraftUsed);
            System.out.println("Available Overdraft: ₹" + (OVERDRAFT_LIMIT - overdraftUsed));
        } else {
            System.out.println("Available Overdraft: ₹" + OVERDRAFT_LIMIT);
        }
    }
    
    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.println("Overdraft Limit: ₹" + OVERDRAFT_LIMIT);
        System.out.println("Overdraft Used: ₹" + overdraftUsed);
        System.out.println("Available Credit: ₹" + (balance + OVERDRAFT_LIMIT - overdraftUsed));
    }
    
    public double getOverdraftUsed() {
        return overdraftUsed;
    }
    
    public double getAvailableOverdraft() {
        return OVERDRAFT_LIMIT - overdraftUsed;
    }
}

public class BankAccountDemo {
    public static void main(String[] args) {
        System.out.println("=== Bank Account Management System ===\n");
        
        // Create instances of different account types
        SavingsAccount savingsAcc = new SavingsAccount("SAV001", "Amit Kumar", 1500.0);
        CurrentAccount currentAcc = new CurrentAccount("CUR001", "Priya Sharma", 2000.0);
        
        System.out.println("--- Initial Account Details ---");
        savingsAcc.displayAccountDetails();
        System.out.println();
        currentAcc.displayAccountDetails();
        
        System.out.println("\n=== Savings Account Transactions ===");
        
        // Savings account transactions
        savingsAcc.displayBalance();
        savingsAcc.deposit(500);
        savingsAcc.displayBalance();
        
        // Try to withdraw amount that would violate minimum balance
        savingsAcc.withdraw(1500); // Should be denied
        savingsAcc.withdraw(800);  // Should succeed
        savingsAcc.displayBalance();
        
        // Try to withdraw more than allowed
        savingsAcc.withdraw(600);  // Should be denied (would go below ₹100)
        
        // Add interest
        savingsAcc.addInterest();
        savingsAcc.displayBalance();
        
        System.out.println("\n=== Current Account Transactions ===");
        
        // Current account transactions
        currentAcc.displayBalance();
        currentAcc.deposit(1000);
        currentAcc.displayBalance();
        
        // Normal withdrawal
        currentAcc.withdraw(2500);
        currentAcc.displayBalance();
        
        // Overdraft withdrawal
        currentAcc.withdraw(1000); // This will use overdraft
        currentAcc.displayBalance();
        
        // Another overdraft withdrawal
        currentAcc.withdraw(3000); // This will use more overdraft
        currentAcc.displayBalance();
        
        // Try to exceed overdraft limit
        currentAcc.withdraw(2000); // Should be denied
        
        // Deposit to pay off overdraft
        currentAcc.deposit(3000);
        currentAcc.displayBalance();
        
        System.out.println("\n=== Final Account Status ===");
        System.out.println("Savings Account:");
        savingsAcc.displayBalance();
        System.out.println("\nCurrent Account:");
        currentAcc.displayBalance();
        
        // Demonstrate polymorphism
        System.out.println("\n=== Polymorphism Demonstration ===");
        
        BankAccount[] accounts = {
            new SavingsAccount("SAV002", "Rahul Singh", 5000),
            new CurrentAccount("CUR002", "Neha Patel", 3000),
            new BankAccount("GEN001", "Generic User", 1000)
        };
        
        for (BankAccount account : accounts) {
            System.out.println("\nProcessing " + account.getClass().getSimpleName() + ":");
            account.deposit(200);
            account.withdraw(150);
            account.displayBalance();
        }
    }
}
