package com.crashyet.smartstunting.kader

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.content.Intent
import android.widget.LinearLayout
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
        return inflater.inflate(R.layout.fragment_dasbor_kader, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lineChart = view.findViewById<LineChart>(R.id.lineChart)
        setupLineChart(lineChart)

        val kaderviewall_container = view.findViewById<LinearLayout>(R.id.kaderviewall_container)
        kaderviewall_container.setOnClickListener {
            val intent = Intent(requireContext(), DasboardKaderViewAll::class.java)
            startActivity(intent)
        }
    }

    private fun setupLineChart(lineChart: LineChart) {
        // ====== 1️⃣ Contoh data dummy (bisa nanti diganti dari database atau API) ======
        val dataBeratBadan = listOf(
            Entry(1f, 10.3f),
            Entry(2f, 15.3f),
            Entry(3f, 0f),
            Entry(4f, 14f),
            Entry(5f, 0f)
        )

        val dataJumlahPengukuran = listOf(
            Entry(1f, 1f),
            Entry(2f, 1f),
            Entry(3f, 0f),
            Entry(4f, 1f),
            Entry(5f, 0f)
        )

        val dataPersentaseGiziNormal = listOf(
            Entry(1f, 0f),
            Entry(2f, 0f),
            Entry(3f, 0f),
            Entry(4f, 0f),
            Entry(5f, 0f)
        )

        // ====== 2️⃣ Buat 3 dataset berbeda ======

        val dataSetBerat = LineDataSet(dataBeratBadan, "Rata-rata Berat Badan (kg)").apply {
            color = Color.parseColor("#2196F3") // Biru
            lineWidth = 2.5f
            circleRadius = 4f
            setCircleColor(Color.parseColor("#2196F3"))
            setDrawCircleHole(false)
            valueTextColor = Color.BLACK
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }

        val dataSetPengukuran = LineDataSet(dataJumlahPengukuran, "Jumlah Pengukuran").apply {
            color = Color.parseColor("#4CAF50") // Hijau
            lineWidth = 2.5f
            circleRadius = 4f
            setCircleColor(Color.parseColor("#4CAF50"))
            setDrawCircleHole(false)
            valueTextColor = Color.BLACK
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }

        val dataSetGizi = LineDataSet(dataPersentaseGiziNormal, "Persentase Gizi Normal (%)").apply {
            color = Color.parseColor("#FF9800") // Oranye
            lineWidth = 2.5f
            circleRadius = 4f
            setCircleColor(Color.parseColor("#FF9800"))
            setDrawCircleHole(false)
            valueTextColor = Color.BLACK
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }

        // ====== 3️⃣ Gabungkan ke LineData ======
        val lineData = LineData(dataSetBerat, dataSetPengukuran, dataSetGizi)
        lineChart.data = lineData

        // ====== 4️⃣ Styling chart ======
        with(lineChart) {
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                textColor = Color.DKGRAY
                setDrawGridLines(false)
                granularity = 1f
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

        // Refresh chart
        lineChart.invalidate()
    }

}
