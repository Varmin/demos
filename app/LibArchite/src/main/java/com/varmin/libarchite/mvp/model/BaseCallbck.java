package com.varmin.libarchite.mvp.model;


import com.varmin.libarchite.mvp._IBaseView;

/**
 * Created by HuangYang
 * on 2020-03-06  22：26.
 * 文件描述：
 */
public abstract class BaseCallbck<T> implements Callback<T, Integer> {
    private final _IBaseView mView;

    protected BaseCallbck(_IBaseView view) {
        this.mView = view;
    }

    @Override
    public void pre() {
    }

    @Override
    public void progress(Integer integer) {
    }

    @Override
    public void error(String errorMsg) {
        mView.onError(errorMsg);
    }

    @Override
    public void complete() {
    }
}
