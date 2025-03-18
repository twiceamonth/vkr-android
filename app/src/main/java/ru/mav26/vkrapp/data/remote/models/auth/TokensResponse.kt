package ru.mav26.vkrapp.data.remote.models.auth

import kotlinx.serialization.Serializable

@Serializable
data class TokensResponse(
    val accessToken: String,
    val refreshToken: String
)
