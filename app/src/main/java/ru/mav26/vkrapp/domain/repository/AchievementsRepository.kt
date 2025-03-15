package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.achievements.Achievement
import ru.mav26.vkrapp.domain.model.achievements.AchievementProgress

interface AchievementsRepository {
    fun getAchievementsList() : List<Achievement>

    fun getAchievementsProgress(userLogin: String) : List<AchievementProgress>

    fun startAchievementProgress(userLogin: String, achievementId: String)

    fun updateAchievementProgress(progressId: String, userLogin: String)

    fun resetProgress(progressId: String, userLogin: String)
}