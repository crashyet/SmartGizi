package com.crashyet.smartstunting.data.network

import com.crashyet.smartstunting.data.model.AlatStatsResponse
import com.crashyet.smartstunting.data.model.DataAnakModel
import com.crashyet.smartstunting.data.model.DataKeluargaModel
import com.crashyet.smartstunting.data.model.LoginRequest
import com.crashyet.smartstunting.data.model.LoginResponse
import com.crashyet.smartstunting.data.model.PerformanceKPIResponse
import com.crashyet.smartstunting.data.model.StatsResponse
import com.crashyet.smartstunting.data.model.SurveyStatsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @GET("anak")
    fun getAnak(): Call<List<DataAnakModel>>

    @GET("keluarga")
    fun getKeluarga(): Call<List<DataKeluargaModel>>

    @GET("malnutrisi-stats")
    suspend fun getStats(
        // Pastikan nama query parameter di sini adalah yang dibutuhkan server
        @Query("user_id") userId: Int
    ): Response<StatsResponse>

    @GET("survey-stats")
    suspend fun getSurveyStats(
        // Pastikan nama query parameter di sini adalah yang dibutuhkan server
        @Query("user_id") userId: Int
    ): Response<SurveyStatsResponse>

    @GET("alat-antropometri-stats")
    suspend fun getAlatStats(
        // Pastikan nama query parameter di sini adalah yang dibutuhkan server
        @Query("user_id") userId: Int
    ): Response<AlatStatsResponse>

    @GET("alat-antropometri-stats")
    suspend fun getPerformanceStats(
        // Pastikan nama query parameter di sini adalah yang dibutuhkan server
        @Query("user_id") userId: Int
    ): Response<PerformanceKPIResponse>


}

