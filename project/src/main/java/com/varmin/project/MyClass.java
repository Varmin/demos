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

        test();
    }

    private static void test() {
        // todo Set
        HashSet<String> set = new HashSet<String>();


        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");

        for (int i = 3; i < 10; i++) {
            if(list.contains(String.valueOf(i))){
                boolean tmp = list.remove(String.valueOf(i));
                if(tmp) list.add(0, String.valueOf(i));
            }else {
                list.add(String.valueOf(i));
            }
        }

        System.out.println("MyClass.test"+ Arrays.toString(list.toArray()));
    }

}
