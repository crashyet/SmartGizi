package com.crashyet.smartstunting.kader

data class Balita(
    val nama: String,
    val tanggalLahir: String,
    val jenisKelamin: String,
    val beratBadan: String,
    val tinggiBadan: String,
    val petugas: String,
    val status: String,
    val gizi: String,
    val posisiMenimbang: String,
    val umurBalita: String,
    var expanded: Boolean = false
)