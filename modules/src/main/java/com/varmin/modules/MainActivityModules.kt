package com.varmin.modules

import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.atoshi.opensdk.RespBean
import com.atoshi.opensdk.client.AuthReq
import com.atoshi.opensdk.client.IAuthReq
import com.varmin.modulebase.BaseActivity
import kotlinx.android.synthetic.main.activity_main_demo.*

/**
* created by HY on 2020/6/25
* description: Modules主Activity，只是入口，所有的逻辑放到ModuleMain中。
*/
class MainActivityModules : BaseActivity(), IAuthReq {
    private lateinit var sendAuth: AuthReq
    private val CLIENT_ID = "client_id: WED34587EDX"

    override fun getLayoutId(): Int = R.layout.activity_main_demo

    override fun initView() {
        sendAuth = AuthReq(this, CLIENT_ID)
        sendAuth.handleIntent(intent, this)

        btnAuth.setOnClickListener {
            sendAuth.sendAuthReq()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        sendAuth.handleIntent(intent, this@MainActivityModules)
    }

    override fun initData() {}

    override fun onResp(bean: RespBean) {
        if (bean.status == RespBean.CODE_ERR) {
            Toast.makeText(this, bean.errMsg.toString(), Toast.LENGTH_SHORT).show()
        }else if(bean.status == RespBean.CODE_OK){
            Toast.makeText(this, bean.code.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
