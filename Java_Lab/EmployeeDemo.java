class Employee {
    int empID;
    String empName;
    String empDesignation;

    Employee(int id, String name, String desig) {
        empID = id;
        empName = name;
        empDesignation = desig;
    }

    void display() {
        System.out.println(empID + " " + empName + " " + empDesignation);
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {
        Employee e1 = new Employee(1, "Alice", "Manager");
        Employee e2 = new Employee(2, "Bob", "Developer");
        Employee e3 = new Employee(3, "Charlie", "Tester");
        e1.display();
        e2.display();
        e3.display();
    }
}
