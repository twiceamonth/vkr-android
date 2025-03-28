package ru.mav26.vkrapp.data.remote.models.tasks

import kotlinx.serialization.Serializable


@Serializable
data class HabitDetailsResponse(
    val habitId: String,
    val title: String,
    val difficulty: DifficultyResponse,
    val frequency: FrequencyResponse,
    val timerInterval: String, //LocalTime, // time
    val description: String,
    val createdAt: String, //OffsetDateTime, // timestamptz
    val streakCount: Int,
    val lastPerformedAt: String, //OffsetDateTime, // timestamptz
)
