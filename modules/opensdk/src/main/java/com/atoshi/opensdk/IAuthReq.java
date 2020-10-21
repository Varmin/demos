package com.atoshi.opensdk;

/**
 * author：yang
 * created on：2020/10/20 17:07
 * description: 申请认证接口
 */
public interface IAuthReq {
    /**
     * 检查是否安装Atoshi客户端和版本是否满足功能
     */
    boolean checkAtoshiApp();
    /**
     * 发送认证申请，调起Atoshi客户端
     * @param clientId 客户端id，Atoshi服务提供
     */
    void sendAuthReq(String clientId);
}
