package com.crashyet.smartstunting.admin

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.crashyet.smartstunting.R
import com.crashyet.smartstunting.admin.pengukuran.AdminPengukuran
import com.crashyet.smartstunting.admin.pengukuran.BalitaBerisiko
import com.crashyet.smartstunting.admin.data.DataAnak
import com.crashyet.smartstunting.admin.data.DataKeluarga
import com.crashyet.smartstunting.admin.data.DataKader
import com.crashyet.smartstunting.admin.data.DataPosyandu
import com.crashyet.smartstunting.admin.data.PetugasPosyandu
import com.crashyet.smartstunting.admin.data.AlatAntropometri
import com.crashyet.smartstunting.admin.data.DataPuskesmas

class ViewAllMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_all_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backButton = findViewById<ImageView>(R.id.backtoprev)
        val dataPengukuran = findViewById<LinearLayout>(R.id.data_pengukuran)
        val balitaBerisiko = findViewById<LinearLayout>(R.id.balita_berisiko)
        val dataAnak = findViewById<LinearLayout>(R.id.data_anak)
        val dataKeluarga = findViewById<LinearLayout>(R.id.data_keluarga)
        val dataKader = findViewById<LinearLayout>(R.id.data_kader)
        val dataPetugas = findViewById<LinearLayout>(R.id.data_petugas)
        val dataAlat = findViewById<LinearLayout>(R.id.data_alat)
        val dataPosyandu = findViewById<LinearLayout>(R.id.data_posyandu)
        val dataPuskesmas = findViewById<LinearLayout>(R.id.data_puskesmas)
        val surveyHarian = findViewById<LinearLayout>(R.id.survey_harian)
        val gizi = findViewById<LinearLayout>(R.id.gizi)
        val sanitasiLingkungan = findViewById<LinearLayout>(R.id.sanitasi_lingkungan)
        val menuMakanan = findViewById<LinearLayout>(R.id.menu_makanan)
        val riwayatMenu = findViewById<LinearLayout>(R.id.riwayat_menu)
//        val rekomendasiGizi = findViewById<LinearLayout>(R.id.rekomendasi_gizi)
//        val laporanBulanan = findViewById<LinearLayout>(R.id.laporan_bulanan)
//        val prevalensiWilayah = findViewById<LinearLayout>(R.id.prevalensi_wilayah)

        backButton.setOnClickListener { finish() }

        dataPengukuran.setOnClickListener {
            val intent = Intent(this, AdminPengukuran::class.java)
            startActivity(intent)
        }

        balitaBerisiko.setOnClickListener {
            val intent = Intent(this, BalitaBerisiko::class.java)
            startActivity(intent)
        }

        dataAnak.setOnClickListener {
            val intent = Intent(this, DataAnak::class.java)
            startActivity(intent)
        }

        dataKeluarga.setOnClickListener {
            val intent = Intent(this, DataKeluarga::class.java)
            startActivity(intent)
        }

        dataKader.setOnClickListener {
            val intent = Intent(this, DataKader::class.java)
            startActivity(intent)
        }

        dataPetugas.setOnClickListener {
            val intent = Intent(this, PetugasPosyandu::class.java)
            startActivity(intent)
        }

        dataAlat.setOnClickListener {
            val intent = Intent(this, AlatAntropometri::class.java)
            startActivity(intent)
        }

        dataPosyandu.setOnClickListener {
            val intent = Intent(this, DataPosyandu::class.java)
            startActivity(intent)
        }

        dataPuskesmas.setOnClickListener {
            val intent = Intent(this, DataPuskesmas::class.java)
            startActivity(intent)
        }

        surveyHarian.setOnClickListener {
            val intent = Intent(this, AdminSurveyHarianActivity::class.java)
            startActivity(intent)
        }

        gizi.setOnClickListener {
            val intent = Intent(this, AdminGiziActivity::class.java)
            startActivity(intent)
        }

        sanitasiLingkungan.setOnClickListener {
            val intent = Intent(this, SanitasiLingkunganActivity::class.java)
            startActivity(intent)
        }

        menuMakanan.setOnClickListener {
            val intent = Intent(this, MenuMakananActivity::class.java)
            startActivity(intent)
        }

        riwayatMenu.setOnClickListener {
            val intent = Intent(this, RiwayatMenuActivity::class.java)
            startActivity(intent)
        }

    }

}