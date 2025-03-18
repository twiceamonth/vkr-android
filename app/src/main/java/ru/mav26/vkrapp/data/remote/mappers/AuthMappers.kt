package ru.mav26.vkrapp.data.remote.mappers

import ru.mav26.vkrapp.data.remote.models.auth.TokensResponse
import ru.mav26.vkrapp.domain.model.auth.AuthRequest
import ru.mav26.vkrapp.domain.model.auth.Tokens

fun TokensResponse.fromApi() : Tokens {
    return Tokens(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )
}

fun AuthRequest.toApi() : ru.mav26.vkrapp.data.remote.models.auth.AuthRequest {
    return ru.mav26.vkrapp.data.remote.models.auth.AuthRequest(
        userLogin = this.userLogin,
        password = this.password
    )
}