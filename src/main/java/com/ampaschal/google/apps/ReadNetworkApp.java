package com.ampaschal.google.apps;

import com.ampaschal.google.PermissionsManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadNetworkApp {

    public static void main(String[] args) {
        PermissionsManager.setup();
        performNetworkCount();
    }

    public static void performNetworkCount() {
        try {
            String url = "https://api.sampleapis.com/wines/reds"; // Replace with your desired URL

            // Create URL object
            URL apiUrl = new URL(url);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            // Count the number of objects in the JSON list
            int objectCount = countObjectsInJSONList(response.toString());
            System.out.println("Number of objects: " + objectCount);

            // Close the connection
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int countObjectsInJSONList(String json) {
        // Remove any leading/trailing whitespace characters
        json = json.trim();

        int count = 0;
        boolean inObject = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);

            if (c == '{') {
                if (!inObject) {
                    inObject = true;
                    count++;
                }
            } else if (c == '}') {
                inObject = false;
            }
        }

        return count;
    }
}
