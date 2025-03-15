package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.auth.AuthRequest
import ru.mav26.vkrapp.domain.model.auth.Tokens

interface AuthRepository {
    fun login(authRequest: AuthRequest) : Tokens

    fun register(authRequest: AuthRequest) : Tokens

    fun refresh(refreshToken: String) : Tokens
}