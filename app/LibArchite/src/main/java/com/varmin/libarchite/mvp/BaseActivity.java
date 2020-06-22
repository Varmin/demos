package com.varmin.libarchite.mvp;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

/**
 * Created by HuangYang
 * on 2020-03-09  22：25.
 * 文件描述：
 */
public abstract class BaseActivity extends RxAppCompatActivity implements IBaseView {
    protected String TAG = "BaseActivity";
    protected BaseActivity mActivity;

    //----------------------------------生命周期----------------------------------

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        TAG = getClass().getSimpleName();
        this.mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        _onCreate(savedInstanceState);
        initData();
        initView();

        lifeMethodLog();
    }

    protected abstract int getLayoutId();
    protected void _onCreate(Bundle savedInstanceState) {}
    protected abstract void initData();
    protected abstract void initView();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //-------------------------------------IBaseView---------------------------------------

    @Override
    public void showLoading() {
        Toast.makeText(getContext(), "showLoading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        Toast.makeText(getContext(), "dismissLoading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmpty() {
        Toast.makeText(getContext(), "Empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toast(String toast) {
        onError(toast);
    }

    @Override
    public void dialog(String content) {
        onError(content);
    }

    @Override
    public void dialogCancelc() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    //----------------------------------方法----------------------------------

    /**
     * log开关配置
     */
    public boolean openLog() {
        return true;
    }


    protected void log(String log){
        lifeMethodLog(log);
    }
    protected void lifeMethodLog(){
        lifeMethodLog("");
    }
    protected void lifeMethodLog(String log){
        /*for (int i = 0; i < Thread.currentThread().getStackTrace().length; i++) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[i];
            Log.d(TAG, "lifeMethodLog: index = "+i+", fileName="+stackTraceElement.getFileName()+", ClassName="+stackTraceElement.getClassName()+", MethodName="+stackTraceElement.getMethodName()+", LineNumber="+stackTraceElement.getLineNumber());
        }*/
        //todo log配置、index根据页面结构会变
        String method = Thread.currentThread() .getStackTrace()[5].getMethodName();
        Log.d(TAG, method+"-----------------"+log);
    }

}
