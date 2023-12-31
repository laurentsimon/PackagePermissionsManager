package com.ampaschal.google.agents.agent4;

import com.ampaschal.google.PermissionsManager;
import com.ampaschal.google.TestHelper;
import com.ampaschal.google.enums.ProfileKey;
import com.ampaschal.google.transformers.PermissionsTransformer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.net.Socket;
import java.net.URL;

public class PermissionsAgent {


    public static void premain(String agentArgs, Instrumentation inst) {

        // TestHelper.logTime(ProfileKey.AGENT_CALLED);

        System.out.println("Permissions Agent");

        PermissionsManager.setup();

        inst.addTransformer(new PermissionsTransformer(), true);

        try {
            // We retransform these classes because they are already loaded into the JVM
            inst.retransformClasses(FileInputStream.class, FileOutputStream.class);
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(e);
        }

        // TestHelper.logTime(ProfileKey.AGENT_EXITING);

    }
}
