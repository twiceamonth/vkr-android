package ru.mav26.vkrapp.data.remote.models.tasks

import kotlinx.serialization.Serializable

@Serializable
data class TaskCreate(
    val title: String,
    val endTime: String? = null, // OffsetDateTime, // timestamptz
    val difficulty: String,
    val priority: String,
    val frequency: String,
    val details: List<DetailsCreate> = emptyList(),
    val timerInterval: String? = null, // LocalTime, // time
    val description: String,
    val subtasks: List<String> = emptyList()
)
