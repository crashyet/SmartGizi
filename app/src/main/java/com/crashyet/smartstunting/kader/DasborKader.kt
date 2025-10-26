package com.crashyet.smartstunting.kader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.crashyet.smartstunting.R
import com.google.android.material.bottomnavigation.BottomNavigationView

// Import semua fragment
import com.crashyet.smartstunting.kader.DasborKaderActivity
import com.crashyet.smartstunting.kader.BalitaKaderFragment
import com.crashyet.smartstunting.kader.DataKaderFragment
import com.crashyet.smartstunting.kader.SurveyKaderFragment
import com.crashyet.smartstunting.kader.PengaturanKaderFragment

class DasborKaderActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dasbor_kader)

        bottomNavigation = findViewById(R.id.bottom_navigation)

        // 🔹 Fragment default
        loadFragment(DasborKaderFragment())

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dasbor -> {
                    loadFragment(DasborKaderFragment())
                    true
                }
                R.id.balita -> {
                    loadFragment(BalitaKaderFragment())
                    true
                }
                R.id.data -> {
                    loadFragment(DataKaderFragment())
                    true
                }
                R.id.survey -> {
                    loadFragment(SurveyKaderFragment())
                    true
                }
                R.id.pengaturan -> {
                    loadFragment(PengaturanKaderFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
