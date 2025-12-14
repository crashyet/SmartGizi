package com.crashyet.smartstunting.admin.data

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.R
import com.crashyet.smartstunting.data.adapter.DataAnakAdapter
import com.crashyet.smartstunting.data.adapter.DataKeluargaAdapter
import com.crashyet.smartstunting.data.model.DataAnakModel
import com.crashyet.smartstunting.data.model.DataKeluargaModel
import com.crashyet.smartstunting.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataKeluarga : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var keluargaAdapter: DataKeluargaAdapter
    private val listKeluarga = mutableListOf<DataKeluargaModel>()
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_data_keluarga) // layout activity kamu

        // 1️⃣ Inisialisasi RecyclerView
//        recyclerView = findViewById(R.id.rvDataKeluarga)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)

        // 2️⃣ Tambahkan data dummy (sementara)
//        listKeluarga.add(
//            DataKeluargaModel(
//                id = 1,
//                nama_ayah = "Budi Santoso",
//                nama_ibu = "Siti Aminah",
//                desa = "Karangjati",
//                posyandu = "Posyandu Mawar",
//                no_kk = "1234567890",
//                status_ekonomi = "Menengah ke bawah",
//                alamat = "Jl. Melati No.12",
//                rt_rw = "01/02",
//                jumlah_anak = "3",
//                tahapan_ks = "KS II",
//                status_kek_ibu = "Kondisi Sehat"
//            )
//        )
//
//        listKeluarga.add(
//            DataKeluargaModel(
//                id = 2,
//                nama_ayah = "Andi Wijaya",
//                nama_ibu = "Dewi Lestari",
//                desa = "Sidomulyo",
//                posyandu = "Posyandu Melati",
//                no_kk = "9876543210",
//                status_ekonomi = "Menengah ke atas",
//                alamat = "Jl. Kenanga No.5",
//                rt_rw = "02/04",
//                jumlah_anak = "2",
//                tahapan_ks = "KS I",
//                status_kek_ibu = "Normal"
//            )
//        )

        // 3️⃣ Inisialisasi Adapter
//        keluargaAdapter = DataKeluargaAdapter(listKeluarga)

        // 4️⃣ Set Adapter ke RecyclerView
//        recyclerView.adapter = keluargaAdapter
        webView = findViewById(R.id.webview)

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            loadsImagesAutomatically = true
            useWideViewPort = true
            loadWithOverviewMode = true
            cacheMode = WebSettings.LOAD_DEFAULT
            builtInZoomControls = true
            displayZoomControls = false
        }

        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        // === Ambil token dari login mobile ===
        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val token = sharedPref.getString("token", null)

        if (token.isNullOrEmpty()) {
            finish() // token hilang → balik ke login
            return
        }

        // === AUTO LOGIN + REDIRECT KE TABEL ===
        val url = "https://smart-stunting.com/mobile-login?remember_token=$token&to=data_keluarga"
        webView.loadUrl(url)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    private fun loadDataKeluarga() {
        ApiClient.getInstance(applicationContext).getKeluarga().enqueue(object : Callback<List<DataKeluargaModel>> {
            override fun onResponse(
                call: Call<List<DataKeluargaModel>>,
                response: Response<List<DataKeluargaModel>>
            ) {
                if (response.isSuccessful) {
                    recyclerView.adapter = DataKeluargaAdapter(response.body() ?: emptyList())
                }
            }

            override fun onFailure(call: Call<List<DataKeluargaModel>>, t: Throwable) {
                Toast.makeText(this@DataKeluarga, "Gagal: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
