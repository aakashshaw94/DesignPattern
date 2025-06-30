package CreationalDesignPattern;
import java.io.Serializable;
import java.io.*;

/**
 *
 * ✅ What is Deep Cloning?
 * Shallow Cloning: Copies the object but not the nested (referenced) objects.
 *
 * Deep Cloning: Copies the object and all nested objects (so changes to one do not affect the other at any level).
 *
 * ✅ Why Serialization Works Well?
 * Serialization writes the entire object graph to a stream and reads it back — effectively creating a deep copy.
 *
 * 💡 Simple Example: Deep Cloning via Serialization
 * Let’s say we have a Student with an Address object inside it.
 *
 * 🎯 Key Points:
 * ✅ Deep cloning achieved!
 *
 * Changing the original object does not affect the clone, even for nested objects.
 *
 * Serialization automatically takes care of deep copying complex objects.
 *
 * ✅ Benefits of Deep Cloning via Serialization:
 * Very simple to implement.
 *
 * Works for deeply nested objects.
 *
 * Ensures full object separation.
 *
 * ⚡ Caution:
 * All classes must implement Serializable.
 *
 * Performance can be slower for very large objects.
 *
 * For high-performance cases, manual deep cloning is better.
 *
 * 🔥 Summary Table:
 * Feature	                            Shallow Clone	            Deep Clone via Serialization
 * ---------------------------------------------------------------------------------------------------
 * Copies top-level fields	                ✅	                                ✅
 * Copies nested objects	        ❌ (references are shared)	    ✅ (completely new copies of nested objects)
 * Implementation	                  clone() + Cloneable	            Serialization-based deep copy
 * Recommended for	                    Simple objects	                    Complex, nested objects
 *
 *
 */
public class DeepCopy {
    public class Address implements Serializable {
        String city;

        public Address(String city) {
            this.city = city;
        }
    }

    public class Student implements Serializable {
        String name;
        Address address;

        public Student(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        public void display() {
            System.out.println("Name: " + name + ", City: " + address.city);
        }
    }

    public static class DeepCloneUtility {

        // Generic method for deep cloning using serialization
        @SuppressWarnings("unchecked")
        public static <T> T deepClone(T object) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);

                // Serialize the object
                oos.writeObject(object);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                // Deserialize to create deep copy
                return (T) ois.readObject();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public class Main {
        public void main(String[] args) {

            // Original student
            Address address = new Address("Delhi");
            Student student1 = new Student("John", address);

            // Deep clone the student
            Student student2 = DeepCloneUtility.deepClone(student1);

            // Change the original address
            student1.address.city = "Mumbai";

            // Display both students
            student1.display(); // Should show: Mumbai
            student2.display(); // Should still show: Delhi (deep clone success)
        }
    }

}
