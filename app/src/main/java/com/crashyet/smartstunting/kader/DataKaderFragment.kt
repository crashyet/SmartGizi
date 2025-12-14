package com.crashyet.smartstunting.kader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import android.graphics.Typeface
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.crashyet.smartstunting.R

class DataKaderFragment : Fragment() {

    private lateinit var tabAnak: TextView
    private lateinit var tabKeluarga: TextView
    private lateinit var tabKader: TextView
    private lateinit var tabPosyandu: TextView

    private lateinit var scrollAnak: ScrollView
    private lateinit var scrollKeluarga: ScrollView
    private lateinit var scrollKader: ScrollView
    private lateinit var scrollPosyandu: ScrollView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_data_kader, container, false)

        // Tabs
        tabAnak = view.findViewById(R.id.tabAnak)
        tabKeluarga = view.findViewById(R.id.tabKeluarga)
        tabKader = view.findViewById(R.id.tabKader)
        tabPosyandu = view.findViewById(R.id.tabPosyandu)

        // ScrollViews
        scrollAnak = view.findViewById(R.id.scrollAnak)
        scrollKeluarga = view.findViewById(R.id.scrollKeluarga)
        scrollKader = view.findViewById(R.id.scrollKader)
        scrollPosyandu = view.findViewById(R.id.scrollPosyandu)

        initTabs()
        return view
    }

    private fun initTabs() {
        tabAnak.setOnClickListener { selectTab(tabAnak) }
        tabKeluarga.setOnClickListener { selectTab(tabKeluarga) }
        tabKader.setOnClickListener { selectTab(tabKader) }
        tabPosyandu.setOnClickListener { selectTab(tabPosyandu) }

        // Default: Anak
        selectTab(tabAnak)
    }

    private fun selectTab(selected: TextView) {
        val tabs = listOf(tabAnak, tabKeluarga, tabKader, tabPosyandu)

        // Ganti background tab
        tabs.forEach { tab ->
            if (tab == selected) {
                tab.setBackgroundResource(R.drawable.bg_toskabulat)
                tab.setTextColor(requireContext().getColor(android.R.color.white))
                tab.setTypeface(null,Typeface.BOLD)
            } else {
                tab.setBackgroundResource(android.R.color.white)
                tab.setTextColor(requireContext().getColor(R.color.dark_gray))
                tab.setTypeface(null, Typeface.NORMAL)
            }
        }

        // Tampilkan ScrollView sesuai tab
        when (selected.id) {
            R.id.tabAnak -> showOnly(scrollAnak)
            R.id.tabKeluarga -> showOnly(scrollKeluarga)
            R.id.tabKader -> showOnly(scrollKader)
            R.id.tabPosyandu -> showOnly(scrollPosyandu)
        }
    }

    private fun showOnly(selectedScroll: ScrollView) {
        val allScrolls = listOf(scrollAnak, scrollKeluarga, scrollKader, scrollPosyandu)

        allScrolls.forEach { scroll ->
            scroll.visibility = if (scroll == selectedScroll) View.VISIBLE else View.GONE
        }
    }
}
