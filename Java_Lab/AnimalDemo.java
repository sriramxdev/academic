class Animal {
    String name;
    void makeSound() {
        System.out.println("Some sound...");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Bark! Bark!");
    }
}

public class AnimalDemo {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.name = "Buddy";
        d.makeSound();
        System.out.println("Dog's name: " + d.name);
    }
}
