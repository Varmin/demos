package com.atoshi.opensdk.atoshi;

/**
 * author：yang
 * created on：2020/10/20 17:07
 * description: 返回认证信息接口
 */
public interface IAuthResp {
    void getCode(String clientId);
}
