package ru.mav26.vkrapp.data.remote.models.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequestR(
    val userLogin: String,
    val password: String
)
