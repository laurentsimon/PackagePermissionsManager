package com.ampaschal.google.apps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DumpExecBytecodeApp {

    private static List<String> command = new ArrayList<>();

    public static void main(String[] args) {

        try {
            String name = command.get(0);
            Class<?> permManagerClass = ClassLoader.getSystemClassLoader().loadClass("com.ampaschal.google.PermissionsManager");
            Method logMethod = permManagerClass.getMethod("mockTest", int.class, int.class, String.class);
            logMethod.invoke(null, 2, 2, name);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            System.out.println("Exception thrown in instrumentation");
        }


    }
}
