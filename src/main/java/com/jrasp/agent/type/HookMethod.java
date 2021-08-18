package com.jrasp.agent.type;

import java.util.List;

public class HookMethod {

    private int id;

    private String className;

    private String methodName;

    private String methodDesc;

    private List<Keys> keys;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public List<Keys> getKeys() {
        return keys;
    }

    public void setKeys(List<Keys> keys) {
        this.keys = keys;
    }

    public HookMethod() {
    }

    public HookMethod(int id, String className, String methodName, String methodDesc, List<Keys> keys) {
        this.id = id;
        this.className = className;
        this.methodName = methodName;
        this.methodDesc = methodDesc;
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "HookMethod{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", methodDesc='" + methodDesc + '\'' +
                ", keys=" + keys +
                '}';
    }
}
