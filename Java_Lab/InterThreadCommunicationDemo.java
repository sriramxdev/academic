/**
 * Experiment 5.2: Develop an inter-thread communication mechanism using wait() and notify().
 */

import java.util.ArrayList;
import java.util.List;

// Message passing system using wait() and notify()
class MessageQueue {
    private final List<String> messages = new ArrayList<>();
    private final int maxSize;
    
    public MessageQueue(int maxSize) {
        this.maxSize = maxSize;
    }
    
    // Producer method - adds message to queue
    public synchronized void sendMessage(String message, String senderName) throws InterruptedException {
        while (messages.size() >= maxSize) {
            System.out.println(senderName + " waiting - message queue full (" + messages.size() + "/" + maxSize + ")");
            wait(); // Wait until space is available
        }
        
        messages.add(message);
        System.out.println(senderName + " sent: \"" + message + "\" (Queue: " + messages.size() + "/" + maxSize + ")");
        
        notifyAll(); // Notify all waiting receivers
    }
    
    // Consumer method - removes message from queue
    public synchronized String receiveMessage(String receiverName) throws InterruptedException {
        while (messages.isEmpty()) {
            System.out.println(receiverName + " waiting - no messages available");
            wait(); // Wait until message is available
        }
        
        String message = messages.remove(0);
        System.out.println(receiverName + " received: \"" + message + "\" (Queue: " + messages.size() + "/" + maxSize + ")");
        
        notifyAll(); // Notify all waiting senders
        return message;
    }
    
    public synchronized int getQueueSize() {
        return messages.size();
    }
}

// Print job queue simulation
class PrintQueue {
    private final List<String> printJobs = new ArrayList<>();
    private boolean printerBusy = false;
    
    // Add print job to queue
    public synchronized void addPrintJob(String job, String clientName) throws InterruptedException {
        printJobs.add(job);
        System.out.println(clientName + " added print job: \"" + job + "\" (Queue: " + printJobs.size() + ")");
        
        notifyAll(); // Notify waiting printer
    }
    
    // Printer processes jobs
    public synchronized String getNextJob(String printerName) throws InterruptedException {
        while (printJobs.isEmpty()) {
            System.out.println(printerName + " waiting for print jobs...");
            wait(); // Wait for jobs to be added
        }
        
        String job = printJobs.remove(0);
        printerBusy = true;
        System.out.println(printerName + " started printing: \"" + job + "\" (Queue: " + printJobs.size() + ")");
        
        return job;
    }
    
    // Printer finishes job
    public synchronized void finishJob(String job, String printerName) {
        printerBusy = false;
        System.out.println(printerName + " finished printing: \"" + job + "\"");
        
        notifyAll(); // Notify waiting clients if needed
    }
    
    public synchronized boolean isPrinterBusy() {
        return printerBusy;
    }
    
    public synchronized int getQueueSize() {
        return printJobs.size();
    }
}

// Bank transaction system with account balance notifications
class BankAccountWithNotification {
    private double balance;
    private final String accountNumber;
    private double minimumBalance = 1000.0;
    
    public BankAccountWithNotification(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    // Deposit money and notify waiting threads
    public synchronized void deposit(double amount, String threadName) {
        double oldBalance = balance;
        balance += amount;
        
        System.out.println(threadName + " deposited ₹" + amount + 
                          " (₹" + oldBalance + " → ₹" + balance + ")");
        
        if (oldBalance < minimumBalance && balance >= minimumBalance) {
            System.out.println("*** Balance restored above minimum! Notifying waiting withdrawers ***");
            notifyAll(); // Notify threads waiting for sufficient balance
        }
    }
    
    // Withdraw money, wait if insufficient balance
    public synchronized boolean withdraw(double amount, String threadName) throws InterruptedException {
        while (balance < amount) {
            System.out.println(threadName + " waiting - insufficient balance (Need: ₹" + amount + 
                              ", Available: ₹" + balance + ")");
            wait(); // Wait until sufficient balance is available
        }
        
        double oldBalance = balance;
        balance -= amount;
        
        System.out.println(threadName + " withdrew ₹" + amount + 
                          " (₹" + oldBalance + " → ₹" + balance + ")");
        
        return true;
    }
    
    // Check balance without waiting
    public synchronized double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
}

// Resource pool management
class ResourcePool {
    private final List<String> availableResources = new ArrayList<>();
    private final List<String> usedResources = new ArrayList<>();
    private final int totalResources;
    
    public ResourcePool(int totalResources) {
        this.totalResources = totalResources;
        
        // Initialize resources
        for (int i = 1; i <= totalResources; i++) {
            availableResources.add("Resource-" + i);
        }
    }
    
