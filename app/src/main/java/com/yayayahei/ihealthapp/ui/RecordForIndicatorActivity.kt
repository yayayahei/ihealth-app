package com.yayayahei.ihealthapp.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.anastr.speedviewlib.Gauge
import com.github.anastr.speedviewlib.PointerSpeedometer
import com.github.anastr.speedviewlib.Speedometer
import com.yayayahei.ihealthapp.Injection
import com.yayayahei.ihealthapp.R
import com.yayayahei.ihealthapp.persistence.Indicator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class RecordForIndicatorActivity : AppCompatActivity() {
    private lateinit var indicatorGaugeView: PointerSpeedometer
    private lateinit var indicatorGaugeDateView:TextView
    private lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: IndicatorViewModel by viewModels{ viewModelFactory }

    private val disposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_for_indicator)
        val indicatorId = intent.getIntExtra(INDICATOR_ID, 0)
        val indicatorName = intent.getStringExtra(INDICATOR_NAME)
        showAppBar()
        setActionBarTitle(indicatorName)
        indicatorGaugeView=findViewById(R.id.indicator_gauge)
        indicatorGaugeDateView=findViewById<TextView>(R.id.gauge_date)
        indicatorGaugeView.setStartDegree(180)
        indicatorGaugeView.setEndDegree(360)
        indicatorGaugeView.speedometerMode=Speedometer.Mode.TOP
        indicatorGaugeView.textColor=R.color.colorPrimaryDark
        indicatorGaugeView.speedTextPosition= Gauge.Position.BOTTOM_CENTER
        viewModelFactory = Injection.provideViewModelFactory(this)
        getIndicatorById(indicatorId)
    }
    private fun getIndicatorById(indicatorId: Int){
        disposable.add(viewModel.getAllIndicators(intArrayOf(indicatorId)).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                gotIndicatorData(it.first())
            },{error-> println(error)}))
    }
    private fun gotIndicatorData(indicator:Indicator){
        indicatorGaugeView.unit=indicator.unit
        indicatorGaugeView.minSpeed=indicator.min.toFloat()
        indicatorGaugeView.maxSpeed=indicator.max.toFloat()
        indicatorGaugeView.tickNumber=((indicator.max-indicator.min)/10).toInt()+1
        indicatorGaugeView.speedTo(((indicator.max-indicator.min)/2).toFloat())
        indicatorGaugeDateView.text=SimpleDateFormat("yyyy-MM-dd",Locale.CHINA).format(Date())

    }
    private fun setActionBarTitle(indicatorName:String) {
        supportActionBar?.title = indicatorName
    }


    private fun showAppBar() {
        val toolbar = findViewById<Toolbar>(R.id.record_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
