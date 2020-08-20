package com.varmin.demos.test

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.widget.ImageView

/**
 * author：yang
 * created on：2020/8/11 16:41
 * description: 插屏弹框
 */
class CustomDialog : Dialog {
    private constructor(context: Context): super(context)

    class Builder(private val context: Context){
        private val widthScale = 0.7
        private val whScale = 2.0
        fun create(): CustomDialog{
            return CustomDialog(context).apply {
                var content = ImageView(context).apply {
                    setBackgroundColor(Color.RED)
                }
                setContentView(content)

                setCancelable(false)
                window?.attributes?.run {
                    val displayMetrics = context.resources.displayMetrics
                    width = (displayMetrics.widthPixels * widthScale).toInt()
                    height = (width * whScale).toInt()
                }
            }
        }
    }
}