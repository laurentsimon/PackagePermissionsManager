package com.ampaschal.google;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class PermissionMethodAdapter extends MethodVisitor {

    protected PermissionMethodAdapter(int api) {
        super(api);
    }

    protected PermissionMethodAdapter(MethodVisitor methodVisitor) {
        super(Opcodes.ASM9, methodVisitor);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
//        If it's invoking constructor of FileReader, we substitute with PermFileReader
        if (owner.equals("java/io/FileReader") && name.equals("<init>")) {
            mv.visitMethodInsn(opcode, "com/ampaschal/asm/filepractice/permissions/PermFileReader", name, descriptor, isInterface);
        } else {
            mv.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        }

    }

    @Override
    public void visitTypeInsn(int opcode, String type) {

        if (type.equals("java/io/FileReader")) {
            mv.visitTypeInsn(opcode, "com/ampaschal/asm/filepractice/permissions/PermFileReader");

        } else {
            mv.visitTypeInsn(opcode, type);
        }
    }
}
