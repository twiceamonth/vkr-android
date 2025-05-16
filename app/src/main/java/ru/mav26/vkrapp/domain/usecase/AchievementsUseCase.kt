package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.achievements.Achievement
import ru.mav26.vkrapp.domain.model.achievements.AchievementProgress
import ru.mav26.vkrapp.domain.repository.AchievementsRepository

class AchievementsUseCase(
    private val achievementsRepository: AchievementsRepository
) {
    suspend fun getAchievements() : List<Achievement> {
        return achievementsRepository.getAchievementsList()
    }

    suspend fun getAchievementsProgress() : List<AchievementProgress> {
        return achievementsRepository.getAchievementsProgress()
    }

    suspend fun startProgress(achievementId: String) {
        return achievementsRepository.startAchievementProgress(achievementId)
    }

    suspend fun updateProgress(progressId: String) {
        return achievementsRepository.updateAchievementProgress(progressId)
    }

    suspend fun resetProgress(progressId: String) {
        return achievementsRepository.resetProgress(progressId)
    }
}