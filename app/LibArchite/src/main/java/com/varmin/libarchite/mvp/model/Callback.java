package com.varmin.libarchite.mvp.model;

/**
 * Created by HuangYang
 * on 2020-03-06  22：20.
 * 文件描述：
 */
public interface Callback<T, V> {
    void pre();

    void progress(V v);

    void success(T t);

    void error(String errorMsg);

    void complete();
}
