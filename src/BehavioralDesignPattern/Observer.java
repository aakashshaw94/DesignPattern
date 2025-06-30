package BehavioralDesignPattern;

import java.util.ArrayList;
import java.util.List;

/**
 *✅ Definition:
 * The Observer Pattern defines a one-to-many dependency between objects so that when one object (subject) changes state,
 * all its dependents (observers) are notified automatically.
 *
 *
 *
 *
 * 🎯 In Simple Words:
 * 👉 Observer = Subscriber.
 * When something changes, all subscribed listeners get notified.
 *
 *
 *
 *
 * 📦 Real-World Backend Example: Email Notification System
 * 🔔 Problem:
 * Imagine you are building an order system.
 * When a new order is placed:
 *
 * Send an Email Notification.
 *
 * Send an SMS Notification.
 *
 * Update Dashboard Metrics.
 *
 * 👉 These are all observers of the new order event.
 *
 * ✅ Instead of hard-coding all these actions in the order system, you can decouple them using the Observer Pattern.
 *
 *
 *
 *
 *
 * 💡 Real Backend Parallels:
 * Event-Driven Systems (Kafka, RabbitMQ)
 *
 * Spring Event Listeners (@EventListener)
 *
 * Webhooks
 *
 * Database Change Listeners
 *
 * Notification Services
 *
 *
 *
 *
 *
 *
 * ✅ Let’s Build It: New Order Notification Example
 *
 *
 *
 *
 *
 *
 *
 */

public class Observer {
    public interface Observer {
        void update(String message);
    }
    public class EmailObserver implements Observer {
        public void update(String message) {
            System.out.println("Email Notification: " + message);
        }
    }
    public class SMSObserver implements Observer {
        public void update(String message) {
            System.out.println("SMS Notification: " + message);
        }
    }
    public class DashboardObserver implements Observer {
        public void update(String message) {
            System.out.println("Dashboard Updated: " + message);
        }
    }

    public class OrderService {

        private List<Observer> observers = new ArrayList<>();

        public void subscribe(Observer observer) {
            observers.add(observer);
        }

        public void unsubscribe(Observer observer) {
            observers.remove(observer);
        }

        public void placeOrder(String orderDetails) {
            System.out.println("Order Placed: " + orderDetails);
            notifyAllObservers(orderDetails);
        }

        private void notifyAllObservers(String message) {
            for (Observer observer : observers) {
                observer.update(message);
            }
        }
    }

    public class Main {
        public void main(String[] args) {

            OrderService orderService = new OrderService();

            // Create observers
            Observer email = new EmailObserver();
            Observer sms = new SMSObserver();
            Observer dashboard = new DashboardObserver();

            // Subscribe observers
            orderService.subscribe(email);
            orderService.subscribe(sms);
            orderService.subscribe(dashboard);

            // Place an order (all observers get notified)
            orderService.placeOrder("Order#1234");
        }
    }


}
