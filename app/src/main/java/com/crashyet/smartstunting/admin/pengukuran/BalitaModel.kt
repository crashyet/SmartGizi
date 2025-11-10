package com.crashyet.smartstunting.admin.pengukuran

data class BalitaModel(
    val nama: String,
    val usia: String,
    val beratBadan: String,
    val statusGizi: String,
    var isExpanded: Boolean = false // buat buka/tutup detail
)
