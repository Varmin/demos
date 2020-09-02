package com.varmin.demos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        WebView.setWebContentsDebuggingEnabled(true)
        webview.apply {
            loadUrl("http://game.atoshi.mobi/other/downLoad/index.html")
        }
    }
}