package ru.mav26.vkrapp.data.repository

import io.ktor.client.HttpClient
import ru.mav26.vkrapp.domain.model.auth.AuthRequest
import ru.mav26.vkrapp.domain.model.auth.Tokens
import ru.mav26.vkrapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val client: HttpClient
) : AuthRepository {
    override suspend fun login(authRequest: AuthRequest): Tokens {
        TODO("Not yet implemented")
    }

    override suspend fun register(authRequest: AuthRequest): Tokens {
        TODO("Not yet implemented")
    }

    override suspend fun refresh(refreshToken: String): Tokens {
        TODO("Not yet implemented")
    }

}