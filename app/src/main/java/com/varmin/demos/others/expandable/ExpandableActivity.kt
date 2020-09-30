package com.varmin.demos.others.expandable

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnAttach
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lxj.xpopup.XPopup
import com.varmin.demos.R
import kotlinx.android.synthetic.main.activity_game_community.*

/**
 * 折叠文本：内容、评论
 * 折叠列表
 * 评论框
 */
class ExpandableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_community)

         mData = GamePostItem().apply {
            pages = "1"
            list = arrayListOf<GamePostItem.PostItem>().apply {
                for (i in 0..10) {
                    add(GamePostItem.PostItem().apply {
                        content = "content：$i, 爱丽丝的房间卡洛斯的拉开圣诞节法律上的房间爱上了对方爱丽丝的房间阿斯兰的"
                        listGameEvaluate = arrayListOf<GamePostItem.PostItem.Evaluate>().apply {
                            for (j in 0..3) {
                                add(GamePostItem.PostItem.Evaluate().apply {
                                    content = "evaluate: $i-$j"
                                })
                            }
                        }
                    })
                }
            }
        }

        rv.run {
            adapter = PostAdapter().apply {
                setNewInstance(mData.list)
            }
        }

        btn.setOnClickListener {
            var adapter = rv.adapter as PostAdapter

            var ad = rv.adapter as PostAdapter
            ad.addEvaluate(0, GamePostItem.PostItem.Evaluate().apply {
                userId = "userId: "
                content = "add"
            })

            /*val rv = adapter.getViewByPosition(0, R.id.rvEvaluate)
            rv?.scrollBy(0, -200)

            XPopup.Builder(this)
                .autoOpenSoftInput(true)
                .atView(rv)
                .asCustom(CustomEditTextBottomPopup(this, adapter.data[0]))
                .show()*/
        }
    }


    private lateinit var mData: GamePostItem
    var tag = "可见光和监可见光和监控监控和改进空间控监控和改进空间可见光和监可见光和监控监控和改进空间控监控和改进空间可见光和监可见光和监控监控和改进空间控监控和改进空间可见光和监可见光和监控监控和改进空间控监控和改进空间可见光和监可见光和监控监控和改进空间控监控和改进空间"
    inner class PostAdapter: BaseQuickAdapter<GamePostItem.PostItem, BaseViewHolder>{
        constructor():super(R.layout.item_post)
        var mAdapter = EvaluateAdapter(mData.list[0].listGameEvaluate)
        override fun convert(holder: BaseViewHolder, item: GamePostItem.PostItem) {
            holder.setText(R.id.tvTitle, "Title")
                .setText(R.id.tvTime, "1小时前")
                .setText(R.id.ftv, item.content + ", $tag")

            holder.getView<RecyclerView>(R.id.rvEvaluate).apply {
                //adapter = EvaluateAdapter(item.listGameEvaluate)
                var a = EvaluateAdapter(arrayListOf())
                adapter = a
                a.setNewData(mData.list[0].listGameEvaluate)
            }
        }
        fun addEvaluate(position: Int, evaluate: GamePostItem.PostItem.Evaluate){
            val rvEvaluate = getViewByPosition(position, R.id.rvEvaluate) as RecyclerView
            data[position].listGameEvaluate.add(0, evaluate)
            rvEvaluate.adapter?.notifyItemInserted(0)
        }
    }

    inner class EvaluateAdapter: BaseQuickAdapter<GamePostItem.PostItem.Evaluate, BaseViewHolder>{
        constructor(data: MutableList<GamePostItem.PostItem.Evaluate>):super(
            R.layout.item_post_evaluate,
            data
        )
        override fun convert(holder: BaseViewHolder, item: GamePostItem.PostItem.Evaluate) {
            holder.setText(R.id.tvName, item.userId)
                .setText(R.id.ftv, item.content + ", $tag")
        }

        override fun setNewData(data: MutableList<GamePostItem.PostItem.Evaluate>?) {
            data?.run {
                if(size > 20){
                    headerLayout?.getChildAt(0)?.apply {
                        visibility = View.VISIBLE
                        //todo findview
                    } ?: {
                        val view = TextView(context).apply { text = "共50条评论" }
                        addHeaderView(view)
                    }()
                }else{
                    headerLayout?.getChildAt(0)?.apply {
                        visibility = View.GONE
                    }
                }
            }
            super.setNewData(data)
        }


    }
}