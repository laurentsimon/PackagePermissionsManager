package com.ampaschal.google.transformers;

import com.ampaschal.google.PermissionClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class FilePermissionsTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            if (className.equals("java/io/FileInputStream")) {
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
