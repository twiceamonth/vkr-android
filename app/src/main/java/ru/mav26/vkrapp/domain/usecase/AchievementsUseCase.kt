package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.achievements.Achievement
import ru.mav26.vkrapp.domain.repository.AchievementsRepository

class AchievementsUseCase(
    private val achievementsRepository: AchievementsRepository
) {
    suspend fun getAchievements() : List<Achievement> {
        return achievementsRepository.getAchievementsList()
    }

    suspend fun startProgress(userLogin: String, achievementId: String) {
        return achievementsRepository.startAchievementProgress(userLogin, achievementId)
    }

    suspend fun updateProgress(progressId: String, userLogin: String) {
        return achievementsRepository.updateAchievementProgress(progressId, userLogin)
    }

    suspend fun resetProgress(progressId: String, userLogin: String) {
        return achievementsRepository.resetProgress(progressId, userLogin)
    }
}