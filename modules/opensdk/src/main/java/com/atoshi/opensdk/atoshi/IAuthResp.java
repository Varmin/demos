package com.atoshi.opensdk.atoshi;

/**
 * author：yang
 * created on：2020/10/20 17:07
 * description:
 */
public interface IAuthResp {
    /**
     * 获取第三方App信息
     * @param clientId
     */
    void getThirdAppInfo(String clientId);

    /**
     * 获取code
     */
    void getCode();
}
