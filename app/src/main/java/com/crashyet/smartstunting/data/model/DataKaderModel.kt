package com.crashyet.smartstunting.data.model

import java.sql.Date

data class DataKaderModel(
    val kader_id: Int,
    val tipe_kader: String,
    val pendidikan_kader: String,
    val nomor_sk: String,
    val tanggal_sk: String,
    val status_kader: String,
    val desa_id: Int,
    val posyandu_id: Int,
)
