package ru.mav26.vkrapp.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import ru.mav26.vkrapp.data.remote.mappers.fromApi
import ru.mav26.vkrapp.data.remote.models.achievements.AchievementProgressResponse
import ru.mav26.vkrapp.data.remote.models.achievements.AchievementResponse
import ru.mav26.vkrapp.domain.model.achievements.Achievement
import ru.mav26.vkrapp.domain.model.achievements.AchievementProgress
import ru.mav26.vkrapp.domain.repository.AchievementsRepository

class AchievementsRepositoryImpl(
    private val client: HttpClient
) : AchievementsRepository {
    override suspend fun getAchievementsList(): List<Achievement> {
        val response: List<AchievementResponse> =
            client.get("/get-achievements-list").body()
        return response.map { it.fromApi() }
    }

    override suspend fun getAchievementsProgress(): List<AchievementProgress> {
        val response: List<AchievementProgressResponse> =
            client.get("/get-achievements-progress").body()
        return response.map { it.fromApi() }
    }

    override suspend fun startAchievementProgress(achievementId: String) {
        val response = client.post("/start-achievement-progress/${achievementId}")
    }

    override suspend fun updateAchievementProgress(progressId: String) {
        val response = client.post("/update-achievement-progress/${progressId}")
    }

    override suspend fun resetProgress(progressId: String) {
        val response = client.post("/reset-progress/${progressId}")
    }

}