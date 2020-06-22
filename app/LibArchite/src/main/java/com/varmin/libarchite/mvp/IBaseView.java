package com.varmin.libarchite.mvp;

import android.content.Context;

/**
 * Created by HuangYang
 * on 2020-02-28  14：38.
 * 文件描述：todo 封装，有默认，可以填充任何第三方
 */
public interface IBaseView {
    /**
     * 显示加载框
     */
    void showLoading();

    /**
     * 隐藏加载框
     */
    void dismissLoading();

    /**
     * 错误数据
     */
    void onError(String errorMsg);

    /**
     * 提示信息：toast
     */
    void toast(String toast);
    void dialog(String content);
    void dialogCancelc();
    /**
     * 空数据
     */
    void onEmpty();

    /**
     * 上下文
     */
    Context getContext();
}
