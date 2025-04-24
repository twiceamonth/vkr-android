package ru.mav26.vkrapp.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.mav26.vkrapp.data.local.DataStoreManager
import ru.mav26.vkrapp.data.local.TokenData
import ru.mav26.vkrapp.data.remote.models.auth.RefreshTokenRequest
import ru.mav26.vkrapp.data.remote.models.auth.TokensResponse
import ru.mav26.vkrapp.domain.model.auth.AuthRequest
import ru.mav26.vkrapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val client: HttpClient,
    private val dataStore: DataStoreManager
) : AuthRepository {
    override suspend fun login(authRequest: AuthRequest) {
        val response: TokensResponse = client.post("/login") {
            contentType(ContentType.Application.Json)
            setBody(authRequest)
        }.body()

        dataStore.saveTokens(TokenData(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken
        ))
    }

    override suspend fun register(authRequest: AuthRequest) {
        val response: TokensResponse = client.post("/register") {
            contentType(ContentType.Application.Json)
            setBody(authRequest)
        }.body()

        dataStore.saveTokens(TokenData(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken
        ))
    }

    override suspend fun refresh(refreshToken: String) {
        val response: TokensResponse = client.post("/refresh") {
            contentType(ContentType.Application.Json)
            setBody(RefreshTokenRequest(refreshToken))
        }.body()

        dataStore.saveTokens(TokenData(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken
        ))
    }

}