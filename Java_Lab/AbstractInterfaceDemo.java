/**
 * Experiment 3.5: Create an abstract class Vehicle with an abstract method startEngine().
 * Implement this class in Car and Bike subclasses, providing their own versions of startEngine().
 * Also, create an interface FuelType with a method fuelEfficiency(), and implement it in both subclasses.
 * In the main method, create objects of Car and Bike, call startEngine(), and print their fuel efficiency.
 */

// Abstract class Vehicle
abstract class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    protected boolean isEngineRunning;
    
    // Constructor
    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.isEngineRunning = false;
    }
    
    // Abstract method that must be implemented by subclasses
    public abstract void startEngine();
    
    // Concrete methods
    public void stopEngine() {
        if (isEngineRunning) {
            isEngineRunning = false;
            System.out.println(brand + " " + model + " engine stopped.");
        } else {
            System.out.println("Engine is already stopped.");
        }
    }
    
    public void displayInfo() {
        System.out.println("Vehicle: " + brand + " " + model + " (" + year + ")");
        System.out.println("Engine Status: " + (isEngineRunning ? "Running" : "Stopped"));
    }
    
    // Getter methods
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public boolean isEngineRunning() { return isEngineRunning; }
}

// Interface FuelType
interface FuelType {
    // Abstract method for fuel efficiency
    double fuelEfficiency();
    
    // Default method (Java 8 feature)
    default void displayFuelInfo() {
        System.out.println("Fuel Efficiency: " + fuelEfficiency() + " km/l");
    }
    
    // Static method (Java 8 feature)
    static void fuelTypeInfo() {
        System.out.println("This interface defines fuel efficiency standards.");
    }
}

// Additional interface for demonstration
interface Maintenance {
    void performMaintenance();
    
    default int getMaintenanceInterval() {
        return 6; // months
    }
}

// Car class implementing abstract Vehicle and FuelType interface
class Car extends Vehicle implements FuelType, Maintenance {
    private int numberOfDoors;
    private String fuelType;
    private double engineCapacity;
    
    public Car(String brand, String model, int year, int numberOfDoors, String fuelType, double engineCapacity) {
        super(brand, model, year);
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.engineCapacity = engineCapacity;
    }
    
    // Implementation of abstract method from Vehicle
    @Override
    public void startEngine() {
        if (!isEngineRunning) {
            isEngineRunning = true;
            System.out.println("Car engine started: " + brand + " " + model + " is ready to drive!");
            System.out.println("*Vroooom* - Smooth car engine sound");
        } else {
            System.out.println("Car engine is already running.");
        }
    }
    
    // Implementation of fuelEfficiency from FuelType interface
    @Override
    public double fuelEfficiency() {
        // Fuel efficiency calculation based on engine capacity and type
        double baseEfficiency = 20.0; // km/l
        if (fuelType.equalsIgnoreCase("diesel")) {
            baseEfficiency = 25.0;
        } else if (fuelType.equalsIgnoreCase("electric")) {
            return 100.0; // km/charge equivalent
        }
        
        // Larger engines typically less efficient
        if (engineCapacity > 2.0) {
            baseEfficiency *= 0.8;
        }
        
        return baseEfficiency;
    }
    
    // Implementation of performMaintenance from Maintenance interface
    @Override
    public void performMaintenance() {
        System.out.println("Performing car maintenance:");
        System.out.println("- Oil change");
        System.out.println("- Brake inspection");
        System.out.println("- Tire rotation");
        System.out.println("- Engine tuning");
    }
    
    // Car-specific methods
    public void honk() {
        System.out.println("Beep! Beep! - Car horn");
    }
    
    public void openTrunk() {
        System.out.println("Car trunk opened.");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Doors: " + numberOfDoors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Engine Capacity: " + engineCapacity + "L");
    }
}

// Bike class implementing abstract Vehicle and FuelType interface
class Bike extends Vehicle implements FuelType, Maintenance {
    private String bikeType;
    private boolean hasCarrier;
    private int engineCapacityCC;
    
    public Bike(String brand, String model, int year, String bikeType, boolean hasCarrier, int engineCapacityCC) {
        super(brand, model, year);
        this.bikeType = bikeType;
        this.hasCarrier = hasCarrier;
        this.engineCapacityCC = engineCapacityCC;
    }
    
    // Implementation of abstract method from Vehicle
    @Override
    public void startEngine() {
        if (!isEngineRunning) {
            isEngineRunning = true;
            System.out.println("Bike engine started: " + brand + " " + model + " is ready to ride!");
            System.out.println("*Vroom vroom* - Powerful bike engine roar");
        } else {
            System.out.println("Bike engine is already running.");
        }
    }
    
