package com.varmin.project.java.reflectAnnotation;


/**
 * created by HYY on 2020/7/16
 * description:
 */
class BeanB extends Bean {
    public BeanB(String params){
        this.mTag = params;
    }
    @Override
    public void run() {
        System.out.println("BeanB.run: ~~~~~"+mTag);
    }
}
