package com.crashyet.smartstunting.kader

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.crashyet.smartstunting.R

class DataKaderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment
        return inflater.inflate(R.layout.fragment_data_kader, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil referensi view dari layout
        val tabPengukuran = view.findViewById<TextView>(R.id.tabPengukuran)
        val tabResiko = view.findViewById<TextView>(R.id.tabResiko)
        val containerPengukuran = view.findViewById<View>(R.id.containerPengukuran)
        val containerResiko = view.findViewById<View>(R.id.containerResiko)

        // Default tampilan saat fragment pertama kali dibuka
        containerPengukuran.visibility = View.VISIBLE
        containerResiko.visibility = View.GONE
        tabPengukuran.setBackgroundResource(R.drawable.bg_weclome)
        tabPengukuran.setTextColor(Color.WHITE)
        tabResiko.setBackgroundResource(R.color.white)
        tabResiko.setTextColor(Color.DKGRAY)

        // Saat tab Pengukuran diklik
        tabPengukuran.setOnClickListener {
            containerPengukuran.visibility = View.VISIBLE
            containerResiko.visibility = View.GONE

            tabPengukuran.setBackgroundResource(R.drawable.bg_weclome)
            tabPengukuran.setTextColor(Color.WHITE)
            tabResiko.setBackgroundResource(R.color.white)
            tabResiko.setTextColor(Color.DKGRAY)
        }

        // Saat tab Resiko diklik
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
