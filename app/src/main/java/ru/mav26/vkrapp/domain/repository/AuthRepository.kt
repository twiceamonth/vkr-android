package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.auth.AuthRequest

interface AuthRepository {
    suspend fun login(authRequest: AuthRequest)

    suspend fun register(authRequest: AuthRequest)

    suspend fun refresh(refreshToken: String)
}