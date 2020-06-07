package com.yayayahei.ihealthapp.ui

import androidx.lifecycle.ViewModel
import com.yayayahei.ihealthapp.persistence.IndicatorRecord
import com.yayayahei.ihealthapp.persistence.IndicatorRecordDao
import io.reactivex.Completable
import io.reactivex.Observable

class IndicatorRecordViewModel(private val dataSource: IndicatorRecordDao) : ViewModel() {
    fun insertIndicatorRecord(indicatorRecord: IndicatorRecord): Completable {
        return dataSource.insertIndicatorRecord(indicatorRecord)
    }

    fun getLastRecordOfToday(indicatorId: Int): Observable<IndicatorRecord?> {
        return dataSource.getLastRecordOfToday(indicatorId)
    }
}