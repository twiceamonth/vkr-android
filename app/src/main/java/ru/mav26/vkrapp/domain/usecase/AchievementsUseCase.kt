package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.achievements.Achievement
import ru.mav26.vkrapp.domain.repository.AchievementsRepository

class AchievementsUseCase(
    private val achievementsRepository: AchievementsRepository
) {
    fun getAchievements() : List<Achievement> {
        return achievementsRepository.getAchievementsList()
    }

    fun startProgress(userLogin: String, achievementId: String) {
        return achievementsRepository.startAchievementProgress(userLogin, achievementId)
    }

    fun updateProgress(progressId: String, userLogin: String) {
        return achievementsRepository.updateAchievementProgress(progressId, userLogin)
    }

    fun resetProgress(progressId: String, userLogin: String) {
        return achievementsRepository.resetProgress(progressId, userLogin)
    }
}