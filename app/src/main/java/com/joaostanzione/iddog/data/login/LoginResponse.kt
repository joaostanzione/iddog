package com.joaostanzione.iddog.data.login

data class LoginResponse(
    val user: User?
)
data class User(
    val _id: String,
    val __v: Int,
    val createdAt: String,
    val email: String,
    val token: String,
    val updatedAt: String
)
