package com.varmin.modules

import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.atoshi.opensdk.AuthBean
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

    override fun getLayoutId(): Int = R.layout.activity_main_demo

    override fun initView() {
        sendAuth = AuthReq(this)
        sendAuth.handleIntent(intent, this)
        btnAuth.setOnClickListener {
            sendAuth.sendAuthReq(AuthReq.CLIENT_ID)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.run {
            sendAuth.handleIntent(intent, this@MainActivityModules)
        }
    }

    override fun initData() {
    }

    override fun onResp(bean: AuthBean) {
        if (TextUtils.equals("0", bean.status)) {
            Toast.makeText(this, bean.errMsg.toString(), Toast.LENGTH_SHORT).show()
        }else if(TextUtils.equals("1", bean.status)){
            Toast.makeText(this, bean.code.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
