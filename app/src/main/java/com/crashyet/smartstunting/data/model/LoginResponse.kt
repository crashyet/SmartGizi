package com.crashyet.smartstunting.data.model

data class LoginResponse(
    val success: Boolean,
    val token: String?,
    val message: String?,
    val user: UserData?
)

data class UserData(
    val id: Int,
    val name: String,
    val email: String,
    val role: String
)
