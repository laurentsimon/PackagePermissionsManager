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
            if (className.equals("java/io/FileInputStream") || className.equals("java/net/Socket") || className.equals("java/lang/ProcessBuilder")) {
                System.out.println("ProcessBuilder getting transformed");

                ClassReader classReader = new ClassReader(classfileBuffer);
                ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
                TraceClassVisitor traceClassVisitor = new TraceClassVisitor(classWriter, new PrintWriter(System.out));
                PermissionClassVisitor permClassVisitor = new PermissionClassVisitor(traceClassVisitor, className);
                classReader.accept(permClassVisitor, ClassReader.EXPAND_FRAMES);

                byte[] transformedClass = classWriter.toByteArray();

                if (className.equals("java/io/FileInputStream")) {
                    TestHelper.writeToFile(transformedClass, "/usr/local/google/home/pamusuo/Research/ByteBuddyTutorial/ByteBuddyTutorial/src/main/java/com/ampaschal/asm/filepractice/TFileInputStream.class");
                }
                return transformedClass;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return classfileBuffer;
    }
}
