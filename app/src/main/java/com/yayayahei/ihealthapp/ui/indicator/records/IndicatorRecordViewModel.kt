package com.yayayahei.ihealthapp.ui.indicator.records

import androidx.lifecycle.ViewModel
import com.yayayahei.ihealthapp.persistence.indicator.records.IndicatorRecord
import com.yayayahei.ihealthapp.persistence.indicator.records.IndicatorRecordDao
import io.reactivex.Completable
import io.reactivex.Observable

class IndicatorRecordViewModel(private val dataSource: IndicatorRecordDao) : ViewModel() {
    fun insertIndicatorRecord(indicatorRecord: IndicatorRecord): Completable {
        return dataSource.insertIndicatorRecord(indicatorRecord)
    }

    fun getLastRecordOfToday(indicatorId: Int): Observable<IndicatorRecord?> {
        return dataSource.getLastRecordOfToday(indicatorId)
    }

    fun getRecords(indicatorId:Int):Observable<List<IndicatorRecord>>{
        return dataSource.getRecords(indicatorId)
    }
}