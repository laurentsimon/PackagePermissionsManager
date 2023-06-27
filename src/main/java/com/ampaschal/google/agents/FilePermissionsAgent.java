package com.ampaschal.google.agents;

import com.ampaschal.google.transformers.FilePermissionsTransformer;
import com.ampaschal.google.transformers.PermissionsTransformer;

import java.io.FileInputStream;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.net.URL;

public class FilePermissionsAgent {


    public static void premain(String agentArgs, Instrumentation inst) {

        System.out.println("Permissions Agent");

        inst.addTransformer(new FilePermissionsTransformer(), true);

        try {

            inst.retransformClasses(FileInputStream.class);
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(e);
        }


    }
}