    // Implementation of fuelEfficiency from FuelType interface
    @Override
    public double fuelEfficiency() {
        // Bikes generally more fuel efficient than cars
        double baseEfficiency = 45.0; // km/l
        
        // Efficiency varies with engine capacity
        if (engineCapacityCC <= 150) {
            baseEfficiency = 60.0;
        } else if (engineCapacityCC <= 250) {
            baseEfficiency = 40.0;
        } else {
            baseEfficiency = 25.0; // High-performance bikes
        }
        
        return baseEfficiency;
    }
    
    // Implementation of performMaintenance from Maintenance interface
    @Override
    public void performMaintenance() {
        System.out.println("Performing bike maintenance:");
        System.out.println("- Chain lubrication");
        System.out.println("- Brake pad check");
        System.out.println("- Air filter cleaning");
        System.out.println("- Spark plug inspection");
    }
    
    // Bike-specific methods
    public void ringBell() {
        System.out.println("Ring! Ring! - Bike bell");
    }
    
    public void wheelie() {
        if (isEngineRunning) {
            System.out.println("Performing wheelie on " + brand + " " + model + "!");
        } else {
            System.out.println("Start the engine first to perform wheelie!");
        }
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: " + bikeType);
        System.out.println("Has Carrier: " + (hasCarrier ? "Yes" : "No"));
        System.out.println("Engine: " + engineCapacityCC + " CC");
    }
}

public class AbstractInterfaceDemo {
    public static void main(String[] args) {
        System.out.println("=== Abstract Class and Interface Demonstration ===\n");
        
        // Create objects of Car and Bike
        Car car = new Car("Toyota", "Camry", 2023, 4, "Petrol", 2.5);
        Bike bike = new Bike("Honda", "CBR600RR", 2023, "Sports", false, 600);
        
        System.out.println("--- Vehicle Information ---");
        car.displayInfo();
        System.out.println();
        bike.displayInfo();
        
        System.out.println("\n--- Starting Engines ---");
        
        // Call startEngine() method (abstract method implementation)
        car.startEngine();
        bike.startEngine();
        
        // Try starting again (should show already running message)
        System.out.println("\nTrying to start engines again:");
        car.startEngine();
        bike.startEngine();
        
        System.out.println("\n--- Fuel Efficiency ---");
        
        // Print fuel efficiency (interface method implementation)
        System.out.println("Car (" + car.getBrand() + " " + car.getModel() + "):");
        car.displayFuelInfo(); // Using default method from interface
        
        System.out.println("Bike (" + bike.getBrand() + " " + bike.getModel() + "):");
        bike.displayFuelInfo(); // Using default method from interface
        
        // Direct fuel efficiency access
        System.out.println("\nDirect efficiency values:");
        System.out.println("Car efficiency: " + car.fuelEfficiency() + " km/l");
        System.out.println("Bike efficiency: " + bike.fuelEfficiency() + " km/l");
        
        System.out.println("\n--- Vehicle-Specific Features ---");
        
        car.honk();
        car.openTrunk();
        
        bike.ringBell();
        bike.wheelie();
        
        System.out.println("\n--- Maintenance ---");
        
        System.out.println("Car maintenance:");
        car.performMaintenance();
        System.out.println("Maintenance interval: " + car.getMaintenanceInterval() + " months");
        
        System.out.println("\nBike maintenance:");
        bike.performMaintenance();
        System.out.println("Maintenance interval: " + bike.getMaintenanceInterval() + " months");
        
        System.out.println("\n--- Stopping Engines ---");
        
        car.stopEngine();
        bike.stopEngine();
        
        // Polymorphism demonstration
        System.out.println("\n--- Polymorphism with Abstract Class ---");
        
        Vehicle[] vehicles = {
            new Car("BMW", "X5", 2023, 4, "Diesel", 3.0),
            new Bike("Yamaha", "R15", 2023, "Sports", true, 155)
        };
        
        for (Vehicle vehicle : vehicles) {
            System.out.println("\nProcessing " + vehicle.getClass().getSimpleName() + ":");
            vehicle.startEngine(); // Calls appropriate implementation
            vehicle.displayInfo();
            
            // Check if vehicle implements FuelType interface
            if (vehicle instanceof FuelType) {
                FuelType fuelVehicle = (FuelType) vehicle;
                System.out.println("Fuel efficiency: " + fuelVehicle.fuelEfficiency() + " km/l");
            }
            
            vehicle.stopEngine();
        }
        
        // Interface static method
        System.out.println("\n--- Interface Static Method ---");
        FuelType.fuelTypeInfo();
        
        // Additional demonstration
        System.out.println("\n--- Comparison ---");
        System.out.println("Car vs Bike fuel efficiency:");
        System.out.printf("Car: %.1f km/l | Bike: %.1f km/l%n", 
                         car.fuelEfficiency(), bike.fuelEfficiency());
        System.out.println("Bike is " + 
                          String.format("%.1f", bike.fuelEfficiency() / car.fuelEfficiency()) + 
                          "x more fuel efficient than car.");
    }
}
