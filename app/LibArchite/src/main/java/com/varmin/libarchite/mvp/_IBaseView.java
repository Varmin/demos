package com.varmin.libarchite.mvp;

import android.content.Context;

/**
 * Created by HuangYang
 * on 2020-02-28  14：38.
 * 文件描述：
 */
public interface _IBaseView {
    /**
     * 加载框
     */
    void showLoading();
    void showLoading(String content);
    void dismissLoading();

    /**
     * 提示信息：toast
     */
    void toast(String toast);

    /**
     * 错误数据
     */
    void onError(String errorMsg);

    /**
     * 上下文
     */
    Context getContext();
}
