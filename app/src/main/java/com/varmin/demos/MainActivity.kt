package com.varmin.demos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.varmin.demos.others.expandable.ExpandableActivity
import com.varmin.libutils.startAndFinish

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startAndFinish(ExpandableActivity::class.java)
    }
}