package com.varmin.project;


import com.varmin.project.algorithm.ArrayRealm;

public class MyClass {
    public static void main(String[] args) {
        //System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        System.out.println("=======================JavaPro start=======================");
        //algorithmRun();
        kotlinRun();
        System.out.println("=======================JavaPro over=======================");
    }

    private static void algorithmRun() {
        new ArrayRealm().run();
    }

    private static void kotlinRun() {

    }


}
