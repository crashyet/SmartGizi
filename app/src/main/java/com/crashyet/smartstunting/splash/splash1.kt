package com.crashyet.smartstunting.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.crashyet.smartstunting.R
import com.crashyet.smartstunting.auth.LoginActivity

class splash1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash1)

        val logo = findViewById<ImageView>(R.id.logoImage)
        val anim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fade_in)
        logo.startAnimation(anim)

        // Delay 2 detik
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}