

* Auth.Req/Resp
## client
* Auth: IAuthReq
    * isAtoshiInstalled: Boolean
    * sendAuth(appid: Req)
    * registBroadCast
        * parseResp()//onCreate、onNewIntent
    * getAccessToken(code): accessToken
    * getUserInfo(accessToken): UserInfo
    
    
## atoshi
* AuthActivity: IAuthResp: xml配置
    * parseReq(Req)//解析Req, onCreate/onNewIntent
    * checkLoginSelf()
    * getCode(): code
    * sendAuthResp()//给第三方code

    
