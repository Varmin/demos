package com.atoshi.opensdk.client;

import com.atoshi.opensdk.RespBean;

/**
 * author：yang
 * created on：2020/10/20 17:07
 * description:
 */
public interface IAuthReq {
    /**
     * 认证回调：在onCreate/onNewIntent中调用handleIntent
     */
    void onResp(RespBean bean);
}
