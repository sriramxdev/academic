/**
 * Experiment 6.2: Implement a Java program demonstrating the use of ArrayList, LinkedList, and HashSet.
 */

import java.util.*;
import java.util.stream.Collectors;

// Custom class for demonstrations
class Book {
    private final String title;
    private final String author;
    private final int year;
    private final String isbn;
    private final double price;
    
    public Book(String title, String author, int year, String isbn, double price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.price = price;
    }
    
    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public String getIsbn() { return isbn; }
    public double getPrice() { return price; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(isbn, book.isbn); // Books are equal if ISBN is same
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(isbn); // Hash based on ISBN
    }
    
    @Override
    public String toString() {
        return String.format("Book{title='%s', author='%s', year=%d, isbn='%s', price=$%.2f}", 
                           title, author, year, isbn, price);
    }
}

// Performance measurement utility
class PerformanceMeasurement {
    public static void measureOperation(String operation, Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        System.out.printf("%s took %.3f ms%n", operation, duration);
    }
}

public class CollectionsFrameworkDemo {
    
    private static void printSection(String title) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println(title);
        System.out.println("=".repeat(70));
    }
    
    /**
     * Demonstrates ArrayList operations
     */
    private static void demonstrateArrayList() {
        printSection("ARRAYLIST DEMONSTRATION");
        
        System.out.println("1. Basic ArrayList Operations:");
        
        // Create ArrayList
        ArrayList<String> fruits = new ArrayList<>();
        
        // Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");
        fruits.add("Elderberry");
        
        System.out.println("Initial list: " + fruits);
        System.out.println("Size: " + fruits.size());
        
        // Access elements
        System.out.println("First element: " + fruits.get(0));
        System.out.println("Last element: " + fruits.get(fruits.size() - 1));
        
        // Insert at specific position
        fruits.add(2, "Coconut");
        System.out.println("After inserting Coconut at index 2: " + fruits);
        
        // Update element
        fruits.set(1, "Blueberry");
        System.out.println("After updating index 1 to Blueberry: " + fruits);
        
        // Remove elements
        fruits.remove("Date");
        System.out.println("After removing Date: " + fruits);
        
        fruits.remove(0);
        System.out.println("After removing element at index 0: " + fruits);
        
        System.out.println("\n2. ArrayList Search and Contains:");
        
        // Search operations
        System.out.println("Contains Cherry: " + fruits.contains("Cherry"));
        System.out.println("Index of Cherry: " + fruits.indexOf("Cherry"));
        System.out.println("Contains Mango: " + fruits.contains("Mango"));
        
        System.out.println("\n3. ArrayList Iteration:");
        
        // Different ways to iterate
        System.out.println("Enhanced for loop:");
        for (String fruit : fruits) {
            System.out.println("  " + fruit);
        }
        
        System.out.println("Using Iterator:");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            System.out.println("  " + iterator.next());
        }
        
        System.out.println("Using Lambda and forEach:");
        fruits.forEach(fruit -> System.out.println("  " + fruit));
        
        System.out.println("\n4. ArrayList with Custom Objects:");
        
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Java: The Complete Reference", "Herbert Schildt", 2020, "978-1260440232", 45.99));
        books.add(new Book("Effective Java", "Joshua Bloch", 2018, "978-0134685991", 52.99));
        books.add(new Book("Clean Code", "Robert Martin", 2008, "978-0132350884", 39.99));
        books.add(new Book("Spring in Action", "Craig Walls", 2020, "978-1617297571", 49.99));
        
        System.out.println("Books in ArrayList:");
        books.forEach(System.out::println);
        
        // Sorting
        books.sort(Comparator.comparing(Book::getTitle));
        System.out.println("\nSorted by title:");
        books.forEach(System.out::println);
        
        // Filtering
        List<Book> expensiveBooks = books.stream()
            .filter(book -> book.getPrice() > 45.0)
            .collect(Collectors.toList());
        
        System.out.println("\nExpensive books (> $45):");
        expensiveBooks.forEach(System.out::println);
    }
    
    /**
     * Demonstrates LinkedList operations
     */
    private static void demonstrateLinkedList() {
        printSection("LINKEDLIST DEMONSTRATION");
        
        System.out.println("1. Basic LinkedList Operations:");
        
        // Create LinkedList
        LinkedList<Integer> numbers = new LinkedList<>();
        
        // Add elements
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);
        
        System.out.println("Initial list: " + numbers);
        
        // LinkedList specific operations
        numbers.addFirst(5);
        System.out.println("After addFirst(5): " + numbers);
        
        numbers.addLast(60);
        System.out.println("After addLast(60): " + numbers);
        
        System.out.println("First element: " + numbers.getFirst());
        System.out.println("Last element: " + numbers.getLast());
        
        // Remove operations
        int removedFirst = numbers.removeFirst();
        int removedLast = numbers.removeLast();
        System.out.println("Removed first: " + removedFirst + ", last: " + removedLast);
        System.out.println("After removals: " + numbers);
        
        System.out.println("\n2. LinkedList as Queue (FIFO):");
        
        LinkedList<String> queue = new LinkedList<>();
        
        // Enqueue operations
        queue.offer("Customer 1");
        queue.offer("Customer 2");
        queue.offer("Customer 3");
        queue.offer("Customer 4");
        
        System.out.println("Queue: " + queue);
        
        // Dequeue operations
        while (!queue.isEmpty()) {
            String customer = queue.poll();
            System.out.println("Serving: " + customer + ", Remaining: " + queue);
        }
        
        System.out.println("\n3. LinkedList as Stack (LIFO):");
        
        LinkedList<String> stack = new LinkedList<>();
        
        // Push operations
        stack.push("Page 1");
        stack.push("Page 2");
        stack.push("Page 3");
        stack.push("Page 4");
        
        System.out.println("Stack: " + stack);
        
        // Pop operations
        while (!stack.isEmpty()) {
            String page = stack.pop();
            System.out.println("Back to: " + page + ", Stack: " + stack);
        }
        
        System.out.println("\n4. LinkedList Performance Characteristics:");
        
        LinkedList<Integer> performanceList = new LinkedList<>();
        
        // Add elements
        PerformanceMeasurement.measureOperation("Adding 10000 elements to LinkedList", () -> {
            for (int i = 0; i < 10000; i++) {
                performanceList.add(i);
            }
        });
        
        // Insert at beginning
        PerformanceMeasurement.measureOperation("Inserting 1000 elements at beginning", () -> {
            for (int i = 0; i < 1000; i++) {
                performanceList.addFirst(i);
            }
        });
        
        // Access elements (slower for LinkedList)
        PerformanceMeasurement.measureOperation("Accessing random elements 1000 times", () -> {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                int index = random.nextInt(performanceList.size());
                performanceList.get(index);
            }
        });
        
        System.out.println("Final LinkedList size: " + performanceList.size());
    }
    
    /**
     * Demonstrates HashSet operations
     */
    private static void demonstrateHashSet() {
        printSection("HASHSET DEMONSTRATION");
        
        System.out.println("1. Basic HashSet Operations:");
        
        // Create HashSet
        HashSet<String> colors = new HashSet<>();
        
        // Add elements
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Yellow");
        colors.add("Purple");
        
        System.out.println("Initial set: " + colors);
        System.out.println("Size: " + colors.size());
        
        // Attempt to add duplicate
        boolean added = colors.add("Red");
        System.out.println("Adding duplicate 'Red': " + added);
        System.out.println("Set after duplicate add: " + colors);
        
        // Contains check
        System.out.println("Contains Green: " + colors.contains("Green"));
        System.out.println("Contains Orange: " + colors.contains("Orange"));
        
        // Remove element
        boolean removed = colors.remove("Yellow");
        System.out.println("Removed Yellow: " + removed);
        System.out.println("Set after removal: " + colors);
        
        System.out.println("\n2. HashSet Uniqueness with Custom Objects:");
        
        HashSet<Book> bookSet = new HashSet<>();
        
        Book book1 = new Book("Design Patterns", "Gang of Four", 1994, "978-0201633610", 54.99);
        Book book2 = new Book("Java Concurrency", "Brian Goetz", 2006, "978-0321349606", 59.99);
        Book book3 = new Book("Design Patterns", "Gang of Four", 1994, "978-0201633610", 54.99); // Same ISBN as book1
        
        bookSet.add(book1);
        bookSet.add(book2);
        bookSet.add(book3); // Won't be added due to same ISBN
        
        System.out.println("Books in HashSet (duplicates removed):");
        bookSet.forEach(System.out::println);
        System.out.println("Set size: " + bookSet.size());
        
        System.out.println("\n3. Set Operations:");
        
        HashSet<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        HashSet<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));
        
        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);
        
        // Union
        HashSet<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union: " + union);
        
        // Intersection
        HashSet<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);
        
        // Difference
        HashSet<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (Set1 - Set2): " + difference);
        
        System.out.println("\n4. HashSet Performance:");
        
        HashSet<Integer> performanceSet = new HashSet<>();
        
        // Add elements
        PerformanceMeasurement.measureOperation("Adding 100000 elements to HashSet", () -> {
            for (int i = 0; i < 100000; i++) {
                performanceSet.add(i);
            }
        });
        
        // Lookup operations
        PerformanceMeasurement.measureOperation("Looking up 10000 elements in HashSet", () -> {
            Random random = new Random();
            for (int i = 0; i < 10000; i++) {
                int value = random.nextInt(100000);
                performanceSet.contains(value);
            }
        });
        
        // Remove operations
        PerformanceMeasurement.measureOperation("Removing 10000 elements from HashSet", () -> {
            for (int i = 0; i < 10000; i++) {
                performanceSet.remove(i);
            }
        });
        
        System.out.println("Final HashSet size: " + performanceSet.size());
    }
    
    /**
     * Compares performance between ArrayList, LinkedList, and HashSet
     */
    private static void compareCollectionPerformance() {
        printSection("COLLECTION PERFORMANCE COMPARISON");
        
        int numElements = 50000;
        
        System.out.println("Performance comparison with " + numElements + " elements:");
        
        // ArrayList performance
        System.out.println("\n1. ArrayList Performance:");
        ArrayList<Integer> arrayList = new ArrayList<>();
        
        PerformanceMeasurement.measureOperation("ArrayList - Adding elements", () -> {
            for (int i = 0; i < numElements; i++) {
                arrayList.add(i);
            }
        });
        
        PerformanceMeasurement.measureOperation("ArrayList - Random access (1000 times)", () -> {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                int index = random.nextInt(arrayList.size());
                arrayList.get(index);
            }
        });
        
        PerformanceMeasurement.measureOperation("ArrayList - Search operations (1000 times)", () -> {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                int value = random.nextInt(numElements);
                arrayList.contains(value);
            }
        });
        
        // LinkedList performance
        System.out.println("\n2. LinkedList Performance:");
        LinkedList<Integer> linkedList = new LinkedList<>();
        
        PerformanceMeasurement.measureOperation("LinkedList - Adding elements", () -> {
            for (int i = 0; i < numElements; i++) {
                linkedList.add(i);
            }
        });
        
        PerformanceMeasurement.measureOperation("LinkedList - Random access (1000 times)", () -> {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                int index = random.nextInt(linkedList.size());
                linkedList.get(index);
            }
        });
        
        PerformanceMeasurement.measureOperation("LinkedList - Insert at beginning (1000 times)", () -> {
            for (int i = 0; i < 1000; i++) {
                linkedList.addFirst(i);
            }
        });
        
        // HashSet performance
        System.out.println("\n3. HashSet Performance:");
        HashSet<Integer> hashSet = new HashSet<>();
        
        PerformanceMeasurement.measureOperation("HashSet - Adding elements", () -> {
            for (int i = 0; i < numElements; i++) {
                hashSet.add(i);
            }
        });
        
        PerformanceMeasurement.measureOperation("HashSet - Search operations (10000 times)", () -> {
            Random random = new Random();
            for (int i = 0; i < 10000; i++) {
                int value = random.nextInt(numElements);
                hashSet.contains(value);
            }
        });
        
        PerformanceMeasurement.measureOperation("HashSet - Remove operations (1000 times)", () -> {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                int value = random.nextInt(numElements);
                hashSet.remove(value);
            }
        });
        
        System.out.println("\nFinal sizes:");
        System.out.println("ArrayList: " + arrayList.size());
        System.out.println("LinkedList: " + linkedList.size());
        System.out.println("HashSet: " + hashSet.size());
    }
    
    /**
     * Demonstrates practical use cases for each collection
     */
    private static void demonstratePracticalUseCases() {
        printSection("PRACTICAL USE CASES");
        
        System.out.println("1. ArrayList - Student Grade Management:");
        
        ArrayList<Double> grades = new ArrayList<>();
        grades.addAll(Arrays.asList(85.5, 92.0, 78.5, 95.0, 88.0, 76.5, 90.5));
        
        System.out.println("Grades: " + grades);
        
        // Calculate statistics
        double sum = grades.stream().mapToDouble(Double::doubleValue).sum();
        double average = sum / grades.size();
        double max = grades.stream().mapToDouble(Double::doubleValue).max().orElse(0);
        double min = grades.stream().mapToDouble(Double::doubleValue).min().orElse(0);
        
        System.out.printf("Average: %.2f, Max: %.2f, Min: %.2f%n", average, max, min);
        
        // Filter passing grades
        List<Double> passingGrades = grades.stream()
            .filter(grade -> grade >= 80.0)
            .sorted(Collections.reverseOrder())
            .collect(Collectors.toList());
        
        System.out.println("Passing grades (≥80): " + passingGrades);
        
        System.out.println("\n2. LinkedList - Browser History:");
        
        LinkedList<String> browserHistory = new LinkedList<>();
        
        // Simulate browsing
        String[] websites = {"google.com", "stackoverflow.com", "github.com", "oracle.com", "baeldung.com"};
        
        for (String site : websites) {
            browserHistory.addLast(site);
            System.out.println("Visited: " + site + " | History: " + browserHistory);
        }
        
        // Go back in history
        System.out.println("\nGoing back in history:");
        while (browserHistory.size() > 1) {
            String lastSite = browserHistory.removeLast();
            System.out.println("Back from: " + lastSite + " | Current: " + browserHistory.getLast());
        }
        
        System.out.println("\n3. HashSet - Unique Visitor Tracking:");
        
        HashSet<String> uniqueVisitors = new HashSet<>();
        String[] visitorLogs = {
            "user123", "user456", "user789", "user123", "user999", 
            "user456", "user111", "user789", "user123", "user222"
        };
        
        System.out.println("Visitor log: " + Arrays.toString(visitorLogs));
        
        for (String visitor : visitorLogs) {
            uniqueVisitors.add(visitor);
        }
        
        System.out.println("Unique visitors: " + uniqueVisitors);
        System.out.println("Total visits: " + visitorLogs.length);
        System.out.println("Unique visitors count: " + uniqueVisitors.size());
        
        // Find repeat visitors
        Set<String> repeatVisitors = new HashSet<>();
        Set<String> seen = new HashSet<>();
        
        for (String visitor : visitorLogs) {
            if (!seen.add(visitor)) {
                repeatVisitors.add(visitor);
            }
        }
        
        System.out.println("Repeat visitors: " + repeatVisitors);
    }
    
    /**
     * Demonstrates collection conversions and interoperability
     */
    private static void demonstrateCollectionConversions() {
        printSection("COLLECTION CONVERSIONS AND INTEROPERABILITY");
        
        // Start with ArrayList
        ArrayList<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry", "Date", "Elderberry"));
        System.out.println("Original ArrayList: " + fruits);
        
        // Convert to LinkedList
        LinkedList<String> fruitsLinkedList = new LinkedList<>(fruits);
        System.out.println("Converted to LinkedList: " + fruitsLinkedList);
        
        // Convert to HashSet (removes duplicates)
        fruits.add("Apple"); // Add duplicate
        fruits.add("Banana"); // Add duplicate
        System.out.println("ArrayList with duplicates: " + fruits);
        
        HashSet<String> fruitsSet = new HashSet<>(fruits);
        System.out.println("Converted to HashSet (duplicates removed): " + fruitsSet);
        
        // Convert back to ArrayList (sorted)
        ArrayList<String> sortedFruits = new ArrayList<>(fruitsSet);
        Collections.sort(sortedFruits);
        System.out.println("Back to sorted ArrayList: " + sortedFruits);
        
        // Array conversions
        String[] fruitsArray = sortedFruits.toArray(new String[0]);
        System.out.println("Converted to Array: " + Arrays.toString(fruitsArray));
        
        // Array back to List
        List<String> fruitsFromArray = Arrays.asList(fruitsArray);
        System.out.println("Array back to List: " + fruitsFromArray);
        
        // Stream operations across collections
        System.out.println("\nStream operations:");
        
        List<String> longNames = fruits.stream()
            .filter(fruit -> fruit.length() > 5)
            .map(String::toUpperCase)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        
        System.out.println("Long fruit names (>5 chars, uppercase, distinct): " + longNames);
    }
    
    public static void main(String[] args) {
        System.out.println("JAVA COLLECTIONS FRAMEWORK DEMONSTRATION");
        System.out.println("This program demonstrates ArrayList, LinkedList, and HashSet usage and characteristics");
        
        try {
            demonstrateArrayList();
            demonstrateLinkedList();
            demonstrateHashSet();
            compareCollectionPerformance();
            demonstratePracticalUseCases();
            demonstrateCollectionConversions();
            
        } catch (Exception e) {
            System.err.println("Error during demonstration: " + e.getMessage());
        } finally {
            printSection("COLLECTIONS FRAMEWORK DEMO COMPLETED");
            
            System.out.println("Summary of Collection Characteristics:");
            
            System.out.println("\nArrayList:");
            System.out.println("✓ Resizable array implementation");
            System.out.println("✓ Fast random access O(1)");
            System.out.println("✓ Slow insertion/deletion in middle O(n)");
            System.out.println("✓ Allows duplicates");
            System.out.println("✓ Maintains insertion order");
            System.out.println("✓ Best for: Frequent access, rare insertions/deletions");
            
            System.out.println("\nLinkedList:");
            System.out.println("✓ Doubly-linked list implementation");
            System.out.println("✓ Fast insertion/deletion at ends O(1)");
            System.out.println("✓ Slow random access O(n)");
            System.out.println("✓ Allows duplicates");
            System.out.println("✓ Maintains insertion order");
            System.out.println("✓ Best for: Frequent insertions/deletions, queue/stack operations");
            
            System.out.println("\nHashSet:");
            System.out.println("✓ Hash table implementation");
            System.out.println("✓ Fast add/remove/contains O(1) average");
            System.out.println("✓ No duplicates allowed");
            System.out.println("✓ No guaranteed order");
            System.out.println("✓ Best for: Unique elements, fast lookups");
            
            System.out.println("\nChoose the right collection based on your use case!");
        }
    }
}
