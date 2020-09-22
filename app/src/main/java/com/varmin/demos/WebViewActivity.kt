package com.varmin.demos

import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.varmin.libview.WebViewGoogle
import com.varmin.libview.WebViewTbs

const val webUrl = "http://game.atoshi.mobi/other/android/?openid=oaMf80tEUmX9jkuXwhxKLyrTM5yQ&token=zSmHnySSLjKYcQRpPxbvvmHTleBFzMC0"
//const val webUrl = "https://x5.tencent.com/docs/index.html"
//https://www.baidu.com/
class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_web_view)

        //WebView.setWebContentsDebuggingEnabled(true)

//        initWebTbs()
        initWebChrome()
    }

    private fun initWebTbs() {
        var webView = WebViewTbs(this).apply {
            layoutParams = ViewGroup.LayoutParams(-1, -1)
            settings.javaScriptEnabled = true
            loadUrl(webUrl)
        }
        setContentView(webView)
    }

    private fun initWebChrome() {
        var webView = WebViewGoogle(this).apply {
            layoutParams = ViewGroup.LayoutParams(-1, -1)
            settings.javaScriptEnabled = true
            loadUrl(webUrl)
        }
        setContentView(webView)
    }
}