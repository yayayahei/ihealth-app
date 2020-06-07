package com.yayayahei.ihealthapp.persistence

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Indicator::class,IndicatorRecord::class], version = 2)
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
