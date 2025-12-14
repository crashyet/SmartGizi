package com.crashyet.smartstunting.data.model

import com.google.gson.annotations.SerializedName

data class StatsResponse(
    @SerializedName("total_anak")
    val totalAnak: Int,

    @SerializedName("coverage_pengukuran")
    val coveragePengukuran: Double, // Asumsi ini adalah persentase (Double/Float)

    @SerializedName("gizi_normal")
    val giziNormal: Int,

    @SerializedName("gizi_kurang")
    val giziKurang: Int,

    @SerializedName("gizi_buruk")
    val giziBuruk: Int,

    @SerializedName("gizi_lebih_obesitas")
    val giziLebihObesitas: Int,

    @SerializedName("kasus_stunting")
    val kasusStunting: Int,

    @SerializedName("trend_pengukuran")
    val trendPengukuran: TrendData
)

data class TrendData(
    @SerializedName("bulan_ini")
    val bulanIni: Int,

    @SerializedName("bulan_lalu")
    val bulanLalu: Int,

    @SerializedName("persentase")
    val persentase: Double // Asumsi ini adalah persentase trend (Double/Float)
)