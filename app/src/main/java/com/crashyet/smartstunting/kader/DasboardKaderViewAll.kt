package com.crashyet.smartstunting.kader

import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.crashyet.smartstunting.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class DasboardKaderViewAll : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dasboard_kader_view_all)

        // Atur padding untuk edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton = findViewById<ImageView>(R.id.backtoprev)
        val lineChart = findViewById<LineChart>(R.id.viewallchart)
        val listAnakBeresiko = findViewById<ListView>(R.id.listAnakBeresiko)

        // Tombol kembali
        backButton.setOnClickListener { finish() }

        // Setup Chart
        setupViewAllChart(lineChart)

        // Setup ListView
        setupListView(listAnakBeresiko)
    }

    private fun setupViewAllChart(chart: LineChart) {
        val entries = arrayListOf(
            Entry(0f, 2.5f),
            Entry(1f, 3.2f),
            Entry(2f, 4.0f),
            Entry(3f, 4.8f),
            Entry(4f, 5.5f)
        )

        val dataSet = LineDataSet(entries, "Berat Anak (kg)").apply {
            color = getColor(R.color.purple_500)
            setCircleColor(getColor(R.color.teal_700))
            lineWidth = 2f
            circleRadius = 4f
            setDrawValues(true)
            valueTextColor = getColor(android.R.color.black)
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }

        chart.data = LineData(dataSet)

        chart.apply {
            description.isEnabled = false
            legend.isEnabled = true
            setTouchEnabled(true)
            setPinchZoom(true)
            axisRight.isEnabled = false
            animateX(1500)
        }

        val xAxisLabels = listOf("Jan", "Feb", "Mar", "Apr", "Mei")
        chart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            granularity = 1f
            valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        }

        chart.invalidate()
    }

    private fun setupListView(listView: ListView) {
        val items = arrayOf(
            "Balita A - Berat: 12.5kg",
            "Balita B - Berat: 11.8kg",
            "Balita C - Berat: 13.2kg",
            "Balita D - Berat: 10.9kg"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selected = items[position]
            Toast.makeText(this, "Kamu memilih: $selected", Toast.LENGTH_SHORT).show()
        }
    }
}
