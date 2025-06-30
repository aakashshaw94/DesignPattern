package SOLID;

/**
 * Liskov Substitution Principle (LSP) is one of the five SOLID principles of object-oriented design.
 * It states that objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.
 * In other words, if class S is a subclass of class T, then objects of type T should be replaceable with objects of type S without altering any of the desirable properties of the program.
 */
public class LiskovSubstitutionPrinciple {
    // Example of a class that adheres to LSP
    public class Bird {
        public void fly() {
            System.out.println("Flying");
        }
    }

    public class Sparrow extends Bird {
        @Override
        public void fly() {
            System.out.println("Sparrow flying");
        }
    }

    public class Penguin extends Bird {
        @Override
        public void fly() {
            throw new UnsupportedOperationException("Penguins can't fly");
        }
    }

    // This violates LSP because Penguin cannot be used as a substitute for Bird
    // A better design would be to separate flying birds from non-flying birds
    public class FlyingBird extends Bird {
        @Override
        public void fly() {
            System.out.println("Flying bird flying");
        }
    }
    public class NonFlyingBird extends Bird {
        @Override
        public void fly() {
            throw new UnsupportedOperationException("This bird cannot fly");
        }
    }
    public void demonstrateLSP() {
        Bird sparrow = new Sparrow();
        sparrow.fly(); // Works fine

        Bird penguin = new NonFlyingBird();
        try {
            penguin.fly(); // Throws exception
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        LiskovSubstitutionPrinciple lsp = new LiskovSubstitutionPrinciple();
        lsp.demonstrateLSP();
    }
    // This main method demonstrates the Liskov Substitution Principle
    // by showing how subclasses can be used interchangeably with their superclass
    // while adhering to the principle's requirements.
}
