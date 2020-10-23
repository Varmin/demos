package com.atoshi.opensdk.client;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.atoshi.opensdk.RespBean;
import static com.atoshi.opensdk.Constants.*;

/**
 * author：yang
 * created on：2020/10/20 17:04
 * description: 三方App发送认证信息
 */
public class AuthReq{
    private static final String TAG = "AuthReq";
    private final Context mContext;
    private final String mClientId;

    public AuthReq(Context context, String clientId){
        this.mContext = context;
        this.mClientId = clientId;
    }

    /**
     * 检查Atoshi客户端
     * @return -1: 未安装，0：Atoshi版本号小，1：符合唤起条件
     */
    public int checkAtoshiApp() {
        try {
            PackageInfo packInfo = this.mContext.getPackageManager().getPackageInfo(PACKAGE_NAME, PackageManager.GET_CONFIGURATIONS);
            if (packInfo != null) {
                return packInfo.versionCode < MIN_VERSION_CODE ? 0 : 1;
            }else{
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 跳转Atoshi
     * 使用Intent直接打开Atoshi页面，防止使用action被恶意调起其它App
     */
    public void sendAuthReq() {
        if (checkAtoshiApp() > 0) {
            String targetClassName = null;
            try {
                targetClassName = mContext.getPackageManager()
                        .getApplicationInfo(PACKAGE_NAME, PackageManager.GET_META_DATA)
                        .metaData.getString(META_ATOSHI_AUTH_ACTIVITY);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            if (TextUtils.isEmpty(targetClassName))  return;
            Intent intent = new Intent();
            intent.setClassName(PACKAGE_NAME, targetClassName);
            intent.putExtra(EXTRA_PACKAGE_NAME, mContext.getPackageName());
            intent.putExtra(EXTRA_CLIENT_ID, mClientId);
            mContext.startActivity(intent);
        }else {
            Toast.makeText(mContext, "请确认Atoshi客户端", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 处理Atoshi返回过来的信息
     */
    public void handleIntent(Intent intent, IAuthReq authReq){
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            String clientId = bundle.getString(EXTRA_CLIENT_ID);
            if (TextUtils.equals(mClientId, clientId)) {
                RespBean bean = new RespBean();
                bean.status = bundle.getInt(EXTRA_STATUS);
                bean.errMsg = bundle.getString(EXTRA_ERR_MSG);
                bean.code = bundle.getString(EXTRA_AUTH_CODE);
                authReq.onResp(bean);
            }
        }
    }
}
