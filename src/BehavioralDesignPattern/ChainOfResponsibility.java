package BehavioralDesignPattern;

/**
 *
 * ✅ Definition:
 * The Chain of Responsibility Pattern allows you to pass a request along a chain of handlers.
 * Each handler can either process the request or pass it to the next handler in the chain.
 *
 *
 *
 *
 * 🎯 In Simple Words:
 * 👉 Chain = Request Pipeline.
 * Each handler has the chance to either handle the request or pass it along to the next handler.
 *
 *
 *
 *
 *
 * 📦 Real-World Backend Example: HTTP Request Processing (Filters/Middleware)
 * 🔔 Problem:
 * When a backend server processes an HTTP request, it often passes through:
 *
 * -Authentication
 *
 * -Logging
 *
 * -Validation
 *
 * -Actual Request Handler
 *
 * ✅ Each step may handle, modify, or block the request, then pass it to the next handler.
 *
 * 👉 This is a perfect fit for the Chain of Responsibility Pattern.
 *
 *
 *
 *
 *
 * 💡 Real Backend Parallels:
 * Spring Boot Filter Chain
 *
 * Express.js Middleware
 *
 * Logging Framework Chains
 *
 * Exception Handling Chains
 *
 *
 *
 * ✅ Let’s Build It: HTTP Request Handler Chain
 *
 */
public class ChainOfResponsibility {


    /**
     * ✅ Key Takeaways:
     * Concept	                Explanation
     * ----------------------------------------------------------------
     * Chain	                Multiple handlers (Auth → Log → Business)
     * Flexibility	            Each handler can process or block the request
     * Benefit	                Easy to add/remove/replace handlers without changing the core structure
     *
     *
     *
     *
     * 🎯 Real Backend Use Cases:
     * Spring Boot Filter Chain: Authentication → Logging → Validation → Controller
     *
     * Express.js Middleware Chain
     *
     * Exception Handling Pipelines
     *
     * Approval Workflows: Step 1 → Step 2 → Step 3 approvals
     *
     * Logging Levels: DEBUG → INFO → WARN → ERROR → FATAL
     *
     *
     *
     *
     * ✅ Why This Example is Practical:
     * ✔️ Backend developers build request processing chains all the time.
     * ✔️ Chain of Responsibility is the core idea behind most web frameworks’ middleware.
     * ✔️ Helps break down complex logic into small, reusable components.
     *
     *
     *
     *
     *
     * 🔥 When to Use Chain of Responsibility:
     * When you need multiple handlers to process a request step-by-step.
     *
     * When each handler may choose to process or skip the request.
     *
     * When you want to add/remove handlers dynamically.
     *
     *
     *
     *
     */
    public interface RequestHandler {
        void handle(String request);
        void setNext(RequestHandler next);
    }
    public abstract class AbstractHandler implements RequestHandler {
        protected RequestHandler next;

        public void setNext(RequestHandler next) {
            this.next = next;
        }

        public void handle(String request) {
            if (next != null) {
                next.handle(request);
            }
        }
    }
    public class AuthHandler extends AbstractHandler {

        public void handle(String request) {
            if (request.contains("authToken")) {
                System.out.println("[Auth] Authentication passed.");
                super.handle(request); // Pass to next handler
            } else {
                System.out.println("[Auth] Authentication failed. Request blocked.");
            }
        }
    }
    public class LoggingHandler extends AbstractHandler {

        public void handle(String request) {
            System.out.println("[Log] Request logged: " + request);
            super.handle(request);
        }
    }
    public class BusinessHandler extends AbstractHandler {

        public void handle(String request) {
            System.out.println("[Business] Processing request: " + request);
        }
    }
    public class Main {
        public void main(String[] args) {

            // Create handlers
            RequestHandler auth = new AuthHandler();
            RequestHandler log = new LoggingHandler();
            RequestHandler business = new BusinessHandler();

            // Build the chain: Auth -> Log -> Business
            auth.setNext(log);
            log.setNext(business);

            // Process request with authToken
            System.out.println("----- Request 1 -----");
            auth.handle("Request data with authToken");

            // Process request without authToken
            System.out.println("\n----- Request 2 -----");
            auth.handle("Request data without token");
        }
    }

}
