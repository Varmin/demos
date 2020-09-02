package com.varmin.libutils

import android.app.Activity
import android.content.Intent

/**
 * created by HYY on 2020/6/25
 * description: 顶层函数
 */

/**
 * startActivity封装
 */
fun Activity.start(targetActivity: Class<out Activity>) {
    startActivity(Intent(this, targetActivity))
}
fun Activity.startAndFinish(targetActivity: Class<out Activity>) {
    startActivity(Intent(this, targetActivity))
    finish()
}
