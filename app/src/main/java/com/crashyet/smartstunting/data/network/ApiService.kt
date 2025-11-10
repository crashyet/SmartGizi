package com.crashyet.smartstunting.data.network

import com.crashyet.smartstunting.data.model.DataAnakModel
import com.crashyet.smartstunting.data.model.DataKeluargaModel
import com.crashyet.smartstunting.data.model.LoginRequest
import com.crashyet.smartstunting.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET

interface ApiService {
    @POST("login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @GET("anak")
    fun getAnak(): Call<List<DataAnakModel>>

    @GET("keluarga")
    fun getKeluarga(): Call<List<DataKeluargaModel>>
}

