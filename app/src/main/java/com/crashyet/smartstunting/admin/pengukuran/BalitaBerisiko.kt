package com.crashyet.smartstunting.admin.pengukuran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.R

class BalitaBerisiko : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: BalitaAdapter
    private lateinit var listBalita: MutableList<BalitaModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_balita_berisiko)

        recyclerView = findViewById(R.id.rvBalita)
        recyclerView.layoutManager = LinearLayoutManager(this)

//        listBalita = mutableListOf(
//            BalitaModel("Andi Karangturi", "24 Agustus 2023", 26, "Laki-laki", "5.0 kg", "5.0 cm", "Tinggi", "Gizi Kurang", "-5.77", "-26.19", "-4.80", "Sangat Pendek"),
//            BalitaModel("Riko Gentasari", "24 Agustus 2024", 12, "Perempuan", "9.0 kg", "80.0 cm", "Rendah", "Gizi Baik", "0.08", "2.31", "-1.50", "Normal")
//        )

//        adapter = BalitaAdapter(listBalita)
//        recyclerView.adapter = adapter
    }
}