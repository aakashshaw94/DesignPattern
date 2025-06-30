package StructuralDesignPattern;

/**
 *
 * ✅ Definition:
 * The Decorator Pattern lets you dynamically add new behavior to objects without changing their structure or existing code.
 *
 * 🎯 In Simple Words:
 * 👉 Decorator = Wrapper.
 * It’s like wrapping an object with extra functionality on the fly.
 *
 *
 *
 *
 * 📦 Real-World Backend Example: Logging and Validation in API Requests
 * 🔔 Problem:
 * You are building a backend service that processes HTTP API requests.
 *
 * 👉 Basic request handling should:
 *
 * Process the request.
 *
 * 👉 But sometimes you want to:
 *
 * Add logging.
 *
 * Add validation.
 *
 * Add authentication.
 *
 * Add rate limiting.
 *
 * ✅ Decorator Pattern allows you to:
 *
 * Add these behaviors dynamically without rewriting or subclassing the request handler.
 *
 * 💡 Real Backend Parallel:
 * Spring Boot’s Filter Chain, Servlet Wrappers, and Middleware in Express.js all work like decorators.
 *
 *
 *
 *
 *
 * ✅ Key Takeaways:
 * Concept	            Explanation
 * -------------------------------------------------------------
 * Core Object	        BasicRequestHandler – handles the core logic
 * Decorator	        LoggingHandler, ValidationHandler – add extra behavior
 * Dynamic Wrapping	    You can wrap in any order and stack multiple decorators
 *
 * 🎯 Real Backend Use Cases:
 * Spring Boot Filters / Interceptors: Logging, validation, authentication, rate limiting.
 *
 * Express.js Middleware: Each middleware wraps the request processing pipeline.
 *
 * Logging Libraries: Wrapping loggers with extra file writing, database storage, or async support.
 *
 * HTTP Clients: Retry wrappers, timeout wrappers, logging wrappers.
 *
 * ✅ Why This Example Works:
 * ✔️ Backend developers decorate API requests all the time.
 * ✔️ You can dynamically stack as many behaviors as needed without changing the core logic.
 * ✔️ Clean separation of concerns: logging, validation, processing are all decoupled.
 *
 * 🔥 Decorator Pattern is Perfect When:
 * You want to add functionality without touching existing classes.
 *
 * You need runtime flexibility (wrap different combinations based on configuration).
 *
 *
 *
 */
public class Decorator {
    public interface RequestHandler {
        void handle(String request);
    }
    public class BasicRequestHandler implements RequestHandler {
        public void handle(String request) {
            System.out.println("Handling request: " + request);
        }
    }
    public abstract class RequestHandlerDecorator implements RequestHandler {
        protected RequestHandler handler;

        public RequestHandlerDecorator(RequestHandler handler) {
            this.handler = handler;
        }

        public void handle(String request) {
            handler.handle(request); // Delegate to the original handler
        }
    }
    public class LoggingHandler extends RequestHandlerDecorator {

        public LoggingHandler(RequestHandler handler) {
            super(handler);
        }

        public void handle(String request) {
            System.out.println("[LOG] Request received: " + request);
            super.handle(request); // Continue the chain
        }
    }
    public class ValidationHandler extends RequestHandlerDecorator {

        public ValidationHandler(RequestHandler handler) {
            super(handler);
        }

        public void handle(String request) {
            if (request == null || request.isEmpty()) {
                System.out.println("[ERROR] Invalid request.");
                return;
            }
            System.out.println("[VALIDATION] Request is valid.");
            super.handle(request);
        }
    }
    public class Main {
        public void main(String[] args) {

            // Build the decorated request handler
            RequestHandler handler = new LoggingHandler(
                    new ValidationHandler(
                            new BasicRequestHandler()));

            // Handle a valid request
            handler.handle("GET /api/users");

            System.out.println();

            // Handle an invalid request
            handler.handle("");
        }
    }

}
