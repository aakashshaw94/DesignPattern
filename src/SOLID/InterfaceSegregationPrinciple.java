package SOLID;
/**
 * Interface Segregation Principle (ISP) states that no client should be forced to depend on methods it does not use.
 * This principle encourages the creation of smaller, more specific interfaces rather than a large, general-purpose interface.
 * It helps in reducing the side effects and frequency of required changes by splitting the interfaces into smaller ones.
 */
public class InterfaceSegregationPrinciple {
    // Example of a large interface that violates ISP
    public interface Worker {
        void work();
        void eat();
    }

    // Example of a class that implements the large interface
    public class HumanWorker implements Worker {
        @Override
        public void work() {
            System.out.println("Working...");
        }

        @Override
        public void eat() {
            System.out.println("Eating...");
        }
    }

    // Example of a smaller interface that adheres to ISP
    public interface Workable {
        void work();
    }

    public interface Eatable {
        void eat();
    }

    // Example of a class that implements the smaller interfaces
    public class RobotWorker implements Workable {
        @Override
        public void work() {
            System.out.println("Robot working...");
        }
    }

    public class Human implements Workable, Eatable {
        @Override
        public void work() {
            System.out.println("Human working...");
        }

        @Override
        public void eat() {
            System.out.println("Human eating...");
        }
    }
}
