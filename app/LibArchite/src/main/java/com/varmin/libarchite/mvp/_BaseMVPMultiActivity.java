package com.varmin.libarchite.mvp;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangYang
 * on 2020-03-12  17：01.
 * 文件描述：
 */
public abstract class _BaseMVPMultiActivity<P extends _IBasePresenter> extends _BaseActivity {
    private List<P> mPresenterList;

    @Override
    protected void _onCreate(Bundle savedInstanceState) {
        mPresenterList = new ArrayList<>();
        createPresenters(mPresenterList);
        for (P p : mPresenterList) {
            p.attachView(this);
        }
    }

    protected abstract void createPresenters(List<P> presenters);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (P p : mPresenterList) {
            p.detachView();
        }
    }
}
