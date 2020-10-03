package com.yayayahei.ihealthapp.ui.indicator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yayayahei.ihealthapp.Injection
import com.yayayahei.ihealthapp.R
import com.yayayahei.ihealthapp.persistence.indicator.Indicator
import com.yayayahei.ihealthapp.ui.*
import com.yayayahei.ihealthapp.ui.indicator.records.RecordForIndicatorActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

const val INDICATOR_ID = "com.yayayahei.ihealthapp.INDICATOR_ID"
const val INDICATOR_NAME = "com.yayayahei.ihealthapp.INDICATOR_NAME"

class MainActivity : AppCompatActivity() {

    private lateinit var indicatorRecyclerView: RecyclerView
    private lateinit var viewAdapter: IndicatorViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: IndicatorViewModel by viewModels { viewModelFactory }

    private val disposable = CompositeDisposable()
    private lateinit var indicators: List<Indicator>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelFactory = Injection.provideViewModelFactory(this)

        getAllIndicators()

    }

    fun renderIndicatorsList() {
        viewManager = LinearLayoutManager(this)
        viewAdapter =
            IndicatorViewAdapter(indicators)
        indicatorRecyclerView = findViewById<RecyclerView>(R.id.indicator_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        viewAdapter.onItemClick = { indicator ->
            run {
                println("click: " + indicator.iid)
                val intent = Intent(this, RecordForIndicatorActivity::class.java)
                intent.putExtra(INDICATOR_ID,indicator.iid)
                intent.putExtra(INDICATOR_NAME,indicator.name)
                startActivity(intent)
            }
        }
    }

    private fun getAllIndicators() {
        disposable.add(
            viewModel
                .getAllIndicators()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("got all indicators")
                    println("indicators in db")
                    for (indi in it) {
                        println(indi)
                    }
                    indicators = it
                    renderIndicatorsList()
                }, { error -> println(error) })
        )
    }

    fun clearAllIndicators(view: View) {
        disposable.add(
            viewModel.deleteAllIndicators()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("deleted all indicators")
                }, { error -> println(error) })
        )
    }

    fun gotoAddIndicatorActivity(view: View) {
        val intent = Intent(this, CreateIndicatorActivity::class.java)
        startActivity(intent)
    }

}
