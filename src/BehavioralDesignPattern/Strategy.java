package BehavioralDesignPattern;

/**
 *✅ Definition:
 * The Strategy Pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable.
 *
 *
 *
 *
 * 🎯 In Simple Words:
 * 👉 Strategy = Selectable Algorithm.
 * You can change the behavior/algorithm at runtime without changing the object itself.
 *
 *
 *
 *
 * 📦 Real-World Backend Example: Payment Processing System
 * 🔔 Problem:
 * Imagine you are building an e-commerce backend.
 *
 * You want to:
 *
 * Allow multiple payment methods: Credit Card, PayPal, Google Pay, etc.
 *
 * Select the payment strategy at runtime.
 *
 * 👉 Instead of writing complex if-else logic inside the payment processor, you can use the Strategy
 * Pattern to cleanly switch payment methods.
 *
 *
 *
 *
 * 💡 Real Backend Parallels:
 * Payment Gateways
 *
 * Sorting Algorithms (dynamic selection)
 *
 * Compression Strategies (ZIP, RAR, GZIP)
 *
 * Authentication Strategies (OAuth, JWT, LDAP)
 *
 *
 *
 *
 *
 *
 * ✅ Let’s Build It: Payment Processing Example
 *
 *
 *
 */
public class Strategy {
    /**
     *
     * ✅ Key Takeaways:
     * Concept	                    Explanation
     * --------------------------------------------------------------
     * Context	                    PaymentService uses a payment strategy
     * Strategy Interface	        Common interface for all payment methods
     * Concrete Strategies	        CreditCardPayment, PayPalPayment, GooglePayPayment
     *
     * 🎯 Real Backend Use Cases:
     * Payment Processing Systems
     *
     * Authentication Strategies
     *
     * Sorting Algorithms in APIs
     *
     * Compression Formats in File Uploads
     *
     * Shipping Cost Calculation (Air, Land, Sea)
     *
     *
     *
     *
     *
     * ✅ Why This Example is Practical:
     * ✔️ Backend systems frequently need dynamic selection of logic.
     * ✔️ Strategy pattern avoids big switch-case or if-else chains.
     * ✔️ Makes the system extensible (you can easily add new payment methods later).
     *
     *
     *
     *
     * 🔥 When to Use Strategy Pattern:
     * When you need to select an algorithm at runtime.
     *
     * When you want to eliminate complex conditional statements.
     *
     * When you need to swap behaviors easily without changing the main object.
     *
     *
     *
     *
     */
    public interface PaymentStrategy {
        void pay(int amount);
    }
    public class CreditCardPayment implements PaymentStrategy {

        public void pay(int amount) {
            System.out.println("Paid " + amount + " using Credit Card.");
        }
    }
    public class PayPalPayment implements PaymentStrategy {

        public void pay(int amount) {
            System.out.println("Paid " + amount + " using PayPal.");
        }
    }
    public class GooglePayPayment implements PaymentStrategy {

        public void pay(int amount) {
            System.out.println("Paid " + amount + " using Google Pay.");
        }
    }
    public class PaymentService {

        private PaymentStrategy paymentStrategy;

        // Select strategy at runtime
        public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        public void processPayment(int amount) {
            paymentStrategy.pay(amount);
        }
    }
    public class Main {
        public void main(String[] args) {

            PaymentService paymentService = new PaymentService();

            // Pay using Credit Card
            paymentService.setPaymentStrategy(new CreditCardPayment());
            paymentService.processPayment(500);

            // Pay using PayPal
            paymentService.setPaymentStrategy(new PayPalPayment());
            paymentService.processPayment(1000);

            // Pay using Google Pay
            paymentService.setPaymentStrategy(new GooglePayPayment());
            paymentService.processPayment(750);
        }
    }

}