    // Acquire a resource
    public synchronized String acquireResource(String clientName) throws InterruptedException {
        while (availableResources.isEmpty()) {
            System.out.println(clientName + " waiting for available resource...");
            wait(); // Wait until a resource becomes available
        }
        
        String resource = availableResources.remove(0);
        usedResources.add(resource);
        
        System.out.println(clientName + " acquired " + resource + 
                          " (Available: " + availableResources.size() + 
                          ", Used: " + usedResources.size() + ")");
        
        return resource;
    }
    
    // Release a resource
    public synchronized void releaseResource(String resource, String clientName) {
        if (usedResources.remove(resource)) {
            availableResources.add(resource);
            
            System.out.println(clientName + " released " + resource + 
                              " (Available: " + availableResources.size() + 
                              ", Used: " + usedResources.size() + ")");
            
            notify(); // Notify one waiting client
        }
    }
    
    public synchronized int getAvailableCount() {
        return availableResources.size();
    }
}

// Message sender thread
class MessageSender extends Thread {
    private final MessageQueue messageQueue;
    private final String[] messages;
    private final int delay;
    
    public MessageSender(MessageQueue messageQueue, String[] messages, int delay, String name) {
        super(name);
        this.messageQueue = messageQueue;
        this.messages = messages;
        this.delay = delay;
    }
    
    @Override
    public void run() {
        try {
            for (String message : messages) {
                messageQueue.sendMessage(message, getName());
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(getName() + " interrupted");
        }
    }
}

// Message receiver thread
class MessageReceiver extends Thread {
    private final MessageQueue messageQueue;
    private final int messagesToReceive;
    private final int delay;
    
    public MessageReceiver(MessageQueue messageQueue, int messagesToReceive, int delay, String name) {
        super(name);
        this.messageQueue = messageQueue;
        this.messagesToReceive = messagesToReceive;
        this.delay = delay;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 0; i < messagesToReceive; i++) {
                messageQueue.receiveMessage(getName());
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(getName() + " interrupted");
        }
    }
}

// Print client thread
class PrintClient extends Thread {
    private final PrintQueue printQueue;
    private final String[] jobs;
    private final int delay;
    
    public PrintClient(PrintQueue printQueue, String[] jobs, int delay, String name) {
        super(name);
        this.printQueue = printQueue;
        this.jobs = jobs;
        this.delay = delay;
    }
    
    @Override
    public void run() {
        try {
            for (String job : jobs) {
                printQueue.addPrintJob(job, getName());
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(getName() + " interrupted");
        }
    }
}

// Printer thread
class Printer extends Thread {
    private final PrintQueue printQueue;
    private final int jobsToProcess;
    private final int processingTime;
    
    public Printer(PrintQueue printQueue, int jobsToProcess, int processingTime, String name) {
        super(name);
        this.printQueue = printQueue;
        this.jobsToProcess = jobsToProcess;
        this.processingTime = processingTime;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 0; i < jobsToProcess; i++) {
                String job = printQueue.getNextJob(getName());
                
                // Simulate printing time
                Thread.sleep(processingTime);
                
                printQueue.finishJob(job, getName());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(getName() + " interrupted");
        }
    }
}

// Bank transaction thread
class BankTransactionThread extends Thread {
    private final BankAccountWithNotification account;
    private final String operation;
    private final double[] amounts;
    private final int delay;
    
    public BankTransactionThread(BankAccountWithNotification account, String operation, 
                               double[] amounts, int delay, String name) {
        super(name);
        this.account = account;
        this.operation = operation;
        this.amounts = amounts;
        this.delay = delay;
    }
    
    @Override
    public void run() {
        try {
            for (double amount : amounts) {
                if ("deposit".equals(operation)) {
                    account.deposit(amount, getName());
                } else if ("withdraw".equals(operation)) {
                    account.withdraw(amount, getName());
                }
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(getName() + " interrupted");
        }
    }
}

// Resource client thread
class ResourceClient extends Thread {
    private final ResourcePool resourcePool;
    private final int resourceUsageTime;
    private final int operationCount;
    
    public ResourceClient(ResourcePool resourcePool, int resourceUsageTime, int operationCount, String name) {
        super(name);
        this.resourcePool = resourcePool;
        this.resourceUsageTime = resourceUsageTime;
        this.operationCount = operationCount;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 0; i < operationCount; i++) {
                // Acquire resource
                String resource = resourcePool.acquireResource(getName());
                
                // Use resource for some time
                Thread.sleep(resourceUsageTime);
                
                // Release resource
                resourcePool.releaseResource(resource, getName());
                
                // Wait before next operation
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(getName() + " interrupted");
        }
    }
}

public class InterThreadCommunicationDemo {
    
    private static void printSection(String title) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println(title);
        System.out.println("=".repeat(70));
    }
    
