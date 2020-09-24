package com.varmin.project;


import com.varmin.project.algorithm.TreeRealm;
import com.varmin.project.java.generic.GenericMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MyClass {
    public static void main(String[] args) {
        //System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        System.out.println("**********************************************JavaPro start**********************************************");
        algorithmRun();
        javaRun();
        kotlinRun();
        System.out.println("**********************************************JavaPro over**********************************************");
    }



    private static void kotlinRun() {
        System.out.println("=================Kotlin==================");
//        new MainKotlin().run();
//        new MainReflectKotlin().run();
    }

    private static void javaRun() {
        System.out.println("=================Java==================");
        //new MainReflect().run();
        new GenericMain().run();
    }

    private static void algorithmRun() {
        System.out.println("=================Algorithm==================");
        //new ArrayRealm().run();
//        new CharRealm().run();
//        new NodeRealm().run();
        new TreeRealm().run();

        System.out.println("MyClass.algorithmRun: "+ GameHelper.INSTANCE.getMLatestList());
        GameHelper.INSTANCE.put("1");
        GameHelper.INSTANCE.put("2");
        GameHelper.INSTANCE.put("3");
        GameHelper.INSTANCE.put("2");
        GameHelper.INSTANCE.put("0");
        System.out.println("MyClass.algorithmRun: "+ GameHelper.INSTANCE.getMLatestList());
    }


}
