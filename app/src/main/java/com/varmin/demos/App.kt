package com.varmin.demos

import android.app.Application
import com.tamsiree.rxkit.RxTool
import com.tencent.smtt.sdk.QbSdk

/**
 * author：yang
 * created on：2020/8/21 15:06
 * description:
 */
class App: Application(){
    override fun onCreate() {
        super.onCreate()
        println("App.onCreate------")
        RxTool.init(this)

        QbSdk.initX5Environment(this, object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {
                println("App.onCoreInitFinished")
            }

            override fun onViewInitFinished(p0: Boolean) {
                println("App.onViewInitFinished: $p0")
            }
        })
    }
}