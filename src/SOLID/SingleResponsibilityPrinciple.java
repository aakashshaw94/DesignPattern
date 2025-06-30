package SOLID;
/**
 * Single Responsibility Principle (SRP) states that a class should have only one reason to change, meaning it should have only
 * one job or responsibility.
 * This principle helps in reducing the complexity of the code and makes it easier to maintain.
 *
 * Example:
 * A class that handles user authentication should not also handle user profile management.
 * Instead, create separate classes for each responsibility.
 */
public class SingleResponsibilityPrinciple {
    // Example of a class that violates SRP
    public class User {
        private String username;
        private String password;

        public void authenticate() {
            // Authentication logic
        }

        public void updateProfile() {
            // Profile update logic
        }
    }

    // Example of a class that adheres to SRP
    public class Authenticator {
        public void authenticate(User user) {
            // Authentication logic
        }
    }

    public class UserProfileManager {
        public void updateProfile(User user) {
            // Profile update logic
        }
    }

}
