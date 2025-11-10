package com.crashyet.smartstunting.kader

data class BalitaResiko(
    val nama: String,
    val tanggal: String,
    val jenisKelamin: String,
    val bb: String,
    val tb: String,
    val levelRisiko: String,
    val statusGizi: String,
    val zBbU: String,
    val zTbU: String,
    val zBbTb: String,
    val statusStunting: String,
    var expanded: Boolean = false

)
