package com.atoshi.opensdk.atoshi;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.atoshi.opensdk.client.AuthReq;

/**
 * author：yang
 * created on：2020/10/21 15:58
 * description:
 */
public class AuthResp{
    private static final String TAG = "AuthResp";
    private final Activity mAct;
    private final IAuthResp mAuthResp;
    private String clientID;
    private String targetPackgeName;

    public AuthResp(Activity activity){
        this.mAct = activity;
        this.mAuthResp = (IAuthResp)mAct;
    }

    public String getClientID(){
        return clientID;
    }

    /**
     * 解析第三方App传递的信息
     */
    public void parseExtra() {
        Bundle bundle = mAct.getIntent().getExtras();
        if (bundle != null) {
            clientID = bundle.getString(AuthReq.EXTRA_CLIENT_ID);
            targetPackgeName = bundle.getString(AuthReq.EXTRA_PACKAGE_NAME);
            Log.d(TAG, "parseExtra: clientId = "+clientID);
        }
    }

    /**
     * 认证完成跳转第三方App
     * @plaram status 0失败，1成功
     * @param errMsg 0时设置
     * @param code 1时设置
     */
    public void sendCode(String status, String errMsg, String code){
        Log.d(TAG, "sendCode: code = "+ code);
        String targetClassName = null;
        try {
            targetClassName = mAct.getPackageManager()
                    .getApplicationInfo(targetPackgeName, PackageManager.GET_META_DATA)
                    .metaData.getString(AuthReq.META_THIRD_AUTH_ACTIVITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(targetPackgeName) || TextUtils.isEmpty(targetClassName)) {
            Toast.makeText(mAct, "No Target!", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG, "sendCode: "+ targetPackgeName+", "+ targetClassName);
        Intent intent = new Intent();
        intent.setClassName(targetPackgeName, targetClassName);
        intent.putExtra(AuthReq.EXTRA_CLIENT_ID, clientID);
        intent.putExtra(AuthReq.EXTRA_STATUS, status);
        intent.putExtra(AuthReq.EXTRA_ERRMSG, errMsg);
        // TODO: yang 2020/10/22 加密、解密：到也没太大必要
        intent.putExtra(AuthReq.EXTRA_AUTH_CODE, code);

        mAct.finish();
        mAct.startActivity(intent);
    }
}
