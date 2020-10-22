package com.atoshi.opensdk.client;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.atoshi.opensdk.AuthBean;

/**
 * author：yang
 * created on：2020/10/20 17:04
 * description: 发送认证信息
 */
public class AuthReq{
    private static final String TAG = "AuthReq";
    private final Context mContext;

    public String ALERT_NEED_INSTALL_ATOSHI = "请先安装Atoshi客户端";
    public String ALERT_UPDATE_INSTALL_ATOSHI = "请先更新Atoshi客户端";

    private final String PACKAGE_NAME = "com.varmin.modulelogin";
    private String TARGET_CLASS_NAME = null;
    private final String META_ATOSHI_AUTH_ACTIVITY = "META_ATOSHI_AUTH_ACTIVITY";
    public static final String META_THIRD_AUTH_ACTIVITY = "META_THIRD_AUTH_ACTIVITY";
    private final int MIN_VERSION_CODE = 90;

    public static final String CLIENT_ID = "1234567890";
    public static final String EXTRA_PACKAGE_NAME = "extra_package_name";
    public static final String EXTRA_CLIENT_ID = "extra_client_id";
    public static final String EXTRA_STATUS = "extra_status";
    public static final String EXTRA_ERRMSG = "extra_errmsg";
    public static final String EXTRA_AUTH_CODE = "extra_auth_code";

    public AuthReq(Context context){
        this.mContext = context;
    }

    public boolean checkAtoshiApp() {
        try {
            PackageInfo packInfo = this.mContext.getPackageManager().getPackageInfo(PACKAGE_NAME, PackageManager.GET_CONFIGURATIONS);
            if (packInfo != null) {
                if (packInfo.versionCode < MIN_VERSION_CODE) {
                    Toast.makeText(mContext, ALERT_UPDATE_INSTALL_ATOSHI, Toast.LENGTH_SHORT).show();
                    return false;
                }else {
                    return true;
                }
            }else{
                Toast.makeText(mContext, ALERT_NEED_INSTALL_ATOSHI, Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(mContext, ALERT_NEED_INSTALL_ATOSHI, Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //todo 分别判断：安装、版本； 提示信息等

    /**
     * 使用Intent直接打开Atoshi页面，防止使用action被恶意调起其它App
     * @param clientId 客户端id，Atoshi服务提供
     */
    public void sendAuthReq(String clientId) {
        if (checkAtoshiApp()) {//todo 不应做过多提示，或让其复写
            try {
                TARGET_CLASS_NAME = mContext.getPackageManager()
                        .getApplicationInfo(PACKAGE_NAME, PackageManager.GET_META_DATA)
                        .metaData.getString(META_ATOSHI_AUTH_ACTIVITY);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(TARGET_CLASS_NAME)) {
                return;
            }
            Intent intent = new Intent();
            intent.setClassName(PACKAGE_NAME, TARGET_CLASS_NAME);
            intent.putExtra(EXTRA_CLIENT_ID, clientId);
            intent.putExtra(EXTRA_PACKAGE_NAME, mContext.getPackageName());
            mContext.startActivity(intent);
        }
    }

    public void handleIntent(Intent intent, IAuthReq authReq){
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            String clientId = bundle.getString(EXTRA_CLIENT_ID, null);
            if (TextUtils.equals(CLIENT_ID, clientId)) {
                AuthBean bean = new AuthBean();
                bean.status = bundle.getString(EXTRA_STATUS);
                bean.errMsg = bundle.getString(EXTRA_ERRMSG);
                bean.code = bundle.getString(EXTRA_AUTH_CODE);
                authReq.onResp(bean);
            }
        }
    }
}
