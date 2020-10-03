package com.yayayahei.ihealthapp.ui.indicator.records

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.yayayahei.ihealthapp.R
import com.yayayahei.ihealthapp.persistence.IndicatorRecord
import kotlinx.android.synthetic.main.indicator_record_item_in_list.view.*

class IndicatorRecordViewAdapter(private val indicatorRecords: List<IndicatorRecord>) :
    RecyclerView.Adapter<IndicatorRecordViewAdapter.IndicatorRecordViewHolder>() {
    var onItemClick: ((IndicatorRecord) -> Unit)? = null

    inner class IndicatorRecordViewHolder(val indicatorRecordView: ConstraintLayout) :
        RecyclerView.ViewHolder(indicatorRecordView) {
        val indicatorName: TextView = indicatorRecordView.indicatorRecordItemName

        init {
            indicatorRecordView.setOnClickListener {
                onItemClick?.invoke(indicatorRecords[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorRecordViewHolder {
        val indicatorRecordView = LayoutInflater.from(parent.context)
            .inflate(R.layout.indicator_record_item_in_list, parent, false) as ConstraintLayout

        return IndicatorRecordViewHolder(indicatorRecordView)
    }

    override fun getItemCount() = indicatorRecords.count()
    override fun onBindViewHolder(holderRecord: IndicatorRecordViewHolder, position: Int) {
        holderRecord.indicatorName.text =
            indicatorRecords[position].createTime.toString() + ": " + indicatorRecords[position].value
    }
}