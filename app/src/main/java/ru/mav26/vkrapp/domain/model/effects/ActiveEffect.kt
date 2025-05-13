package ru.mav26.vkrapp.domain.model.effects

import java.time.OffsetDateTime

data class ActiveEffect(
    val activeEffectId: String,
    val endDate: OffsetDateTime,
    val isCompleted: Boolean,
    val effectName: String,
    val description: String,
    val effectIcon: String,
    val criteriaType: String,
    val criteriaValue: Int
)
