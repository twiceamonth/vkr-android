package ru.mav26.vkrapp.data.remote

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.util.AttributeKey
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.mav26.vkrapp.data.local.TokenDataStoreManager
import ru.mav26.vkrapp.domain.repository.AuthRepository

class TokenRefreshInterceptor(
    private val tokenManager: TokenDataStoreManager,
    private val authApiProvider: () -> AuthRepository
) : HttpClientPlugin<Unit, TokenRefreshInterceptor> {

    private val authApi by lazy { authApiProvider() }

    private val mutex = Mutex()

    override val key: AttributeKey<TokenRefreshInterceptor>
            = AttributeKey("TokenRefreshInterceptor")

    override fun prepare(block: Unit.() -> Unit) = this

    override fun install(plugin: TokenRefreshInterceptor, scope: HttpClient) {
        scope.plugin(HttpSend).apply {
            intercept { request ->
                // first attempt
                var call = execute(request)
                if (call.response.status != HttpStatusCode.Unauthorized) {
                    return@intercept call
                }

                // on 401, refresh token under lock
                mutex.withLock {
                    if (refreshToken()) {
                        // re-build request with new token
                        request.headers[HttpHeaders.Authorization] = "Bearer ${tokenManager.getAccessToken()}"
                        call.cancel()
                        call = execute(request)  // <-- retry
                    } else {
                        tokenManager.clearTokens()
                    }
                }

                return@intercept call
            }
        }
    }

    private suspend fun refreshToken(): Boolean {
        return try {
            val refreshToken = tokenManager.getRefreshToken() ?: return false
            val newTokens = authApi.refresh(refreshToken)

            runBlocking { tokenManager.getAccessToken() }?.let {
                runBlocking { tokenManager.getRefreshToken() }?.let { it1 ->
                    tokenManager.saveTokens(
                        accessToken = it,
                        refreshToken = it1
                    )
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }

}

