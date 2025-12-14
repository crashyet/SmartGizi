package com.crashyet.smartstunting.kader

data class AnakModel(
    val nama: String,
    val umur: String,
    val jk: String,
    val nik: String,
    val namaAyah: String,
    val posyandu: String,
    val status: String,
    var expanded: Boolean = false
)
