abstract class BankAccount {
    protected double balance;
    BankAccount(double balance) { this.balance = balance; }
    abstract void withdraw(double amount);
    void deposit(double amount) { balance += amount; }
    void displayBalance() { System.out.println("Balance: ₹" + balance); }
}

class SavingsAccount extends BankAccount {
    SavingsAccount(double balance) { super(balance); }
    void withdraw(double amount) {
        if (balance - amount < 100) {
            System.out.println("Cannot withdraw, minimum balance ₹100 required.");
        } else {
            balance -= amount;
        }
    }
}

class CurrentAccount extends BankAccount {
    CurrentAccount(double balance) { super(balance); }
    void withdraw(double amount) {
        if (balance - amount < -5000) {
            System.out.println("Overdraft limit exceeded.");
        } else {
            balance -= amount;
        }
    }
}

public class BankAccountDemo {
    public static void main(String[] args) {
        SavingsAccount sa = new SavingsAccount(500);
        CurrentAccount ca = new CurrentAccount(1000);
        sa.withdraw(450);
        sa.displayBalance();
        ca.withdraw(6000);
        ca.displayBalance();
        ca.deposit(2000);
        ca.displayBalance();
    }
}
