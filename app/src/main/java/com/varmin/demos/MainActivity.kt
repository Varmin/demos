package com.varmin.demos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tamsiree.rxkit.RxPermissionsTool
import com.varmin.libutils.start

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start(WebViewActivity::class.java)

    }
}