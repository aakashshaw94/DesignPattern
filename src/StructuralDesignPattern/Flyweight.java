package StructuralDesignPattern;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * ✅ Definition:
 * The Flyweight Pattern is used to minimize memory usage by sharing common objects instead of creating new ones each time.
 *
 * 🎯 In Simple Words:
 * 👉 Flyweight = Shared Objects.
 * It avoids creating duplicate objects when many objects can share the same intrinsic data.
 *
 * 📦 Real-World Backend Example: Database Connection Pooling
 * 🔔 Problem:
 * In a backend system:
 *
 * -You need many database connections.
 *
 * -Creating a new connection every time is very costly.
 *
 * -Solution: You reuse (share) existing connections from a pool.
 *
 * ✅ This is a perfect example of the Flyweight Pattern.
 *
 * 💡 Real Backend Parallel:
 * -JDBC Connection Pooling
 *
 * -Thread Pools
 *
 * -String Pooling in Java (String.intern())
 *
 * -Caching Systems (like Ehcache, Redis key sharing)
 *
 * ✅ Let’s Build It: Database Connection Pool (Flyweight Example)
 *
 *
 *
 */
public class Flyweight {

    /**
     *
     *
     * ✅ Key Takeaways:
     * Concept	            Explanation
     * ------------------------------------------------------------------------
     * Flyweight	        DatabaseConnection (shared object)
     * Factory	            ConnectionPool (manages and reuses shared objects)
     * Client	            Requests connections, but shared ones are reused
     *
     * 🎯 Real Backend Use Cases:
     * Database Connection Pools (HikariCP, Apache DBCP)
     *
     * Thread Pools
     *
     * Object Caching (Redis, Memcached)
     *
     * String Interning in Java
     *
     * Rendering Systems: Sharing icons, fonts, or styles.
     *
     * ✅ Why This Example is Realistic:
     * ✔️ Backend developers rely on connection pools in nearly every application.
     * ✔️ Connection pool managers use Flyweight Pattern under the hood.
     * ✔️ Helps optimize memory and performance when creating many heavy objects.
     *
     * 🔥 When to Use Flyweight:
     * When you have lots of similar objects.
     *
     * When the cost of creating objects is high.
     *
     * When most of the object’s data can be shared (intrinsic) and only a small part changes per request (extrinsic).
     *
     * ✅ Bonus:
     * Intrinsic State vs Extrinsic State:
     * Intrinsic (shared): Connection details like ID.
     *
     * Extrinsic (unique): Specific query or transaction details (not stored in the flyweight).
     *
     *
     *
     *
     *
     */
    public class DatabaseConnection {

        private String connectionId;

        public DatabaseConnection(String connectionId) {
            this.connectionId = connectionId;
        }

        public void connect() {
            System.out.println("Using connection: " + connectionId);
        }
    }
    //flyweight factory

    public class ConnectionPool {

        private Map<String, DatabaseConnection> connectionPool = new HashMap<>();

        public DatabaseConnection getConnection(String connectionId) {
            if (!connectionPool.containsKey(connectionId)) {
                System.out.println("Creating new connection: " + connectionId);
                connectionPool.put(connectionId, new DatabaseConnection(connectionId));
            }
            return connectionPool.get(connectionId);
        }
    }

    public class Main {
        public void main(String[] args) {

            ConnectionPool pool = new ConnectionPool();

            // Reusing shared connections
            DatabaseConnection conn1 = pool.getConnection("CONN-1");
            conn1.connect();

            DatabaseConnection conn2 = pool.getConnection("CONN-2");
            conn2.connect();

            DatabaseConnection conn3 = pool.getConnection("CONN-1"); // Reuses existing
            conn3.connect();

            // Check if conn1 and conn3 are the same object
            System.out.println("conn1 and conn3 are the same? " + (conn1 == conn3));
        }
    }

}
