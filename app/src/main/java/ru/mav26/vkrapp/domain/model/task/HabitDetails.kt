package ru.mav26.vkrapp.domain.model.task

import java.time.LocalTime
import java.time.OffsetDateTime

data class HabitDetails(
    val habitId: String,
    val title: String,
    val difficulty: Difficulty,
    val frequency: Frequency,
    val timerInterval: LocalTime,
    val description: String,
    val createdAt: OffsetDateTime,
    val streakCount: Int,
    val lastPerformedAt: OffsetDateTime,
)
