package ru.mav26.vkrapp.domain.model.achievements

data class Achievement(
    val achievementId: String,
    val title: String,
    val description: String,
    val resetOnEvent: Boolean,
    val resentEventType: String,
    val criteriaType: String,
    val criteriaValue: Int,
    val imagePath: String
)
