package com.ampaschal.google.adapters;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

public class ASMifierAdapter extends ClassVisitor {

    PrintWriter pw = new PrintWriter(System.out);
    TraceClassVisitor tracer;

    public ASMifierAdapter(ClassVisitor cv) {
        super(Opcodes.ASM9, cv);

        tracer = new TraceClassVisitor(cv, new ASMifier(), pw);
        this.cv = tracer;
    }

    public void visitEnd(){
        tracer.visitEnd();
//        System.out.println(tracer.p.getText());
    }
}