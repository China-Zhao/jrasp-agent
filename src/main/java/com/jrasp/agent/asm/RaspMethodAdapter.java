package com.jrasp.agent.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

public class RaspMethodAdapter extends AdviceAdapter {

    private String className;

    private String methodName;

    private String methodDesc;

    protected RaspMethodAdapter(int api, MethodVisitor methodVisitor, int access, String name,
                                String descriptor, String className, String methodName, String methodDesc) {
        super(api, methodVisitor, access, name, descriptor);
        this.className = className;
        this.methodName = methodName;
        this.methodDesc = methodDesc;
    }

    @Override
    public void onMethodEnter() {
        Label continueLabel = new Label();
        loadArgArray();
        push(className);
        push(methodName);
        push(methodDesc);
        visitMethodInsn(INVOKESTATIC,
                "com/jrasp/agent/script/AgentScript",
                "check",
                "([Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z",
                false);
        visitJumpInsn(IFNE, continueLabel);
        // TODO 完善异常抛出告警，让用户知道发生了阻断
        throwException(Type.getType("Lcom/jrasp/agent/exception/RaspException;"), methodName + methodDesc);
        visitLabel(continueLabel);
    }
}
