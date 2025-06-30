package StructuralDesignPattern;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * ✅ Definition:
 * The Proxy Pattern provides a surrogate or placeholder for another object to control access to it.
 *
 * 🎯 In Simple Words:
 * 👉 Proxy = Middleman.
 * It controls, restricts, or adds extra behavior when accessing the real object.
 *
 * 📦 Real-World Backend Example: API Rate Limiting Proxy
 * 🔔 Problem:
 * Imagine you are building an API Gateway that limits how many API requests a client can make.
 *
 * You want:
 *
 * To protect the real API service (heavy processing).
 *
 * To control access and limit the number of requests.
 *
 * 👉 This is a perfect use case for the Proxy Pattern.
 *
 * 💡 Real Backend Parallels:
 * Spring AOP Proxies (for transactions, security, logging)
 *
 * API Gateways (rate limiting, authentication proxies)
 *
 * Database Access Proxies (lazy loading)
 *
 * Security Proxies (authentication wrappers)
 *
 * ✅ Let’s Build It: API Rate Limiting Proxy Example
 *
 *
 */
public class Proxy {

    public class RealAPIService {

        public void fetchData(String client) {
            System.out.println("Fetching data for client: " + client);
        }
    }

    public class APIProxy {

        private RealAPIService realAPIService = new RealAPIService();
        private Map<String, Integer> clientRequestCount = new HashMap<>();
        private static final int MAX_REQUESTS = 3;

        public void fetchData(String client) {
            int requests = clientRequestCount.getOrDefault(client, 0);

            if (requests >= MAX_REQUESTS) {
                System.out.println("[PROXY] Request limit reached for client: " + client);
                return;
            }

            clientRequestCount.put(client, requests + 1);
            realAPIService.fetchData(client);
        }
    }

    public class Main {
        public void main(String[] args) {

            APIProxy proxy = new APIProxy();

            proxy.fetchData("ClientA");
            proxy.fetchData("ClientA");
            proxy.fetchData("ClientA");

            // This one should be blocked
            proxy.fetchData("ClientA");

            // Another client, starts fresh
            proxy.fetchData("ClientB");
        }
    }


}
