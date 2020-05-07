package com.yayayahei.ihealthapp

import android.content.Context
import com.yayayahei.ihealthapp.persistence.AppDatabase
import com.yayayahei.ihealthapp.persistence.IndicatorDao
import com.yayayahei.ihealthapp.ui.ViewModelFactory

object Injection {
fun provideIndicatorDataSource(context: Context):IndicatorDao{
    val database = AppDatabase.getInstance(context)
    return database.indicatorDao()
}
    fun provideViewModelFactory(context:Context):ViewModelFactory{
        val dataSource = provideIndicatorDataSource(context)
        return ViewModelFactory(dataSource)
    }
}