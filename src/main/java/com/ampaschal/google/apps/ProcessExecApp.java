package com.ampaschal.google.apps;

import com.ampaschal.google.PermissionsManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessExecApp {

    public static void main(String[] args) {

        PermissionsManager.setup();

        performShellExec();
    }

    public static void performShellExec() {
        try {
            // Command to execute
            String command = "ls -l";

            // Create the runtime instance
            Runtime runtime = Runtime.getRuntime();

            // Start the process
            Process process = runtime.exec(command);

            // Read the output from the process
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();

            System.out.println("Command executed, exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
