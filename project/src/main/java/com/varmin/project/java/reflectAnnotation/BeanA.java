package com.varmin.project.java.reflectAnnotation;

/**
 * created by HYY on 2020/7/14
 * description:
 */
class BeanA extends Bean {
    public BeanA(){}
    public BeanA(String params){
        this.mTag = params;
    }
    public BeanA(int params, String params2){}
    private BeanA(String params, String params2, String params3){}

    public String puTag = "public_tag";
    public String puMethod(String str){
        return str;
    }

    protected String proTag = "protected_tag";
    protected String proMethod(String str){
        return str;
    }

    private String priTag = "private_tag";
    private String priMethod(String str){
        return str;
    }

    @Override
    public void run() {
        System.out.println("BeanA.run: ~~~~"+mTag);
    }
}