    /**
     * Demonstrates message passing between threads
     */
    private static void demonstrateMessagePassing() throws InterruptedException {
        printSection("MESSAGE PASSING DEMO");
        
        MessageQueue messageQueue = new MessageQueue(3);
        
        // Create senders with different messages
        String[] messages1 = {"Hello", "How are you?", "Good morning"};
        String[] messages2 = {"Hi there", "Nice weather", "See you later", "Goodbye"};
        
        MessageSender sender1 = new MessageSender(messageQueue, messages1, 200, "Sender-1");
        MessageSender sender2 = new MessageSender(messageQueue, messages2, 300, "Sender-2");
        
        // Create receivers
        MessageReceiver receiver1 = new MessageReceiver(messageQueue, 4, 400, "Receiver-1");
        MessageReceiver receiver2 = new MessageReceiver(messageQueue, 3, 500, "Receiver-2");
        
        System.out.println("Starting message passing demo (Queue size: 3)");
        
        // Start all threads
        sender1.start();
        sender2.start();
        receiver1.start();
        receiver2.start();
        
        // Wait for completion
        sender1.join();
        sender2.join();
        receiver1.join();
        receiver2.join();
        
        System.out.println("Message passing demo completed. Final queue size: " + messageQueue.getQueueSize());
    }
    
    /**
     * Demonstrates print queue management
     */
    private static void demonstratePrintQueue() throws InterruptedException {
        printSection("PRINT QUEUE DEMO");
        
        PrintQueue printQueue = new PrintQueue();
        
        // Create print clients
        String[] jobs1 = {"Report.pdf", "Invoice.doc", "Presentation.ppt"};
        String[] jobs2 = {"Photo1.jpg", "Photo2.jpg"};
        String[] jobs3 = {"Contract.pdf", "Letter.doc", "Spreadsheet.xls"};
        
        PrintClient client1 = new PrintClient(printQueue, jobs1, 150, "Client-1");
        PrintClient client2 = new PrintClient(printQueue, jobs2, 200, "Client-2");
        PrintClient client3 = new PrintClient(printQueue, jobs3, 100, "Client-3");
        
        // Create printers
        Printer printer1 = new Printer(printQueue, 4, 300, "Printer-1");
        Printer printer2 = new Printer(printQueue, 4, 400, "Printer-2");
        
        System.out.println("Starting print queue demo");
        
        // Start clients first
        client1.start();
        client2.start();
        client3.start();
        
        // Start printers after a short delay
        Thread.sleep(100);
        printer1.start();
        printer2.start();
        
        // Wait for completion
        client1.join();
        client2.join();
        client3.join();
        printer1.join();
        printer2.join();
        
        System.out.println("Print queue demo completed. Final queue size: " + printQueue.getQueueSize());
    }
    
    /**
     * Demonstrates bank account with balance-based waiting
     */
    private static void demonstrateBankAccountNotification() throws InterruptedException {
        printSection("BANK ACCOUNT NOTIFICATION DEMO");
        
        BankAccountWithNotification account = new BankAccountWithNotification("ACC001", 500.0);
        System.out.println("Initial balance: ₹" + account.getBalance());
        
        // Create transaction threads
        double[] deposits = {200.0, 800.0, 300.0};
        double[] withdrawals1 = {1000.0, 400.0}; // First withdrawal will wait
        double[] withdrawals2 = {600.0, 200.0};
        
        BankTransactionThread depositor = new BankTransactionThread(account, "deposit", deposits, 400, "Depositor");
        BankTransactionThread withdrawer1 = new BankTransactionThread(account, "withdraw", withdrawals1, 300, "Withdrawer-1");
        BankTransactionThread withdrawer2 = new BankTransactionThread(account, "withdraw", withdrawals2, 500, "Withdrawer-2");
        
        System.out.println("Starting bank transaction demo");
        
        // Start withdrawers first (they will wait for sufficient balance)
        withdrawer1.start();
        withdrawer2.start();
        
        // Start depositor after a delay
        Thread.sleep(200);
        depositor.start();
        
        // Wait for completion
        depositor.join();
        withdrawer1.join();
        withdrawer2.join();
        
        System.out.println("Bank transaction demo completed. Final balance: ₹" + account.getBalance());
    }
    
    /**
     * Demonstrates resource pool management
     */
    private static void demonstrateResourcePool() throws InterruptedException {
        printSection("RESOURCE POOL DEMO");
        
        ResourcePool resourcePool = new ResourcePool(3); // Only 3 resources available
        
        // Create resource clients
        ResourceClient client1 = new ResourceClient(resourcePool, 400, 2, "Client-1");
        ResourceClient client2 = new ResourceClient(resourcePool, 300, 2, "Client-2");
        ResourceClient client3 = new ResourceClient(resourcePool, 500, 2, "Client-3");
        ResourceClient client4 = new ResourceClient(resourcePool, 200, 1, "Client-4");
        
        System.out.println("Starting resource pool demo (3 resources available)");
        
        // Start all clients
        client1.start();
        client2.start();
        client3.start();
        client4.start();
        
        // Wait for completion
        client1.join();
        client2.join();
        client3.join();
        client4.join();
        
        System.out.println("Resource pool demo completed. Available resources: " + resourcePool.getAvailableCount());
    }
    
