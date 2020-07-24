package com.varmin.project.base;

/**
 * created by HYY on 2020/7/15
 * description:
 */
public abstract class BaseImpl implements IBase{
    public void printLine(){
        System.out.println("--------------------------------");
    }
    public void printLine(String tag){
        System.out.println("--------------"+tag+"------------------");
    }
}
