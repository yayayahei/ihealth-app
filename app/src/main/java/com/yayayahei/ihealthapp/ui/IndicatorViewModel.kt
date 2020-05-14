package com.yayayahei.ihealthapp.ui

import androidx.lifecycle.ViewModel
import com.yayayahei.ihealthapp.persistence.Indicator
import com.yayayahei.ihealthapp.persistence.IndicatorDao
import io.reactivex.Completable
import io.reactivex.Observable

class IndicatorViewModel(private val dataSource: IndicatorDao) : ViewModel() {
    fun insertIndicator(indicator: Indicator): Completable {
        return dataSource.insertAll(indicator)
    }

     fun getAllIndicators(): Observable<List<Indicator>> {
        return dataSource.getAll()
    }

    fun deleteAllIndicators(): Completable {
        return dataSource.deleteAll()
    }
}