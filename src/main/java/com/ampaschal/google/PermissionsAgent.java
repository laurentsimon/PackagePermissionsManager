package com.ampaschal.google;

import com.ampaschal.google.transformers.PermissionsTransformer;

import java.io.FileInputStream;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.net.URL;

public class PermissionsAgent {


    public static void premain(String agentArgs, Instrumentation inst) {

        inst.addTransformer(new PermissionsTransformer(), true);

        try {

            inst.retransformClasses(FileInputStream.class, URL.class);
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(e);
        }


    }
}
