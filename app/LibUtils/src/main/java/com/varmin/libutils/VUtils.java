package com.varmin.libutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.TypedValue;

import org.w3c.dom.Text;

/**
 * Created by HuangYang
 * on 2019-05-28  22:59.
 * 文件描述：常用工具类
 */
public class VUtils {
    //--------------------------startActivity--------------------------
    public static void startAct(Context context, Class<Activity> act){
        Intent intent = new Intent(context, act);
        context.startActivity(intent);
    }
    public static void startActAndFinish(Context context, Class<Activity> act){
        startAct(context, act);
        ((Activity)context).finish();
    }
    //--------------------------string--------------------------
    public static boolean isEmptyOrNull(String content){
        return TextUtils.isEmpty(content)
                || TextUtils.equals("null", content)
                || TextUtils.equals("undefined", content)
                || TextUtils.equals("NaN", content);
    }
    //--------------------------display--------------------------

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     */
    public static int sp2px(Context context, float spVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spVal,context.getResources().getDisplayMetrics());
    }

    //--------------------------package info--------------------------

    /**
     * 获取versionCode
     * @throws PackageManager.NameNotFoundException
     */
    public static int getVersionCode(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
    }

    /**
     * 获取versionName
     * @throws PackageManager.NameNotFoundException
     */
    public static String getVersionName(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    }
}
