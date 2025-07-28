/**
 * Experiment 5.3: Write a Java program to read data from two files, merge their content,
 * and write the merged output into a new file. Ensure proper exception handling for file operations.
 */

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileHandlingDemo {
    
    private static final String INPUT_FILE_1 = "input_file_1.txt";
    private static final String INPUT_FILE_2 = "input_file_2.txt";
    private static final String OUTPUT_FILE = "merged_output.txt";
    private static final String LOG_FILE = "file_operations_log.txt";
    
    /**
     * Creates sample input files for demonstration
     */
    private static void createSampleFiles() {
        System.out.println("Creating sample input files...");
        
        // Content for first file
        String[] content1 = {
            "=== STUDENT INFORMATION - SECTION A ===",
            "1. John Doe - Computer Science - Grade: A",
            "2. Jane Smith - Information Technology - Grade: B+",
            "3. Bob Wilson - Software Engineering - Grade: A-",
            "4. Alice Brown - Data Science - Grade: A+",
            "5. Charlie Davis - Cybersecurity - Grade: B",
            "",
            "Section A Total Students: 5",
            "Average Grade: A-",
            "=== END OF SECTION A ==="
        };
        
        // Content for second file
        String[] content2 = {
            "=== STUDENT INFORMATION - SECTION B ===",
            "1. Emma Johnson - Computer Science - Grade: A",
            "2. Michael Chen - Machine Learning - Grade: A+",
            "3. Sarah Williams - Web Development - Grade: B+",
            "4. David Miller - Database Management - Grade: A-",
            "5. Lisa Garcia - Network Security - Grade: B",
            "6. Tom Anderson - Mobile Development - Grade: A",
            "",
            "Section B Total Students: 6",
            "Average Grade: A-",
            "=== END OF SECTION B ==="
        };
        
        // Create first file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INPUT_FILE_1))) {
            for (String line : content1) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("✓ Created " + INPUT_FILE_1 + " (" + content1.length + " lines)");
        } catch (IOException e) {
            System.err.println("✗ Error creating " + INPUT_FILE_1 + ": " + e.getMessage());
        }
        
        // Create second file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INPUT_FILE_2))) {
            for (String line : content2) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("✓ Created " + INPUT_FILE_2 + " (" + content2.length + " lines)");
        } catch (IOException e) {
            System.err.println("✗ Error creating " + INPUT_FILE_2 + ": " + e.getMessage());
        }
    }
    
    /**
     * Reads content from a file using BufferedReader
     */
    private static List<String> readFileContent(String filename) throws IOException {
        List<String> content = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }
            System.out.println("✓ Successfully read " + filename + " (" + content.size() + " lines)");
        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + filename, e);
        } catch (IOException e) {
            throw new IOException("Error reading file: " + filename + " - " + e.getMessage(), e);
        }
        
        return content;
    }
    
    /**
     * Alternative method to read file using Files.readAllLines (Java 7+)
     */
    private static List<String> readFileUsingNIO(String filename) throws IOException {
        try {
            Path path = Paths.get(filename);
            List<String> content = Files.readAllLines(path);
            System.out.println("✓ Successfully read " + filename + " using NIO (" + content.size() + " lines)");
            return content;
        } catch (NoSuchFileException e) {
            throw new IOException("File not found: " + filename, e);
        } catch (IOException e) {
            throw new IOException("Error reading file using NIO: " + filename + " - " + e.getMessage(), e);
        }
    }
    
    /**
     * Merges content from two files with additional metadata
     */
    private static List<String> mergeFileContents(List<String> content1, List<String> content2, 
                                                 String file1Name, String file2Name) {
        List<String> mergedContent = new ArrayList<>();
        
        // Add header with timestamp
        mergedContent.add("=".repeat(80));
        mergedContent.add("MERGED FILE CONTENT");
        mergedContent.add("Generated on: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mergedContent.add("Source Files: " + file1Name + " + " + file2Name);
        mergedContent.add("=".repeat(80));
        mergedContent.add("");
        
        // Add content from first file
        mergedContent.add(">>> CONTENT FROM: " + file1Name + " <<<");
        mergedContent.addAll(content1);
        mergedContent.add("");
        mergedContent.add(">>> END OF " + file1Name + " <<<");
        mergedContent.add("");
        
        // Add separator
        mergedContent.add("-".repeat(50));
        mergedContent.add("");
        
        // Add content from second file
        mergedContent.add(">>> CONTENT FROM: " + file2Name + " <<<");
        mergedContent.addAll(content2);
        mergedContent.add("");
        mergedContent.add(">>> END OF " + file2Name + " <<<");
        mergedContent.add("");
        
        // Add footer with statistics
        mergedContent.add("=".repeat(80));
        mergedContent.add("MERGE STATISTICS");
        mergedContent.add("File 1 (" + file1Name + "): " + content1.size() + " lines");
        mergedContent.add("File 2 (" + file2Name + "): " + content2.size() + " lines");
        mergedContent.add("Total merged content: " + (content1.size() + content2.size()) + " lines");
        mergedContent.add("Final output: " + mergedContent.size() + " lines (including headers/footers)");
        mergedContent.add("=".repeat(80));
        
        System.out.println("✓ Successfully merged contents (Total lines: " + mergedContent.size() + ")");
        return mergedContent;
    }
    
    /**
     * Writes merged content to output file
     */
    private static void writeMergedContent(List<String> content, String outputFilename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            for (String line : content) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("✓ Successfully wrote merged content to " + outputFilename + 
                              " (" + content.size() + " lines)");
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + outputFilename + " - " + e.getMessage(), e);
        }
    }
    
    /**
     * Alternative method to write file using Files.write (Java 7+)
     */
    private static void writeFileUsingNIO(List<String> content, String outputFilename) throws IOException {
        try {
            Path path = Paths.get(outputFilename);
            Files.write(path, content);
            System.out.println("✓ Successfully wrote file using NIO: " + outputFilename + 
                              " (" + content.size() + " lines)");
        } catch (IOException e) {
            throw new IOException("Error writing file using NIO: " + outputFilename + " - " + e.getMessage(), e);
        }
    }
    
    /**
     * Logs operations to a log file
     */
    private static void logOperation(String operation, boolean success, String details) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String status = success ? "SUCCESS" : "FAILURE";
        String logEntry = String.format("[%s] %s - %s: %s", timestamp, status, operation, details);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Warning: Could not write to log file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates various file reading methods with exception handling
     */
    private static void demonstrateFileReading() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("FILE READING DEMONSTRATION");
        System.out.println("=".repeat(60));
        
        String[] testFiles = {INPUT_FILE_1, INPUT_FILE_2, "nonexistent_file.txt"};
        
        for (String filename : testFiles) {
            System.out.println("\nTesting file: " + filename);
            
            // Method 1: Using BufferedReader
            try {
                List<String> content = readFileContent(filename);
                System.out.println("  BufferedReader: Success (" + content.size() + " lines)");
                logOperation("Read with BufferedReader", true, filename + " - " + content.size() + " lines");
            } catch (IOException e) {
                System.err.println("  BufferedReader: Failed - " + e.getMessage());
                logOperation("Read with BufferedReader", false, filename + " - " + e.getMessage());
            }
            
            // Method 2: Using NIO (only for existing files)
            if (!filename.equals("nonexistent_file.txt")) {
                try {
                    List<String> content = readFileUsingNIO(filename);
                    System.out.println("  NIO Files: Success (" + content.size() + " lines)");
                    logOperation("Read with NIO", true, filename + " - " + content.size() + " lines");
                } catch (IOException e) {
                    System.err.println("  NIO Files: Failed - " + e.getMessage());
                    logOperation("Read with NIO", false, filename + " - " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Main file merging operation with comprehensive exception handling
     */
    private static void performFileMerging() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("FILE MERGING OPERATION");
        System.out.println("=".repeat(60));
        
        List<String> content1 = null;
        List<String> content2 = null;
        
        try {
            // Step 1: Read first file
            System.out.println("\nStep 1: Reading " + INPUT_FILE_1);
            content1 = readFileContent(INPUT_FILE_1);
            logOperation("Read File 1", true, INPUT_FILE_1 + " - " + content1.size() + " lines");
            
            // Step 2: Read second file
            System.out.println("\nStep 2: Reading " + INPUT_FILE_2);
            content2 = readFileContent(INPUT_FILE_2);
            logOperation("Read File 2", true, INPUT_FILE_2 + " - " + content2.size() + " lines");
            
            // Step 3: Merge contents
            System.out.println("\nStep 3: Merging file contents");
            List<String> mergedContent = mergeFileContents(content1, content2, INPUT_FILE_1, INPUT_FILE_2);
            logOperation("Merge Contents", true, "Merged " + content1.size() + " + " + content2.size() + " lines");
            
            // Step 4: Write merged content
            System.out.println("\nStep 4: Writing merged content to " + OUTPUT_FILE);
            writeMergedContent(mergedContent, OUTPUT_FILE);
            logOperation("Write Merged File", true, OUTPUT_FILE + " - " + mergedContent.size() + " lines");
            
            // Step 5: Create backup using NIO
            String backupFile = "backup_" + OUTPUT_FILE;
            System.out.println("\nStep 5: Creating backup using NIO: " + backupFile);
            writeFileUsingNIO(mergedContent, backupFile);
            logOperation("Create Backup", true, backupFile + " - " + mergedContent.size() + " lines");
            
            System.out.println("\n✓ File merging operation completed successfully!");
            
        } catch (IOException e) {
            System.err.println("\n✗ File merging operation failed!");
            System.err.println("Error: " + e.getMessage());
            
            // Log the failure
            logOperation("File Merging Operation", false, e.getMessage());
            
            // Print stack trace for debugging
            System.err.println("\nDetailed error information:");
            e.printStackTrace();
            
            // Attempt to provide helpful suggestions
            System.err.println("\nTroubleshooting suggestions:");
            if (e.getCause() instanceof FileNotFoundException) {
                System.err.println("- Check if input files exist and are readable");
                System.err.println("- Verify file paths are correct");
            } else {
                System.err.println("- Check file permissions");
                System.err.println("- Ensure sufficient disk space");
                System.err.println("- Verify files are not locked by other applications");
            }
            
        } catch (Exception e) {
            System.err.println("\n✗ Unexpected error during file operations!");
            System.err.println("Error: " + e.getMessage());
            logOperation("Unexpected Error", false, e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates file properties and metadata
     */
    private static void demonstrateFileProperties() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("FILE PROPERTIES DEMONSTRATION");
        System.out.println("=".repeat(60));
        
        String[] files = {INPUT_FILE_1, INPUT_FILE_2, OUTPUT_FILE, LOG_FILE};
        
        for (String filename : files) {
            try {
                File file = new File(filename);
                if (file.exists()) {
                    System.out.println("\nFile: " + filename);
                    System.out.println("  Exists: " + file.exists());
                    System.out.println("  Size: " + file.length() + " bytes");
                    System.out.println("  Readable: " + file.canRead());
                    System.out.println("  Writable: " + file.canWrite());
                    System.out.println("  Last modified: " + new Date(file.lastModified()));
                    System.out.println("  Absolute path: " + file.getAbsolutePath());
                    
                    // Using NIO for additional properties
                    try {
                        Path path = Paths.get(filename);
                        System.out.println("  File type: " + Files.probeContentType(path));
                        System.out.println("  Is regular file: " + Files.isRegularFile(path));
                    } catch (IOException e) {
                        System.err.println("  Could not read NIO properties: " + e.getMessage());
                    }
                } else {
                    System.out.println("\nFile: " + filename + " - Does not exist");
                }
            } catch (Exception e) {
                System.err.println("\nError checking properties of " + filename + ": " + e.getMessage());
            }
        }
    }
    
    /**
     * Demonstrates different exception handling strategies
     */
    private static void demonstrateExceptionHandling() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXCEPTION HANDLING STRATEGIES");
        System.out.println("=".repeat(60));
        
        // Strategy 1: Try-with-resources (automatic resource management)
        System.out.println("\n1. Try-with-resources (recommended):");
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE_1));
             BufferedWriter writer = new BufferedWriter(new FileWriter("temp_copy.txt"))) {
            
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
                lineCount++;
            }
            System.out.println("✓ Copied " + lineCount + " lines using try-with-resources");
            
        } catch (IOException e) {
            System.err.println("✗ Try-with-resources failed: " + e.getMessage());
        }
        // Note: Resources are automatically closed, even if exception occurs
        
        // Strategy 2: Traditional try-catch-finally
        System.out.println("\n2. Traditional try-catch-finally:");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(INPUT_FILE_2));
            String firstLine = reader.readLine();
            System.out.println("✓ First line: " + firstLine);
            
        } catch (IOException e) {
            System.err.println("✗ Traditional approach failed: " + e.getMessage());
        } finally {
            // Manual resource cleanup
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("✓ Reader closed manually in finally block");
                } catch (IOException e) {
                    System.err.println("✗ Error closing reader: " + e.getMessage());
                }
            }
        }
        
        // Strategy 3: Multiple exception types
        System.out.println("\n3. Handling multiple exception types:");
        try {
            // This might throw various exceptions
            Path path = Paths.get("test_file.txt");
            Files.write(path, Arrays.asList("Test content"));
            Files.delete(path);
            System.out.println("✓ Created and deleted test file successfully");
            
        } catch (NoSuchFileException e) {
            System.err.println("✗ File not found: " + e.getMessage());
        } catch (AccessDeniedException e) {
            System.err.println("✗ Access denied: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("✗ General I/O error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("✗ Unexpected error: " + e.getMessage());
        }
    }
    
    /**
     * Cleanup temporary files
     */
    private static void cleanup() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("CLEANUP OPERATIONS");
        System.out.println("=".repeat(60));
        
        String[] filesToDelete = {"temp_copy.txt", "test_file.txt"};
        
        for (String filename : filesToDelete) {
            try {
                File file = new File(filename);
                if (file.exists() && file.delete()) {
                    System.out.println("✓ Deleted temporary file: " + filename);
                }
            } catch (Exception e) {
                System.err.println("✗ Could not delete " + filename + ": " + e.getMessage());
            }
        }
        
        // Show final file status
        System.out.println("\nFinal file status:");
        String[] finalFiles = {INPUT_FILE_1, INPUT_FILE_2, OUTPUT_FILE, "backup_" + OUTPUT_FILE, LOG_FILE};
        for (String filename : finalFiles) {
            File file = new File(filename);
            if (file.exists()) {
                System.out.println("  " + filename + " - " + file.length() + " bytes");
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("JAVA FILE HANDLING WITH EXCEPTION HANDLING DEMONSTRATION");
        System.out.println("This program demonstrates reading, merging, and writing files with proper exception handling");
        
        try {
            // Initialize log file
            logOperation("Program Start", true, "File handling demonstration started");
            
            // Step 1: Create sample files
            createSampleFiles();
            
            // Step 2: Demonstrate different file reading methods
            demonstrateFileReading();
            
            // Step 3: Perform main file merging operation
            performFileMerging();
            
            // Step 4: Show file properties
            demonstrateFileProperties();
            
            // Step 5: Demonstrate exception handling strategies
            demonstrateExceptionHandling();
            
            // Step 6: Cleanup
            cleanup();
            
            logOperation("Program Complete", true, "All operations completed successfully");
            
        } catch (Exception e) {
            System.err.println("\nUnexpected error in main method: " + e.getMessage());
            logOperation("Program Error", false, "Main method error: " + e.getMessage());
        } finally {
            System.out.println("\n" + "=".repeat(80));
            System.out.println("FILE HANDLING DEMONSTRATION COMPLETED");
            System.out.println("=".repeat(80));
            
            System.out.println("\nKey concepts demonstrated:");
            System.out.println("✓ Reading files using BufferedReader and NIO");
            System.out.println("✓ Writing files using BufferedWriter and NIO");
            System.out.println("✓ Merging content from multiple files");
            System.out.println("✓ Comprehensive exception handling");
            System.out.println("✓ Try-with-resources for automatic resource management");
            System.out.println("✓ File properties and metadata access");
            System.out.println("✓ Logging operations for debugging");
            
            System.out.println("\nGenerated files:");
            System.out.println("• " + INPUT_FILE_1 + " - Sample input file 1");
            System.out.println("• " + INPUT_FILE_2 + " - Sample input file 2");
            System.out.println("• " + OUTPUT_FILE + " - Merged output file");
            System.out.println("• backup_" + OUTPUT_FILE + " - Backup copy");
            System.out.println("• " + LOG_FILE + " - Operation log");
        }
    }
}
