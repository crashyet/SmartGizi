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

class DataKaderFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BalitaAdapter
    private val listBalita = mutableListOf<Balita>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment
        val view = inflater.inflate(R.layout.fragment_data_kader, container, false)

        // --- Setup RecyclerView ---
        recyclerView = view.findViewById(R.id.recyclerView)
        listBalita.addAll(
            listOf(
                Balita("Rizky Karangturi", "06 Agustus 2025", "Laki", "14 kg", "101.2 cm", "Ns. Ahmad Fauzi", "Normal", "Gizi Baik"),
                Balita("Andi Karangturi", "11 Mei 2025", "Laki", "13.5 kg", "99.4 cm", "Ns. Ahmad Fauzi", "Normal", "Gizi Baik")
            )
        )
        adapter = BalitaAdapter(listBalita)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- Setup Tabs ---
        val tabPengukuran = view.findViewById<TextView>(R.id.tabPengukuran)
        val tabResiko = view.findViewById<TextView>(R.id.tabResiko)
        val containerPengukuran = view.findViewById<View>(R.id.containerPengukuran)
        val containerResiko = view.findViewById<View>(R.id.containerResiko)

        // Default tampilan
        containerPengukuran.visibility = View.VISIBLE
        containerResiko.visibility = View.GONE
        tabPengukuran.setBackgroundResource(R.drawable.bg_weclome)
        tabPengukuran.setTextColor(Color.WHITE)
        tabResiko.setBackgroundResource(R.color.white)
        tabResiko.setTextColor(Color.DKGRAY)

        tabPengukuran.setOnClickListener {
            containerPengukuran.visibility = View.VISIBLE
            containerResiko.visibility = View.GONE

            tabPengukuran.setBackgroundResource(R.drawable.bg_weclome)
            tabPengukuran.setTextColor(Color.WHITE)
            tabResiko.setBackgroundResource(R.color.white)
            tabResiko.setTextColor(Color.DKGRAY)
        }

        tabResiko.setOnClickListener {
            containerPengukuran.visibility = View.GONE
            containerResiko.visibility = View.VISIBLE

            tabResiko.setBackgroundResource(R.drawable.bg_weclome)
            tabResiko.setTextColor(Color.WHITE)
            tabPengukuran.setBackgroundResource(R.color.white)
            tabPengukuran.setTextColor(Color.DKGRAY)
        }
    }
}
