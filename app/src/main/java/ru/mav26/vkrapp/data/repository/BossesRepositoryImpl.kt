package ru.mav26.vkrapp.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import ru.mav26.vkrapp.data.remote.mappers.fromApi
import ru.mav26.vkrapp.data.remote.models.bosses.ActiveBossResponse
import ru.mav26.vkrapp.domain.model.bosses.ActiveBoss
import ru.mav26.vkrapp.domain.repository.BossesRepository

class BossesRepositoryImpl(
    private val client: HttpClient
) : BossesRepository {
    override suspend fun getActiveBoss(userLogin: String): ActiveBoss? {
        val response: ActiveBossResponse =
            client.get("/get-active-boss/${userLogin}").body()
        return response.fromApi()
    }

    override suspend fun makeDamage(userLogin: String, taskDiff: String) {
        val response = client.post("/make-damage/${userLogin}/${taskDiff}")
    }

}