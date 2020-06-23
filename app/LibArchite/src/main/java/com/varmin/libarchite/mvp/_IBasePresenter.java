package com.varmin.libarchite.mvp;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by HuangYang
 * on 2020-02-29  16：28.
 * 文件描述：
 */
public interface _IBasePresenter<V extends _IBaseView> {
    void attachView(V view);
    void initPresenter();
    void initModel();
    void detachView();
    boolean isAttachView();

    void addSubscribe(Disposable disposable);
    void unSubscribe();

    <T> boolean addRxBusSubscribe(Class<T> eventType, Consumer<T> act);
}
