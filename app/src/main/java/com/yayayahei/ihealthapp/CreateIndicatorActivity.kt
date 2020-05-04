package com.yayayahei.ihealthapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class CreateIndicatorActivity : AppCompatActivity() {

    lateinit var discard_button: TextView
    lateinit var save_button: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_indicator)




        InitializeFields()
    }

    fun InitializeFields() {
        val toolbar = findViewById<Toolbar>(R.id.edit_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowCustomEnabled(true)

        val mInflater = LayoutInflater.from(this)
        val customAction: View = mInflater.inflate(R.layout.edit_custom_action_bar, null)
        supportActionBar?.customView = customAction

        discard_button = customAction.findViewById(R.id.discard_button)
        discard_button.setOnClickListener(View.OnClickListener { finish() })
        save_button = customAction.findViewById(R.id.save_button)
        save_button.setOnClickListener(View.OnClickListener { finish() })
    }
}
