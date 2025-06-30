package CreationalDesignPattern;

/**
 * ✅ Definition:
 * The Abstract Factory Pattern provides an interface to create families of related objects without specifying their
 * concrete classes.
 *
 * 👉 In Simple Words:
 * It’s a factory of factories.
 * It creates related objects together (like a set or family).
 *
 * 📦 Real-Life Example:
 * 🎮 Gaming Theme Example:
 * Suppose you are making a game that has two themes:
 *
 * Futuristic World ➜ Creates Futuristic Vehicles & Weapons
 *
 * Medieval World ➜ Creates Horses & Swords
 *
 * You need a system that creates:
 *
 * Vehicle + Weapon for each world (family of products).
 *
 * That’s where Abstract Factory fits perfectly!
 *
 * 🎯 Quick Summary:
 * Concept	                        Description
 * -------------------------------------------------------------
 * What	                            Factory of factories:Creates related objects without specifying their classes.
 * Why	                            Keeps object families consistent, makes switching themes easy.
 * When	                            When objects must be used together and you want to easily switch families.
 * Use Cases	                    UI themes, Game worlds, Cross-platform tools.
 *
 * 🆚 Difference Between Factory Method & Abstract Factory:
 * Feature	        Factory Method	        Abstract Factory
 * ----------------------------------------------------------------
 * Creates	        One Product	            Multiple related products (family)
 * Structure	    Single factory	        Factory of factories
 * Complexity	    Simple	                More structured and scalable
 *
 */
public class AbstractFactory {
    public interface Vehicle {
        void drive();
    }
    public interface Weapon {
        void use();
    }
    public class FlyingCar implements Vehicle {
        public void drive() {
            System.out.println("Driving a flying car.");
        }
    }

    public class LaserGun implements Weapon {
        public void use() {
            System.out.println("Firing a laser gun.");
        }
    }
    public class Horse implements Vehicle {
        public void drive() {
            System.out.println("Riding a horse.");
        }
    }

    public class Sword implements Weapon {
        public void use() {
            System.out.println("Using Sword");
        }
    }
    public interface GameFactory {
        Vehicle createVehicle();
        Weapon createWeapon();
    }
    public class FuturisticFactory implements GameFactory {
        public Vehicle createVehicle() {
            return new FlyingCar();
        }

        public Weapon createWeapon() {
            return new LaserGun();
        }
    }
    public class MedievalFactory implements GameFactory {
        public Vehicle createVehicle() {
            return new Horse();
        }

        public Weapon createWeapon() {
            return new Sword();
        }
    }
    public class Game {
        public void main(String[] args) {
            // Choose game theme: Futuristic
            GameFactory factory = new FuturisticFactory();
            Vehicle vehicle = factory.createVehicle();
            Weapon weapon = factory.createWeapon();

            vehicle.drive();
            weapon.use();

            // Switch to Medieval theme
            factory = new MedievalFactory();
            vehicle = factory.createVehicle();
            weapon = factory.createWeapon();

            vehicle.drive();
            weapon.use();
        }
    }

}
