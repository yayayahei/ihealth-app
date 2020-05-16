package com.yayayahei.ihealthapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.yayayahei.ihealthapp.R
import com.yayayahei.ihealthapp.persistence.Indicator
import kotlinx.android.synthetic.main.indicator_item_in_list.view.*
import org.w3c.dom.Text

class IndicatorViewAdapter(private val indicators: List<Indicator>) :
    RecyclerView.Adapter<IndicatorViewAdapter.IndicatorViewHolder>() {
    var onItemClick: ((Indicator) -> Unit)? = null

    inner class IndicatorViewHolder(val indicatorView: ConstraintLayout) :
        RecyclerView.ViewHolder(indicatorView) {
        val indicatorName: TextView = indicatorView.indicatorItemName

        init {
            indicatorView.setOnClickListener {
                onItemClick?.invoke(indicators[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        val indicatorView = LayoutInflater.from(parent.context)
            .inflate(R.layout.indicator_item_in_list, parent, false) as ConstraintLayout

        return IndicatorViewHolder(indicatorView)
    }

    override fun getItemCount() = indicators.count()
    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) {
        holder.indicatorName.text = indicators[position].name
    }
}