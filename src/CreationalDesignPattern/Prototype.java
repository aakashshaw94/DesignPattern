package CreationalDesignPattern;

/**
 *
 * ✅ Definition:
 * The Prototype Pattern is used to create a copy (clone) of an existing object instead of creating a new one from scratch.
 *
 * 👉 In simple words:
 * You make a duplicate of an object quickly.
 *
 * 🎯 Why Use Prototype Pattern?
 * When creating a new object is expensive or time-consuming.
 *
 * When you need many similar objects.
 *
 * 📦 Real-Life Example:
 * Imagine you have a Resume template.
 *
 * Instead of making a new resume from zero every time, you just make a copy and update your details.
 *
 * 🔥 What Happened?
 * ✔️ Original object created.
 * ✔️ Clone made directly without using new keyword to create a fresh object.
 * ✔️ Both are separate objects (modifying one does not affect the other unless it’s a deep clone).
 *
 * ✅ Key Points:
 * Concept	                    Description
 * --------------------------------------------------------------------------
 * Purpose	                    Copy an existing object quickly.
 * How	                        Using the clone() method or copy constructors.
 * When	                        When object creation is costly or you need many similar objects.
 * Example in Java	            ArrayList.clone(), HashMap.clone(), Prototype beans in Spring.
 *
 * 📌 Bonus:
 * 👉 Java Clone Types:
 * Shallow Copy: Copies only top-level fields (like our example).
 *
 * Deep Copy: Copies all nested objects (more advanced but safer for complex objects).
 *
 * ✅ Summary:
 * Prototype = Copy object quickly.
 *
 * Use when creating new objects is expensive.
 *
 * In Java: Achieved using Cloneable interface and clone() method.
 *
 *
 */
public class Prototype {
    public class Resume implements Cloneable {

        private String name;
        private String qualification;

        public Resume(String name, String qualification) {
            this.name = name;
            this.qualification = qualification;
        }

        public void display() {
            System.out.println("Name: " + name + ", Qualification: " + qualification);
        }

        // Implement clone method
        @Override
        public Resume clone() {
            try {
                return (Resume) super.clone();
            } catch (CloneNotSupportedException e) {
                return null; // Handle exception properly in real use
            }
        }
    }
    public class Main {
        public void main(String[] args) {

            // Original Resume
            Resume original = new Resume("John", "B.Tech");
            original.display();

            // Clone the Resume
            Resume copy = original.clone();
            copy.display();
        }
    }
}
