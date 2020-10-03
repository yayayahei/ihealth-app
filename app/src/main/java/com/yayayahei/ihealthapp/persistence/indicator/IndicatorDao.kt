package com.yayayahei.ihealthapp.persistence.indicator

import androidx.room.*
import com.yayayahei.ihealthapp.persistence.indicator.Indicator
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface IndicatorDao {
    @Query("Select * from indicator")
    fun getAll(): Observable<List<Indicator>>

    @Query("Select * from indicator where iid in (:indicatorIds)")
    fun loadAllByIds(indicatorIds: IntArray): Observable<List<Indicator>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg indicators: Indicator): Completable

    @Delete
    fun delete(indicator: Indicator)

    @Query("delete from indicator")
    fun deleteAll(): Completable
}