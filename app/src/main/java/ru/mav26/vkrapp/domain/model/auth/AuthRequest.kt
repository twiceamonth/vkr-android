package ru.mav26.vkrapp.domain.model.auth

data class AuthRequest(
    val userLogin: String,
    val password: String
)
