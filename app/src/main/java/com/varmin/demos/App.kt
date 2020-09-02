package com.varmin.demos

import android.app.Application
import com.tamsiree.rxkit.RxTool

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
    }
}