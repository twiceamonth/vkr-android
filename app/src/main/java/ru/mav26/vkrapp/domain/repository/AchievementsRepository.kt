package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.achievements.Achievement
import ru.mav26.vkrapp.domain.model.achievements.AchievementProgress

interface AchievementsRepository {
    suspend fun getAchievementsList() : List<Achievement>

    suspend fun getAchievementsProgress() : List<AchievementProgress>

    suspend fun startAchievementProgress( achievementId: String)

    suspend fun updateAchievementProgress(progressId: String)

    suspend fun resetProgress(progressId: String)
}