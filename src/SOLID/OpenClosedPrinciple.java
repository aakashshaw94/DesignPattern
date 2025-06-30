package SOLID;
/**
 * Open/Closed Principle (OCP) is one of the SOLID principles of object-oriented design.
 * It states that software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.
 * This means that the behavior of a module can be extended without modifying its source code.
 *
 * Example:
 * - You can add new functionality by adding new classes or methods rather than changing existing ones.
 */
public class OpenClosedPrinciple {
    // Example of a class that adheres to OCP
    public interface Shape {
        double area();
    }

    public class Circle implements Shape {
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }

    public class Rectangle implements Shape {
        private double width;
        private double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double area() {
            return width * height;
        }
    }

    // New shape can be added without modifying existing code
    public class Triangle implements Shape {
        private double base;
        private double height;

        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }

        @Override
        public double area() {
            return 0.5 * base * height;
        }
    }
}
