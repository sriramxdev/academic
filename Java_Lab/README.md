# Java Programming Experiments

This folder contains comprehensive Java programming experiments covering various concepts from basic programming to advanced frameworks.

## Experiment List

### 1. Basic Java Programming Experiments

#### 1.1 Palindrome Check (`PalindromeCheck.java`)
- Program to check if a given string is a palindrome
- Handles spaces and case-insensitive comparison
- **Compilation**: `javac PalindromeCheck.java`
- **Execution**: `java PalindromeCheck`

#### 1.2 Fibonacci Series (`FibonacciSeries.java`)
- Generates Fibonacci series up to 'n' numbers
- Implements both iterative and recursive approaches
- **Compilation**: `javac FibonacciSeries.java`
- **Execution**: `java FibonacciSeries`

### 2. Java Programs Using Command-Line Arguments

#### 2.1 Factorial Calculator (`FactorialCommandLine.java`)
- Computes factorial of a number using command-line arguments
- Implements both iterative and recursive approaches
- **Compilation**: `javac FactorialCommandLine.java`
- **Execution**: `java FactorialCommandLine <number>`
- **Example**: `java FactorialCommandLine 5`

#### 2.2 Second Largest Number (`SecondLargestCommandLine.java`)
- Finds second largest number from command-line arguments
- Implements both sorting and optimal O(n) approaches
- **Compilation**: `javac SecondLargestCommandLine.java`
- **Execution**: `java SecondLargestCommandLine <num1> <num2> ... <numN>`
- **Example**: `java SecondLargestCommandLine 5 8 3 9 1 7`

### 3. Object-Oriented Programming Experiments

#### 3.1 Employee Class (`EmployeeDemo.java`)
- Demonstrates class creation with attributes and constructor
- Shows encapsulation with getter/setter methods
- **Compilation**: `javac EmployeeDemo.java`
- **Execution**: `java EmployeeDemo`

#### 3.2 Method Overloading (`MethodOverloadingDemo.java`)
- Demonstrates method overloading with different parameter types
- Shows various overloaded `add()` methods
- **Compilation**: `javac MethodOverloadingDemo.java`
- **Execution**: `java MethodOverloadingDemo`

#### 3.3 Inheritance (`InheritanceDemo.java`)
- Demonstrates inheritance with Animal and Dog classes
- Shows method overriding and polymorphism
- **Compilation**: `javac InheritanceDemo.java`
- **Execution**: `java InheritanceDemo`

#### 3.4 Bank Account System (`BankAccountDemo.java`)
- Demonstrates inheritance with BankAccount, SavingsAccount, and CurrentAccount
- Shows different withdrawal rules and overdraft facilities
- **Compilation**: `javac BankAccountDemo.java`
- **Execution**: `java BankAccountDemo`

#### 3.5 Abstract Classes and Interfaces (`AbstractInterfaceDemo.java`)
- Demonstrates abstract Vehicle class and FuelType interface
- Shows implementation in Car and Bike subclasses
- **Compilation**: `javac AbstractInterfaceDemo.java`
- **Execution**: `java AbstractInterfaceDemo`

### 4. Packages and Exception Handling Experiments

#### 4.1 Java Packages and JAR Creation (`PackageDemo.java`)
- Demonstrates creating and using custom packages
- Includes utility classes in `com.university.math` and `com.university.utils`
- **Compilation**: 
  ```bash
  javac -d . com/university/math/*.java com/university/utils/*.java PackageDemo.java
  ```
- **Create JAR**: `jar cf university-utils.jar com/ PackageDemo.class`
- **Run from JAR**: `java -cp university-utils.jar PackageDemo`
- **Batch script**: `compile-and-jar.bat` (Windows)

#### 4.2 User-Defined Exceptions (`UserDefinedExceptionsDemo.java`)
- Demonstrates custom exception classes with try-catch-finally
- Shows various exception handling scenarios
- **Compilation**: `javac UserDefinedExceptionsDemo.java`
- **Execution**: `java UserDefinedExceptionsDemo`

#### 4.3 Checked vs Unchecked Exceptions (`CheckedUncheckedException.java`)
- Comprehensive demonstration of different exception types
- Shows compilation differences between checked and unchecked exceptions
- **Compilation**: `javac CheckedUncheckedException.java`
- **Execution**: `java CheckedUncheckedException`

### 5. Multithreading and File Handling Experiments

#### 5.1 Thread Synchronization (`ThreadSynchronizationDemo.java`)
- Demonstrates various synchronization mechanisms
- Shows race conditions and their solutions
- Includes Producer-Consumer pattern
- **Compilation**: `javac ThreadSynchronizationDemo.java`
- **Execution**: `java ThreadSynchronizationDemo`

