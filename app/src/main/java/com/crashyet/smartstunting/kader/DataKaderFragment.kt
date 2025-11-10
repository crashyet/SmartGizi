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
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter

class DataKaderFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BalitaAdapter
    private val listBalita = mutableListOf<Balita>()

    private lateinit var recyclerViewBalitaResiko: RecyclerView
    private lateinit var resikoAdapter: BalitaResikoAdapter
    private val listBalitaResiko = mutableListOf<BalitaResiko>()

    private lateinit var tabPengukuran: TextView
    private lateinit var tabResiko: TextView
    private lateinit var containerPengukuran: View
    private lateinit var containerResiko: View

    private lateinit var trendChart: LineChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_data_kader, container, false)

        // === 1️⃣ Pengukuran (Balita) ===
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = BalitaAdapter(listBalita)
        recyclerView.adapter = adapter

        listBalita.addAll(
            listOf(
                Balita(
                    "Rizky Karangturi",
                    "06 Agustus 2025",
                    "Laki-laki",
                    "14 kg",
                    "101.2 cm",
                    "Ns. Ahmad Fauzi",
                    "Normal",
                    "Gizi Baik",
                    "Terlentang",
                    "4th 2bln 3hari"
                ),
                Balita(
                    "Andy Karangturi",
                    "11 Mei 2025",
                    "Laki-laki",
                    "13.5 kg",
                    "98.7 cm",
                    "Ns. Ahmad Fauzi",
                    "Normal",
                    "Gizi Baik",
                    "Berdiri",
                    "2th 2bln 3hari"
                )
            )
        )
        adapter.notifyDataSetChanged()

        // === 2️⃣ Balita Berisiko ===
        recyclerViewBalitaResiko = view.findViewById(R.id.recyclerViewBalita)
        recyclerViewBalitaResiko.layoutManager = LinearLayoutManager(requireContext())
        resikoAdapter = BalitaResikoAdapter(listBalitaResiko)
        recyclerViewBalitaResiko.adapter = resikoAdapter

        // Dummy Data Balita Berisiko
        listBalitaResiko.addAll(
            listOf(
                BalitaResiko(
                    "Eko Gentasari",
                    "08 Oktober 2025 • 11 bln",
                    "Laki-laki",
                    "9 kg",
                    "80 cm",
                    "Rendah",
                    "Gizi Baik",
                    "0.08",
                    "2.31",
                    "-1.50",
                    "Normal"
                ),
                BalitaResiko(
                    "Siti Munaroh",
                    "09 Oktober 2025 • 11 bln",
                    "Perempuan",
                    "10 kg",
                    "75 cm",
                    "AMAN BANG",
                    "Gizi Baik",
                    "0.08",
                    "2.31",
                    "-1.50",
                    "SUPER SEHAT"
                )
            )
        )
        resikoAdapter.notifyDataSetChanged()

        // === 3️⃣ Chart ===
        trendChart = view.findViewById(R.id.TrendPengukuran6bulan)
        setupChart()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // === Tab Inisialisasi ===
        tabPengukuran = view.findViewById(R.id.tabPengukuran)
        tabResiko = view.findViewById(R.id.tabResiko)
        containerPengukuran = view.findViewById(R.id.containerPengukuran)
        containerResiko = view.findViewById(R.id.containerResiko)

        // Default tampilan tab pertama
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
        val entries = mutableListOf<Entry>()
        val months = listOf("M1", "M2", "M3", "M4", "M5", "M6")
        val weights = listOf(12.0f, 12.5f, 13.0f, 13.5f, 14.0f, 14.2f)

        weights.forEachIndexed { index, w -> entries.add(Entry(index.toFloat(), w)) }

        val dataSet = LineDataSet(entries, "Berat Badan").apply {
            color = Color.parseColor("#4CAF50")
            lineWidth = 2f
            setDrawCircles(true)
            setCircleColor(Color.parseColor("#4CAF50"))
            circleRadius = 4f
            setDrawValues(true)
            valueTextColor = Color.DKGRAY
            valueTextSize = 12f
        }

        trendChart.data = LineData(dataSet)

        trendChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            granularity = 1f
            valueFormatter = XAxisValueFormatter(months)
        }

        trendChart.axisLeft.axisMinimum = 10f
        trendChart.axisLeft.axisMaximum = 16f
        trendChart.axisRight.isEnabled = false

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
