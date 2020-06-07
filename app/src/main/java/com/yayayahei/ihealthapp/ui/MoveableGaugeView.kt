package com.yayayahei.ihealthapp.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.github.anastr.speedviewlib.PointerSpeedometer

class MoveableGaugeView
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    PointerSpeedometer(context, attrs, defStyleAttr) {
    fun getValueAtDegree(degree: Float): Float {
        return super.getSpeedAtDegree(degree)
    }

    fun refreshValueByDegree(view: View, motionEvent: MotionEvent) {
        val dx = view.width * .5 - motionEvent.x
        val dy = view.height - motionEvent.y
        var angle = Math.toDegrees(Math.atan2(dy.toDouble(), dx)).toInt() + 180

//        fix at min and max endpoint
        if (angle < 180) {
//            fix at max
            if (dx < 0) {
                angle = 360
            }
//            fix at min
            if (dx > 0) {
                angle = 180
            }
        }
//        println(angle)
        setSpeedAt(getValueAtDegree(angle.toFloat()))
    }

    init {
        setOnTouchListener(OnTouchListener() { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_MOVE,
                MotionEvent.ACTION_DOWN,
                MotionEvent.ACTION_UP
                -> {
                    refreshValueByDegree(view, motionEvent)
                    return@OnTouchListener true
                }
                else -> true
            }

        })
    }

}