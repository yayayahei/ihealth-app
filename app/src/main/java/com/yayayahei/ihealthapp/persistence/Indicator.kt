package com.yayayahei.ihealthapp.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val DEFAULT_INDICATOR_MIN = 0.0
const val DEFAULT_INDICATOR_MAX = 100.0
const val DEFAULT_INDICATOR_NAME = "体重"
const val DEFAULT_INDICATOR_UNIT = "kg"

@Entity
data class Indicator(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "unit") var unit: String,
    @ColumnInfo(name = "min") var min: Double,
    @ColumnInfo(name = "max") var max: Double
) {
    @PrimaryKey(autoGenerate = true)
    var iid: Int = 0

    init {
        if (name.isEmpty()) {
            name = DEFAULT_INDICATOR_NAME
        }
        if (unit.isEmpty()) {
            unit = DEFAULT_INDICATOR_UNIT
        }
    }

    override fun toString(): String {
        return "name: $name\n" +
                "unit: $unit\n" +
                "min: $min\n" +
                "max: $max\n"
    }
}
