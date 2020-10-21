package com.atoshi.opensdk.client;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

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
    public boolean checkAtoshiApp() {
        try {
            PackageInfo packInfo = this.mContext.getPackageManager().getPackageInfo("com.yzl.yuanzi", PackageManager.GET_CONFIGURATIONS);
            if (packInfo != null) {
                if (packInfo.getLongVersionCode() < 190) {
                    Toast.makeText(mContext, "请先更新Atoshi客户端", Toast.LENGTH_SHORT).show();
                    return false;
                }else {
                    return true;
                }
            }else{
                Toast.makeText(mContext, "请先安装Atoshi客户端", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void sendAuthReq(String clientId) {
        
    }
}
