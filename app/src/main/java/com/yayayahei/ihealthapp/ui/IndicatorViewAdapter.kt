package com.yayayahei.ihealthapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yayayahei.ihealthapp.R
import com.yayayahei.ihealthapp.persistence.Indicator

class IndicatorViewAdapter(private val indicators: List<Indicator>) :
    RecyclerView.Adapter<IndicatorViewAdapter.MyViewHolder>() {
    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.indicator_item_in_list, parent, false) as TextView

        return MyViewHolder(textView)
    }

    override fun getItemCount() = indicators.count()
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = indicators[position].name
    }

}