#### 5.2 Inter-thread Communication (`InterThreadCommunicationDemo.java`)
- Demonstrates wait() and notify() mechanisms
- Shows message passing and resource management
- **Compilation**: `javac InterThreadCommunicationDemo.java`
- **Execution**: `java InterThreadCommunicationDemo`

#### 5.3 File Handling (`FileHandlingDemo.java`)
- Reads from two files, merges content, and writes to output file
- Comprehensive exception handling for file operations
- Creates sample files automatically
- **Compilation**: `javac FileHandlingDemo.java`
- **Execution**: `java FileHandlingDemo`

### 6. Java New Features & Collections Framework Experiments

#### 6.1 Lambda Expressions and Method References (`LambdaMethodReferenceDemo.java`)
- Demonstrates lambda expressions with various syntaxes
- Shows method references (static, instance, constructor)
- Includes built-in functional interfaces
- **Compilation**: `javac LambdaMethodReferenceDemo.java`
- **Execution**: `java LambdaMethodReferenceDemo`

#### 6.2 Collections Framework (`CollectionsFrameworkDemo.java`)
- Demonstrates ArrayList, LinkedList, and HashSet
- Performance comparisons and practical use cases
- Shows collection conversions and interoperability
- **Compilation**: `javac CollectionsFrameworkDemo.java`
- **Execution**: `java CollectionsFrameworkDemo`

### 7. Spring Framework & Spring Boot Experiments

#### 7.1 Spring Dependency Injection
*[To be implemented - requires Spring framework setup]*

#### 7.2 RESTful Web Service with Spring Boot
*[To be implemented - requires Spring Boot setup]*

#### 7.3 Frontend Web Application with Spring Boot
*[To be implemented - requires Spring Boot and web dependencies]*

## System Requirements

- **Java Version**: Java 8 or higher (for lambda expressions and method references)
- **Operating System**: Windows, macOS, or Linux
- **IDE**: Any Java IDE (VS Code, IntelliJ IDEA, Eclipse) or command line

## Compilation Notes

1. **Single File Compilation**:
   ```bash
   javac ClassName.java
   java ClassName
   ```

2. **Package-based Compilation**:
   ```bash
   javac -d . packagepath/*.java MainClass.java
   java MainClass
   ```

3. **JAR Creation**:
   ```bash
   jar cf filename.jar *.class
   java -cp filename.jar MainClass
   ```

## Key Concepts Covered

### Object-Oriented Programming
- Classes and Objects
- Encapsulation, Inheritance, Polymorphism
- Method Overloading and Overriding
- Abstract Classes and Interfaces

### Exception Handling
- Try-catch-finally blocks
- Checked vs Unchecked exceptions
- Custom exception classes
- Resource management

### Multithreading
- Thread synchronization
- wait() and notify() mechanisms
- Producer-Consumer pattern
- Deadlock prevention

### File I/O
- Reading and writing files
- Exception handling in file operations
- NIO vs traditional I/O

### Modern Java Features
- Lambda expressions
- Method references
- Functional interfaces
- Stream API

### Collections Framework
- ArrayList vs LinkedList performance
- HashSet for unique elements
- Collection conversions
- Stream operations

### Packages and Modular Programming
- Package creation and usage
- JAR file creation
- Classpath management

## Generated Files

The programs create various output files:
- `input_file_1.txt`, `input_file_2.txt` - Sample input files
- `merged_output.txt` - Merged file content
- `backup_merged_output.txt` - Backup copy
- `file_operations_log.txt` - Operation log
- `university-utils.jar` - JAR file (when created)

## Best Practices Demonstrated

1. **Code Organization**: Proper package structure and naming conventions
2. **Error Handling**: Comprehensive exception handling strategies
3. **Resource Management**: Try-with-resources for automatic cleanup
4. **Performance**: Choosing appropriate data structures
5. **Documentation**: Comprehensive comments and JavaDoc
6. **Testing**: Multiple test cases and edge conditions

## Troubleshooting

### Common Issues:
1. **ClassNotFoundException**: Ensure proper classpath
2. **Package not found**: Check package directory structure
3. **Compilation errors**: Verify Java version compatibility
4. **File not found**: Check file permissions and paths

### Java Version Compatibility:
- Lambda expressions: Java 8+
- Method references: Java 8+
- Try-with-resources: Java 7+
- Basic features: Java 6+

## Learning Outcomes

After completing these experiments, you will understand:
- Core Java programming concepts
- Object-oriented design principles
- Exception handling best practices
- Multithreading and synchronization
- File I/O operations
- Modern Java features (lambdas, streams)
- Collections framework usage
- Package and JAR management

## Next Steps

1. **Spring Framework**: Learn dependency injection and IoC
2. **Spring Boot**: Build REST APIs and web applications
3. **Database Integration**: JDBC and JPA/Hibernate
4. **Testing**: JUnit and Mockito frameworks
5. **Build Tools**: Maven and Gradle
6. **Advanced Topics**: Microservices, reactive programming
