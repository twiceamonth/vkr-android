package ru.mav26.vkrapp.domain.model.achievements

import java.time.OffsetDateTime
import java.util.Date

data class AchievementProgress(
    val userLogin: String,
    val achievementId: String,
    val imagePath: String,
    val progressId: String,
    val title: String,
    val description: String,
    val resetOnEvent: Boolean,
    val resentEventType: String,
    val criteriaType: String,
    val criteriaValue: Int,
    val progressValue: Int,
    val isCompleted: Boolean,
    val completeDate: Date? = null
)
