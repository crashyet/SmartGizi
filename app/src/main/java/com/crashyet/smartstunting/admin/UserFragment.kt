package com.crashyet.smartstunting.admin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.crashyet.smartstunting.R

class UserFragment : Fragment() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_user, container, false)
        webView = view.findViewById(R.id.webview)

        // === WebView Settings ===
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

        // === Ambil token login ===
        val sharedPref = requireActivity()
            .getSharedPreferences("user_prefs", AppCompatActivity.MODE_PRIVATE)

        val token = sharedPref.getString("token", null)

        if (token.isNullOrEmpty()) {
            // Token hilang → keluar app / balik login
            requireActivity().finish()
            return view
        }

        // === Auto Login ke Web ===
        val url =
            "https://smart-stunting.com/mobile-login?remember_token=$token&to=user"
        webView.loadUrl(url)

        // === Handle tombol BACK ===
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (webView.canGoBack()) {
                        webView.goBack()
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            }
        )

        return view
    }
}
