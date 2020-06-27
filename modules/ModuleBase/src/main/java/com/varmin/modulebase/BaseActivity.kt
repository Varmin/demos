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
abstract class BaseActivity : _BaseActivity() {
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


    /**
    在主线程发送：
    默认，在哪发送，在哪处理
    onMessageEvent_Posting type=REFRESH_DATA, main

    在主线程处理
    onMessageEvent_Main type=REFRESH_DATA, main
    在主线程接收（有序）
    onMessageEvent_MainOrder type=REFRESH_DATA, main

    在主线程发布时再后台线程处理，在子线程发布就用子线程处理
    onMessageEvent_Background type=REFRESH_DATA, pool-1-thread-2

    无论在哪发送，新开一个线程处理（线程是相互独立的）
    onMessageEvent_Async type=REFRESH_DATA, pool-1-thread-1

    TODO 为什么无论哪个threadMode都能收到发送的消息？
    因为是同一个EventBus
    TODO 即使是同一个EventBus，那每次发送消息之后，不同的threadMode收到消息
    例如Async类型，即使不是给Async的，也都会开启一个线程么？

    在子线程发送：
    onMessageEvent_Posting type=REFRESH_DATA, Thread-2

    onMessageEvent_Main type=REFRESH_DATA, main
    onMessageEvent_MainOrder type=REFRESH_DATA, main

    onMessageEvent_Async type=REFRESH_DATA, pool-1-thread-1
    onMessageEvent_Background type=REFRESH_DATA, Thread-2
     */


    /**
    1. 优先级
    2. threadMode
    3. 取消事件
    4. 粘性事件
    5. 每个EventBus的发布/订阅是相互隔离的
    6. 注册、发送、分发过程
     **/

    //优先级不影响不同ThreadModes的优先级
    //不同ThreadModes还是不同线程？试验是ThreadModes不同，影响同一线程的优先级
    @Subscribe(threadMode = ThreadMode.POSTING)
    fun onMessagePosting(event: MessageEvent) {
        println("$TAG.onMessagePosting:${Thread.currentThread().name}, ${event.event}")

        //仅限于在POSTING线程中取消
        //POSTING、Main虽然是不同的ThreadMode，也证明了优先级的影响同一线程，而不是单纯的不同TheadMode
        //EventBus.getDefault().cancelEventDelivery(event) ;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageMain(event: MessageEvent) {
        println("$TAG.onMessageMain:${Thread.currentThread().name}, ${event.event}")
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    fun onMessageMainOrder(event: MessageEvent) {
        println("$TAG.onMessageMainOrder:${Thread.currentThread().name}, ${event.event}")
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onMessageBackground(event: MessageEvent) {
        println("$TAG.onMessageBackground:${Thread.currentThread().name}, ${event.event}")
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onMessageAsync(event: MessageEvent) {
        println("$TAG.onMessageAsync:${Thread.currentThread().name}, ${event.event}")
    }
}