package com.jrasp.agent;

import com.jrasp.agent.asm.RaspClassVisitor;
import com.jrasp.agent.common.ClassFileHelper;
import com.jrasp.agent.script.AgentScript;
import com.jrasp.agent.type.HookClass;
import com.jrasp.agent.type.HookMethod;
import com.jrasp.agent.type.Keys;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rasptransformer implements ClassFileTransformer {

    // 目标类
    private Map<String, HookClass> hookClassMap = new HashMap<>(64);

    public void init() {
        try {
            Yaml yaml = new Yaml(new Constructor(HookClass.class));
            InputStream inputStream = this.getClass().getResourceAsStream("/class.yaml");
            for (Object object : yaml.loadAll(inputStream)) {
                HookClass hookClass = (HookClass) object;
                String className = hookClass.getName(); // hookClass=java/lang/String
                hookClassMap.put(className, hookClass);
                List<HookMethod> methods = hookClass.getMethods();
                for (int i = 0; i < methods.size(); i++) {
                    HookMethod hookMethod = methods.get(i);
                    String methodName = hookMethod.getMethodName();
                    String methodDesc = hookMethod.getMethodDesc();
                    List<Keys> keys = hookMethod.getKeys();
                    AgentScript.scriptMap.put(className + methodName + methodDesc, keys);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        // className=java/lang/String
        HookClass targetClass = hookClassMap.get(className);
        if (targetClass == null) return null;

        ClassReader reader = new ClassReader(classfileBuffer);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
        RaspClassVisitor myClassVisitor = new RaspClassVisitor(writer);

        myClassVisitor.setClassID(targetClass.getId());
        myClassVisitor.setClassName(className);

        List<HookMethod> methods = targetClass.getMethods();
        HashMap<String, HookMethod> methodMap = new HashMap<>();
        for (HookMethod hookMethod : methods) {
            hookMethod.setClassName(className);
            methodMap.put(hookMethod.getClassName() + hookMethod.getMethodName() + hookMethod.getMethodDesc(), hookMethod);
        }
        myClassVisitor.setMethodMap(methodMap);

        reader.accept(myClassVisitor, ClassReader.SKIP_FRAMES);
        byte[] bytes = writer.toByteArray();
        ClassFileHelper.dumpClass(bytes, className.replace("/", "_"));
        return bytes;
    }
}
