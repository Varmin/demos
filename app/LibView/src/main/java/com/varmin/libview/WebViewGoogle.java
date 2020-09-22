package com.varmin.libview;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.webkit.WebView;

/**
 * author：yang
 * created on：2020/8/31 17:06
 * description: webview google chrome
 */
public class WebViewGoogle extends WebView {
    public WebViewGoogle(Context context) {
        super(Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 22 ? context.createConfigurationContext(new Configuration()) : context);
//        super(context);
        System.out.println("WebViewGoogle.WebViewGoogle: "+ Build.VERSION.SDK_INT);
    }
}
