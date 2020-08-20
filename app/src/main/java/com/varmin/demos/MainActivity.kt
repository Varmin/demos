package com.varmin.demos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.varmin.demos.test.CustomDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CustomDialog.Builder(this).create().show()
    }
}