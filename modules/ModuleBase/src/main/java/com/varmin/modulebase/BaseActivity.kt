package com.varmin.modulebase

import android.os.Bundle
import butterknife.ButterKnife
import butterknife.Unbinder
import com.varmin.libarchite.mvp._BaseActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * created by HYY on 2020/6/23
 * description:
 */
abstract open class BaseActivity : _BaseActivity() {
    private var mUnbinder: Unbinder? = null

    override fun _onCreate(savedInstanceState: Bundle?) {
        super._onCreate(savedInstanceState)
        mUnbinder = ButterKnife.bind(this)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mUnbinder?.unbind()
        EventBus.getDefault().unregister(this)
    }

    //--------------------------EventBus--------------------------
    @Subscribe(threadMode = ThreadMode.POSTING)
    fun onMessagePosting(event: MessageEvent) {
        println("$TAG.onMessagePosting: ${event.event}")
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageMain(event: MessageEvent) {
        println("$TAG.onMessageMain: ${event.event}")
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    fun onMessageMainOrder(event: MessageEvent) {
        println("$TAG.onMessageMainOrder: ${event.event}")
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onMessageBackground(event: MessageEvent) {
        println("$TAG.onMessageBackground: ${event.event}")
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onMessageAsync(event: MessageEvent) {
        println("$TAG.onMessageAsync: ${event.event}")
    }

}