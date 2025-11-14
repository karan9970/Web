import java.net.*;
import java.io.*;
import java.util.Scanner;

class IP {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter the name of website (hostname or full URL): ");
            String web = sc.nextLine().trim();

            if (web.isEmpty()) {
                System.out.println("No input provided. Exiting.");
                return;
            }

            String host = web;
            // If input looks like a URL or contains a path, try to parse and extract host.
            if (web.contains("://") || web.contains("/")) {
                try {
                    URL url = web.contains("://") ? new URL(web) : new URL("http://" + web);
                    host = url.getHost();
                    if (host == null || host.isEmpty()) {
                        // Fall back to original input if parsing produced empty host
                        host = web;
                    }
                } catch (MalformedURLException e) {
                    // Fallback: treat the whole input as a hostname
                    System.out.println("Warning: couldn't parse input as URL, treating it as hostname.");
                    host = web;
                }
            }

            // Remove surrounding brackets for IPv6 literals like [::1]
            if (host.startsWith("[") && host.endsWith("]")) {
                host = host.substring(1, host.length() - 1);
            }

            try {
                InetAddress addr = InetAddress.getByName(host);
                System.out.println("Resolved host: " + host);
                System.out.println("IP address: " + addr.getHostAddress());
            } catch (UnknownHostException e) {
                System.out.println("Could not resolve host '" + host + "': " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e);
        }
    }
}
