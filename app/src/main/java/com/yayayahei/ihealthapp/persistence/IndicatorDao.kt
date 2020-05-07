package com.yayayahei.ihealthapp.persistence

import androidx.room.*
import io.reactivex.Completable

@Dao
interface IndicatorDao {
    @Query("Select * from indicator")
    fun getAll(): List<Indicator>

    @Query("Select * from indicator where iid in (:indicatorIds)")
    fun loadAllByIds(indicatorIds: IntArray): List<Indicator>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg indicators: Indicator): Completable

    @Delete
    fun delete(indicator: Indicator)
}