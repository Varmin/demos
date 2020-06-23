package com.varmin.libarchite.mvp;

import android.util.Log;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Locale;

/**
 * Created by HuangYang
 * on 2020-03-06  21：08.
 * 文件描述：
 */
public class ViewDynamicProxy<V extends _IBaseView, P extends _IBasePresenter> implements InvocationHandler {
    private String TAG;
    private P mBasePresenter;
    private V mBaseView;

    public ViewDynamicProxy(V baseView, P basePresenter) {
        this.mBaseView = baseView;
        this.mBasePresenter = basePresenter;
        TAG = mBasePresenter.getClass().getSimpleName();
    }

    @SuppressWarnings("unchecked")
    public V newProxyInstance() {
        return (V) Proxy.newProxyInstance(mBaseView.getClass().getClassLoader(),
                mBaseView.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if (mBasePresenter.isAttachView()) {
            result = method.invoke(mBaseView, args);
        } else {
            Log.d(TAG, String.format(Locale.CHINA, "动态代理：*********View已销毁，不执行调用%s********** ", method.getName()));
        }

        Log.d(TAG, "动态代理：proxy = " + proxy.getClass().getName() + "， method = " + method.getName() + ", result = " + result);
        return result;
    }
}
