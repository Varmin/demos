package com.varmin.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.varmin.libutils.LensHelper

class MainActivityDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_demo)

        LensHelper.init(application)
        LensHelper.config(application)

    }
}