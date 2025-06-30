package CreationalDesignPattern;

/**
 * ✅ Definition:
 * The Factory Method Pattern defines an interface for creating an object, but lets the subclasses
 * decide which class to instantiate.
 *
 * In simple words:
 * 👉 Factory Method hides the object creation logic and provides a common interface to create objects.
 *
 * 🎯 Why Use Factory Method?
 * When you don’t know beforehand which class object you need.
 *
 * To make your code flexible and scalable.
 *
 * 📦 Real-Life Example:
 * Think of a coffee shop:
 *
 * You order a drink (common interface).
 *
 * The barista decides whether to give you Latte or Cappuccino (specific class).
 *
 * 🚀 Quick Summary:
 * Concept	        Description
 * -------------------------------------------------
 * What	            Creates objects without exposing the creation logic to the client.
 * Why	            Adds flexibility to the code, easy to add new types later.
 * Use cases	    UI components, Coffee Shops, Document creators, Database drivers.
 * Key Benefit	    Reduces tight coupling between client and concrete classes.
 *
 *
 */

public class FactoryMethod {
    // Product Interface
    public interface Coffee {
        void prepare();
    }

    public class Latte implements Coffee {
        public void prepare() {
            System.out.println("Preparing a Latte.");
        }
    }

    public class Cappuccino implements Coffee {
        public void prepare() {
            System.out.println("Preparing a Cappuccino.");
        }
    }

    public class CoffeeFactory {
        // Factory Method
        public Coffee getCoffee(String type) {
            if (type.equalsIgnoreCase("Latte")) {
                return new Latte();
            } else if (type.equalsIgnoreCase("Cappuccino")) {
                return new Cappuccino();
            }
            return null;
        }
    }

    public class Main {
        public void main(String[] args) {

            CoffeeFactory factory = new CoffeeFactory();

            Coffee coffee1 = factory.getCoffee("Latte");
            coffee1.prepare();

            Coffee coffee2 = factory.getCoffee("Cappuccino");
            coffee2.prepare();
        }
    }

}
