package com.crashyet.smartstunting.data.model

import com.google.gson.annotations.SerializedName

data class PerformanceKPIResponse(
    @SerializedName("coverage")
    val coverage: CoverageData,

    @SerializedName("posyandu_aktif")
    val posyanduAktif: PosyanduAktifData,

    @SerializedName("stunting_rate")
    val stuntingRate: StuntingRateData
)

data class CoverageData(
    @SerializedName("target")
    val target: Double, // 80.0

    @SerializedName("actual")
    val actual: Double, // Actual coverage in %

    @SerializedName("trend")
    val trend: Double, // Difference vs last month

    @SerializedName("coverage_bulan_lalu")
    val coverageBulanLalu: Double,

    @SerializedName("total_anak")
    val totalAnak: Int,

    @SerializedName("anak_terukur")
    val anakTerukur: Int
)

data class PosyanduAktifData(
    @SerializedName("total_posyandu")
    val totalPosyandu: Int,

    @SerializedName("posyandu_aktif")
    val posyanduAktif: Int,

    @SerializedName("persentase_aktif")
    val persentaseAktif: Double
)

data class StuntingRateData(
    @SerializedName("target_nasional")
    val targetNasional: Double, // 14.28

    @SerializedName("actual_rate")
    val actualRate: Double, // Current stunting rate in %

    @SerializedName("total_terukur")
    val totalTerukur: Int,

    @SerializedName("stunting_count")
    val stuntingCount: Int
)