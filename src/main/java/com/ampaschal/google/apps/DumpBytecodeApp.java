package com.ampaschal.google.apps;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DumpBytecodeApp {

    public static void main(String[] args) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callerName = stackTrace[1].getClassName(); // 2 represents the index of the class calling the class's constructor

//        This check avoids a StackOverFLow error resulting from an infinite loop where loadClass method tries calling FileInputStream again
        if (!callerName.startsWith("jdk.internal.loader") && !callerName.startsWith("sun.misc.URLClassPath$FileLoader")) {
            try {
                Class<?> permManagerClass = ClassLoader.getSystemClassLoader().loadClass("com.ampaschal.google.PermissionsManager");
                Method logMethod = permManagerClass.getMethod("mockTest", int.class, int.class, String.class);
                String name = "filename";
                logMethod.invoke(null, 0, 0, new File(name).getAbsolutePath());
            } catch (IllegalAccessException | NoSuchMethodException | ClassNotFoundException e) {
                System.out.println("Exception thrown in instrumentation");
                System.out.println(e);
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();

                if (cause instanceof SecurityException) {
                    throw (SecurityException) cause;
                }
            }
        }
    }
}
