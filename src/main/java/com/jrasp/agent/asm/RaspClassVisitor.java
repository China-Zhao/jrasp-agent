package com.jrasp.agent.asm;

import com.jrasp.agent.type.HookMethod;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Map;

public class RaspClassVisitor extends ClassVisitor implements Opcodes {

    private int classID;

    private String className;

    private Map<String, HookMethod> methodMap;

    public RaspClassVisitor(ClassVisitor cv) {
        super(ASM9, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        HookMethod hookMethod = methodMap.get(className+name + desc);
        if (hookMethod == null) {
            return mv;
        }
        return new RaspMethodAdapter(ASM9, mv, access, name, desc, className,name,desc);
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, HookMethod> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<String, HookMethod> methodMap) {
        this.methodMap = methodMap;
    }
}
