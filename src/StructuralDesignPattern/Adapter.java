package StructuralDesignPattern;

/**
 *
 *
 * ✅ Definition:
 * The Adapter Pattern allows two incompatible interfaces to work together.
 *
 * 👉 In simple words:
 * It’s like a plug converter. It adapts one interface to another.
 *
 * 🎯 Real-Life Example:
 * ⚡ Power Plug Adapter:
 * You have a US plug but a European socket.
 *
 * The plug doesn’t fit → you use an adapter to connect them.
 *
 * 👉 The adapter bridges the gap.
 *
 * 💻 Simple Java Example: Audio Player
 * 🎧 Scenario:
 * Target Interface: MusicPlayer → expects .mp3 files.
 *
 * Existing Class: VLCPlayer → plays .vlc files (incompatible).
 *
 * 👉 We need an Adapter to make VLCPlayer work with MusicPlayer.
 *
 * 🔥 Quick Summary:
 * Concept	                            Explanation
 * -----------------------------------------------------------------
 * What	                                Adapter allows incompatible classes to work together.
 * How	                                Converts one interface to another.
 * Real-life	                        Plug adapter, card reader, USB to HDMI adapter.
 * Java Example	                        Java I/O Streams (InputStreamReader adapts InputStream to Reader).
 *
 *
 *
 * 🎯 Key Points:
 * Adapter = Translator
 *
 * Helps integrate legacy code or third-party classes.
 *
 * Keeps your system open for extension without changing existing code.
 *
 */
public class Adapter {


    /**
     * 🚀 Real-Time Example: Payment Gateway Integration (Adapter Pattern)
     *
     *
     *
     * 🎯 Problem:
     * You are building an e-commerce app that uses your company’s internal PaymentProcessor interface.
     *
     * Suddenly, you need to integrate PayPal, which has a completely different API.
     *
     * 💡 Challenge:
     * Your system expects a pay(amount) method, but PayPal's API uses makePayment(paymentAmount).
     *
     * 👉 The interfaces don’t match.
     *
     * ✅ Adapter Pattern Solution:
     * We can create a PayPal Adapter that converts your system’s expected method (pay) to PayPal’s method (makePayment).
     *
     *
     */

    public interface PaymentProcessor {
        void pay(double amount);
    }
    public class PayPalAPI {
        public void makePayment(double paymentAmount) {
            System.out.println("Payment of $" + paymentAmount + " made using PayPal.");
        }
    }
    public class PayPalAdapter implements PaymentProcessor {

        private PayPalAPI payPalAPI = new PayPalAPI();

        @Override
        public void pay(double amount) {
            // Adapting your system’s method to PayPal’s method
            payPalAPI.makePayment(amount);
        }
    }
    public class Main2 {
        public void main(String[] args) {

            // Your system uses PaymentProcessor interface
            PaymentProcessor payment = new PayPalAdapter();
            payment.pay(500.0); // You don't care about PayPal's method, it just works
        }
    }



    /**
     *
     *
     * Example Simple
     */
    public interface MusicPlayer {
        void play(String audioType, String fileName);
    }

    public class VLCPlayer {
        public void playVLC(String fileName) {
            System.out.println("Playing VLC file: " + fileName);
        }
    }

    // Adapter Class
    public class MediaAdapter implements MusicPlayer {
        VLCPlayer vlcPlayer = new VLCPlayer();

        @Override
        public void play(String audioType, String fileName) {
            if (audioType.equalsIgnoreCase("vlc")) {
                vlcPlayer.playVLC(fileName);  // Adapting VLCPlayer to MusicPlayer
            }
        }
    }

    public class AudioPlayer implements MusicPlayer {

        MediaAdapter adapter;

        @Override
        public void play(String audioType, String fileName) {
            if (audioType.equalsIgnoreCase("mp3")) {
                System.out.println("Playing MP3 file: " + fileName);
            } else if (audioType.equalsIgnoreCase("vlc")) {
                adapter = new MediaAdapter();
                adapter.play(audioType, fileName);  // Delegate to adapter
            } else {
                System.out.println("Unsupported format: " + audioType);
            }
        }
    }
    public class Main {
        public void main(String[] args) {

            AudioPlayer player = new AudioPlayer();

            player.play("mp3", "song.mp3");  // Plays directly
            player.play("vlc", "video.vlc"); // Uses adapter
            player.play("avi", "movie.avi"); // Unsupported
        }
    }

}
