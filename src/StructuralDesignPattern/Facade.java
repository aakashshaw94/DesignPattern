package StructuralDesignPattern;

/**
 *
 *
 * ✅ Definition:
 * The Facade Pattern provides a simplified interface to a complex subsystem.
 *
 *
 * 🎯 In Simple Words:
 * 👉 Facade = Simplifier.
 * It hides the complexity of multiple systems behind one easy-to-use interface.
 *
 *
 * 📦 Real-World Backend Example:
 * User Registration Workflow in Backend
 * When you register a new user:
 * You need to:
 *
 * -Save user data to the database.
 *
 * -Send a welcome email.
 *
 * -Log the registration activity.
 *
 * -Create a user profile.
 *
 * 👉 Normally, you would need to call each of these services individually.
 *
 * ✅ Facade Pattern simplifies this:
 *
 * -You provide one method: registerUser()
 *
 * -The Facade internally calls all the complex subsystems.
 *
 *
 *
 *
 * 💡 Real Backend Parallel:
 * In Spring Boot: Service classes often act as facades over repositories, mailers, loggers, etc.
 *
 * Payment Gateways: Provide simple payment APIs but internally deal with multiple processors.
 *
 * ✅ Let’s Build It: User Registration Facade
 *
 *
 */
public class Facade {
    /**
     *
     *
     * ✅ Key Takeaways:
     * Concept	        Explanation
     * -------------------------------------------------------------
     * Facade	        UserRegistrationFacade simplifies complex workflow
     * Subsystems	    UserDatabaseService, EmailService, LoggingService, ProfileService
     * Benefit	        Client only needs to call one method to complete registration
     *
     * 🎯 Real Backend Use Cases:
     * User Authentication/Registration Services: Usually facades that orchestrate DB, Email, Logging, Profile creation.
     *
     * Payment Processing: Facade calls tokenization, fraud check, payment API, confirmation mail.
     *
     * E-commerce Checkout: One method that internally calls inventory, payment, shipping, and order services.
     *
     * Spring Security: Facade over complex authentication mechanisms (OAuth, JWT, sessions, etc.).
     *
     * ✅ Why This Example is Realistic:
     * ✔️ Backend developers orchestrate workflows all the time.
     * ✔️ Real services are often composed of smaller services.
     * ✔️ Facade makes your code clean, simple, and easy to maintain.
     * ✔️ Promotes low coupling: clients don’t know or care about internal service details.
     *
     * 🔥 When to Use Facade:
     * When you want to hide system complexity.
     *
     * When you need to provide a unified API.
     *
     * When you want to reduce coupling between client code and the subsystem.
     *
     *
     *
     */
    public class UserDatabaseService {
        public void saveUser(String username) {
            System.out.println("Saving user: " + username);
        }
    }
    public class EmailService {
        public void sendWelcomeEmail(String username) {
            System.out.println("Sending welcome email to: " + username);
        }
    }
    public class LoggingService {
        public void logActivity(String activity) {
            System.out.println("Logging activity: " + activity);
        }
    }
    public class ProfileService {
        public void createUserProfile(String username) {
            System.out.println("Creating profile for: " + username);
        }
    }
    public class UserRegistrationFacade {

        private UserDatabaseService userDatabaseService = new UserDatabaseService();
        private EmailService emailService = new EmailService();
        private LoggingService loggingService = new LoggingService();
        private ProfileService profileService = new ProfileService();

        public void registerUser(String username) {
            userDatabaseService.saveUser(username);
            profileService.createUserProfile(username);
            emailService.sendWelcomeEmail(username);
            loggingService.logActivity("User registered: " + username);
            System.out.println("User registration completed.\n");
        }
    }
    public class Main {
        public void main(String[] args) {

            UserRegistrationFacade registrationFacade = new UserRegistrationFacade();

            // Register a user using simplified facade
            registrationFacade.registerUser("JohnDoe");

            registrationFacade.registerUser("JaneSmith");
        }
    }

}
