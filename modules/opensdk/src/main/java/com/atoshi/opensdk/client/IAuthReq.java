package com.atoshi.opensdk.client;

import com.atoshi.opensdk.AuthBean;

/**
 * author：yang
 * created on：2020/10/20 17:07
 */
public interface IAuthReq {
    /**
     * 认证回调：在onCreate/onNewIntent中调用handleIntent
     * @param bean
     */
    void onResp(AuthBean bean);
}
