package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.auth.AuthRequest
import ru.mav26.vkrapp.domain.model.auth.Tokens

interface AuthRepository {
    suspend fun login(authRequest: AuthRequest) : Tokens

    suspend fun register(authRequest: AuthRequest) : Tokens

    suspend fun refresh(refreshToken: String) : Tokens
}