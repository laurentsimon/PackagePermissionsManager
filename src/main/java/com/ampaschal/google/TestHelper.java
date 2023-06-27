package com.ampaschal.google;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestHelper {

    public static void writeToFile(byte[] byteArray, String filePath) {

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(byteArray);
            System.out.println("Byte array written to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the byte array to file: " + e.getMessage());
        }
    }
}
