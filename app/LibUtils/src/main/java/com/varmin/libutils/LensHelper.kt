package com.varmin.libutils

import android.app.Application
import com.qiyi.lens.Lens
import com.qiyi.lens.LensUtil
import com.qiyi.lens.utils.DefaultHookImpl
import com.qiyi.lens.utils.KeyLogConfig
import com.qiyi.lens.utils.UIUtils


/**
 * created by HYY on 2020/7/11
 * description:
 */
class LensHelper {
    companion object {
        fun init(application: Application) {
            // 初始化Lens
            Lens.init(application, BuildConfig.DEBUG)
        }

        fun config(application: Application) {
            // 配置Lens: 在Application.onCreate 之后调用
            LensUtil.buildConfig()
                .defaultOpen(false)
                .enableDeviceInfo(true)
                .enableKeyLog(KeyLogConfig.builder().addFilter("Main").setMaxLine(1000))
                .enableFPS(true)
                .enableLaunchTime(true)
                .setHookFrameWorkImpl(DefaultHookImpl())
                .enableActivityAnalyzer(true)
                .enableNetworkAnalyze(false)
                .enableCrashInfo(true)
//                .addCustomBlockEntrance(customBlockEntrance, BlockFactory())
//                .addCustomJumpEntrance(customJumpEntrance, JumpAction())
                .initAsPluginMode(Lens.isSDKMode())
                .enableViewInfo(true)
                .show(Lens.wrapContext(application), UIUtils.getScreenWidth(application) / 5 * 3)
        }
    }
}