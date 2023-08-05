package com.ampaschal.google.transformers;

import com.ampaschal.google.PermissionClassVisitor;
import com.ampaschal.google.TestHelper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class PermissionsTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            if (className != null && (className.equals("java/io/FileInputStream") || className.equals("java/io/FileOutputStream") || className.equals("java/net/Socket") || className.equals("java/lang/ProcessBuilder"))) {
                // System.out.println("Rewriting class: " + className);
                ClassReader classReader = new ClassReader(classfileBuffer);
                ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
                PermissionClassVisitor permClassVisitor = new PermissionClassVisitor(classWriter, className);
                classReader.accept(permClassVisitor, ClassReader.EXPAND_FRAMES);

                return classWriter.toByteArray();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return classfileBuffer;
    }
}
