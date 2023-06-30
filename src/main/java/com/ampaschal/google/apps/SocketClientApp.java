package com.ampaschal.google.apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientApp {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8888)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String dataToSend = "Hello, Server!";
            out.println(dataToSend);

            String receivedData = in.readLine();
            System.out.println("Received data: " + receivedData);

            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("An error occurred while running the client.");
            e.printStackTrace();
        }
    }
}
