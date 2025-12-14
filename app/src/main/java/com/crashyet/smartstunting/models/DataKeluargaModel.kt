package com.crashyet.smartstunting.kader

data class DataKeluargaModel(
    val namaBalita: String,
    val namaIbu: String,
    val kecamatan: String,
    val posyandu: String,
    val noKK: String,
    val statusEkonomi: String,
    val alamat: String,
    val rtRw: String,
    val jumlahAnak: String,
    val tahapanKS: String,
    val statusKEKIbu: String,
    var expanded: Boolean = false
)
