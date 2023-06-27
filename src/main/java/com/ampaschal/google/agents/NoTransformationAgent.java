package com.ampaschal.google.agents;

import com.ampaschal.google.transformers.PermissionsTransformer;

import java.io.FileInputStream;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.net.URL;

public class NoTransformationAgent {
    public static void premain(String agentArgs, Instrumentation inst) {

        System.out.println("No transformation Agent");

    }
}
