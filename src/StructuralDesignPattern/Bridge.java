package StructuralDesignPattern;

/**
 * ✅ Definition:
 * The Bridge Pattern is used to separate abstraction from implementation so that both can evolve independently.
 *
 * 🎯 In Simple Words:
 * 👉 Bridge = Decouple layers.
 * It separates what something does (abstraction) from how it is done (implementation).
 *
 * 📦 Real-World, Practical Example: TV Remote Control
 * Problem:
 * You have:
 *
 * Different Remotes: Basic Remote, Smart Remote
 *
 * Different TVs: Samsung TV, Sony TV
 *
 * Challenge:
 * You don’t want to create:
 *
 * Samsung + Basic Remote
 *
 * Sony + Basic Remote
 *
 * Samsung + Smart Remote
 *
 * Sony + Smart Remote
 *
 * 👉 It would explode into too many combinations.
 *
 * 🎯 Solution: Bridge Pattern
 * Separate Remote Control (Abstraction) from TV Brand (Implementation)
 *
 * You can mix and match any remote with any TV without creating multiple subclasses.
 *
 *
 * ✅ Key Takeaways:
 * Concept	                    Explanation
 * -----------------------------------------------------------------
 * Abstraction	                RemoteControl (what the user uses)
 * Implementation	            TV (brand-specific implementations)
 * Purpose	                    Decouples remote controls from TV brands. You can add new remotes or TVs independently.
 *
 *
 *
 *
 */
public class Bridge {
    public interface TV {
        void turnOn();
        void turnOff();
    }
    public class SamsungTV implements TV {
        public void turnOn() {
            System.out.println("Turning on Samsung TV.");
        }

        public void turnOff() {
            System.out.println("Turning off Samsung TV.");
        }
    }

    public class SonyTV implements TV {
        public void turnOn() {
            System.out.println("Turning on Sony TV.");
        }

        public void turnOff() {
            System.out.println("Turning off Sony TV.");
        }
    }
    public abstract class RemoteControl {
        protected TV tv; // Bridge to implementation

        public RemoteControl(TV tv) {
            this.tv = tv;
        }

        public abstract void turnOn();
        public abstract void turnOff();
    }
    public class BasicRemote extends RemoteControl {

        public BasicRemote(TV tv) {
            super(tv);
        }

        public void turnOn() {
            tv.turnOn();
        }

        public void turnOff() {
            tv.turnOff();
        }
    }
    public class Main1 {
        public void main(String[] args) {

            // Basic Remote with Samsung TV
            RemoteControl remote1 = new BasicRemote(new SamsungTV());
            remote1.turnOn();
            remote1.turnOff();

            // Basic Remote with Sony TV
            RemoteControl remote2 = new BasicRemote(new SonyTV());
            remote2.turnOn();
            remote2.turnOff();
        }
    }

    /**
     * 🚀 Real-World Backend Example: Notification System
     *
     *
     * 🎯 Problem:
     * You are building a Notification Service for a backend system.
     *
     * You need to support:
     *
     * Multiple Notification Types:
     *
     * Alerts
     *
     * Reports
     *
     * Reminders
     *
     * Multiple Delivery Channels:
     *
     * Email
     *
     * SMS
     *
     * Push Notification
     *
     *
     *
     *
     * ❌ Without Bridge Pattern:
     * You would end up creating:
     *
     * AlertEmailNotification
     *
     * AlertSMSNotification
     *
     * ReportEmailNotification
     *
     * ReportSMSNotification
     *
     * ReminderPushNotification
     * 👉 Class Explosion!
     *
     *
     *
     *
     * ✅ With Bridge Pattern:
     * You decouple Notification Type (abstraction) from Delivery Channel (implementation).
     *
     * You can add new notification types without touching channels.
     *
     * You can add new delivery channels without touching notifications.
     *
     *
     *
     * ✅ Key Takeaways:
     * Concept	                Example
     * ---------------------------------------------------------------------
     * Abstraction	            Notification Type (Alert, Report, Reminder)
     * Implementation	        Delivery Channels (Email, SMS, Push)
     * Result	                You can mix any notification with any delivery channel without creating new subclasses.
     *
     * ✅ Why This Backend Example Makes Sense:
     * 🔸 You can add new notifications (like ReminderNotification) easily.
     *
     * 🔸 You can add new channels (like PushSender, WhatsAppSender) without touching existing code.
     *
     * 🔸 Perfect for microservices and API-driven architectures.
     *
     * 🔸 Backend systems like Twilio, SendGrid, and Firebase Messaging work this way.
     *
     * 🎯 Real Backend Examples:
     * Spring Boot Notifications: Email, SMS, WebSocket push all handled through pluggable channels.
     *
     * Messaging Platforms: Decoupling message type (order event, alert event) from brokers (Kafka, RabbitMQ, etc.).
     *
     * Logging Frameworks: Logs (Error, Info, Debug) routed to different destinations (File, Console,
     * Database) using bridge-like architecture.
     *
     *
     */

    public interface MessageSender {
        void sendMessage(String message);
    }

    public class EmailSender implements MessageSender {
        public void sendMessage(String message) {
            System.out.println("Sending Email: " + message);
        }
    }

    public class SMSSender implements MessageSender {
        public void sendMessage(String message) {
            System.out.println("Sending SMS: " + message);
        }
    }

    public abstract class Notification {
        protected MessageSender sender;

        public Notification(MessageSender sender) {
            this.sender = sender;
        }

        public abstract void send(String message);
    }
    public class AlertNotification extends Notification {

        public AlertNotification(MessageSender sender) {
            super(sender);
        }

        public void send(String message) {
            sender.sendMessage("[ALERT] " + message);
        }
    }

    public class ReportNotification extends Notification {

        public ReportNotification(MessageSender sender) {
            super(sender);
        }

        public void send(String message) {
            sender.sendMessage("[REPORT] " + message);
        }
    }

    public class Main {
        public void main(String[] args) {

            // Sending an Alert via Email
            Notification alert = new AlertNotification(new EmailSender());
            alert.send("Server Down!");

            // Sending a Report via SMS
            Notification report = new ReportNotification(new SMSSender());
            report.send("Daily report generated.");
        }
    }

}
