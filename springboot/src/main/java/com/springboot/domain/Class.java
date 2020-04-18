package com.springboot.domain;

public class Class {
    private  int cid;
    private String name;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Class{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                '}';
    }
}
