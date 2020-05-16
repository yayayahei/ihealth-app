package com.yayayahei.ihealthapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.yayayahei.ihealthapp.R
import com.yayayahei.ihealthapp.persistence.Indicator

class IndicatorViewAdapter(private val indicators: List<Indicator>) :
    RecyclerView.Adapter<IndicatorViewAdapter.IndicatorViewHolder>() {
    //    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
    class IndicatorViewHolder(val indicatorView: ConstraintLayout) :
        RecyclerView.ViewHolder(indicatorView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        val indicatorView = LayoutInflater.from(parent.context)
            .inflate(R.layout.indicator_item_in_list, parent, false) as ConstraintLayout

        return IndicatorViewHolder(indicatorView)
    }

    override fun getItemCount() = indicators.count()
    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) {
        val textView = holder.indicatorView.findViewById<TextView>(R.id.indicatorItemName)
        textView.text = indicators[position].name
    }

}