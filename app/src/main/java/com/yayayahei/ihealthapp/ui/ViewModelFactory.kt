package com.yayayahei.ihealthapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yayayahei.ihealthapp.persistence.IndicatorDao

class ViewModelFactory(private val dataSource: IndicatorDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IndicatorViewModel::class.java)) {
            return IndicatorViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}