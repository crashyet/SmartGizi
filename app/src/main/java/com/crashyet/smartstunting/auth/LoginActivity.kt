package com.crashyet.smartstunting.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.crashyet.smartstunting.R
import com.crashyet.smartstunting.admin.AdminBaseLayout
import com.crashyet.smartstunting.kader.DasborKaderActivity
import com.crashyet.smartstunting.data.model.LoginRequest
import com.crashyet.smartstunting.data.model.LoginResponse
import com.crashyet.smartstunting.data.network.ApiClient
//import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val edtEmail = findViewById<EditText>(R.id.email)
        val edtPassword = findViewById<EditText>(R.id.password)

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val request = LoginRequest(email, password)
//            ApiClient.instance.loginUser(request)
                ApiClient.getInstance(this).loginUser(request)
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>

                    ) {
                        if (response.isSuccessful && response.body()?.message == "Login berhasil") {
                            val user = response.body()?.user
                            val role = user?.role


                            Toast.makeText(this@LoginActivity, "Selamat datang ${user?.nama_lengkap}", Toast.LENGTH_LONG).show()

                            // Simpan token + role ke SharedPreferences
                            val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
                            with(sharedPref.edit()) {
                                putString("token", response.body()?.token)
                                putInt("user_id", user?.user_id ?: 0)
                                putString("nama_lengkap", user?.nama_lengkap)
                                putString("email", user?.email)
                                putString("role", user?.role)
                                apply()
                            }

                            // Arahkan ke halaman sesuai role
                            when (role) {
                                "petugas_puskesmas" -> {
                                    val intent = Intent(this@LoginActivity, AdminBaseLayout::class.java)
                                    startActivity(intent)
                                }
                                "kader" -> {
                                    val intent = Intent(this@LoginActivity, DasborKaderActivity::class.java)
                                    startActivity(intent)
                                }
//                                else -> {
//                                    val intent = Intent(this@LoginActivity, UserActivity::class.java)
//                                    startActivity(intent)
//                                }
                                else -> Toast.makeText(this@LoginActivity, "User Tidak Ditemukan!", Toast.LENGTH_SHORT).show()
                            }

                            finish()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}