package BehavioralDesignPattern;

import java.util.ArrayList;
import java.util.List;

/***
 *
 * ✅ Definition:
 * The Iterator Pattern provides a way to access elements of a collection sequentially without
 * exposing its internal structure.
 *
 *
 *
 * 🎯 In Simple Words:
 * 👉 Iterator = Sequential Access.
 * You can go through the items one by one without knowing how they are stored inside.
 *
 *
 *
 *
 * 📦 Real-World Backend Example: Custom Collection Traversal
 * 🔔 Problem:
 * Let’s say you are building a User Collection that stores user records.
 *
 * ✅ You want to:
 *
 * -Provide a standard way to iterate over users (like a for-loop)
 *
 * -Keep the collection’s internal structure hidden from the client.
 *
 * 👉 This is exactly what the Iterator Pattern solves.
 *
 *
 *
 *
 *
 * 💡 Real Backend Parallels:
 * -Java Collections Framework: Iterator, ListIterator
 *
 * -Database Result Sets
 *
 * -Spring Data Pageables (iterating through paginated results)
 *
 * -Streaming APIs
 *
 *
 *
 *
 *
 * ✅ Let’s Build It: Custom User Collection with Iterator
 *
 *
 *
 *
 *
 */

public class Iterator {
    /**
     *
     * ✅ Key Takeaways:
     * Concept	                Explanation
     * --------------------------------------------------------------
     * Iterator	                Provides hasNext() and next() methods for traversal
     * Collection	            Hides internal storage (like ArrayList)
     * Client	                Can iterate without knowing how data is stored
     *
     *
     *
     * 🎯 Real Backend Use Cases:
     * -Database Row Iterators (ResultSet in JDBC)
     *
     * -Pagination Cursors
     *
     * -Streaming APIs
     *
     * -Spring Pageable / Iterator in Repositories
     *
     *
     *
     *
     *
     * ✅ Why This Example is Practical:
     * ✔️ Backend developers traverse collections all the time.
     * ✔️ Iterator pattern abstracts away collection structure.
     * ✔️ Makes it easy to switch internal data storage without changing iteration logic.
     *
     *
     *
     *
     *
     * 🔥 When to Use Iterator Pattern:
     * -When you want to traverse collections uniformly.
     *
     * -When you want to hide internal data structures.
     *
     * -When you want to provide flexible ways to iterate (like forward, backward, filtered).
     *
     *
     *
     */
    public interface Iterator<T> {
        boolean hasNext();
        T next();
    }
    public class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    public interface UserCollection {
        void addUser(User user);
        Iterator<User> createIterator();
    }

    public class UserList implements UserCollection {

        private List<User> users = new ArrayList<>();

        public void addUser(User user) {
            users.add(user);
        }

        public Iterator<User> createIterator() {
            return new UserIterator(users);
        }
    }
    public class UserIterator implements Iterator<User> {

        private List<User> users;
        private int position = 0;

        public UserIterator(List<User> users) {
            this.users = users;
        }

        public boolean hasNext() {
            return position < users.size();
        }

        public User next() {
            return users.get(position++);
        }
    }

    public class Main {
        public void main(String[] args) {

            UserCollection userCollection = new UserList();
            userCollection.addUser(new User("John"));
            userCollection.addUser(new User("Jane"));
            userCollection.addUser(new User("Mike"));

            Iterator<User> iterator = userCollection.createIterator();

            while (iterator.hasNext()) {
                User user = iterator.next();
                System.out.println("User: " + user.getName());
            }
        }
    }



}
