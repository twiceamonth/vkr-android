package ru.mav26.vkrapp.data.remote

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.util.AttributeKey
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
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
        scope.plugin(HttpSend).intercept { originalRequest ->
            var call = execute(originalRequest)

            if (call.response.status != HttpStatusCode.Unauthorized) {
                return@intercept call
            }

            mutex.withLock {
                val refreshed = plugin.refreshToken()
                if (refreshed) {
                    val newAccess = plugin.tokenManager.accessTokenFlow.firstOrNull()
                    if (!newAccess.isNullOrBlank()) {
                        // Удаляем старый заголовок и добавляем новый
                        originalRequest.headers.remove(HttpHeaders.Authorization)
                        originalRequest.headers.append(HttpHeaders.Authorization, "Bearer $newAccess")

                        // Второй вызов execute() повторно использует тот же builder
                        call.cancel()
                        call = execute(originalRequest)
                    }
                } else {
                    plugin.tokenManager.clearTokens()
                }
            }

            return@intercept call
        }
    }

    private suspend fun refreshToken(): Boolean {
        val refresh = tokenManager.refreshTokenFlow.firstOrNull() ?: return false
        return try {
            authApi.refresh(refresh) // Метод сам записывает токены
            true
        } catch (e: Exception) {
            false
        }
    }

}

