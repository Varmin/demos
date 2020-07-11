package com.varmin.demo;

import android.view.View;

/**
 * created by HYY on 2020/7/6
 * description:
 */
class Test {
    public Test(Runnable runnable){}
    public Test setRunnable(Runnable runnable){
        return this;
    }
//    public Test(View.OnClickListener listener){}
//    public void setClickListener(View.OnClickListener listener){}
    public void setRunnable(View.OnClickListener listener){}
}
