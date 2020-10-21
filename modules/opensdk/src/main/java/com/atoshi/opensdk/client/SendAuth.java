package com.atoshi.opensdk.client;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.atoshi.opensdk.IAuthReq;

/**
 * author：yang
 * created on：2020/10/20 17:04
 * description: 发送认证信息
 */
public class SendAuth implements IAuthReq {
    private final Context mContext;

    public SendAuth(Context context){
        this.mContext = context;
    }

    @Override
    public boolean isAtoshiInstalled() {
        try {
            PackageInfo packInfo = this.mContext.getPackageManager().getPackageInfo("com.yzl.yuanzi", PackageManager.GET_CONFIGURATIONS);
            return packInfo != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkAtoshiVersionUsable() {
        return false;
    }

    @Override
    public void sendAuthReq(String clientId) {


    }
}
