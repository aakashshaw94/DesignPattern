package StructuralDesignPattern;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * ✅ Definition:
 * The Composite Pattern lets you treat individual objects and groups of objects in the same way.
 *
 *
 * 🎯 In Simple Words:
 * 👉 Composite = Tree-like structure.
 * It allows you to build hierarchies (like folders and files) where you can:
 *
 *
 * Treat a single item and a group of items the same way.
 *
 * 📦 Real-World Example: Backend Role-Based Access Control (RBAC)
 * 🔔 Problem:
 * You are building a permissions system in a backend application.
 *
 * You have:
 *
 * Individual Permissions → like READ, WRITE
 *
 * Groups of Permissions → like AdminRole (which contains many permissions)
 *
 * You want to:
 *
 * Check if a user has permission
 *
 * Whether that permission is a single action or part of a group, the checking logic should work the same way.
 *
 * ✅ Composite Pattern Perfectly Fits!
 *
 *
 */
public class Composite {

    /**
     * ✅ Key Takeaways:
     * Concept	Real-World Example
     * Leaf	Single Permission (READ, WRITE)
     * Composite	Role containing multiple permissions
     * Benefit	Treat single and grouped permissions the same way
     *
     * 🎯 Real-World Backend Examples:
     * File Systems: Files and Folders (Folders can contain files or other folders)
     *
     * Menu Systems: Menu Items and Sub-Menus in UI apps
     *
     * RBAC (Role-Based Access Control): Permissions and Roles hierarchy
     *
     * Organization Structures: Employees, Managers (Managers can have employees under them)
     *
     * ✅ Why It’s Useful:
     * ✔️ Simplifies complex hierarchical structures
     * ✔️ Supports recursion naturally
     * ✔️ You can add, remove, or traverse items uniformly whether it’s a single item or a group.
     *
     * 🔥 Composite Pattern is Perfect When:
     * You have tree structures.
     *
     * You need to treat part and whole objects uniformly.
     *
     *
     */
    public interface PermissionComponent {
        void showPermission();
    }
    public class Permission implements PermissionComponent {

        private String name;

        public Permission(String name) {
            this.name = name;
        }

        public void showPermission() {
            System.out.println("Permission: " + name);
        }
    }

    public class Role implements PermissionComponent {

        private String roleName;
        private List<PermissionComponent> permissions = new ArrayList<>();

        public Role(String roleName) {
            this.roleName = roleName;
        }

        public void add(PermissionComponent permission) {
            permissions.add(permission);
        }

        public void remove(PermissionComponent permission) {
            permissions.remove(permission);
        }

        public void showPermission() {
            System.out.println("Role: " + roleName);
            for (PermissionComponent permission : permissions) {
                permission.showPermission();
            }
        }
    }

    public class Main {
        public void main(String[] args) {

            // Individual Permissions
            Permission read = new Permission("READ");
            Permission write = new Permission("WRITE");
            Permission delete = new Permission("DELETE");

            // Admin Role (Composite)
            Role adminRole = new Role("Admin");
            adminRole.add(read);
            adminRole.add(write);
            adminRole.add(delete);

            // User Role (Composite)
            Role userRole = new Role("User");
            userRole.add(read);

            // Show Permissions for Admin
            adminRole.showPermission();

            // Show Permissions for User
            userRole.showPermission();
        }
    }

  /**
   *
   * 🚀 Real-World Backend Example: E-commerce Category System (Composite Pattern)
   * 🎯 Problem:
   * You are building an E-commerce API.
   *
   * You have:
   *
   * Categories (like Electronics, Clothing)
   *
   * Subcategories (like Mobiles, Laptops under Electronics)
   *
   *
   *
   * 👉 You want to:
   *
   * Build category trees that can go many levels deep.
   *
   * Add, remove, display, or traverse categories and subcategories in a uniform way.
   *
   *
   * Electronics
   *  ├── Mobiles
   *  ├── Laptops
   *  └── Cameras
   *
   *
   *
   *  ✅ Each category can be:
   *
   * A leaf node (no subcategories)
   *
   * A composite node (has subcategories)
   *
   * ✅ Composite Pattern: Perfect Solution
   * It allows:
   *
   * Single category = Leaf
   *
   * Category with subcategories = Composite
   *
   * Uniform treatment for both.
   *
   *
   *
   * ✅ Key Takeaways:
   * Concept	                Explanation
   * -----------------------------------------------------------------
   * Leaf Node	                Single category (like Mobiles, Laptops)
   * Composite Node	            Category with subcategories (like Electronics)
   * Client View	            Can treat both uniformly
   *
   * ✅ Real Backend Systems That Use Composite:
   * E-commerce Categories/Subcategories API (Amazon, Flipkart)
   *
   * Organization Hierarchies API (Manager → Team Lead → Employee)
   *
   * Filesystem API (Folder → Files → Subfolders)
   *
   * Forum Threads API (Parent post → Replies → Replies to replies)
   *
   * 🎯 Why This Example is Realistic:
   * ✔️ Backend developers build nested category trees all the time.
   * ✔️ APIs for tree traversal and search are very common.
   * ✔️ You need a design where adding subcategories is seamless without breaking existing structures.
   * ✔️ Database modeling (using parent-child relationships) maps directly to this design.
   *
   * ✅ Benefits:
   * Clean, hierarchical design.
   *
   * Easy traversal.
   *
   * Supports dynamic addition/removal.
   *
   * Same API for leaf and composite.
   *
   *
   *
   */

  public interface CategoryComponent {
      void showCategory();
  }
  public class CategoryItem implements CategoryComponent {

        private String name;

        public CategoryItem(String name) {
            this.name = name;
        }

        public void showCategory() {
            System.out.println("Category: " + name);
        }
  }

    public class CategoryComposite implements CategoryComponent {

        private String name;
        private List<CategoryComponent> subcategories = new ArrayList<>();

        public CategoryComposite(String name) {
            this.name = name;
        }

        public void add(CategoryComponent category) {
            subcategories.add(category);
        }

        public void remove(CategoryComponent category) {
            subcategories.remove(category);
        }

        public void showCategory() {
            System.out.println("Category: " + name);
            for (CategoryComponent subcategory : subcategories) {
                subcategory.showCategory();
            }
        }
    }

    public class Main1 {
        public void main(String[] args) {

            // Leaf Categories
            CategoryComponent mobiles = new CategoryItem("Mobiles");
            CategoryComponent laptops = new CategoryItem("Laptops");
            CategoryComponent cameras = new CategoryItem("Cameras");

            // Composite Category: Electronics
            CategoryComposite electronics = new CategoryComposite("Electronics");
            electronics.add(mobiles);
            electronics.add(laptops);
            electronics.add(cameras);

            // Leaf Category
            CategoryComponent clothing = new CategoryItem("Clothing");

            // Top Level Composite: All Categories
            CategoryComposite allCategories = new CategoryComposite("All Categories");
            allCategories.add(electronics);
            allCategories.add(clothing);

            // Display the entire category tree
            allCategories.showCategory();
        }
    }



}
