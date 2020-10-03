package com.yayayahei.ihealthapp

import android.content.Context
import com.yayayahei.ihealthapp.persistence.AppDatabase
import com.yayayahei.ihealthapp.persistence.indicator.IndicatorDao
import com.yayayahei.ihealthapp.persistence.indicator.records.IndicatorRecordDao
import com.yayayahei.ihealthapp.ui.ViewModelFactory

object Injection {
    fun provideIndicatorDataSource(context: Context): IndicatorDao {
        val database = AppDatabase.getInstance(context)
        return database.indicatorDao()
    }

    fun provideIndicatorRecordDataSource(context: Context): IndicatorRecordDao {
        val database = AppDatabase.getInstance(context)
        return database.indicatorRecordDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        return ViewModelFactory(
            provideIndicatorDataSource(context),
            provideIndicatorRecordDataSource(context)
        )
    }
}