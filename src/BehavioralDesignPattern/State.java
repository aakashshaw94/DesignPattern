package BehavioralDesignPattern;

/**
 *
 * ✅ Definition:
 * The State Pattern allows an object to change its behavior when its internal state changes.
 * It appears as if the object has changed its class.
 *
 *
 *
 *
 * 🎯 In Simple Words:
 * 👉 State = Dynamic Behavior Change.
 * When the state of an object changes, its behavior changes automatically.
 *
 *
 *
 *
 *
 *
 * 📦 Real-World Backend Example: Order Processing States
 * 🔔 Problem:
 * Imagine an Order Processing System.
 *
 * An order can have multiple states:
 *
 * New
 *
 * Processing
 *
 * Shipped
 *
 * Delivered
 *
 * 👉 Each state should have different behavior.
 *
 * ✅ Instead of using complex if-else chains, the State Pattern organizes the logic cleanly.
 *
 *
 *
 *
 *
 *
 * 💡 Real Backend Parallels:
 * Order Workflow Systems
 *
 * Stateful Workflow Engines
 *
 * Payment Processing States
 *
 * Authorization State Machines
 *
 *
 *
 *
 * ✅ Let’s Build It: Order Processing Example
 *
 *
 *
 *
 */
public class State {

    /**
     *
     *
     * ✅ Key Takeaways:
     * Concept	                Explanation
     * ------------------------------------------------------------------------------
     * Context	                The object whose behavior changes (OrderContext)
     * State	                Represents the current status (New, Processing, Shipped, Delivered)
     * Dynamic Behavior	        The object changes its behavior based on the current state
     *
     *
     *
     * 🎯 Real Backend Use Cases:
     * Order Management Systems
     *
     * Ticket Status Flows (Open → In Progress → Resolved)
     *
     * User Account States (Active, Suspended, Closed)
     *
     * Payment Status Transitions
     *
     * Workflow Engines (Spring State Machine)
     *
     *
     *
     *
     *
     * ✅ Why This Example is Practical:
     * ✔️ Backend developers often build systems with status transitions.
     * ✔️ State Pattern helps avoid messy if-else or switch statements.
     * ✔️ Cleanly separates state-specific behavior.
     *
     *
     *
     *
     *
     * 🔥 When to Use State Pattern:
     * When an object’s behavior depends on its current state.
     *
     * When you want to avoid complex conditionals.
     *
     * When the state transitions are dynamic and scalable.
     */
    public interface OrderState {
        void next(OrderContext context);
        void previous(OrderContext context);
        void printStatus();
    }
    public class NewOrderState implements OrderState {

        public void next(OrderContext context) {
            context.setState(new ProcessingState());
        }

        public void previous(OrderContext context) {
            System.out.println("This is the first state. No previous state.");
        }

        public void printStatus() {
            System.out.println("Order is in NEW state.");
        }
    }
    public class ProcessingState implements OrderState {

        public void next(OrderContext context) {
            context.setState(new ShippedState());
        }

        public void previous(OrderContext context) {
            context.setState(new NewOrderState());
        }

        public void printStatus() {
            System.out.println("Order is PROCESSING.");
        }
    }
    public class ShippedState implements OrderState {

        public void next(OrderContext context) {
            context.setState(new DeliveredState());
        }

        public void previous(OrderContext context) {
            context.setState(new ProcessingState());
        }

        public void printStatus() {
            System.out.println("Order has been SHIPPED.");
        }
    }
    public class DeliveredState implements OrderState {

        public void next(OrderContext context) {
            System.out.println("This is the final state. No next state.");
        }

        public void previous(OrderContext context) {
            context.setState(new ShippedState());
        }

        public void printStatus() {
            System.out.println("Order is DELIVERED.");
        }
    }
    public class OrderContext {

        private OrderState state;

        public OrderContext() {
            state = new NewOrderState(); // Default state
        }

        public void setState(OrderState state) {
            this.state = state;
        }

        public void nextState() {
            state.next(this);
        }

        public void previousState() {
            state.previous(this);
        }

        public void printStatus() {
            state.printStatus();
        }
    }
    public class Main {
        public void main(String[] args) {

            OrderContext order = new OrderContext();

            order.printStatus();  // New Order

            order.nextState();    // Move to Processing
            order.printStatus();

            order.nextState();    // Move to Shipped
            order.printStatus();

            order.nextState();    // Move to Delivered
            order.printStatus();

            order.nextState();    // Try to move beyond Delivered
        }
    }

}
