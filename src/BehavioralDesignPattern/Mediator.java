package BehavioralDesignPattern;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * ✅ Definition:
 * The Mediator Pattern defines a central object (Mediator) that controls communication between multiple objects,
 * reducing direct dependencies between them.
 *
 *
 *
 *
 * 🎯 In Simple Words:
 * 👉 Mediator = Communication Hub.
 * Instead of having objects talk to each other directly, they communicate via a mediator.
 * This keeps the system less tightly coupled and easier to manage.
 *
 *
 *
 *
 *
 * 📦 Real-World Backend Example: Chat Room System
 * 🔔 Problem:
 * Imagine a chat room backend where multiple users can send messages to each other.
 *
 * 👉 Without Mediator:
 *
 * Every user would need to know all other users to send them messages → Tight Coupling 🚫
 *
 * 👉 With Mediator:
 *
 * All users just send messages to the Chat Room (Mediator), and it delivers messages to the right users → Loose Coupling ✅
 *
 *
 *
 *
 *
 *
 * 💡 Real Backend Parallels:
 * -Message Brokers (like RabbitMQ, Kafka)
 *
 * -Event Buses
 *
 * -Chat Servers
 *
 * -Notification Systems
 *
 * -Air Traffic Control Systems (classic example)
 *
 *
 *
 *
 * ✅ Let’s Build It: Simple Chat Room Example
 *
 */
public class Mediator {
    /**
     *
     * ✅ Key Takeaways:
     * Concept	                Explanation
     * --------------------------------------------------------------
     * Mediator	                ChatRoom manages communication between users
     * Colleagues	            Users send messages via the ChatRoom
     * Loose Coupling	        Users don’t know about each other, only about the mediator\
     *
     *
     *
     * 🎯 Real Backend Use Cases:
     * -Message Brokers: Kafka, RabbitMQ (producers and consumers don’t know each other)
     *
     * -Chat Applications: Central chat server as a mediator
     *
     * -Event Bus Systems: Spring ApplicationEvents, Node.js EventEmitter
     *
     * -Air Traffic Control: Planes communicate via the control tower
     *
     *
     *
     *
     * ✅ Why This Example is Practical:
     * ✔️ Backend systems often need decoupled communication.
     * ✔️ Mediator pattern keeps the system organized and easy to extend.
     * ✔️ Prevents tight coupling between many interconnected objects.
     *
     *
     *
     *
     *
     * 🔥 When to Use Mediator Pattern:
     * -When many objects need to communicate but you want to avoid tight coupling.
     *
     * -When you want a centralized point of control.
     *
     * -When you need simplified, flexible, and maintainable communication.
     *
     *
     *
     */
    public interface ChatRoomMediator {
        void showMessage(String message, User user);
    }

    public class ChatRoom implements ChatRoomMediator {

        private List<User> users = new ArrayList<>();

        public void addUser(User user) {
            users.add(user);
        }

        public void showMessage(String message, User sender) {
            for (User user : users) {
                // Sender should not receive their own message
                if (user != sender) {
                    user.receive(message, sender.getName());
                }
            }
        }
    }
    public class User {

        private String name;
        private ChatRoomMediator chatRoom;

        public User(String name, ChatRoomMediator chatRoom) {
            this.name = name;
            this.chatRoom = chatRoom;
        }

        public String getName() {
            return name;
        }

        public void send(String message) {
            System.out.println(this.name + " sends: " + message);
            chatRoom.showMessage(message, this);
        }

        public void receive(String message, String senderName) {
            System.out.println(this.name + " receives from " + senderName + ": " + message);
        }
    }
    public class Main {
        public void main(String[] args) {

            ChatRoom chatRoom = new ChatRoom();

            User user1 = new User("Alice", chatRoom);
            User user2 = new User("Bob", chatRoom);
            User user3 = new User("Charlie", chatRoom);

            chatRoom.addUser(user1);
            chatRoom.addUser(user2);
            chatRoom.addUser(user3);

            user1.send("Hello, everyone!");
            user2.send("Hi Alice!");
        }
    }


}
