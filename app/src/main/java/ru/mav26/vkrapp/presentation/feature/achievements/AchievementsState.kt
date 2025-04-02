package ru.mav26.vkrapp.presentation.feature.achievements

import ru.mav26.vkrapp.domain.model.achievements.Achievement
import ru.mav26.vkrapp.domain.model.achievements.AchievementProgress

data class AchievementsState(
    val achievementsList: List<Achievement> = emptyList(),
    val achievementsProgress: List<AchievementProgress> = emptyList()
)
