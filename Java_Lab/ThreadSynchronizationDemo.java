/**
 * Experiment 5.1: Create a multithreaded Java program that demonstrates thread synchronization.
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Shared resource class for demonstration
class BankAccount {
    private double balance;
    private final String accountNumber;
    
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    // Synchronized method for deposit
    public synchronized void deposit(double amount, String threadName) {
        System.out.println(threadName + " attempting to deposit ₹" + amount);
        double oldBalance = balance;
        
        // Simulate processing time
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        balance += amount;
        System.out.println(threadName + " deposited ₹" + amount + 
                          " (Balance: ₹" + oldBalance + " → ₹" + balance + ")");
    }
    
    // Synchronized method for withdrawal
    public synchronized boolean withdraw(double amount, String threadName) {
        System.out.println(threadName + " attempting to withdraw ₹" + amount);
        
        if (balance >= amount) {
            double oldBalance = balance;
            
            // Simulate processing time
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            balance -= amount;
            System.out.println(threadName + " withdrew ₹" + amount + 
                              " (Balance: ₹" + oldBalance + " → ₹" + balance + ")");
            return true;
        } else {
            System.out.println(threadName + " withdrawal failed - Insufficient balance (₹" + balance + ")");
            return false;
        }
    }
    
    public synchronized double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
}

// Counter class to demonstrate synchronization issues and solutions
class Counter {
    private int count = 0;
    private final Lock lock = new ReentrantLock();
    
    // Unsynchronized increment - will cause race condition
    public void incrementUnsafe() {
        count++;
    }
    
    // Synchronized increment using synchronized keyword
    public synchronized void incrementSafe() {
        count++;
    }
    
    // Synchronized increment using explicit locks
    public void incrementWithLock() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
    
    // Synchronized block example
    public void incrementWithSyncBlock() {
        synchronized(this) {
            count++;
        }
    }
    
    public synchronized int getCount() {
        return count;
    }
    
    public synchronized void reset() {
        count = 0;
    }
}

// Thread class for bank operations
class BankOperationThread extends Thread {
    private final BankAccount account;
    private final String operation;
    private final double amount;
    private final int operations;
    
    public BankOperationThread(BankAccount account, String operation, double amount, int operations, String name) {
        super(name);
        this.account = account;
        this.operation = operation;
        this.amount = amount;
        this.operations = operations;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < operations; i++) {
            if ("deposit".equals(operation)) {
                account.deposit(amount, getName());
            } else if ("withdraw".equals(operation)) {
                account.withdraw(amount, getName());
            }
            
            // Small delay between operations
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

// Thread class for counter operations
class CounterThread extends Thread {
    private final Counter counter;
    private final String method;
    private final int increments;
    
    public CounterThread(Counter counter, String method, int increments, String name) {
        super(name);
        this.counter = counter;
        this.method = method;
        this.increments = increments;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < increments; i++) {
            switch (method) {
                case "unsafe":
                    counter.incrementUnsafe();
                    break;
                case "safe":
                    counter.incrementSafe();
                    break;
                case "lock":
                    counter.incrementWithLock();
                    break;
                case "block":
                    counter.incrementWithSyncBlock();
                    break;
            }
        }
    }
}

// Producer-Consumer example using synchronization
class Buffer {
    private final int[] buffer;
    private int count = 0;
    private int in = 0;
    private int out = 0;
    
    public Buffer(int size) {
        buffer = new int[size];
    }
    
    // Producer method - adds item to buffer
    public synchronized void produce(int item, String producerName) throws InterruptedException {
        while (count == buffer.length) {
            System.out.println(producerName + " waiting - buffer full");
            wait(); // Wait until space is available
        }
        
        buffer[in] = item;
        in = (in + 1) % buffer.length;
        count++;
        
        System.out.println(producerName + " produced: " + item + " (buffer size: " + count + ")");
        notifyAll(); // Notify waiting consumers
    }
    
    // Consumer method - removes item from buffer
    public synchronized int consume(String consumerName) throws InterruptedException {
        while (count == 0) {
            System.out.println(consumerName + " waiting - buffer empty");
            wait(); // Wait until item is available
        }
        
        int item = buffer[out];
        out = (out + 1) % buffer.length;
        count--;
        
        System.out.println(consumerName + " consumed: " + item + " (buffer size: " + count + ")");
        notifyAll(); // Notify waiting producers
        
        return item;
    }
}

class Producer extends Thread {
    private final Buffer buffer;
    private final int items;
    
    public Producer(Buffer buffer, int items, String name) {
        super(name);
        this.buffer = buffer;
        this.items = items;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 1; i <= items; i++) {
                buffer.produce(i, getName());
                Thread.sleep(100); // Simulate production time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer extends Thread {
    private final Buffer buffer;
    private final int items;
    
    public Consumer(Buffer buffer, int items, String name) {
        super(name);
        this.buffer = buffer;
        this.items = items;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 0; i < items; i++) {
                buffer.consume(getName());
                Thread.sleep(150); // Simulate consumption time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ThreadSynchronizationDemo {
    
    private static void printSection(String title) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(title);
        System.out.println("=".repeat(60));
    }
    
    /**
     * Demonstrates race condition and its solution using synchronized methods
     */
    private static void demonstrateCounterSynchronization() throws InterruptedException {
        printSection("COUNTER SYNCHRONIZATION DEMO");
        
        Counter counter = new Counter();
        int numThreads = 5;
        int incrementsPerThread = 1000;
        
        // Test 1: Unsafe increment (race condition)
        System.out.println("1. Testing UNSAFE increment (race condition expected):");
        counter.reset();
        
        Thread[] unsafeThreads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            unsafeThreads[i] = new CounterThread(counter, "unsafe", incrementsPerThread, "UnsafeThread-" + i);
            unsafeThreads[i].start();
        }
        
        for (Thread thread : unsafeThreads) {
            thread.join();
        }
        
        int expectedCount = numThreads * incrementsPerThread;
        int actualCount = counter.getCount();
        System.out.println("Expected count: " + expectedCount);
        System.out.println("Actual count: " + actualCount);
        System.out.println("Race condition occurred: " + (actualCount != expectedCount));
        
        // Test 2: Safe increment (synchronized method)
        System.out.println("\n2. Testing SAFE increment (synchronized method):");
        counter.reset();
        
        Thread[] safeThreads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            safeThreads[i] = new CounterThread(counter, "safe", incrementsPerThread, "SafeThread-" + i);
            safeThreads[i].start();
        }
        
        for (Thread thread : safeThreads) {
            thread.join();
        }
        
        actualCount = counter.getCount();
        System.out.println("Expected count: " + expectedCount);
        System.out.println("Actual count: " + actualCount);
        System.out.println("Synchronization successful: " + (actualCount == expectedCount));
        
        // Test 3: Lock-based synchronization
        System.out.println("\n3. Testing LOCK-based increment:");
        counter.reset();
        
        Thread[] lockThreads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            lockThreads[i] = new CounterThread(counter, "lock", incrementsPerThread, "LockThread-" + i);
            lockThreads[i].start();
        }
        
        for (Thread thread : lockThreads) {
            thread.join();
        }
        
        actualCount = counter.getCount();
        System.out.println("Expected count: " + expectedCount);
        System.out.println("Actual count: " + actualCount);
        System.out.println("Lock synchronization successful: " + (actualCount == expectedCount));
    }
    
    /**
     * Demonstrates synchronized methods in a realistic scenario
     */
    private static void demonstrateBankAccountSynchronization() throws InterruptedException {
        printSection("BANK ACCOUNT SYNCHRONIZATION DEMO");
        
        BankAccount account = new BankAccount("ACC001", 10000.0);
        System.out.println("Initial balance: ₹" + account.getBalance());
        
        // Create multiple threads for deposits and withdrawals
        Thread depositor1 = new BankOperationThread(account, "deposit", 500, 3, "Depositor-1");
        Thread depositor2 = new BankOperationThread(account, "deposit", 300, 4, "Depositor-2");
        Thread withdrawer1 = new BankOperationThread(account, "withdraw", 400, 3, "Withdrawer-1");
        Thread withdrawer2 = new BankOperationThread(account, "withdraw", 600, 2, "Withdrawer-2");
        
        // Start all threads
        depositor1.start();
        depositor2.start();
        withdrawer1.start();
        withdrawer2.start();
        
        // Wait for all threads to complete
        depositor1.join();
        depositor2.join();
        withdrawer1.join();
        withdrawer2.join();
        
        System.out.println("\nFinal balance: ₹" + account.getBalance());
        
        // Calculate expected balance
        double expectedBalance = 10000 + (500 * 3) + (300 * 4) - (400 * 3) - (600 * 2);
        System.out.println("Expected balance: ₹" + expectedBalance);
        System.out.println("Balance verification: " + (Math.abs(account.getBalance() - expectedBalance) < 0.01));
    }
    
    /**
     * Demonstrates Producer-Consumer pattern with wait() and notify()
     */
    private static void demonstrateProducerConsumer() throws InterruptedException {
        printSection("PRODUCER-CONSUMER SYNCHRONIZATION DEMO");
        
        Buffer buffer = new Buffer(5); // Buffer of size 5
        
        // Create producers and consumers
        Producer producer1 = new Producer(buffer, 8, "Producer-1");
        Producer producer2 = new Producer(buffer, 6, "Producer-2");
        Consumer consumer1 = new Consumer(buffer, 7, "Consumer-1");
        Consumer consumer2 = new Consumer(buffer, 7, "Consumer-2");
        
        System.out.println("Starting Producer-Consumer demo with buffer size 5");
        
        // Start all threads
        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        
        // Wait for all threads to complete
        producer1.join();
        producer2.join();
        consumer1.join();
        consumer2.join();
        
        System.out.println("Producer-Consumer demo completed");
    }
    
    /**
     * Demonstrates various synchronization mechanisms
     */
    private static void demonstrateSynchronizationMechanisms() {
        printSection("SYNCHRONIZATION MECHANISMS OVERVIEW");
        
        System.out.println("1. SYNCHRONIZED METHODS:");
        System.out.println("   - Entire method is synchronized");
        System.out.println("   - Uses object's intrinsic lock");
        System.out.println("   - Example: public synchronized void method() { ... }");
        
        System.out.println("\n2. SYNCHRONIZED BLOCKS:");
        System.out.println("   - Only specific code block is synchronized");
        System.out.println("   - Can specify which object to lock on");
        System.out.println("   - Example: synchronized(this) { ... }");
        
        System.out.println("\n3. EXPLICIT LOCKS (ReentrantLock):");
        System.out.println("   - More flexible than synchronized");
        System.out.println("   - Supports try-lock, timed lock, interruptible lock");
        System.out.println("   - Must manually unlock in finally block");
        
        System.out.println("\n4. WAIT() AND NOTIFY():");
        System.out.println("   - Used for inter-thread communication");
        System.out.println("   - wait() releases lock and waits");
        System.out.println("   - notify()/notifyAll() wakes up waiting threads");
        
        System.out.println("\n5. VOLATILE KEYWORD:");
        System.out.println("   - Ensures visibility of variable changes");
        System.out.println("   - Prevents caching of variable values");
        System.out.println("   - Does not provide atomicity");
    }
    
    /**
     * Demonstrates deadlock scenario and its prevention
     */
    private static void demonstrateDeadlockPrevention() throws InterruptedException {
        printSection("DEADLOCK PREVENTION DEMO");
        
        final Object lock1 = new Object();
        final Object lock2 = new Object();
        
        // Thread 1: acquires lock1 then lock2
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread-1: Acquired lock1");
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                System.out.println("Thread-1: Waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread-1: Acquired lock2");
                }
            }
        }, "Thread-1");
        
        // Thread 2: acquires lock1 then lock2 (same order to prevent deadlock)
        Thread thread2 = new Thread(() -> {
            synchronized (lock1) { // Same order as Thread-1
                System.out.println("Thread-2: Acquired lock1");
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                System.out.println("Thread-2: Waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread-2: Acquired lock2");
                }
            }
        }, "Thread-2");
        
        System.out.println("Starting threads with proper lock ordering (no deadlock):");
        
        thread1.start();
        thread2.start();
        
        thread1.join();
        thread2.join();
        
        System.out.println("Both threads completed successfully - no deadlock occurred");
    }
    
    public static void main(String[] args) {
        System.out.println("JAVA THREAD SYNCHRONIZATION DEMONSTRATION");
        System.out.println("This program demonstrates various thread synchronization techniques");
        
        try {
            demonstrateCounterSynchronization();
            demonstrateBankAccountSynchronization();
            demonstrateProducerConsumer();
            demonstrateSynchronizationMechanisms();
            demonstrateDeadlockPrevention();
            
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            printSection("SYNCHRONIZATION DEMO COMPLETED");
            
            System.out.println("Key Takeaways:");
            System.out.println("✓ Synchronization prevents race conditions");
            System.out.println("✓ Multiple synchronization mechanisms available");
            System.out.println("✓ wait()/notify() enables inter-thread communication");
            System.out.println("✓ Proper lock ordering prevents deadlocks");
            System.out.println("✓ Choose appropriate synchronization based on requirements");
        }
    }
}
