package ru.mav26.vkrapp.data.remote.models.achievements

import kotlinx.serialization.Serializable

@Serializable
data class AchievementResponse(
    val achievementId: String,
    val title: String,
    val description: String,
    val resetOnEvent: Boolean,
    val resentEventType: String,
    val criteriaType: String,
    val criteriaValue: Int,
    val imagePath: String
)
