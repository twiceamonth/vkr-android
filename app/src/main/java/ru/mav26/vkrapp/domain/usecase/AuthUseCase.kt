package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.auth.AuthRequest
import ru.mav26.vkrapp.domain.model.auth.Tokens
import ru.mav26.vkrapp.domain.repository.AuthRepository

class AuthUseCase(
    private val authRepository: AuthRepository
) {
    fun login(authRequest: AuthRequest) : Tokens {
        return authRepository.login(authRequest)
    }

    fun register(authRequest: AuthRequest) : Tokens {
        return authRepository.register(authRequest)
    }

    fun refresh(refreshToken: String) : Tokens {
        return authRepository.refresh(refreshToken)
    }
}