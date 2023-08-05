package com.ampaschal.google.adapters;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.LocalVariablesSorter;

public class AddSocketPermissionAdapter extends LocalVariablesSorter {

    public AddSocketPermissionAdapter(int access, String descriptor, MethodVisitor methodVisitor) {
        super(Opcodes.ASM9, access, descriptor, methodVisitor);
    }

    @Override
    public void visitCode() {
        super.visitCode();

        {
            MethodVisitor methodVisitor = mv;
            Label label0 = new Label();
            Label label1 = new Label();
            Label label2 = new Label();
            Label label12 = new Label();
            methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/IllegalAccessException");
            methodVisitor.visitTryCatchBlock(label0, label1, label12, "java/lang/reflect/InvocationTargetException");
            methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/NoSuchMethodException");
            methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/ClassNotFoundException");
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(14, label0);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/ClassLoader", "getSystemClassLoader", "()Ljava/lang/ClassLoader;", false);
            methodVisitor.visitLdcInsn("com.ampaschal.google.PermissionsManager");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/ClassLoader", "loadClass", "(Ljava/lang/String;)Ljava/lang/Class;", false);
            int lv = newLocal(Type.INT_TYPE);
            methodVisitor.visitVarInsn(Opcodes.ASTORE, lv);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(15, label3);
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
            Label label5 = new Label();
            methodVisitor.visitLabel(label5);
            methodVisitor.visitLineNumber(17, label5);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, lv);
            methodVisitor.visitInsn(Opcodes.ACONST_NULL);
            methodVisitor.visitInsn(Opcodes.ICONST_3);
            methodVisitor.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
            methodVisitor.visitInsn(Opcodes.DUP);
            methodVisitor.visitInsn(Opcodes.ICONST_0);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            methodVisitor.visitInsn(Opcodes.AASTORE);
            methodVisitor.visitInsn(Opcodes.DUP);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitInsn(Opcodes.ICONST_3);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            methodVisitor.visitInsn(Opcodes.AASTORE);
            methodVisitor.visitInsn(Opcodes.DUP);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, "java/net/InetSocketAddress");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/net/InetSocketAddress", "getHostName", "()Ljava/lang/String;", false);
            methodVisitor.visitInsn(Opcodes.AASTORE);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/reflect/Method", "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", false);
            methodVisitor.visitInsn(Opcodes.POP);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(21, label1);
            Label label6 = new Label();
            methodVisitor.visitJumpInsn(Opcodes.GOTO, label6);
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(18, label2);
            Label label7 = new Label();
            methodVisitor.visitLabel(label7);
            methodVisitor.visitLineNumber(20, label7);
            methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitLdcInsn("Network Access Permission not granted");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);


            Label label13 = new Label();
            methodVisitor.visitLabel(label13);
            methodVisitor.visitLineNumber(31, label13);
            methodVisitor.visitJumpInsn(Opcodes.GOTO, label6);
            methodVisitor.visitLabel(label12);
            methodVisitor.visitLineNumber(25, label12);
            lv = newLocal(Type.INT_TYPE);
            methodVisitor.visitVarInsn(Opcodes.ASTORE, lv);
            Label label14 = new Label();
            methodVisitor.visitLabel(label14);
            methodVisitor.visitLineNumber(26, label14);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, lv);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/reflect/InvocationTargetException", "getCause", "()Ljava/lang/Throwable;", false);
            lv = newLocal(Type.INT_TYPE);
            methodVisitor.visitVarInsn(Opcodes.ASTORE, lv);
            Label label15 = new Label();
            methodVisitor.visitLabel(label15);
            methodVisitor.visitLineNumber(28, label15);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, lv);
            methodVisitor.visitTypeInsn(Opcodes.INSTANCEOF, "java/lang/SecurityException");
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label6);
            Label label16 = new Label();
            methodVisitor.visitLabel(label16);
            methodVisitor.visitLineNumber(29, label16);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, lv);
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/SecurityException");
            methodVisitor.visitInsn(Opcodes.ATHROW);

            methodVisitor.visitLabel(label6);
        }
    }
}
