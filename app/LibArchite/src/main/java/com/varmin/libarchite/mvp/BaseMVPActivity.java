package com.varmin.libarchite.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import javax.inject.Inject;

/**
 * Created by HuangYang
 * on 2020-02-28  15：51.
 */
public abstract class BaseMVPActivity<P extends IBasePresenter, Ac> extends BaseActivity {

    @Inject
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        inject(getActivityComponent());
        if (mPresenter != null) mPresenter.attachView(this);
        super.onCreate(savedInstanceState);
    }

/*    @Override
    protected void _onCreate(Bundle savedInstanceState) {
        inject(getActivityComponent());
        if (mPresenter != null) mPresenter.attachView(this);
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }


    protected abstract void inject(Ac activityComponent);
    protected abstract Ac getActivityComponent();

}

