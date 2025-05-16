package ru.mav26.vkrapp.data.remote.models.tasks

import kotlinx.serialization.Serializable

@Serializable
data class HabitCreate(
    val title: String,
    val difficulty: String,
    val frequency: String,
    val timerInterval: String? = null, //LocalTime, // time
    val description: String
)
