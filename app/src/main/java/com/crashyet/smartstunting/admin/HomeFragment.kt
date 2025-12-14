package com.crashyet.smartstunting.admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.crashyet.smartstunting.R
import com.crashyet.smartstunting.data.model.StatsResponse
import com.crashyet.smartstunting.data.network.ApiClient
import com.crashyet.smartstunting.kader.DasboardKaderViewAll
import com.github.mikephil.charting.charts.LineChart
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val btn_kelola = view.findViewById<TextView>(R.id.btn_kelola)
        btn_kelola.setOnClickListener {
            val intent = Intent(requireContext(), ViewAllMenu::class.java)
            startActivity(intent)
        }



        val surveyHariIni = view.findViewById<TextView>(R.id.TV_SurveyHariIni)
        val surveyBulanIni = view.findViewById<TextView>(R.id.TV_SurveyBulanIni)
        val totalAnakAktif = view.findViewById<TextView>(R.id.TV_TotalAnakAktif)
        val belumSurvey = view.findViewById<TextView>(R.id.TV_BelumSurvey)
        val totalPengukuran = view.findViewById<TextView>(R.id.TV_TotalPengukuran)
        val kasusStunting = view.findViewById<TextView>(R.id.TV_KasusStunting)


        // --- PERBAIKAN 1: Ambil SharedPreferences dari Activity Context ---
        val sharedPref = requireActivity().getSharedPreferences("user_prefs", AppCompatActivity.MODE_PRIVATE)
        val userId = sharedPref.getInt("user_id", 0)

        if (userId > 0) {
            lifecycleScope.launch {
                try {
                    // Panggil suspend function
                    val response = ApiClient.getInstance(requireContext()).getStats(userId = userId)
                    val responseSurvey = ApiClient.getInstance(requireContext()).getSurveyStats(userId = userId)
                    val responseAlatStats = ApiClient.getInstance(requireContext()).getAlatStats(userId = userId)
                    val responsePerformanceStats = ApiClient.getInstance(requireContext()).getPerformanceStats(userId = userId)


                    if (responseSurvey.isSuccessful) {
                        val data = responseSurvey.body()

                        // 4. Update UI di Main Thread (otomatis di Coroutine setelah response)
                        if (data != null) {
                            // Masukkan data ke TextView
                            surveyHariIni.text = "${data.surveyHariIni}"
                            surveyBulanIni.text = "${data.surveyBulanIni}"
                            totalAnakAktif.text = "${data.totalAnakAktif}"
                            belumSurvey.text = "${data.belumSurveyHariIni}"
                        }
                    } else {
                        surveyHariIni.text = "0"
                        surveyBulanIni.text = "0"
                        totalAnakAktif.text = "0"
                        belumSurvey.text = "0"
                    }

                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            totalPengukuran.text = "${data.trendPengukuran.bulanIni}"
                            kasusStunting.text = "${data.kasusStunting}"
                        }
                    } else {
                        totalPengukuran.text = "0"
                        kasusStunting.text = "0"
                    }

                } catch (e: Exception) {
                    Log.e("StatsError", "Network/Parsing Error: ${e.message}", e)
                }
            }
        } else {
            Log.w("StatsError", "User ID is 0. Aborting API call.")
        }
    }
}