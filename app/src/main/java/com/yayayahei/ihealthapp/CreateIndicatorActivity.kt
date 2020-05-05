package com.yayayahei.ihealthapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class CreateIndicatorActivity : AppCompatActivity() {

    private lateinit var discardButton: TextView
    private lateinit var saveButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_indicator)
        initializeFields()
    }

    private fun initializeFields() {
        initActionBarFields()
    }

    private fun initActionBarFields() {
        val toolbar = findViewById<Toolbar>(R.id.edit_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowCustomEnabled(true)

        val mInflater = LayoutInflater.from(this)
        val customAction: View = mInflater.inflate(R.layout.edit_custom_action_bar, null)
        supportActionBar?.customView = customAction

        discardButton = customAction.findViewById(R.id.discard_button)
        discardButton.setOnClickListener(View.OnClickListener { finish() })
        saveButton = customAction.findViewById(R.id.save_button)
        saveButton.setOnClickListener(View.OnClickListener { finish() })
    }
}
