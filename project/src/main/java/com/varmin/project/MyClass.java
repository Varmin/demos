package com.varmin.project;


import com.varmin.project.algorithm.ArrayRealm;
import com.varmin.project.java.reflectAnnotation.MainReflect;
import com.varmin.project.kotlin.MainKotlin;

public class MyClass {
    public static void main(String[] args) {
        //System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        System.out.println("=======================JavaPro start=======================");
        algorithmRun();
//        javaRun();
//        kotlinRun();
        System.out.println("=======================JavaPro over=======================");
    }



    private static void kotlinRun() {
        System.out.println("=================Kotlin==================");
        new MainKotlin().run();
    }

    private static void javaRun() {
        System.out.println("=================Java==================");
        new MainReflect().run();
    }

    private static void algorithmRun() {
        System.out.println("=================Algorithm==================");
        new ArrayRealm().run();
    }

}
