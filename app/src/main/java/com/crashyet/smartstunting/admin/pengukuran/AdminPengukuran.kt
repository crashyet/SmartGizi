package com.crashyet.smartstunting.admin.pengukuran

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.crashyet.smartstunting.R

class AdminPengukuran : AppCompatActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.admin_pengukuran)
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
        val url = "https://smart-stunting.com/mobile-login?remember_token=$token&to=data_pengukuran_petugas"
        webView.loadUrl(url)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}