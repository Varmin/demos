package com.varmin.demos

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.varmin.demos.others.expandable.ExpandableActivity
import com.varmin.libutils.startAndFinish
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startAndFinish(ExpandableActivity::class.java)

       /* banner.run {
            indicator = CircleIndicator(this@MainActivity).apply {
            }
//            setIndicatorNormalColorRes(R.color.blue)
//            setIndicatorSelectedColorRes(R.color.red)

            setIndicatorNormalColor(Color.BLUE)
            setIndicatorSelectedColor(Color.RED)

            setIndicatorHeight(30)
            setIndicatorWidth(30, 55)
            setIndicatorRadius(30)
            isAutoLoop(true)

            adapter = ImageAdapter(arrayOf("1", "2", "3").asList())
        }*/

        banner.indicator = CircleIndicator(this@MainActivity)
        banner.setIndicatorNormalColor(Color.BLUE)
        banner.setIndicatorSelectedColor(Color.RED)
        banner.setIndicatorNormalWidth(10)
        banner.setIndicatorSelectedWidth(30)
        banner.isAutoLoop(true)

        banner.adapter = ImageAdapter(arrayOf("1", "2", "3").asList())
    }

    inner class ImageAdapter(data: List<String>): BannerAdapter<String, ImageAdapter.ViewHolder>(data){
        inner class ViewHolder: RecyclerView.ViewHolder{
            public lateinit var mView: ImageView
            constructor(view: ImageView):super(view){
                mView = view
            }
        }

        override fun onCreateHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            return ViewHolder(ImageView(this@MainActivity).apply {
                layoutParams = ViewGroup.LayoutParams(-1, -1)
                scaleType = ImageView.ScaleType.CENTER_CROP
            })
        }

        override fun onBindView(holder: ViewHolder?, data: String?, position: Int, size: Int) {
            holder?.mView?.setImageResource(R.drawable.wechat_header)
        }
    }
}