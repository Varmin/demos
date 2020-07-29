package com.varmin.modulemain

import com.bumptech.glide.Glide
import com.varmin.modulebase.BaseActivity
import kotlinx.android.synthetic.main.activity_main_main.*

class MainActivityMain : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_main_main

    override fun initView() {
    }

    override fun initData() {
        Glide.with(this)
//            .asGif()
//            .error(R.mipmap.ic_launcher)
            .load(R.mipmap.icon_redpacket)
//            .load(resources.getDrawable(R.mipmap.icon_redpacket))
            .into(ivTest)
    }

}
