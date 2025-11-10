package com.crashyet.smartstunting.admin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.crashyet.smartstunting.R
import com.google.android.material.bottomnavigation.BottomNavigationView

// fragment
import com.crashyet.smartstunting.admin.HomeFragment
import com.crashyet.smartstunting.admin.LaporanFragment

class AdminBaseLayout : AppCompatActivity() {

    private lateinit var tabHome: TextView
    private lateinit var tabLaporan: TextView
    private lateinit var tabUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_base_layout)

        // Inisialisasi view
        tabHome = findViewById(R.id.tabHome)
        tabLaporan = findViewById(R.id.tabLaporan)
        tabUser = findViewById(R.id.tabUser)

        // Tampilkan fragment default (Home)
        replaceFragment(HomeFragment())
        setActiveTab(tabHome)

        // Listener setiap tab
        tabHome.setOnClickListener {
            replaceFragment(HomeFragment())
            setActiveTab(tabHome)
        }

        tabLaporan.setOnClickListener {
            replaceFragment(LaporanFragment())
            setActiveTab(tabLaporan)
        }

        tabUser.setOnClickListener {
            replaceFragment(UserFragment())
            setActiveTab(tabUser)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun setActiveTab(activeTab: TextView) {
        // Reset semua tab ke gaya default
        val tabs = listOf(tabHome, tabLaporan, tabUser)
        for (tab in tabs) {
            tab.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
            tab.setTextColor(ContextCompat.getColor(this, R.color.dark_gray))
            tab.setTypeface(null, android.graphics.Typeface.NORMAL)
        }

        // Aktifkan tab yang diklik
        activeTab.setBackgroundResource(R.drawable.bg_hijautoska) // background hijau milikmu
        activeTab.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        activeTab.setTypeface(null, android.graphics.Typeface.BOLD)
    }
}