package com.ampaschal.google.apps;

public class SocketConnectionApp {

    public static void main(String[] args) {
        performSocketConnection();
    }

    public static void performSocketConnection() {
        // Start the server in a separate thread
        Thread serverThread = new Thread(() -> {
            System.out.println("Starting the server...");
            ServerSocketApp.main(null);
        });
        serverThread.start();

        try {
            // Wait for a moment to ensure the server is running before starting the client
            Thread.sleep(1000);

            // Start the client
            System.out.println("Starting the client...");
            SocketClientApp.main(null);
        } catch (InterruptedException e) {
            System.out.println("An error occurred while running the main program.");
            e.printStackTrace();
        }

        // Wait for the server thread to complete
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            System.out.println("An error occurred while running the main program.");
            e.printStackTrace();
        }
    }

}
