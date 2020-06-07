package com.yayayahei.ihealthapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.anastr.speedviewlib.Gauge
import com.github.anastr.speedviewlib.Speedometer
import com.yayayahei.ihealthapp.Injection
import com.yayayahei.ihealthapp.R
import com.yayayahei.ihealthapp.persistence.Indicator
import com.yayayahei.ihealthapp.persistence.IndicatorRecord
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class RecordForIndicatorActivity : AppCompatActivity() {
    private lateinit var indicatorGaugeView: MoveableGaugeView
    private lateinit var indicatorGaugeDateView: TextView
    private lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: IndicatorViewModel by viewModels { viewModelFactory }
    private val indicatorRecordViewModel: IndicatorRecordViewModel by viewModels { viewModelFactory }
    private val disposable = CompositeDisposable()

    private lateinit var indicator: Indicator
    private var indicatorRecord: IndicatorRecord? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_for_indicator)

        val indicatorName = intent.getStringExtra(INDICATOR_NAME)
        showAppBar(indicatorName)

        findViews()
        initIndicatorGaugeView()

        viewModelFactory = Injection.provideViewModelFactory(this)

        val indicatorId = intent.getIntExtra(INDICATOR_ID, 0)
        getIndicatorById(indicatorId)
    }

    private fun findViews() {
        indicatorGaugeView = findViewById(R.id.indicator_gauge)
        indicatorGaugeDateView = findViewById(R.id.gauge_date)
    }

    private fun initIndicatorGaugeView() {
        indicatorGaugeView.setStartDegree(180)
        indicatorGaugeView.setEndDegree(360)
        indicatorGaugeView.speedometerMode = Speedometer.Mode.TOP
        indicatorGaugeView.textColor = R.color.colorPrimaryDark
        indicatorGaugeView.speedTextPosition = Gauge.Position.BOTTOM_CENTER
    }

    private fun getIndicatorById(indicatorId: Int) {
        disposable.add(
            viewModel.getAllIndicators(intArrayOf(indicatorId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    indicator = it.first()
                    initIndicatorGaugeView(it.first())
                }, { error -> println(error) })

            )
    }

    private fun refreshIndicatorGaugeView() {
        if (indicatorRecord != null) {
            indicatorGaugeView.speedTo(indicatorRecord!!.value.toFloat(), 0)
        } else {
            indicatorGaugeView.speedTo(
                ((indicator.max - indicator.min) / 2 + indicator.min).toFloat(),
                0
            )
        }
    }

    private fun initIndicatorGaugeView(
        indicator: Indicator
    ) {
        indicatorGaugeView.unit = indicator.unit
        indicatorGaugeView.minSpeed = indicator.min.toFloat()
        indicatorGaugeView.maxSpeed = indicator.max.toFloat()
//        indicatorGaugeView.tickNumber = ((indicator.max - indicator.min) / 10).toInt() + 1
        indicatorGaugeView.tickNumber = 11
        disposable.add(
            indicatorRecordViewModel.getLastRecordOfToday(indicator.iid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    indicatorRecord = it
                    refreshIndicatorGaugeView()
                }, { error -> println(error) })
        )

        indicatorGaugeDateView.text = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(Date())

    }

    private fun showAppBar(indicatorName: String) {
        val toolbar = findViewById<Toolbar>(R.id.record_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = indicatorName
    }

    fun gotoEditGaugeActivity(view: View) {
        val intent = Intent(this, EditGaugeActivity::class.java)
        startActivity(intent)
    }

    fun saveIndicator(view: View) {
        val indicatorRecord =
            IndicatorRecord(indicator.iid, indicatorGaugeView.speed.toDouble(), Date())
        println(indicator)
        println(indicatorRecord)
        disposable.add(
            indicatorRecordViewModel
                .insertIndicatorRecord(indicatorRecord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println(indicatorRecord.toString() + "\nsaved!")
//                    println("indicators in db")
//                    for (indi in viewModel.getAllIndicators()) {
//                        println(indi)
//                    }
                    val text =
                        "${indicatorRecord.value}${indicator.unit} ${getString(R.string.save_succeed)}"
                    val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_LONG)
                    toast.show()
                }, { error -> println(error) })
        )
    }
}
