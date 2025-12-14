package com.crashyet.smartstunting.admin.data

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.R
import com.crashyet.smartstunting.data.adapter.DataAnakAdapter
import com.crashyet.smartstunting.data.model.DataAnakModel
import com.crashyet.smartstunting.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataAnak : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_data_anak)

//        recyclerView = findViewById(R.id.rvDataAnak)
//        recyclerView.layoutManager = LinearLayoutManager(this)

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
        val url = "https://smart-stunting.com/mobile-login?remember_token=$token&to=data_anak"
        webView.loadUrl(url)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

//    private fun loadDataDummy() {
//        val listAnak = listOf(
//            DataAnakModel(
//                id = 1,
//                nama = "Andi Karangturi",
//                umur = "2 tahun 2 bulan 4 hari",
//                jenis_kelamin = "Laki-laki",
//                nik = "3300112201030005",
//                posyandu = "Cempaka 1",
//                nama_ayah = "Slamet Karangturi",
//                status = "Aktif"
//            ),
//            DataAnakModel(
//                id = 2,
//                nama = "Rizky Karangturi",
//                umur = "4 tahun 2 bulan 4 hari",
//                jenis_kelamin = "Laki-laki",
//                nik = "3300112201030006",
//                posyandu = "Cempaka 2",
//                nama_ayah = "Sujono Karangturi",
//                status = "Tidak Aktif"
//            ),
//            DataAnakModel(
//                id = 3,
//                nama = "Melati Karangturi",
//                umur = "3 tahun 1 bulan 2 hari",
//                jenis_kelamin = "Perempuan",
//                nik = "3300112201030007",
//                posyandu = "Cempaka 3",
//                nama_ayah = "Santoso Karangturi",
//                status = "Aktif"
//            )
//        )
//
//        recyclerView.adapter = DataAnakAdapter(listAnak)
//    }

    private fun loadDataAnak() {
        ApiClient.getInstance(applicationContext).getAnak().enqueue(object : Callback<List<DataAnakModel>> {
            override fun onResponse(
                call: Call<List<DataAnakModel>>,
                response: Response<List<DataAnakModel>>
            ) {
                if (response.isSuccessful) {
                    recyclerView.adapter = DataAnakAdapter(response.body() ?: emptyList())
                }
            }

            override fun onFailure(call: Call<List<DataAnakModel>>, t: Throwable) {
                Toast.makeText(this@DataAnak, "Gagal: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}