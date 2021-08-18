package com.jrasp.agent.type;

import java.util.List;

public class Keys {

    private int index;

    private List<String> equals;

    private List<String> startWith;

    private List<String> endsWith;

    private List<String> contains;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setEquals(List<String> equals) {
        this.equals = equals;
    }

    public List<String> getEquals() {
        return equals;
    }

    public void setStartWith(List<String> startWith) {
        this.startWith = startWith;
    }

    public List<String> getStartWith() {
        return startWith;
    }

    public void setEndsWith(List<String> endsWith) {
        this.endsWith = endsWith;
    }

    public List<String> getEndsWith() {
        return endsWith;
    }

    public void setContains(List<String> contains) {
        this.contains = contains;
    }

    public List<String> getContains() {
        return contains;
    }

    public Keys() {
    }

    public Keys(int index, List<String> equals, List<String> startWith, List<String> endsWith, List<String> contains) {
        this.index = index;
        this.equals = equals;
        this.startWith = startWith;
        this.endsWith = endsWith;
        this.contains = contains;
    }

    @Override
    public String toString() {
        return "Keys{" +
                "index=" + index +
                ", equals=" + equals +
                ", startWith=" + startWith +
                ", endsWith=" + endsWith +
                ", contains=" + contains +
                '}';
    }
}
