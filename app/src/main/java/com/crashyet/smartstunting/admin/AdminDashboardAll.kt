package com.crashyet.smartstunting.admin

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.crashyet.smartstunting.R
import com.crashyet.smartstunting.data.network.ApiClient
import kotlinx.coroutines.launch

class AdminDashboardAll : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard_all)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backButton = findViewById<ImageView>(R.id.backtoprev)
        backButton.setOnClickListener { finish() }


        val SurveyHariIni = findViewById<TextView>(R.id.TV_SurveyHariIni)
        val SurveyBulanIni = findViewById<TextView>(R.id.TV_SurveyBulanIni)
        val TotalAnakAktif = findViewById<TextView>(R.id.TV_TotalAnakAktif)
        val BelumSurvey = findViewById<TextView>(R.id.TV_BelumSurvey)
        val TotalAnakTerdaftar = findViewById<TextView>(R.id.TV_TotalAnakTerdaftar)
        val StatusGiziNormal = findViewById<TextView>(R.id.TV_StatusGiziNormal)
        val GiziKurang = findViewById<TextView>(R.id.TV_GiziKurang)
        val KasusStunting = findViewById<TextView>(R.id.TV_KasusStunting)
        val GiziBuruk = findViewById<TextView>(R.id.TV_GiziBuruk)
        val GiziLebihObesitas = findViewById<TextView>(R.id.TV_GiziLebihObesitas)
        val PengukuranBulanIni = findViewById<TextView>(R.id.TV_PengukuranBulanIni)
        val AlatAntropometri = findViewById<TextView>(R.id.TV_AlatAntropometri)
        val PosyanduDenganAlat = findViewById<TextView>(R.id.TV_PosyanduDenganAlat)
        val AlatTersedia = findViewById<TextView>(R.id.TV_AlatTersedia)
//        val CoveragePengukuran = findViewById<TextView>(R.id.TV_CoveragePengukuran)
//        val PosyanduAktif = findViewById<TextView>(R.id.TV_PosyanduAktif)
//        val StuntingRate = findViewById<TextView>(R.id.TV_StuntingRate)
//        val TrendCoverage = findViewById<TextView>(R.id.TV_TrendCoverage)



        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val userId = sharedPref.getInt("user_id", 0)

        if (userId > 0) {
            lifecycleScope.launch {
                try {
                    // Panggil suspend function
                    val response = ApiClient.getInstance(applicationContext).getStats(userId = userId)
                    val responseSurvey = ApiClient.getInstance(applicationContext).getSurveyStats(userId = userId)
                    val responseAlatStats = ApiClient.getInstance(applicationContext).getAlatStats(userId = userId)
                    val responsePerformanceStats = ApiClient.getInstance(applicationContext).getPerformanceStats(userId = userId)


                    if (responseSurvey.isSuccessful) {
                        val data = responseSurvey.body()

                        // 4. Update UI di Main Thread (otomatis di Coroutine setelah response)
                        if (data != null) {
                            // Masukkan data ke TextView
                            SurveyHariIni.text = "${data.surveyHariIni}"
                            SurveyBulanIni.text = "${data.surveyBulanIni}"
                            TotalAnakAktif.text = "${data.totalAnakAktif}"
                            BelumSurvey.text = "${data.belumSurveyHariIni}"
                        }
                    }

                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            TotalAnakTerdaftar.text = "${data.totalAnak}"
//                            CoveragePengukuran.text = "${data.coveragePengukuran}"
                            StatusGiziNormal.text = "${data.giziNormal}"
                            GiziKurang.text = "${data.giziKurang}"
                            GiziBuruk.text = "${data.giziBuruk}"
                            GiziLebihObesitas.text = "${data.giziLebihObesitas}"
                            KasusStunting.text = "${data.kasusStunting}"
                            PengukuranBulanIni.text = "${data.trendPengukuran.bulanIni}"
                        }
                    }

                    if (responseAlatStats.isSuccessful) {
                        val data = responseAlatStats.body()
                        if (data != null) {
                            AlatAntropometri.text = "${data.totalJenisAlat}"
                            PosyanduDenganAlat.text = "${data.posyanduDenganAlat}"
                            AlatTersedia.text = "${data.totalAlatTersediaSemua}"
                        }
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