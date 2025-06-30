package BehavioralDesignPattern;

/**
 *
 * ✅ Definition:
 * The Interpreter Pattern is used to define a language's grammar and interpret sentences in that language.
 *
 *
 *
 * 🎯 In Simple Words:
 * 👉 Interpreter = Mini-Language Processor.
 * It helps you build and evaluate simple languages or expression rules.
 *
 *
 *
 *
 * 📦 Real-World Backend Example:
 * Search Query Filtering (Simple Query Language)
 * 🔔 Problem:
 * Let’s say you want to filter user data with simple search queries like:
 *
 * -Name == "John"
 *
 * -Age > 25
 *
 * -Name == "John" AND Age > 25
 *
 * ✅ You can use the Interpreter Pattern to process and evaluate these custom search expressions.
 *
 *
 *
 *
 *
 * 💡 Real Backend Parallels:
 * -Rule Engines: Validate data or apply business rules.
 *
 * -Search Filters: Like MongoDB query format or ElasticSearch DSL.
 *
 * -Expression Evaluators: Schedulers, calculators.
 *
 * -Custom Configuration Languages.
 *
 *
 *
 *
 *
 *
 * ✅ Let’s Build It: Simple Expression Evaluator
 *
 *
 *
 *
 *
 */

public class Interpreter {
    /***
     *
     * ✅ Key Takeaways:
     * Concept	                    Explanation
     * -------------------------------------------------------------
     * Expression Interface	        Common interpreter contract
     * Terminal Expressions	        Basic checks (name, age)
     * Non-Terminal Expression	    Combines terminal expressions using AND logic
     * Client	                    Builds and evaluates complex rules
     *
     *
     *
     *
     * 🎯 Real Backend Use Cases:
     * Search Filters
     *
     * Validation Rules Engine
     *
     * Simple Query Languages (SQL-like DSL)
     *
     * Command or Expression Parsing
     *
     * Workflow Condition Evaluation
     *
     *
     *
     *
     * ✅ Why This Example is Practical:
     * ✔️ Backend developers build filtering systems and rule evaluators all the time.
     * ✔️ Interpreter Pattern helps represent, parse, and evaluate these rules flexibly.
     * ✔️ Easy to extend (you can add OR, NOT, etc.).
     *
     *
     *
     *
     *
     * 🔥 When to Use Interpreter Pattern:
     * -When you need to evaluate or parse mini-languages.
     *
     * -When you want to create flexible, configurable rules.
     *
     * -When you want to decouple the rule definition from the evaluation.
     *
     *
     *
     *
     *
     */
    public interface Expression {
        boolean interpret(User user);
    }
    public class NameEqualsExpression implements Expression {

        private String name;

        public NameEqualsExpression(String name) {
            this.name = name;
        }

        public boolean interpret(User user) {
            return user.getName().equalsIgnoreCase(name);
        }
    }
    public class AgeGreaterThanExpression implements Expression {

        private int age;

        public AgeGreaterThanExpression(int age) {
            this.age = age;
        }

        public boolean interpret(User user) {
            return user.getAge() > age;
        }
    }
    public class AndExpression implements Expression {

        private Expression expr1;
        private Expression expr2;

        public AndExpression(Expression expr1, Expression expr2) {
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        public boolean interpret(User user) {
            return expr1.interpret(user) && expr2.interpret(user);
        }
    }
    public class User {

        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
    public class Main {
        public void main(String[] args) {

            // Expressions
            Expression isJohn = new NameEqualsExpression("John");
            Expression isOlderThan25 = new AgeGreaterThanExpression(25);
            Expression complexRule = new AndExpression(isJohn, isOlderThan25);

            // Test user
            User user1 = new User("John", 30);
            User user2 = new User("John", 20);
            User user3 = new User("Mike", 30);

            System.out.println("User1 matches? " + complexRule.interpret(user1)); // true
            System.out.println("User2 matches? " + complexRule.interpret(user2)); // false
            System.out.println("User3 matches? " + complexRule.interpret(user3)); // false
        }
    }

}
