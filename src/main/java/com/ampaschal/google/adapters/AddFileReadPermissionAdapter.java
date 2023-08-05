package com.ampaschal.google.adapters;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.LocalVariablesSorter;

public class AddFileReadPermissionAdapter extends LocalVariablesSorter {

    String methodDescriptor;

    public AddFileReadPermissionAdapter(int access, String desc, MethodVisitor methodVisitor) {
        super(Opcodes.ASM9, access, desc, methodVisitor);
        this.methodDescriptor = desc;
    }

    // visitCode is called once for every method. We add our instrumentation here so it appears at the top of the method
    @Override
    public void visitCode() {
        super.visitCode();

        MethodVisitor methodVisitor = mv;
        Label label0 = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/IllegalAccessException");
        methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/reflect/InvocationTargetException");
        methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/NoSuchMethodException");
        methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/ClassNotFoundException");


        Label label3 = new Label();
        methodVisitor.visitLabel(label3);
        methodVisitor.visitLineNumber(12, label3);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;", false);
        int lv = newLocal(Type.INT_TYPE);
        methodVisitor.visitVarInsn(Opcodes.ASTORE, lv);
        Label label4 = new Label();
        methodVisitor.visitLabel(label4);
        methodVisitor.visitLineNumber(13, label4);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, lv);
        methodVisitor.visitInsn(Opcodes.ICONST_2);
        methodVisitor.visitInsn(Opcodes.AALOAD);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StackTraceElement", "getClassName", "()Ljava/lang/String;", false);
        lv = newLocal(Type.INT_TYPE);
        methodVisitor.visitVarInsn(Opcodes.ASTORE, lv);



        Label label5 = new Label();
        methodVisitor.visitLabel(label5);
        methodVisitor.visitLineNumber(16, label5);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, lv);
        methodVisitor.visitLdcInsn("jdk.internal.loader");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", "startsWith", "(Ljava/lang/String;)Z", false);
        Label label6 = new Label();
        methodVisitor.visitJumpInsn(Opcodes.IFNE, label6);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, lv);
        methodVisitor.visitLdcInsn("sun.misc.URLClassPath$FileLoader");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", "startsWith", "(Ljava/lang/String;)Z", false);
        methodVisitor.visitJumpInsn(Opcodes.IFNE, label6);



        // Label label5 = new Label();
        // methodVisitor.visitLabel(label5);
        // methodVisitor.visitLineNumber(15, label5);
        // methodVisitor.visitVarInsn(Opcodes.ALOAD, lv);
        // methodVisitor.visitLdcInsn("jdk.internal.loader");
        // methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", "startsWith", "(Ljava/lang/String;)Z", false);
        // Label label6 = new Label();
        // methodVisitor.visitJumpInsn(Opcodes.IFNE, label6);





        methodVisitor.visitLabel(label0);
        methodVisitor.visitLineNumber(11, label0);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/ClassLoader", "getSystemClassLoader", "()Ljava/lang/ClassLoader;", false);
        methodVisitor.visitLdcInsn("com.ampaschal.google.PermissionsManager");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/ClassLoader", "loadClass", "(Ljava/lang/String;)Ljava/lang/Class;", false);
        lv = newLocal(Type.INT_TYPE);
        methodVisitor.visitVarInsn(Opcodes.ASTORE, lv);
        Label label7 = new Label();
        methodVisitor.visitLabel(label7);
        methodVisitor.visitLineNumber(12, label7);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, lv);
        methodVisitor.visitLdcInsn("checkPermission");
        methodVisitor.visitInsn(Opcodes.ICONST_3);
        methodVisitor.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Class");
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/Integer", "TYPE", "Ljava/lang/Class;");
        methodVisitor.visitInsn(Opcodes.AASTORE);
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/Integer", "TYPE", "Ljava/lang/Class;");
        methodVisitor.visitInsn(Opcodes.AASTORE);
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitInsn(Opcodes.ICONST_2);
        methodVisitor.visitLdcInsn(Type.getType("Ljava/lang/String;"));
        methodVisitor.visitInsn(Opcodes.AASTORE);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
        lv = newLocal(Type.INT_TYPE);
        methodVisitor.visitVarInsn(Opcodes.ASTORE, lv);
        Label label8 = new Label();
        methodVisitor.visitLabel(label8);
        methodVisitor.visitLineNumber(13, label8);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, lv);
        methodVisitor.visitInsn(Opcodes.ACONST_NULL);
        methodVisitor.visitInsn(Opcodes.ICONST_3);
        methodVisitor.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        methodVisitor.visitInsn(Opcodes.AASTORE);
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        methodVisitor.visitInsn(Opcodes.AASTORE);
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitInsn(Opcodes.ICONST_2);

        if (methodDescriptor.equals("(Ljava/lang/String;)V")) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, "java/io/File");
            methodVisitor.visitInsn(Opcodes.DUP);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/io/File", "<init>", "(Ljava/lang/String;)V", false);
        } else if (methodDescriptor.equals("(Ljava/io/File;)V")) {
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
        }

        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/File", "getAbsolutePath", "()Ljava/lang/String;", false);
        methodVisitor.visitInsn(Opcodes.AASTORE);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/reflect/Method", "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", false);

        methodVisitor.visitInsn(Opcodes.POP);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitLineNumber(16, label1);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label6);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLineNumber(14, label2);
        lv = newLocal(Type.INT_TYPE);
        methodVisitor.visitVarInsn(Opcodes.ASTORE, lv);
        Label label10 = new Label();
        methodVisitor.visitLabel(label10);
        methodVisitor.visitLineNumber(15, label10);
        methodVisitor.visitTypeInsn(Opcodes.NEW, "java/lang/RuntimeException");
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, lv);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "(Ljava/lang/Throwable;)V", false);
        methodVisitor.visitInsn(Opcodes.ATHROW);
        methodVisitor.visitLabel(label6);
        methodVisitor.visitLineNumber(17, label6);

    }

//    @Override
//    public void visitMaxs(int maxStack, int maxLocals) {
//        super.visitMaxs(maxStack + 3, maxLocals + 3);
//    }

    @Override
    public void visitEnd() {
        mv.visitEnd();
    }

}
