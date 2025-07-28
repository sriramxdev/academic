/**
 * Experiment 3.1: Create a Java class Employee with attributes empID, empName, and empDesignation.
 * Initialize these attributes using a constructor and create three instances. Display their details.
 */

class Employee {
    private int empID;
    private String empName;
    private String empDesignation;
    
    // Constructor to initialize employee attributes
    public Employee(int empID, String empName, String empDesignation) {
        this.empID = empID;
        this.empName = empName;
        this.empDesignation = empDesignation;
    }
    
    // Getter methods
    public int getEmpID() {
        return empID;
    }
    
    public String getEmpName() {
        return empName;
    }
    
    public String getEmpDesignation() {
        return empDesignation;
    }
    
    // Setter methods
    public void setEmpID(int empID) {
        this.empID = empID;
    }
    
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    public void setEmpDesignation(String empDesignation) {
        this.empDesignation = empDesignation;
    }
    
    // Method to display employee details
    public void displayDetails() {
        System.out.println("Employee Details:");
        System.out.println("ID: " + empID);
        System.out.println("Name: " + empName);
        System.out.println("Designation: " + empDesignation);
        System.out.println("------------------------");
    }
    
    // Override toString method for better representation
    @Override
    public String toString() {
        return "Employee{ID=" + empID + ", Name='" + empName + "', Designation='" + empDesignation + "'}";
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {
        System.out.println("=== Employee Management System ===\n");
        
        // Create three instances of Employee
        Employee emp1 = new Employee(101, "Amit Sharma", "Software Engineer");
        Employee emp2 = new Employee(102, "Priya Singh", "Project Manager");
        Employee emp3 = new Employee(103, "Rahul Kumar", "Senior Developer");
        
        // Display details of all employees
        System.out.println("Displaying details of all employees:\n");
        
        emp1.displayDetails();
        emp2.displayDetails();
        emp3.displayDetails();
        
        // Demonstrate getter and setter methods
        System.out.println("=== Demonstrating Getter/Setter Methods ===\n");
        
        System.out.println("Original details of Employee 1:");
        System.out.println(emp1.toString());
        
        // Modify employee details using setter methods
        emp1.setEmpDesignation("Senior Software Engineer");
        
        System.out.println("\nAfter promotion:");
        System.out.println(emp1.toString());
        
        // Display all employees using toString method
        System.out.println("\n=== All Employees Summary ===");
        Employee[] employees = {emp1, emp2, emp3};
        
        for (int i = 0; i < employees.length; i++) {
            System.out.println((i + 1) + ". " + employees[i].toString());
        }
    }
}
