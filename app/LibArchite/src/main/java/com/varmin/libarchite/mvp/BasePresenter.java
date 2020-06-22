package com.varmin.libarchite.mvp;


import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by HuangYang
 * on 2020-02-28  14：44.
 * 文件描述：
 */
public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    protected String TAG = getClass().getSimpleName();

    /**
     * 动态代理类: 其它对象如需使用baseView，传入该实例
     * 使用动态代理：调用mView方法时，不再判断页面是否已经关闭，如果已经finish则不会调用委托类方法。
     *             注意：这里的mView指Activity实现的View接口代理，而不是Activity的代理。也就是说只在Presenter中可以不判断finish直接调用mView方法
     */
    protected V mView;
    /**
     * 真实view类
     */
    private V mBaseView;

    //----------------------------------IbaseView----------------------------------
    @Override
    public void attachView(V view) {
        this.mBaseView = view;
        this.mView = new ViewDynamicProxy<V, BasePresenter>(mBaseView, this).newProxyInstance();
        initPresenter();
        initModel();
    }

    @Override
    public void initPresenter() {}

    @Override
    public void initModel() {
        //this.mModel = new HttpModel();
    }


    @Override
    public void detachView() {
        this.mBaseView = null;
        //this.mModel = null;
    }

    @Override
    public boolean isAttachView() {
        return mBaseView != null;
    }

    @Override
    public void addSubscribe(Disposable disposable) {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public <T> boolean addRxBusSubscribe(Class<T> eventType, Consumer<T> act) {
        return false;
    }


}
