package com.crashyet.smartstunting.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.crashyet.smartstunting.R
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class LaporanFragment : Fragment() {
    private lateinit var spinnerDariBulan: Spinner
    private lateinit var spinnerSampaiBulan: Spinner
    private lateinit var spinnerTahun: Spinner
    private lateinit var spinnerJenisLaporan: Spinner
    private lateinit var btnGenerate: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment
        val view = inflater.inflate(R.layout.fragment_laporan, container, false)

        // 🔗 Inisialisasi komponen dari layout
        spinnerDariBulan = view.findViewById(R.id.spinnerDariBulan)
        spinnerSampaiBulan = view.findViewById(R.id.spinnerSampaiBulan)
        spinnerTahun = view.findViewById(R.id.spinnerTahun)
        spinnerJenisLaporan = view.findViewById(R.id.spinnerJenisLaporan)
        btnGenerate = view.findViewById(R.id.btnGenerate)

        setupSpinners()
        setupButton(view)

        return view
    }

    private fun setupSpinners() {
        val bulanList = listOf(
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        )

        val tahunSekarang = Calendar.getInstance().get(Calendar.YEAR)
        val tahunList = (tahunSekarang - 4..tahunSekarang + 1).map { it.toString() }

        val jenisList = listOf(
            "Rekap Kegiatan", "Kinerja Bulanan", "Pengeluaran", "Pendapatan", "Statistik Umum"
        )

        // Gunakan requireContext() untuk context di dalam Fragment
        val adapterBulan = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, bulanList)
        val adapterTahun = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, tahunList)
        val adapterJenis = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, jenisList)

        spinnerDariBulan.adapter = adapterBulan
        spinnerSampaiBulan.adapter = adapterBulan
        spinnerTahun.adapter = adapterTahun
        spinnerJenisLaporan.adapter = adapterJenis

        spinnerDariBulan.setSelection(Calendar.getInstance().get(Calendar.MONTH))
        spinnerSampaiBulan.setSelection(Calendar.getInstance().get(Calendar.MONTH))
        spinnerTahun.setSelection(adapterTahun.getPosition(tahunSekarang.toString()))
    }

    private fun setupButton(view: View) {
        btnGenerate.setOnClickListener {
            val dariBulan = spinnerDariBulan.selectedItem.toString()
            val sampaiBulan = spinnerSampaiBulan.selectedItem.toString()
            val tahun = spinnerTahun.selectedItem.toString()
            val jenis = spinnerJenisLaporan.selectedItem?.toString() ?: "-"

            if (dariBulan.isEmpty() || sampaiBulan.isEmpty() || tahun.isEmpty()) {
                Snackbar.make(view, "Harap isi semua filter laporan.", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Snackbar.make(
                view,
                "Mengunduh laporan $jenis ($dariBulan - $sampaiBulan $tahun)...",
                Snackbar.LENGTH_LONG
            ).show()

            // TODO: Panggil API / proses download laporan di sini
        }
    }
}