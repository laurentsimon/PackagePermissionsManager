package com.ampaschal.google.apps;


import com.ampaschal.google.PermissionsManager;
import com.ampaschal.google.TestHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCountApp {
    public static void main(String[] args) {

        TestHelper.logTime("main");

        PermissionsManager.setup();

        String fileName = "/usr/local/google/home/pamusuo/Research/PackagePermissionsManager/src/main/java/com/ampaschal/google/test.txt";

        try {
            int wordCount = countWords(fileName);
            System.out.println("Number of words in the file: " + wordCount);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        TestHelper.logTime("main-end");
    }

    public static int countWords(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int wordCount = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");
            wordCount += words.length;
        }

        reader.close();
        return wordCount;

    }
}
