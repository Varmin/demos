package com.varmin.project.java.reflectAnnotation;

import com.varmin.project.base.IBase;

/**
 * created by HYY on 2020/7/14
 * description:
 */
abstract class Bean implements IBase {
    protected String mTag;

    public Bean(){}
    public Bean(int params){}
    public Bean(int paramsInt, String params, String params2, String params3){}

    public String _puTag = "_public_tag";
    public String _puMethod(String str){
        return "_"+str;
    }

    protected String _proTag = "_protected_tag";
    protected String _proMethod(String str){
        return "_"+str;
    }

    private String _priTag = "_private_tag";
    private String _priMethod(String str){
        return "_"+str;
    }
}
