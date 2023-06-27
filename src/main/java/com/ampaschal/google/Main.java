package com.ampaschal.google;

import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Main {
    public static void main(String[] args) {
        try {
            JarFile jarFile = new JarFile("/usr/local/google/home/pamusuo/Research/PackagePermissionsManager/target/PackagePermissionsManager-1.0-SNAPSHOT.jar");
            Manifest manifest = jarFile.getManifest();
            manifest.write(System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}