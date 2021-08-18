package com.jrasp.agent.type;

import java.util.List;

public class HookClass {

    private int id;

    private String name;

    private List<HookMethod> methods;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<HookMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<HookMethod> methods) {
        this.methods = methods;
    }

    public HookClass() {
    }

    public HookClass(int id, String name, List<HookMethod> methods) {
        this.id = id;
        this.name = name;
        this.methods = methods;
    }

    @Override
    public String toString() {
        return "HookClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", methods=" + methods +
                '}';
    }
}
