package com.crashyet.smartstunting.kader

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter


class DataKaderFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BalitaAdapter
    private val listBalita = mutableListOf<Balita>()

    private lateinit var tabPengukuran: TextView
    private lateinit var tabResiko: TextView
    private lateinit var containerPengukuran: View
    private lateinit var containerResiko: View

    private lateinit var trendChart: LineChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_data_kader, container, false)

        // Setup RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = BalitaAdapter(listBalita)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Dummy data
        listBalita.addAll(
            listOf(
                Balita("Rizky Karangturi", "06 Agustus 2025", "Laki-laki", "14 kg", "101.2 cm", "Ns. Ahmad Fauzi", "Normal", "Gizi Baik"),
                Balita("Andi Karangturi", "11 Mei 2025", "Laki-laki", "13.5 kg", "99.4 cm", "Ns. Ahmad Fauzi", "Normal", "Gizi Baik")
            )
        )
        adapter.notifyDataSetChanged()

        // Chart
        trendChart = view.findViewById(R.id.TrendPengukuran6bulan)
        setupChart()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabPengukuran = view.findViewById(R.id.tabPengukuran)
        tabResiko = view.findViewById(R.id.tabResiko)
        containerPengukuran = view.findViewById(R.id.containerPengukuran)
        containerResiko = view.findViewById(R.id.containerResiko)

        showTabPengukuran()

        tabPengukuran.setOnClickListener { showTabPengukuran() }
        tabResiko.setOnClickListener { showTabResiko() }
    }

    private fun showTabPengukuran() {
        containerPengukuran.visibility = View.VISIBLE
        containerResiko.visibility = View.GONE
        tabPengukuran.setBackgroundResource(R.drawable.bg_hijautoska)
        tabPengukuran.setTextColor(Color.WHITE)
        tabResiko.setBackgroundResource(R.color.white)
        tabResiko.setTextColor(Color.DKGRAY)
    }

    private fun showTabResiko() {
        containerPengukuran.visibility = View.GONE
        containerResiko.visibility = View.VISIBLE
        tabResiko.setBackgroundResource(R.drawable.bg_hijautoska)
        tabResiko.setTextColor(Color.WHITE)
        tabPengukuran.setBackgroundResource(R.color.white)
        tabPengukuran.setTextColor(Color.DKGRAY)
    }

    private fun setupChart() {
        // Dummy data untuk 6 bulan terakhir
        val entries = mutableListOf<Entry>()
        val months = listOf("M1","M2","M3","M4","M5","M6")
        val weights = listOf(12.0f, 12.5f, 13.0f, 13.5f, 14.0f, 14.2f) // contoh berat badan

        weights.forEachIndexed { index, w ->
            entries.add(Entry(index.toFloat(), w))
        }

        val dataSet = LineDataSet(entries, "Berat Badan")
        dataSet.color = Color.parseColor("#4CAF50") // hijau
        dataSet.lineWidth = 2f
        dataSet.setDrawCircles(true)
        dataSet.setCircleColor(Color.parseColor("#4CAF50"))
        dataSet.circleRadius = 4f
        dataSet.setDrawValues(true)
        dataSet.valueTextColor = Color.DKGRAY
        dataSet.valueTextSize = 12f

        val lineData = LineData(dataSet)
        trendChart.data = lineData

        // XAxis
        val xAxis = trendChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.valueFormatter = XAxisValueFormatter(months)

        // YAxis
        val leftAxis = trendChart.axisLeft
        val rightAxis = trendChart.axisRight
        rightAxis.isEnabled = false
        leftAxis.axisMinimum = 10f
        leftAxis.axisMaximum = 16f

        trendChart.description.isEnabled = false
        trendChart.animateY(800)
        trendChart.invalidate()
    }

    class XAxisValueFormatter(private val values: List<String>) : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            return if (value.toInt() in values.indices) values[value.toInt()] else ""
        }
    }

}
