/**
 * Experiment 3.3: Create a base class Animal with an instance variable name 
 * and a method makeSound() that prints a generic message like "Some sound...".
 * Create a subclass Dog that inherits from Animal and overrides makeSound() 
 * to print "Bark! Bark!".
 * In the main method, create an instance of the Dog class, set its name, 
 * call makeSound(), and print the dog's name.
 */

// Base class Animal
class Animal {
    // Instance variable
    protected String name;
    
    // Default constructor
    public Animal() {
        this.name = "Unknown Animal";
    }
    
    // Parameterized constructor
    public Animal(String name) {
        this.name = name;
    }
    
    // Method that will be overridden
    public void makeSound() {
        System.out.println("Some sound...");
    }
    
    // Getter method for name
    public String getName() {
        return name;
    }
    
    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }
    
    // Method to display animal info
    public void displayInfo() {
        System.out.println("Animal Name: " + name);
    }
    
    // Method to show animal behavior
    public void eat() {
        System.out.println(name + " is eating.");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping.");
    }
}

// Subclass Dog that inherits from Animal
class Dog extends Animal {
    private String breed;
    
    // Default constructor
    public Dog() {
        super(); // Call parent constructor
        this.breed = "Unknown Breed";
    }
    
    // Parameterized constructor
    public Dog(String name) {
        super(name); // Call parent constructor with name
        this.breed = "Unknown Breed";
    }
    
    // Parameterized constructor with breed
    public Dog(String name, String breed) {
        super(name);
        this.breed = breed;
    }
    
    // Override the makeSound method
    @Override
    public void makeSound() {
        System.out.println("Bark! Bark!");
    }
    
    // Dog-specific methods
    public void wagTail() {
        System.out.println(name + " is wagging its tail!");
    }
    
    public void fetch() {
        System.out.println(name + " is fetching the ball!");
    }
    
    // Getter and setter for breed
    public String getBreed() {
        return breed;
    }
    
    public void setBreed(String breed) {
        this.breed = breed;
    }
    
    // Override displayInfo to include breed
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Breed: " + breed);
    }
    
    // Override eat method to show dog-specific behavior
    @Override
    public void eat() {
        System.out.println(name + " is eating dog food happily!");
    }
}

// Another subclass for demonstration
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println("Meow! Meow!");
    }
    
    public void purr() {
        System.out.println(name + " is purring contentedly.");
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("=== Inheritance and Method Overriding Demonstration ===\n");
        
        // Create an instance of the Dog class
        Dog myDog = new Dog();
        
        // Set the dog's name
        myDog.setName("Buddy");
        
        // Call makeSound() method (overridden version)
        System.out.println("--- Basic Requirements ---");
        System.out.print(myDog.getName() + " says: ");
        myDog.makeSound();
        
        // Print the dog's name
        System.out.println("Dog's name: " + myDog.getName());
        
        System.out.println("\n--- Extended Demonstration ---");
        
        // Create another dog with parameterized constructor
        Dog dog2 = new Dog("Max", "Golden Retriever");
        
        // Demonstrate various methods
        System.out.println("\nDog 2 Details:");
        dog2.displayInfo();
        System.out.print(dog2.getName() + " says: ");
        dog2.makeSound();
        
        // Demonstrate inherited and overridden methods
        System.out.println("\n--- Method Behavior ---");
        dog2.eat();        // Overridden method
        dog2.sleep();      // Inherited method
        dog2.wagTail();    // Dog-specific method
        dog2.fetch();      // Dog-specific method
        
        // Demonstrate polymorphism
        System.out.println("\n--- Polymorphism Demonstration ---");
        
        Animal animal1 = new Dog("Charlie", "Beagle");
        Animal animal2 = new Cat("Whiskers");
        Animal animal3 = new Animal("Generic Animal");
        
        Animal[] animals = {animal1, animal2, animal3};
        
        for (Animal animal : animals) {
            System.out.print(animal.getName() + " says: ");
            animal.makeSound(); // Calls appropriate overridden method
        }
        
        // Demonstrate method hiding vs overriding
        System.out.println("\n--- Base Class vs Subclass Methods ---");
        
        Animal baseAnimal = new Animal("Base Animal");
        Dog derivedDog = new Dog("Derived Dog", "Labrador");
        
        System.out.println("Base Animal:");
        baseAnimal.displayInfo();
        System.out.print("Sound: ");
        baseAnimal.makeSound();
        
        System.out.println("\nDerived Dog:");
        derivedDog.displayInfo();
        System.out.print("Sound: ");
        derivedDog.makeSound();
        
        // Type casting demonstration
        System.out.println("\n--- Type Casting ---");
        
        Animal animalRef = new Dog("Rocky", "German Shepherd");
        System.out.print("Via Animal reference: ");
        animalRef.makeSound(); // Still calls Dog's makeSound due to dynamic binding
        
        // Downcast to access Dog-specific methods
        if (animalRef instanceof Dog) {
            Dog dogRef = (Dog) animalRef;
            dogRef.wagTail(); // Now we can access Dog-specific methods
        }
    }
}
