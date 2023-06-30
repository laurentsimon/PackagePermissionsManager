package com.ampaschal.google.apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketApp {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Server is running. Waiting for a connection...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection accepted from: " + clientSocket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String receivedData = in.readLine();
            System.out.println("Received data: " + receivedData);

            out.println(receivedData);

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("An error occurred while running the server.");
            e.printStackTrace();
        }
    }

}
