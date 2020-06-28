package com.varmin.modules

import com.varmin.modulebase.BaseActivity
import com.varmin.modulebase.startAndFinish
import com.varmin.modulemain.MainActivityMain

/**
* created by HY on 2020/6/25
* description: Modules主Activity，只是入口，所有的逻辑放到ModuleMain中。
*/
class MainActivityModules : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_main_demo

    override fun initView() {
    }
    override fun initData() {
        startAndFinish(MainActivityMain::class.java)

    }
}
