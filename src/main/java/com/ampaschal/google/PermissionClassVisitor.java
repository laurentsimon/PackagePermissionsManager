package com.ampaschal.google;

import com.ampaschal.google.adapters.AddFilePermissionAdapter;
import com.ampaschal.google.adapters.AddRuntimePermissionAdapter;
import com.ampaschal.google.adapters.AddSocketPermissionAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class PermissionClassVisitor extends ClassVisitor {

    String className;

    public PermissionClassVisitor(ClassVisitor classVisitor, String className) {
        super(Opcodes.ASM9, classVisitor);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv;
        mv = cv.visitMethod(access, name, descriptor, signature, exceptions);

        // For constructor calls, transform
        if (mv != null) {
//            I am removing the FileDescriptor constructor as having an fd implies the file has already been opened
            if (className.equals("java/io/FileInputStream") && name.equals("<init>") && !descriptor.equals("(Ljava/io/FileDescriptor;)V")) {
                mv = new AddFilePermissionAdapter(access, descriptor, mv);
            } else if (className.equals("java/net/Socket") && name.equals("connect") && descriptor.equals("(Ljava/net/SocketAddress;I)V")) {
                mv = new AddSocketPermissionAdapter(access, descriptor, mv);
            } else if (className.equals("java/lang/ProcessBuilder") && name.equals("start") && descriptor.equals("([Ljava/lang/ProcessBuilder$Redirect;)Ljava/lang/Process;")) {
                mv = new AddRuntimePermissionAdapter(access, descriptor, mv);
            }

        }

        return mv;

    }
}

