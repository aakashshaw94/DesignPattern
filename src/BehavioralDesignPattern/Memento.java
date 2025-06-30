package BehavioralDesignPattern;
import java.util.Stack;

/**
 *
 *
 * ✅ Definition:
 * The Memento Pattern captures and saves an object’s internal state so that it can be restored later without
 * violating encapsulation.
 *
 *
 *
 *
 *
 * 🎯 In Simple Words:
 * 👉 Memento = Save & Restore State.
 * It’s like a Checkpoint or Undo button.
 *
 *
 *
 *
 *
 * 📦 Real-World Backend Example: Undo Feature in Text Editor / Form Changes
 * 🔔 Problem:
 * Let’s say you are building a form editing system (like editing user profile details).
 *
 * ✅ You want to:
 *
 * -Save the current state before making changes.
 *
 * -Provide an Undo button to go back to the previous state.
 *
 * 👉 This is exactly what the Memento Pattern solves.
 *
 *
 *
 *
 *
 * 💡 Real Backend Parallels:
 * -Version History / Snapshots
 *
 * -Undo in Form or Document Editing
 *
 * -Database Rollbacks
 *
 * -State Management in Distributed Systems
 *
 * -Cache Rollbacks in Workflow Engines
 *
 *
 *
 *
 *
 *
 * ✅ Let’s Build It: Form Editing with Undo Feature
 *
 *
 *
 *
 *
 */

public class Memento {
    /**
     *
     * ✅ Key Takeaways:
     * Concept	            Explanation
     * --------------------------------------------------------------
     * Originator	        The object whose state is saved (UserProfile)
     * Memento	            The snapshot of the state
     * Caretaker	        Keeps track of history (History class)
     * Undo	                Restore previous states using the saved mementos
     *
     *
     *
     *
     * 🎯 Real Backend Use Cases:
     * Form Undo Features
     *
     * Workflow or Transaction Rollbacks
     *
     * Snapshotting in Databases
     *
     * State Recovery in Distributed Systems
     *
     * Version Control Systems
     *
     *
     *
     *
     * ✅ Why This Example is Practical:
     * ✔️ Backend developers often need rollback or undo capabilities.
     * ✔️ Memento pattern allows state saving without breaking encapsulation.
     * ✔️ Makes it easy to manage checkpoints in user workflows or batch processes.
     *
     *
     *
     *
     * 🔥 When to Use Memento Pattern:
     * When you need to save and restore object states.
     *
     * When you want undo/redo functionality.
     *
     * When you want to capture snapshots without exposing internal details.
     *
     *
     *
     *
     */
    public class UserProfile {

        private String name;
        private String email;

        public void setState(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public Memento saveToMemento() {
            return new Memento(name, email);
        }

        public void restoreFromMemento(Memento memento) {
            this.name = memento.getName();
            this.email = memento.getEmail();
        }

        public void show() {
            System.out.println("Current State -> Name: " + name + ", Email: " + email);
        }
    }
    public class Memento {

        private final String name;
        private final String email;

        public Memento(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }

    public class History {

        private Stack<Memento> historyStack = new Stack<>();

        public void save(Memento memento) {
            historyStack.push(memento);
        }

        public Memento undo() {
            if (!historyStack.isEmpty()) {
                return historyStack.pop();
            }
            return null;
        }
    }

    public class Main {
        public void main(String[] args) {

            UserProfile profile = new UserProfile();
            History history = new History();

            profile.setState("John Doe", "john@example.com");
            history.save(profile.saveToMemento()); // Save checkpoint
            profile.show();

            profile.setState("Jane Smith", "jane@example.com");
            history.save(profile.saveToMemento()); // Save checkpoint
            profile.show();

            // User wants to undo the last change
            profile.restoreFromMemento(history.undo());
            profile.show();

            // Undo again
            profile.restoreFromMemento(history.undo());
            profile.show();
        }
    }


}
