package com.ampaschal.google.agents;

import com.ampaschal.google.transformers.PermissionsTransformer;

import java.io.FileInputStream;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.net.Socket;
import java.net.URL;

public class PermissionsAgent {


    public static void premain(String agentArgs, Instrumentation inst) {

        System.out.println("Permissions Agent");

        inst.addTransformer(new PermissionsTransformer(), true);

        try {
            inst.retransformClasses(FileInputStream.class, Socket.class, ProcessBuilder.class);
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(e);
        }


    }
}
