/**
 * Experiment 4.1: Create and use Java packages in a program, compile them, and create a JAR file.
 * Package: com.university.math
 */
package com.university.math;

public class GeometryCalculator {
    
    public static final double PI = 3.14159265359;
    
    // Circle calculations
    public static double circleArea(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
        return PI * radius * radius;
    }
    
    public static double circleCircumference(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
        return 2 * PI * radius;
    }
    
    // Rectangle calculations
    public static double rectangleArea(double length, double width) {
        if (length < 0 || width < 0) {
            throw new IllegalArgumentException("Length and width cannot be negative");
        }
        return length * width;
    }
    
    public static double rectanglePerimeter(double length, double width) {
        if (length < 0 || width < 0) {
            throw new IllegalArgumentException("Length and width cannot be negative");
        }
        return 2 * (length + width);
    }
    
    // Triangle calculations
    public static double triangleArea(double base, double height) {
        if (base < 0 || height < 0) {
            throw new IllegalArgumentException("Base and height cannot be negative");
        }
        return 0.5 * base * height;
    }
    
    // Using Heron's formula
    public static double triangleAreaHeron(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("All sides must be positive");
        }
        if (a + b <= c || b + c <= a || a + c <= b) {
            throw new IllegalArgumentException("Invalid triangle sides");
        }
        
        double s = (a + b + c) / 2; // semi-perimeter
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
    
    // Sphere calculations
    public static double sphereVolume(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
        return (4.0/3.0) * PI * radius * radius * radius;
    }
    
    public static double sphereSurfaceArea(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
        return 4 * PI * radius * radius;
    }
    
    public static void displayInfo() {
        System.out.println("GeometryCalculator class from com.university.math package");
        System.out.println("Provides geometric calculations for various shapes");
    }
}
