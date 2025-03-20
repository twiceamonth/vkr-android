package ru.mav26.vkrapp.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.mav26.vkrapp.data.remote.mappers.fromApi
import ru.mav26.vkrapp.data.remote.models.effects.ActiveEffectResponse
import ru.mav26.vkrapp.domain.model.effects.ActiveEffect
import ru.mav26.vkrapp.domain.repository.EffectsRepository

class EffectsRepositoryImpl(
    private val client: HttpClient
) : EffectsRepository {
    override suspend fun getActiveEffect(): ActiveEffect? {
        val response: ActiveEffectResponse = client.get("/get-active-effect").body()
        return response.fromApi()
    }

}