package com.yayayahei.ihealthapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.yayayahei.ihealthapp.R

class RecordForIndicatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_for_indicator)
        val indicatorId = intent.getIntExtra(INDICATOR_ID, 0)
        showAppBar()
        setActionBarTitle(indicatorId)
    }

    private fun setActionBarTitle(indicatorId: Int) {
        println("Got indicator:" + indicatorId)
        supportActionBar?.title = "默认指标:" + indicatorId
    }


    private fun showAppBar() {
        val toolbar = findViewById<Toolbar>(R.id.record_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
