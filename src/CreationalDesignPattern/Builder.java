package CreationalDesignPattern;

/**
 *
 *
 * ✅ Definition:
 * The Builder Pattern is used to construct complex objects step-by-step.
 * It separates how an object is made from its final representation so that the same building process
 * can create different objects.
 *
 * 🎯 Why Use It?
 * When you need to create an object with many optional parts or complex construction steps.
 *
 * 📦 Real-Life Example:
 * 🍔 Building a Burger:
 * You can choose ingredients: bun, lettuce, cheese, sauce, meat, etc.
 *
 * The construction process is step-by-step.
 *
 * You may build different types of burgers using the same steps.
 *
 *
 * 🎯 Key Concepts:
 * Concept	            Explanation
 * ------------------------------------------------------
 * Builder Pattern	    Used to create complex objects step-by-step.
 * Why?	                Handles object creation with many optional parameters.
 * Advantages	        Easy to read, scalable, avoids telescoping constructors.
 * Common Usage	        Builders in Java SDK (e.g., StringBuilder, Streams API).
 *
 * 📌 Benefits:
 * Better readability: Easy to understand what’s being built.
 *
 * Handles optional parameters elegantly.
 *
 * Immutable objects can be easily created.
 *
 * 🔥 Quick Comparison:
 * Pattern	            Purpose
 * -----------------------------------------------------
 * Factory Method	    Decides which object to create.
 * Abstract Factory	    Creates families of objects.
 * Builder	Creates     complex objects step-by-step.
 *
 *
 *
 * ✅ How Builder Pattern is Achieved in Java
 * 📌 Core Principles:
 * Separate Object Construction from Representation.
 *
 * Use a Nested Static Builder Class to build the object step-by-step.
 *
 * The final object is built using a build() method.
 *
 *
 */
public class Builder {
    public class Burger {
        private String bun;
        private String sauce;
        private String cheese;
        private String meat;

        // Private constructor
        private Burger(Builder builder) {
            this.bun = builder.bun;
            this.sauce = builder.sauce;
            this.cheese = builder.cheese;
            this.meat = builder.meat;
        }

        // Builder Class
        public class Builder {
            private String bun;
            private String sauce;
            private String cheese;
            private String meat;

            public Builder setBun(String bun) {
                this.bun = bun;
                return this;
            }

            public Builder setSauce(String sauce) {
                this.sauce = sauce;
                return this;
            }

            public Builder setCheese(String cheese) {
                this.cheese = cheese;
                return this;
            }

            public Builder setMeat(String meat) {
                this.meat = meat;
                return this;
            }

            // Final step: Build the Burger
            public Burger build() {
                return new Burger(this);
            }
        }

        // Display the Burger
        public void display() {
            System.out.println("Burger with: " + bun + ", " + sauce + ", " + cheese + ", " + meat);
        }
    }

    public class Main {
        public void main(String[] args) {

            // Build Veg Burger
            Burger vegBurger = new Burger.Builder()
                    .setBun("Whole Wheat Bun")
                    .setSauce("Tomato Sauce")
                    .setCheese("Cheddar")
                    .build();
            vegBurger.display();

            // Build Chicken Burger
            Burger chickenBurger = new Burger.Builder()
                    .setBun("Sesame Bun")
                    .setSauce("Mayo")
                    .setCheese("Swiss")
                    .setMeat("Chicken Patty")
                    .build();
            chickenBurger.display();
        }
    }

    public class Pizza {
        private String size;
        private boolean cheese;
        private boolean pepperoni;
        private boolean mushrooms;

        // Private constructor
        private Pizza(PizzaBuilder builder) {
            this.size = builder.size;
            this.cheese = builder.cheese;
            this.pepperoni = builder.pepperoni;
            this.mushrooms = builder.mushrooms;
        }

        // Display pizza details
        public void display() {
            System.out.println("Pizza size: " + size);
            System.out.println("Cheese: " + cheese);
            System.out.println("Pepperoni: " + pepperoni);
            System.out.println("Mushrooms: " + mushrooms);
        }

        // Builder Class
        public static class PizzaBuilder {
            private String size;
            private boolean cheese;
            private boolean pepperoni;
            private boolean mushrooms;

            public PizzaBuilder(String size) {
                this.size = size;
            }

            public PizzaBuilder addCheese() {
                this.cheese = true;
                return this;
            }

            public PizzaBuilder addPepperoni() {
                this.pepperoni = true;
                return this;
            }

            public PizzaBuilder addMushrooms() {
                this.mushrooms = true;
                return this;
            }

            public Pizza build() {
                return new Pizza(this);
            }
        }
    }
    public class MainPizza {
        public void main(String[] args) {
            // Build a large pizza with cheese and pepperoni
            Pizza pizza = new Pizza.PizzaBuilder("Large")
                    .addCheese()
                    .addPepperoni()
                    .build();
            pizza.display();

            // Build a medium pizza with mushrooms only
            Pizza pizza2 = new Pizza.PizzaBuilder("Medium")
                    .addMushrooms()
                    .build();
            pizza2.display();
        }
    }


}
