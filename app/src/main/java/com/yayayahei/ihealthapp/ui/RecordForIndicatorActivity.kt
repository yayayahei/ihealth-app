package com.yayayahei.ihealthapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.anastr.speedviewlib.Gauge
import com.github.anastr.speedviewlib.Speedometer
import com.yayayahei.ihealthapp.Injection
import com.yayayahei.ihealthapp.R
import com.yayayahei.ihealthapp.persistence.Indicator
import com.yayayahei.ihealthapp.persistence.IndicatorRecord
import com.yayayahei.ihealthapp.ui.indicator.records.IndicatorRecordViewAdapter
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

    private lateinit var indicatorRecordRecyclerView: RecyclerView
    private lateinit var indicatorRecordListViewManager: RecyclerView.LayoutManager
    private lateinit var indicatorRecordViewAdapter: IndicatorRecordViewAdapter
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
                    initIndicatorGaugeView(indicator)
                    renderIndicatorRecordList(indicator)
                }, { error -> println(error) })

        )
    }

    private fun refreshIndicatorGaugeView() {
        if (indicatorRecord != null) {
            println("refresh indicator gauge view by today's latest record: $indicatorRecord")
            indicatorGaugeView.speedTo(indicatorRecord!!.value.toFloat(), 0)
        }
    }

    private fun initIndicatorGaugeView(indicator: Indicator) {
        println("got indicator:\n$indicator")
        indicatorGaugeView.unit = indicator.unit
        indicatorGaugeView.minSpeed = indicator.min.toFloat()
        indicatorGaugeView.maxSpeed = indicator.max.toFloat()
//        indicatorGaugeView.tickNumber = ((indicator.max - indicator.min) / 10).toInt() + 1
        indicatorGaugeView.tickNumber = 11
        indicatorGaugeView.speedTo(
            ((indicator.max - indicator.min) / 2 + indicator.min).toFloat(),
            0
        )

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

    fun renderIndicatorRecordList(indicator: Indicator) {
        disposable.add(
            indicatorRecordViewModel.getRecords(indicator.iid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    indicatorRecordListViewManager = LinearLayoutManager(this)
                    indicatorRecordViewAdapter = IndicatorRecordViewAdapter(it)
                    indicatorRecordRecyclerView =
                        findViewById<RecyclerView>(R.id.indicator_records_recycler_view).apply {
                            setHasFixedSize(true)
                            layoutManager = indicatorRecordListViewManager
                            adapter = indicatorRecordViewAdapter
                        }
                    indicatorRecordViewAdapter.onItemClick = { indicatorRecord ->
                        run {
                            println("click: " + indicatorRecord.irid)
                        }
                    }
                }, { error -> println(error) })
        )

    }
}