    /**
     * Demonstrates basic wait() and notify() concepts
     */
    private static void demonstrateBasicWaitNotify() throws InterruptedException {
        printSection("BASIC WAIT/NOTIFY CONCEPTS");
        
        final Object lock = new Object();
        final boolean[] condition = {false};
        
        // Waiting thread
        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Waiter: Waiting for condition to become true...");
                    while (!condition[0]) {
                        lock.wait(); // Release lock and wait
                    }
                    System.out.println("Waiter: Condition is now true! Continuing execution...");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Waiter");
        
        // Notifying thread
        Thread notifier = new Thread(() -> {
            try {
                Thread.sleep(1000); // Wait for 1 second
                
                synchronized (lock) {
                    System.out.println("Notifier: Setting condition to true");
                    condition[0] = true;
                    lock.notify(); // Wake up waiting thread
                    System.out.println("Notifier: Notified waiting thread");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Notifier");
        
        System.out.println("Starting basic wait/notify demo");
        
        waiter.start();
        notifier.start();
        
        waiter.join();
        notifier.join();
        
        System.out.println("Basic wait/notify demo completed");
    }
    
    /**
     * Shows the differences between notify() and notifyAll()
     */
    private static void demonstrateNotifyVsNotifyAll() throws InterruptedException {
        printSection("NOTIFY() vs NOTIFYALL() DEMO");
        
        final Object lock = new Object();
        final boolean[] condition = {false};
        
        // Create multiple waiting threads
        Thread[] waiters = new Thread[3];
        for (int i = 0; i < 3; i++) {
            final int threadNum = i + 1;
            waiters[i] = new Thread(() -> {
                synchronized (lock) {
                    try {
                        System.out.println("Waiter-" + threadNum + ": Started waiting...");
                        while (!condition[0]) {
                            lock.wait();
                        }
                        System.out.println("Waiter-" + threadNum + ": Woke up and continuing!");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Waiter-" + threadNum);
        }
        
        // Notifier thread using notifyAll()
        Thread notifier = new Thread(() -> {
            try {
                Thread.sleep(1000);
                
                synchronized (lock) {
                    System.out.println("Notifier: Setting condition to true");
                    condition[0] = true;
                    System.out.println("Notifier: Using notifyAll() to wake up ALL waiting threads");
                    lock.notifyAll(); // Wake up ALL waiting threads
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Notifier");
        
        System.out.println("Starting notify vs notifyAll demo");
        System.out.println("Multiple threads will wait, then notifyAll() will wake them all up");
        
        // Start all waiters
        for (Thread waiter : waiters) {
            waiter.start();
        }
        
        // Start notifier
        notifier.start();
        
        // Wait for completion
        for (Thread waiter : waiters) {
            waiter.join();
        }
        notifier.join();
        
        System.out.println("Notify vs NotifyAll demo completed");
        System.out.println("Note: If we used notify() instead of notifyAll(), only one thread would wake up");
    }
    
    public static void main(String[] args) {
        System.out.println("JAVA INTER-THREAD COMMUNICATION DEMONSTRATION");
        System.out.println("This program demonstrates wait() and notify() for thread coordination");
        
        try {
            demonstrateBasicWaitNotify();
            demonstrateNotifyVsNotifyAll();
            demonstrateMessagePassing();
            demonstratePrintQueue();
            demonstrateBankAccountNotification();
            demonstrateResourcePool();
            
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            printSection("INTER-THREAD COMMUNICATION DEMO COMPLETED");
            
            System.out.println("Key Concepts Demonstrated:");
            System.out.println("✓ wait() - Thread releases lock and waits for notification");
            System.out.println("✓ notify() - Wakes up one waiting thread");
            System.out.println("✓ notifyAll() - Wakes up all waiting threads");
            System.out.println("✓ Producer-Consumer pattern");
            System.out.println("✓ Resource pool management");
            System.out.println("✓ Condition-based waiting and notification");
            System.out.println("✓ Message queuing systems");
            
            System.out.println("\nBest Practices:");
            System.out.println("• Always use wait()/notify() inside synchronized blocks");
            System.out.println("• Use while loops, not if statements, for wait conditions");
            System.out.println("• Prefer notifyAll() over notify() to avoid missed signals");
            System.out.println("• Handle InterruptedException properly");
        }
    }
}
