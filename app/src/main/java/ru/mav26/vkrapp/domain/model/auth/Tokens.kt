package ru.mav26.vkrapp.domain.model.auth

data class Tokens(
    val accessToken: String,
    val refreshToken: String
)
