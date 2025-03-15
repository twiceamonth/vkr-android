package ru.mav26.vkrapp.domain.model.task

import java.time.LocalTime
import java.time.OffsetDateTime

data class HabitEdit(
    val userLogin: String? = null,
    val title: String? = null,
    val difficulty: String? = null,
    val frequency: String? = null,
    val timerInterval: LocalTime? = null,
    val description: String? = null,
    val streakCount: Int? = null,
    val lastPerformedAt: OffsetDateTime? = null
)
