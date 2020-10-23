package com.atoshi.opensdk;

/**
 * author：yang
 * created on：2020/10/23 13:37
 * description:
 */
public class Constants {
    /**
     * Atoshi信息
     */
    public static final int MIN_VERSION_CODE = 90;
    public static final String PACKAGE_NAME = "com.varmin.modulelogin";
    //Atoshi Manifest文件配置信息
    public static final String META_ATOSHI_AUTH_ACTIVITY = "META_ATOSHI_AUTH_ACTIVITY";
    public static final String EXTRA_CLIENT_ID = "atoshi_extra_client_id";
    /**
     * 跳转第三方应用信息
     */
    //第三方 Manifest文件配置信息，Atoshi跳转第三方使用
    public static final String META_THIRD_AUTH_ACTIVITY = "META_THIRD_ATOSHI_AUTH_ACTIVITY";
    //第三方包名
    public static final String EXTRA_PACKAGE_NAME = "extra_package_name";
    //返回认证后的信息
    public static final String EXTRA_STATUS = "atoshi_extra_status";
    public static final String EXTRA_ERR_MSG = "atoshi_extra_err_msg";
    public static final String EXTRA_AUTH_CODE = "atoshi_extra_auth_code";
}
