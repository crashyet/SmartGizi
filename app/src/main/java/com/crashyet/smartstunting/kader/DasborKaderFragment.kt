package com.crashyet.smartstunting.kader

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.crashyet.smartstunting.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class DasborKaderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment
        return inflater.inflate(R.layout.fragment_dasbor_kader, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil referensi ke LineChart dari layout
        val lineChart = view.findViewById<LineChart>(R.id.lineChart)

        // Setup chart-nya
        setupLineChart(lineChart)
    }

    private fun setupLineChart(lineChart: LineChart) {
        // Contoh data — bisa kamu ganti dengan data dinamis
        val entries = listOf(
            Entry(1f, 10f),
            Entry(2f, 15f),
            Entry(3f, 8f),
            Entry(4f, 18f),
            Entry(5f, 13f),
            Entry(6f, 20f)
        )

        val dataSet = LineDataSet(entries, "Survey per Hari").apply {
            color = Color.parseColor("#2196F3") // Biru
            valueTextColor = Color.BLACK
            lineWidth = 2.5f
            circleRadius = 5f
            setCircleColor(Color.parseColor("#2196F3"))
            setDrawCircleHole(false)
            setDrawValues(true)
            mode = LineDataSet.Mode.CUBIC_BEZIER // Biar halus
        }

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        with(lineChart) {
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                textColor = Color.DKGRAY
                setDrawGridLines(false)
            }

            axisLeft.apply {
                textColor = Color.DKGRAY
                axisLineColor = Color.LTGRAY
            }

            axisRight.isEnabled = false
            description.isEnabled = false
            legend.textColor = Color.DKGRAY

            setTouchEnabled(true)
            setPinchZoom(true)
            animateY(1000)
        }

        lineChart.invalidate()
    }
}
