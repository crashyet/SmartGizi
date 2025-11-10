package com.crashyet.smartstunting.admin

data class User(
    val idUser: String,
    val nama: String,
    val nik: String,
    val email: String,
    val role: String,
    val status: Boolean,
    val foto: String?,
    val noHp: String?,
    val namaAyah: String?,
    val namaIbu: String?,
    val puskesmas: String?,
    val gender: String?,
    val tanggalLahir: String?,
    val alamat: String?,
    val emailVerified: Boolean,
    val createdAt: String?,
    val updatedAt: String?
)
