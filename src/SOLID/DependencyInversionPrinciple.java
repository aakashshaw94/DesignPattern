package SOLID;
/**
 * Dependency Inversion Principle (DIP) is one of the SOLID principles of object-oriented design.
 * It states that high-level modules should not depend on low-level modules, but both should depend on abstractions.
 * This principle helps to reduce the coupling between different parts of a system, making it more flexible and easier to maintain.
 */
public class DependencyInversionPrinciple {
    // Example of a high-level module that depends on a low-level module
    public interface Database {
        void connect();
    }

    public class MySQLDatabase implements Database {
        @Override
        public void connect() {
            System.out.println("Connecting to MySQL database");
        }
    }

    public class PostgreSQLDatabase implements Database {
        @Override
        public void connect() {
            System.out.println("Connecting to PostgreSQL database");
        }
    }

    public class UserService {
        private Database database;

        public UserService(Database database) {
            this.database = database;
        }

        public void performDatabaseOperation() {
            database.connect();
            // Perform other operations
        }
    }

    // Example usage
    public void main1(String[] args) {
        Database mySQLDatabase = new MySQLDatabase();
        UserService userService = new UserService(mySQLDatabase);
        userService.performDatabaseOperation();

        Database postgreSQLDatabase = new PostgreSQLDatabase();
        UserService anotherUserService = new UserService(postgreSQLDatabase);
        anotherUserService.performDatabaseOperation();
    }
    // This demonstrates the Dependency Inversion Principle by allowing UserService to depend on the Database abstraction,
    // rather than a specific implementation like MySQL or PostgreSQL.
    public static void main(String[] args) {
        DependencyInversionPrinciple dip = new DependencyInversionPrinciple();
        dip.main(args);
    }
    // This main method demonstrates the Dependency Inversion Principle
    // by showing how high-level modules (UserService) can depend on abstractions (Database)
    // rather than low-level modules (MySQLDatabase, PostgreSQLDatabase).


}
