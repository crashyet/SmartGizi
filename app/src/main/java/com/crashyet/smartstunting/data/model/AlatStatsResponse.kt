package com.crashyet.smartstunting.data.model

import com.google.gson.annotations.SerializedName

data class AlatStatsResponse(
    // Properti Dasar
    @SerializedName("role")
    val role: String,

    @SerializedName("total_jenis_alat")
    val totalJenisAlat: Int,

    // Properti untuk Kader
    @SerializedName("posyandu_id")
    val posyanduId: Int? = null,

    @SerializedName("alat_tersedia_posyandu")
    val alatTersediaPosyandu: Int? = null,

    @SerializedName("alat_rusak_posyandu")
    val alatRusakPosyandu: Int? = null,

    // Properti untuk Admin/Petugas Puskesmas
    @SerializedName("total_posyandu")
    val totalPosyandu: Int? = null,

    @SerializedName("posyandu_dengan_alat")
    val posyanduDenganAlat: Int? = null,

    @SerializedName("total_alat_tersedia_semua")
    val totalAlatTersediaSemua: Int? = null
)