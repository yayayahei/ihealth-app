package com.yayayahei.ihealthapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yayayahei.ihealthapp.persistence.indicator.Indicator
import com.yayayahei.ihealthapp.persistence.indicator.IndicatorDao
import com.yayayahei.ihealthapp.persistence.indicator.records.IndicatorRecord
import com.yayayahei.ihealthapp.persistence.indicator.records.IndicatorRecordDao

@Database(entities = [Indicator::class, IndicatorRecord::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun indicatorDao(): IndicatorDao
    abstract fun indicatorRecordDao(): IndicatorRecordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "ihealth")
                .fallbackToDestructiveMigration()
                .build()
    }
}
