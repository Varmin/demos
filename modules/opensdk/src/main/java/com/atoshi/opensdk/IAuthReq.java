package com.atoshi.opensdk;

/**
 * author：yang
 * created on：2020/10/20 17:07
 * description: 申请认证接口
 */
public interface IAuthReq {
    /**
     * 是否安装Atoshi客户端
     * @return
     */
    boolean isAtoshiInstalled();

    /**
     * 检查Atoshi客户端版本号是否可用
     * @return
     */
    boolean checkAtoshiVersionUsable();

    /**
     * 发送认证申请
     * @param clientId 客户端id，Atoshi服务提供
     */
    void sendAuthReq(String clientId);

//    void getAccessToken(String code);
//
//    void getUserInfo();
}
