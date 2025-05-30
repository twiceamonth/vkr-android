package ru.mav26.vkrapp.data.remote.models.tasks

import kotlinx.serialization.Serializable


@Serializable
data class HabitListResponse(
    val habitId: String,
    val title: String,
    val difficulty: String,
    val frequency: String,
    val timerInterval: String, //LocalTime, // time
    val description: String,
    val streakCount: Int,
    val lastPerformed: String?
)
