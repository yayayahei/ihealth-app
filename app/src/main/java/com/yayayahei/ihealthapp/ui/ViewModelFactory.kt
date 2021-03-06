package com.yayayahei.ihealthapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yayayahei.ihealthapp.persistence.indicator.IndicatorDao
import com.yayayahei.ihealthapp.persistence.indicator.records.IndicatorRecordDao
import com.yayayahei.ihealthapp.ui.indicator.IndicatorViewModel
import com.yayayahei.ihealthapp.ui.indicator.records.IndicatorRecordViewModel

class ViewModelFactory(
    private val dataSource: IndicatorDao,
    private val indicatorRecordDao: IndicatorRecordDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IndicatorViewModel::class.java)) {
            return IndicatorViewModel(
                dataSource
            ) as T
        }
        if (modelClass.isAssignableFrom(IndicatorRecordViewModel::class.java)) {
            return IndicatorRecordViewModel(
                indicatorRecordDao
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}