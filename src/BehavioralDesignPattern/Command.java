package BehavioralDesignPattern;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * ✅ Definition:
 * The Command Pattern turns a request into an object that can be stored, passed around, and executed later.
 *
 *
 *
 *
 * 🎯 In Simple Words:
 * 👉 Command = Encapsulated Request.
 * You package a request (like an API call or an operation) into a separate Command object.
 *
 *
 *
 *
 *
 * 📦 Real-World Backend Example: Task Scheduling (Like Email, SMS, or Batch Jobs)
 * 🔔 Problem:
 * You want to build a Task Scheduler that can:
 *
 *- Send an Email
 *
 *- Send an SMS
 *
 *- Generate a Report
 *
 * 👉 You don’t want to hard-code the task logic into the scheduler.
 *
 * ✅ Instead:
 *
 * -Each task is encapsulated as a Command object.
 *
 * -The scheduler simply executes the command without knowing the details.
 *
 *
 *
 *
 *
 * 💡 Real Backend Parallels:
 * -Job Queues (RabbitMQ, Kafka, AWS SQS) → Commands are queued for later execution.
 *
 * -Undo/Redo Operations in state management.
 *
 * -Spring Batch Jobs
 *
 * -Workflow Engines (like Camunda)
 *
 *
 *
 *
 *
 * ✅ Let’s Build It: Task Scheduler using Command Pattern
 *
 *
 *
 *
 */
public class Command {
    /**
     *
     * ✅ Key Takeaways:
     * Concept	                    Explanation
     * -------------------------------------------------------------
     * Command	                    Encapsulates a request as an object (EmailCommand, SMSCommand)
     * Invoker	                    TaskScheduler holds and executes commands
     * Receiver	                    The actual logic that performs the task (sending email, SMS)
     * Client	                    Creates and schedules commands
     *
     *
     *
     *
     *
     * 🎯 Real Backend Use Cases:
     * Job Queues: Commands are sent to queues and processed later.
     *
     * Undo/Redo Operations: Store commands and reverse them.
     *
     * Batch Processing: Scheduled jobs can be modeled as commands.
     *
     * Workflow Engines: Commands move processes from one step to another.
     *
     *
     *
     *
     *
     *
     * ✅ Why This Example is Practical:
     * ✔️ Task scheduling is common in real backend applications.
     * ✔️ Decouples who requests the task from who executes it.
     * ✔️ Makes it easy to store, log, retry, or undo operations.
     *
     *
     *
     *
     *
     * 🔥 When to Use Command Pattern:
     * When you want to queue, delay, or log requests.
     *
     * When you need to parameterize requests.
     *
     * When you want to decouple the requester from the executor.
     *
     *
     *
     *
     *
     */
    public interface Command {
        void execute();
    }
    public class EmailCommand implements Command {

        private String email;

        public EmailCommand(String email) {
            this.email = email;
        }

        public void execute() {
            System.out.println("Sending Email to: " + email);
        }
    }
    public class SMSCommand implements Command {

        private String phoneNumber;

        public SMSCommand(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void execute() {
            System.out.println("Sending SMS to: " + phoneNumber);
        }
    }

    public class TaskScheduler {

        private Queue<Command> taskQueue = new LinkedList<>();

        public void addTask(Command task) {
            taskQueue.add(task);
        }

        public void runTasks() {
            while (!taskQueue.isEmpty()) {
                Command task = taskQueue.poll();
                task.execute();
            }
        }
    }

    public class Main {
        public void main(String[] args) {

            TaskScheduler scheduler = new TaskScheduler();

            // Add tasks
            scheduler.addTask(new EmailCommand("user1@example.com"));
            scheduler.addTask(new SMSCommand("+1234567890"));
            scheduler.addTask(new EmailCommand("user2@example.com"));

            // Run all tasks
            scheduler.runTasks();
        }
    }


}
