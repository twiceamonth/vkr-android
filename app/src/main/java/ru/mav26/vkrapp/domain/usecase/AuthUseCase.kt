package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.auth.AuthRequest
import ru.mav26.vkrapp.domain.model.auth.Tokens
import ru.mav26.vkrapp.domain.repository.AuthRepository

class AuthUseCase(
    private val authRepository: AuthRepository
) {
    suspend fun login(authRequest: AuthRequest) {
        return authRepository.login(authRequest)
    }

    suspend fun register(authRequest: AuthRequest) {
        return authRepository.register(authRequest)
    }

    suspend fun refresh(refreshToken: String) {
        return authRepository.refresh(refreshToken)
    }
}