package com.yayayahei.ihealthapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.yayayahei.ihealthapp.R

class EditGaugeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_gauge)

        showAppBar()

    }
    private fun showAppBar() {
        val toolbar = findViewById<Toolbar>(R.id.edit_gauge_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title = indicatorName
    }
}