package com.varmin.modulelogin;

import android.app.Application;

/**
 * author：yang
 * created on：2020/10/22 16:43
 * description:
 */
public class AppLogin extends Application {
    public static boolean isInited = false;
    @Override
    public void onCreate() {
        super.onCreate();
        int a = 0;
        for (int i = 0; i < 1_000_000; i++) {
            a++;
        }
        isInited = true;
    }
}
