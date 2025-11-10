package com.crashyet.smartstunting.admin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.crashyet.smartstunting.R
import com.crashyet.smartstunting.kader.DasboardKaderViewAll
import com.github.mikephil.charting.charts.LineChart

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val menuAll = view.findViewById<LinearLayout>(R.id.allfitur_container)
        menuAll.setOnClickListener {
            val intent = Intent(requireContext(), ViewAllMenu::class.java)
            startActivity(intent)
        }

        val dashboardAll = view.findViewById<LinearLayout>(R.id.adminDashboard_viewall)
        dashboardAll.setOnClickListener {
            val intent = Intent(requireContext(), AdminDashboardAll::class.java)
            startActivity(intent)
        }
    }
}