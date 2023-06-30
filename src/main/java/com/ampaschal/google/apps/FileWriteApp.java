package com.ampaschal.google.apps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteApp {

    public static void main(String[] args) {
        performFileWriteOperation();
    }

    public static void performFileWriteOperation() {
        String filename = "src/main/java/com/ampaschal/google/output.txt";
        String data = "Hello, World\nTesting file writes!";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data);
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
