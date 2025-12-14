package com.crashyet.smartstunting.kader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import android.graphics.Typeface
import androidx.fragment.app.Fragment
import com.crashyet.smartstunting.R

class SurveyKaderFragment : Fragment() {

    private lateinit var tabSurvey: TextView
    private lateinit var tabGizi: TextView
    private lateinit var tabSanitasi: TextView

    private lateinit var scrollSurvey: ScrollView
    private lateinit var scrollGizi: ScrollView
    private lateinit var scrollSanitasi: ScrollView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_survey_kader, container, false)

        // Tabs
        tabSurvey = view.findViewById(R.id.tabSurvey)
        tabGizi = view.findViewById(R.id.tabGizi)
        tabSanitasi = view.findViewById(R.id.tabSanitasi)

        // ScrollViews
        scrollSurvey = view.findViewById(R.id.scrollSurvey)
        scrollGizi = view.findViewById(R.id.scrollGizi)
        scrollSanitasi = view.findViewById(R.id.scrollSanitasi)

        initTabs()

        return view
    }

    private fun initTabs() {
        tabSurvey.setOnClickListener { selectTab(tabSurvey) }
        tabGizi.setOnClickListener { selectTab(tabGizi) }
        tabSanitasi.setOnClickListener { selectTab(tabSanitasi) }

        // Default: Anak
        selectTab(tabSurvey)
    }

    private fun selectTab(selected: TextView) {
        val tabs = listOf(tabSurvey, tabGizi, tabSanitasi)

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
            R.id.tabSurvey -> showOnly(scrollSurvey)
            R.id.tabGizi -> showOnly(scrollGizi)
            R.id.tabSanitasi -> showOnly(scrollSanitasi)
        }
    }

    private fun showOnly(selectedScroll: ScrollView) {
        val allScrolls = listOf(scrollSurvey, scrollGizi, scrollSanitasi)

        allScrolls.forEach { scroll ->
            scroll.visibility = if (scroll == selectedScroll) View.VISIBLE else View.GONE
        }
    }
}
