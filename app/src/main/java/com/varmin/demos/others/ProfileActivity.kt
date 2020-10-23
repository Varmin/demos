package com.varmin.demos.others

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.varmin.demos.BaseActivity
import com.varmin.demos.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_profile

    override fun initView() {
        val listAdapter = ListAdapter().apply {
            setNewInstance(arrayListOf<String>().toMutableList().apply {
                (1..100).forEach {
                    add(it.toString())
                }
            })
        }
        rvProfile.adapter = listAdapter
    }

    override fun initData() {}

    inner class ListAdapter: BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_common, null){
        override fun convert(holder: BaseViewHolder, item: String) {
            holder.setText(R.id.tvContent, item)
        }
    }
}