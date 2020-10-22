package com.varmin.demos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.varmin.demos.others.ProfileActivity
import com.varmin.libutils.start

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        start(ProfileActivity::class.java)
    }
}