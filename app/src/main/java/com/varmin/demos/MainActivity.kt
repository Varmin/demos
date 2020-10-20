package com.varmin.demos

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var content = "<p>测试发帖</p><br />\n<p>换行<span style='color:#1abc9c'>测试</span></p>\n\n<p>结尾无换行</p>\n"
        content = "<p>空间和雕刻技法和\n啊啊啊</p>\n\n<p>阿斯顿发射点啊啊</p>\n\n<ol>\n\t<li>啊手动阀手动阀啊</li>\n\t<li>阿斯顿发生啊啊啊</li>\n</ol>\n\n<p>是豆腐干大师傅啊啊啊</p>\n\n<p>地方大师傅似的啊啊啊</p>\n"
//        content = content.replace("(</p>\n)$".toRegex(), "")
//        content = content.replace("(<p>)*$".toRegex(), "")
        var contentReplace = content
            .replace(">\n\n", ">")
            .replace(">\n", ">")
            .replace("<p>", "")
            .replace("</p>", "<br />")
            .replace("\n", "<br />")
            .replace("(<br />)$".toRegex(), "")
        //Log.d(TAG, "onCreate: $content")
        Log.d(TAG, "onCreate, contentReplace= $contentReplace")

        tvTest.text = Html.fromHtml(contentReplace)
//        tvTest.text = Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT)

//        tvTest2.text = Html.fromHtml(contentReplace)
        tvTest2.text = Html.fromHtml(contentReplace, Html.FROM_HTML_MODE_COMPACT)

    }
}