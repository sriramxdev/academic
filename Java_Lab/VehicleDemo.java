abstract class Vehicle {
    abstract void startEngine();
}

interface FuelType {
    void fuelEfficiency();
}

class Car extends Vehicle implements FuelType {
    void startEngine() { System.out.println("Car engine started."); }
    public void fuelEfficiency() { System.out.println("Car: 15 km/l"); }
}

class Bike extends Vehicle implements FuelType {
    void startEngine() { System.out.println("Bike engine started."); }
    public void fuelEfficiency() { System.out.println("Bike: 40 km/l"); }
}

public class VehicleDemo {
    public static void main(String[] args) {
        Car car = new Car();
        Bike bike = new Bike();
        car.startEngine();
        car.fuelEfficiency();
        bike.startEngine();
        bike.fuelEfficiency();
    }
}
