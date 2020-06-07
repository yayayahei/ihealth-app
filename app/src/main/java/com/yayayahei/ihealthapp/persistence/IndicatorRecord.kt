package com.yayayahei.ihealthapp.persistence

import androidx.room.*
import java.util.*

@Entity(
    tableName = "indicator_record",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Indicator::class,
            parentColumns = arrayOf("iid"),
            childColumns = arrayOf("parent_indicator_id")
        )
    )
)
@TypeConverters(DateConverter::class)
class IndicatorRecord(
    @ColumnInfo(name = "parent_indicator_id") var parentIndicatorId: Int,
    @ColumnInfo(name = "value") var value: Double,
    @ColumnInfo(name = "create_time") var createTime: Date,
    @ColumnInfo(name = "update_time") var updateTime: Date? = null
) {
    @PrimaryKey(autoGenerate = true)
    var irid: Int = 0


    override fun toString(): String {
        return "parent_indicator_id: $parentIndicatorId\n" +
                "value: $value\n" +
                "create_time: $createTime\n" +
                "update_time: $updateTime\n"
    }

}