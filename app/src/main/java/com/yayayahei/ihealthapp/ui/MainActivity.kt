package com.yayayahei.ihealthapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yayayahei.ihealthapp.R

const val EXTRA_MESSAGE = "com.yayayahei.ihealthapp.MESSAGE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun gotoAddIndicatorActivity(view: View) {
        val intent = Intent(this, CreateIndicatorActivity::class.java)
        startActivity(intent)
    }
}
