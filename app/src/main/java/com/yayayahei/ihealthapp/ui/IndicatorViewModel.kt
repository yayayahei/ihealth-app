package com.yayayahei.ihealthapp.ui

import androidx.lifecycle.ViewModel
import com.yayayahei.ihealthapp.persistence.Indicator
import com.yayayahei.ihealthapp.persistence.IndicatorDao
import io.reactivex.Completable

class IndicatorViewModel(private val dataSource: IndicatorDao) : ViewModel() {
    fun insertIndicator(indicator: Indicator): Completable {
        return dataSource.insertAll(indicator)
    }

    fun getAllIndicators(): List<Indicator> {
        return dataSource.getAll()
    }
}