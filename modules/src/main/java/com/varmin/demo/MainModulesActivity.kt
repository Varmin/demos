package com.varmin.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.varmin.modulebase.BaseActivity
import com.varmin.modulebase.MessageEvent
import org.greenrobot.eventbus.EventBus

class MainModulesActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_main_demo

    override fun initView() {
    }
    override fun initData() {
        EventBus.getDefault().post(MessageEvent("main"))
    }
}
