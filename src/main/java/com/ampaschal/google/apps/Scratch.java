package com.ampaschal.google.apps;

public class Scratch {
    public static String getPackageName(String className) {
        int lastDotIndex = className.lastIndexOf(".");
        System.out.println("last index: " + lastDotIndex);
        int secondLastDotIndex = className.lastIndexOf(".", lastDotIndex - 1);
        System.out.println("Second last index: " + secondLastDotIndex);

        // If there are less than three dots in the class name, secondLastDotIndex will be -1
        // In that case, we return the substring up to lastDotIndex
        int endIndex = secondLastDotIndex != -1 ? secondLastDotIndex : lastDotIndex;

        return className.substring(0, endIndex);
    }

    public static String getPackageName2(String className) {
        String[] segments = className.split("\\.");
        int numSegments = Math.min(3, segments.length - 1);

        StringBuilder packageNameBuilder = new StringBuilder();
        for (int i = 0; i < numSegments - 1; i++) {
            packageNameBuilder.append(segments[i]).append(".");
        }
        packageNameBuilder.append(segments[numSegments - 1]);

        return packageNameBuilder.toString();
    }

    public static void main(String[] args) {
        String className1 = "com.group.package.subpackage.Classname";
        String packageName1 = getPackageName2(className1);
        System.out.println("Package Name 1: " + packageName1);

        String className2 = "com.package.Classname";
        String packageName2 = getPackageName2(className2);
        System.out.println("Package Name 2: " + packageName2);
    }


}
