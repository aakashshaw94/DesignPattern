package BehavioralDesignPattern;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * ✅ Definition:
 * The Visitor Pattern allows you to add new operations to existing object structures without changing their classes.
 *
 *
 * 🎯 In Simple Words:
 * 👉 Visitor = External Logic Applicator.
 * It allows you to separate algorithms from the objects on which they operate.
 *
 * ✔️ You can add new behaviors without touching existing classes.
 *
 *
 *
 *
 * 📦 Real-World Backend Example: Tax Calculator for Different Products
 * 🔔 Problem:
 * Imagine you have different types of products:
 *
 * Electronics
 *
 * Groceries
 *
 * Clothes
 *
 * ✅ You want to apply different tax calculations based on the product type.
 *
 * 👉 You don’t want to put tax logic inside each product class (that would tightly couple the classes).
 *
 * 👉 Instead, you want to add tax calculation externally.
 *
 * This is where the Visitor Pattern is a perfect fit.
 *
 *
 *
 *
 * 💡 Real Backend Parallels:
 * Tax Calculations for Multiple Entities
 *
 * Discount Rules on Products
 *
 * Validation Layers
 *
 * Serialization of Complex Objects
 *
 * Report Generators Visiting Various Database Entities
 *
 *
 *
 *
 */

class VisitorDesignPattern {
    public interface Visitable {
        void accept(Visitor visitor);

    }
    public interface Visitor {
        void visit(Electronics electronics);
        void visit(Grocery grocery);
        void visit(Clothing clothing);
    }
    public class Electronics implements Visitable {
        private double price;

        public Electronics(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }
    public class Grocery implements Visitable {
        private double price;

        public Grocery(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }
    public class Clothing implements Visitable {
        private double price;

        public Clothing(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }
    public class TaxCalculator implements Visitor {

        public void visit(Electronics electronics) {
            System.out.println("Electronics Tax: " + electronics.getPrice() * 0.18);
        }

        public void visit(Grocery grocery) {
            System.out.println("Grocery Tax: " + grocery.getPrice() * 0.05);
        }

        public void visit(Clothing clothing) {
            System.out.println("Clothing Tax: " + clothing.getPrice() * 0.12);
        }
    }

    public class Main {
        public void main(String[] args) {

            List<Visitable> items = new ArrayList<>();
            items.add(new Electronics(1000));
            items.add(new Grocery(500));
            items.add(new Clothing(800));

            Visitor taxCalculator = new TaxCalculator();

            for (Visitable item : items) {
                item.accept(taxCalculator);
            }
        }
    }

}
