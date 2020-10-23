package com.atoshi.opensdk.atoshi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import static com.atoshi.opensdk.Constants.*;

/**
 * author：yang
 * created on：2020/10/21 15:58
 * description:
 */
public class AuthResp{
    private static final String TAG = "AuthResp";
    private final Context mContext;
    private String clientID;
    private String targetPackgeName;

    public AuthResp(Context context){
        this.mContext = context;
    }

    public String getClientID(){
        return clientID;
    }

    /**
     * 解析第三方App传递的信息
     */
    public void handleIntent(Intent intent, IAuthResp resp) {
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            clientID = bundle.getString(EXTRA_CLIENT_ID);
            targetPackgeName = bundle.getString(EXTRA_PACKAGE_NAME);
            resp.getThirdAppInfo(clientID);
            Log.d(TAG, "parseExtra: clientId = "+clientID);
        }
    }

    /**
     * 认证完成跳转第三方App
     * @plaram status 0失败，1成功
     * @param errMsg 0时设置
     * @param code 1时设置
     */
    public void sendCode(int status, String errMsg, String code){
        Log.d(TAG, "sendCode: code = "+ code);
        String targetClassName = null;
        try {
            targetClassName = mContext.getPackageManager()
                    .getApplicationInfo(targetPackgeName, PackageManager.GET_META_DATA)
                    .metaData.getString(META_THIRD_AUTH_ACTIVITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(targetPackgeName) || TextUtils.isEmpty(targetClassName)) {
            Toast.makeText(mContext, "No Target!", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG, "sendCode: "+ targetPackgeName+", "+ targetClassName);
        Intent intent = new Intent();
        intent.setClassName(targetPackgeName, targetClassName);
        intent.putExtra(EXTRA_CLIENT_ID, clientID);
        intent.putExtra(EXTRA_STATUS, status);
        intent.putExtra(EXTRA_ERR_MSG, errMsg);
        // TODO: yang 2020/10/22 加密、解密：到也没太大必要
        intent.putExtra(EXTRA_AUTH_CODE, code);
        mContext.startActivity(intent);
        ((Activity)mContext).finish();
    }
}
