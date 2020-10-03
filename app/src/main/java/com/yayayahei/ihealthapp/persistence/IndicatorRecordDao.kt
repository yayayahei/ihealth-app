package com.yayayahei.ihealthapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.TemporalField
import java.util.*

@Dao
interface IndicatorRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIndicatorRecord(indicatorRecord: IndicatorRecord): Completable

    @Query("select * from indicator_record where parent_indicator_id=:indicatorId  and create_time>:today order by create_time desc  limit 1")
    fun getLastRecordOfToday(
        indicatorId: Int,
        today: Long? = SimpleDateFormat("yyyy-MM-dd").parse(
            SimpleDateFormat(
                "yyyy-MM-dd",
                Locale.CHINA
            ).format(Date()), ParsePosition(0)
        ).time
    ): Observable<IndicatorRecord?>


    @Query("select * from indicator_record where parent_indicator_id=:indicatorId  order by create_time desc")
    fun getRecords(indicatorId: Int): Observable<List<IndicatorRecord>>

}
