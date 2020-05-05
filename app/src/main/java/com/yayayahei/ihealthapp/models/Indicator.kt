package com.yayayahei.ihealthapp.models

const val DEFAULT_INDICATOR_MIN = 0.0
const val DEFAULT_INDICATOR_MAX = 100.0
const val DEFAULT_INDICATOR_NAME = "体重"
const val DEFAULT_INDICATOR_UNIT = "kg"

class Indicator(
    var name: String,
    var unit: String,
    var min: Double,
    var max: Double
) {
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
