package ru.mav26.vkrapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.http.HttpHeaders
import io.ktor.util.AttributeKey
import ru.mav26.vkrapp.data.local.TokenDataStoreManager

class AccessTokenInterceptor(
    private val tokenManager: TokenDataStoreManager,
) : HttpClientPlugin<Unit, AccessTokenInterceptor> {
    override val key: AttributeKey<AccessTokenInterceptor> =
        AttributeKey("AccessTokenInterceptor")

    override fun prepare(block: Unit.() -> Unit): AccessTokenInterceptor = this

    override fun install(plugin: AccessTokenInterceptor, scope: HttpClient) {
        scope.plugin(HttpSend).intercept { request ->
            plugin.tokenManager.getAccessToken()?.let { token ->
                request.headers[HttpHeaders.Authorization] = "Bearer $token"
            }
            execute(request)
        }
    }
}