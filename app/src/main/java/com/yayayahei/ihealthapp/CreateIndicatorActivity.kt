package com.yayayahei.ihealthapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.yayayahei.ihealthapp.models.*


class CreateIndicatorActivity : AppCompatActivity() {

    private lateinit var discardButton: TextView
    private lateinit var saveButton: TextView
    private lateinit var indicatorNameInput: EditText
    private lateinit var indicatorUnitInput: EditText
    private lateinit var indicatorMinInput: EditText
    private lateinit var indicatorMaxInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_indicator)
        initializeFields()
    }

    private fun initializeFields() {
        initActionBarFields()
        initIndicatorFormFields()
    }

    private fun initIndicatorFormFields() {
        indicatorNameInput = findViewById(R.id.indicator_name_input)
        indicatorUnitInput = findViewById(R.id.indicator_unit_input)
        indicatorMinInput = findViewById(R.id.indicator_min_input)
        indicatorMaxInput = findViewById(R.id.indicator_max_input)
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
        saveButton.setOnClickListener(View.OnClickListener { createIndicator() })
    }

    private fun createIndicator() {
        var indicator = Indicator(
            indicatorNameInput.text.toString(),
            indicatorUnitInput.text.toString(),
            indicatorMinInput.text.toString().toDoubleOrNull() ?: DEFAULT_INDICATOR_MIN,
            indicatorMaxInput.text.toString().toDoubleOrNull() ?: DEFAULT_INDICATOR_MAX
        )
        println(indicator)
        finish()
    }
}
