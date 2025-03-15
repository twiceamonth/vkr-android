package ru.mav26.vkrapp.domain.model.task

data class TaskCreate(
    val title: String,
    val endTime: String? = null, // OffsetDateTime
    val difficulty: String,
    val priority: String,
    val frequency: String,
    val details: List<Details> = emptyList(),
    val timerInterval: String? = null, // LocalTime
    val description: String,
    val subtasks: List<String> = emptyList(),
    val userLogin: String
)
