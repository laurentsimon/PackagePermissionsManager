package com.ampaschal.google;

import com.ampaschal.google.enums.ResourceOp;
import com.ampaschal.google.enums.ResourceType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PermissionsManager {

    private static Map<String, PermissionObject> permissionObjectMap = new HashMap<>();

    public static void log() {
        System.out.println("Permissions check will be done here");

    }

    public static void mockTest(int resourceType, int resourceOp, String resourceItem) {
        System.out.println("Substituting permission check " + resourceItem + resourceType + resourceOp);
    }

    public static void setup() {
//        Set the permissions object
        try {
            parseAndSetPermissionsObject();
        } catch (IOException e) {
            System.out.println("Exception thrown");
            throw new RuntimeException(e);
        }
    }

    private static void parseAndSetPermissionsObject() throws IOException {

        String permissionsFilePath = "/usr/local/google/home/pamusuo/Research/PackagePermissionsManager/src/main/java/com/ampaschal/google/permfiles/deny-fs-package.json";

        File permissionsFile = new File(permissionsFilePath);

        Map<String, PermissionObject> permMap = new ObjectMapper().readValue(permissionsFile, new TypeReference<>() {});

        if (permMap != null && !permMap.isEmpty()) {
            permissionObjectMap.putAll(permMap);
        }
    }

    private static String getSubjectPath() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        String currentClass = stackTrace[1].getClassName();

//        Return the first non-java class in the stackstrace
//        I want to skip the containing class of this method as it clearly can't be the subject
        for (StackTraceElement element: stackTrace) {
            String elementClassName = element.getClassName();
            if (elementClassName.startsWith("java") || elementClassName.startsWith("jdk") || elementClassName.startsWith("sun") || elementClassName.equals(currentClass)) {
                continue;
            }
            return elementClassName;
        }
        return null;
    }

    private static Set<String> getSubjectPaths() {
//        I used a set to avoid repeated entries. This will reduce the overhead when walking the list
        Set<String> subjectPaths = new HashSet<>();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        String currentClass = stackTrace[1].getClassName();

//        Return the first non-java class in the stackstrace
//        I want to skip the containing class of this method as it clearly can't be the subject
        for (StackTraceElement element: stackTrace) {
            String elementClassName = element.getClassName();
            if (elementClassName.startsWith("java") || elementClassName.startsWith("jdk") || elementClassName.startsWith("sun") || elementClassName.equals(currentClass)) {
                continue;
            }
            subjectPaths.add(elementClassName);
        }
        return subjectPaths;
    }

    public static void checkPermission(int resourceTypeInt, int resourceOpInt, String resourceItem) {

        System.out.println("Checking permissions: " + ResourceType.getResourceType(resourceTypeInt));

//        I would have first returned true if the permissionsObject is null, but I am assuming instrumentations are done
//        only if the permissions file is present

        ResourceType resourceType = ResourceType.getResourceType(resourceTypeInt);

        ResourceOp resourceOp = ResourceOp.getResourceOp(resourceOpInt);

        if (resourceType == null || resourceOp == null) {
            throw new SecurityException("Invalid Permission Request");
        }

        Set<String> subjectPaths = getSubjectPaths();

        if (subjectPaths.isEmpty()) {
            return;
        }

//        Check the Permissions cache if access is permitted
        Boolean cachedPermission = checkPermissionCache(subjectPaths, resourceType, resourceOp, resourceItem);

        if (cachedPermission != null) {
            if (cachedPermission) {
                return;
            } else {
                throw new SecurityException("File read " + resourceItem + " not permitted");
            }
        }


//        Get the list of permission objects from the stack trace
        Set<PermissionObject> permissionObjects = getPermissions(subjectPaths);

        if (permissionObjects.isEmpty()) {
            return;
        }

//        We confirm each package in the stacktrace has the necessary permissions
        for (PermissionObject permissionObject: permissionObjects) {
            boolean permitted = performPermissionCheck(permissionObject, resourceType, resourceOp, resourceItem);

            if (!permitted) {
                throw new SecurityException("Access to " + resourceItem + " not permitted");
            }
        }

    }

    private static boolean performPermissionCheck(PermissionObject permissionObject, ResourceType resourceType, ResourceOp resourceOp, String resourceItem) {

//        Permission checking starts from the lowest granularity
        if (resourceType == ResourceType.FS) {
            if (permissionObject.getAllowedPaths().contains(resourceItem)) {
                return true;
            } else if (permissionObject.getDeniedPaths().contains(resourceItem)) {
                return false;
            } else if ((resourceOp == ResourceOp.READ && permissionObject.isFsRead()) || (resourceOp == ResourceOp.WRITE && permissionObject.isFsWrite())) {
                return true;
            } else {
                return permissionObject.isFs();
            }
        } else if (resourceType == ResourceType.Net) {
            if (permissionObject.getAllowedUrls().contains(resourceItem)) {
                return true;
            } else if (permissionObject.getDeniedUrls().contains(resourceItem)) {
                return false;
            } else {
                return permissionObject.isNet();
            }
        } else if (resourceType == ResourceType.RUNTIME) {
            if (permissionObject.getAllowedCommands().contains(resourceItem)) {
                return true;
            } else if (permissionObject.getDeniedCommands().contains(resourceItem)) {
                return false;
            } else if (resourceOp == ResourceOp.EXECUTE && permissionObject.isRuntimeExec()) {
                return true;
            } else {
                return permissionObject.isRuntime();
            }
        }

        return true;
    }

    private static String findClosestPackageName(String subjectPath) {
        String closestPackageName = null;
        int longestMatch = 0;

        for (String packageName: permissionObjectMap.keySet()) {
            if (subjectPath.startsWith(packageName) && packageName.length() > longestMatch) {
                closestPackageName = packageName;
                longestMatch = packageName.length();
            }
        }

        return closestPackageName;

    }

    private static Set<PermissionObject> getPermissions(Set<String> subjectPaths) {

        Set<PermissionObject> permissionObjects = new HashSet<>();

        for (String path: subjectPaths) {
            String packageName = findClosestPackageName(path);

            if (packageName != null) {
                PermissionObject permObject = permissionObjectMap.get(packageName);
                permissionObjects.add(permObject);
            }
        }

        return permissionObjects;

    }

    private static Boolean checkPermissionCache(Set<String> subjectPaths, ResourceType resourceType, ResourceOp resourceOp, String resourceItem) {

        return null;
    }

}
