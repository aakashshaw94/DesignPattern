package CreationalDesignPattern;

/**
 * Singleton Design Pattern
 * Ensures a class has only one instance and provides a global point of access to it.
 * This is a placeholder class for demonstration purposes.
 *
 *
 * How do we achieve this ?
 *
 * Generally constructor is set as private to prevent instantiation from outside the class.
 *
 *
 * Definition:
 *
 * The Singleton Pattern ensures that a class has only one instance and provides a global point of access to that instance.
 *
 * 📌 When to Use:
 * When you need exactly one instance of a class (like Database Connection, Configuration, Logger).
 *
 * When you want to control access to shared resources.
 *
 * 🔨 Key Characteristics:
 * Single instance is created.
 *
 * Global access point (usually via a static method).
 *
 * Can be lazy-loaded (created only when needed).
 *
 * ✅ Simple Real-World Analogy:
 * Imagine a CEO in a company.
 * No matter how many times you ask, there can be only one CEO.
 *
 */
public class Singleton {
    // Singleton Class
    public class Logger {

        // Step 1: Create a private static instance of the class
        private static Logger singleInstance = null;

        // Step 2: Make the constructor private (so no one can create an object from outside)
        private Logger() {
            System.out.println("Logger Initialized!");
        }

        // Step 3: Provide a public static method to get the single instance
        public static Logger getInstance() {
            if (singleInstance == null) {
                singleInstance = new Logger();  // Create the instance if it doesn't exist
            }
            return singleInstance;
        }

        // Example method
        public void log(String message) {
            System.out.println("Log: " + message);
        }

    }

    public class Main {
        public static void main(String[] args) {
            Logger logger1 = Logger.getInstance();
            Logger logger2 = Logger.getInstance();

            logger1.log("First message.");
            logger2.log("Second message.");

            // Check if both logger1 and logger2 are the same object
            System.out.println(logger1 == logger2);  // Output: true
        }
    }




/**
 * In Java, there are multiple ways to implement the Singleton Design Pattern depending on:
 *
 * 1. Thread Safety
 *
 * 2. Lazy Initialization
 *
 * 3. Performance
 *
 * Here are the most common and recommended ways:
 *
 *
 *
 * */

    /**1. ✅ 1. Eager Initialization (Simplest, Thread-Safe)
     * Instance is created at class loading time.
     *
     * ✔️ Pros:
     * Thread-safe without synchronization.
     *
     * Simple.
     *
     * ❌ Cons:
     * Instance is created even if it is never used.
     *
     */
    class EagerSingleton {
        private static final EagerSingleton instance = new EagerSingleton();

        private EagerSingleton() {
            // Private constructor to prevent instantiation
        }

        public static EagerSingleton getInstance() {
            return instance;
        }
    }

    /**
     * ✅ 2. Lazy Initialization (Not Thread-Safe)
     * Instance is created when needed.
     *
     * ❌ Problem:
     * Not thread-safe. In multithreaded environments, two threads might create two instances.
     *
     * */

    public class LazySingleton {

        private static LazySingleton instance;

        private LazySingleton() {}

        public static LazySingleton getInstance() {
            if (instance == null) {
                instance = new LazySingleton();
            }
            return instance;
        }
    }

    /**
     * ✅ 3. Thread-Safe Singleton (Synchronized Method)
     * Ensures that only one thread can access the method at a time.
     *
     * ✔️ Pros:
     * Thread-safe.
     *
     * ❌ Problem:
     * Performance overhead due to synchronization.
     *
     */
    class ThreadSafeSingleton {
        private static ThreadSafeSingleton instance;

        private ThreadSafeSingleton() {}

        public static synchronized ThreadSafeSingleton getInstance() {
            if (instance == null) {
                instance = new ThreadSafeSingleton();
            }
            return instance;
        }
    }

    /**
     * ✅ 4. Double-Checked Locking (Thread-Safe, Lazy Initialization)
     * Combines lazy initialization with thread safety.
     * Synchronizes only when the instance is null (lazy and fast).
     *
     * ✔️ Pros:
     * Thread-safe.
     * Performance is better than synchronized method.
     *
     * ❌ Problem:
     * Slightly more complex code.
     *
     */
    class DoubleCheckedLockingSingleton {
        private static volatile DoubleCheckedLockingSingleton instance;

        private DoubleCheckedLockingSingleton() {}

        public static DoubleCheckedLockingSingleton getInstance() {
            if (instance == null) {
                synchronized (DoubleCheckedLockingSingleton.class) {
                    if (instance == null) {
                        instance = new DoubleCheckedLockingSingleton();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * ✅ 5. Bill Pugh Singleton Design Pattern (Thread-Safe, Lazy Initialization)
     * Uses a static inner helper class to hold the instance.
     * The instance is created only when the getInstance() method is called.
     *
     * ✔️ Pros:
     * Thread-safe.
     * Lazy initialization.
     * No synchronization overhead.
     *
     */
    class BillPughSingleton {
        private BillPughSingleton() {}

        private static class SingletonHelper {
            private static final BillPughSingleton INSTANCE = new BillPughSingleton();
        }

        public static BillPughSingleton getInstance() {
            return SingletonHelper.INSTANCE;
        }
    }

    /**
     * ✅ 6. Enum Singleton (Thread-Safe, Serialization Safe)
     * Uses an enum to implement the singleton pattern.
     * This is the most robust way to create a singleton in Java.
     *
     * ✔️ Pros:
     * Thread-safe.
     * Serialization safe.
     * Simple and concise.
     * Prevents reflection attacks.
     *
     */
    enum EnumSingleton {
        INSTANCE;

        public void someMethod() {
            // Method logic here
            System.out.println("Hello from Enum Singleton!");
        }
    }


}

