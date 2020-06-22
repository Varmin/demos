package com.varmin.project;


//import com.varmin.project.kotlin.Main;

import com.varmin.project.base.Test;

public class MyClass {

    public static void main(String[] args) {
        //System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        System.out.println("=======================JavaPro start=======================");

        // TODO: by HY, 2020/6/22 不能找到Kotlin类
        //new Main().run();
        new Test().run();

        System.out.println("=======================JavaPro over=======================");
    }

}
