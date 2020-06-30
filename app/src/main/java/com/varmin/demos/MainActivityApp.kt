package com.varmin.demos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.varmin.demo.MainActivityDemo

class MainActivityApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        startActivity(Intent(this, MainActivityDemo::class.java))
        finish()
    }
}