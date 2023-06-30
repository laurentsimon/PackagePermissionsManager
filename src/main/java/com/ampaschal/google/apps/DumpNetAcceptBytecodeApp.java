package com.ampaschal.google.apps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.SocketImpl;
import java.net.URL;

public class DumpNetAcceptBytecodeApp {

    static SocketImpl impl;

    public static void main(String[] args) {

        try {


            Class<?> permManagerClass = ClassLoader.getSystemClassLoader().loadClass("com.ampaschal.google.PermissionsManager");
            Method logMethod = permManagerClass.getMethod("mockTest", int.class, int.class, String.class);
            String name = "impl.getInetAddress().getHostAddress()";
            logMethod.invoke(null, 1, 0, new URL(name).toString());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException |
                 MalformedURLException e) {
            System.out.println("Exception thrown in instrumentation");
        }
    }
}
