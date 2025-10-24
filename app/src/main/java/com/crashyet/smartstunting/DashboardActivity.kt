package com.crashyet.smartstunting

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.crashyet.smartstunting.R

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Bind KPI Cards
        setupKpiCard(R.id.cardAnakTerdaftar, "16", "Anak Terdaftar")
        setupKpiCard(R.id.cardSurveyHariIni, "0", "Survey Hari Ini")
        setupKpiCard(R.id.cardStunting, "0", "Kasus Stunting")
        setupKpiCard(R.id.cardGiziNormal, "16", "Status Gizi Normal")

        // Button action
        val btnLihatSemua: Button = findViewById(R.id.btnLihatSemua)
        btnLihatSemua.setOnClickListener {

        }
    }

    private fun setupKpiCard(cardId: Int, value: String, label: String) {
        val cardView = findViewById<androidx.cardview.widget.CardView>(cardId)
        val tvValue = cardView.findViewById<TextView>(R.id.tvKpiValue)
        val tvLabel = cardView.findViewById<TextView>(R.id.tvKpiLabel)

        tvValue.text = value
        tvLabel.text = label
    }
}
