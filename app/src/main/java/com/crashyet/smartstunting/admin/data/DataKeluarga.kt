package com.crashyet.smartstunting.admin.data

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.R
import com.crashyet.smartstunting.data.adapter.DataKeluargaAdapter
import com.crashyet.smartstunting.data.model.DataKeluargaModel

class DataKeluarga : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var keluargaAdapter: DataKeluargaAdapter
    private val listKeluarga = mutableListOf<DataKeluargaModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_data_keluarga) // layout activity kamu

        // 1️⃣ Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.rvDataKeluarga)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        // 2️⃣ Tambahkan data dummy (sementara)
        listKeluarga.add(
            DataKeluargaModel(
                id = 1,
                nama_ayah = "Budi Santoso",
                nama_ibu = "Siti Aminah",
                desa = "Karangjati",
                posyandu = "Posyandu Mawar",
                no_kk = "1234567890",
                status_ekonomi = "Menengah ke bawah",
                alamat = "Jl. Melati No.12",
                rt_rw = "01/02",
                jumlah_anak = "3",
                tahapan_ks = "KS II",
                status_kek_ibu = "Kondisi Sehat"
            )
        )

        listKeluarga.add(
            DataKeluargaModel(
                id = 2,
                nama_ayah = "Andi Wijaya",
                nama_ibu = "Dewi Lestari",
                desa = "Sidomulyo",
                posyandu = "Posyandu Melati",
                no_kk = "9876543210",
                status_ekonomi = "Menengah ke atas",
                alamat = "Jl. Kenanga No.5",
                rt_rw = "02/04",
                jumlah_anak = "2",
                tahapan_ks = "KS I",
                status_kek_ibu = "Normal"
            )
        )

        // 3️⃣ Inisialisasi Adapter
        keluargaAdapter = DataKeluargaAdapter(listKeluarga)

        // 4️⃣ Set Adapter ke RecyclerView
        recyclerView.adapter = keluargaAdapter
    }
}
