package ru.mav26.vkrapp.domain.model.task

import java.time.LocalTime
import java.time.OffsetDateTime

data class TaskCreate(
    val title: String,
    val endTime: OffsetDateTime? = null,
    val difficulty: String,
    val priority: String,
    val frequency: String,
    val details: List<DetailsCreate> = emptyList(),
    val timerInterval: LocalTime? = null,
    val description: String,
    val subtasks: List<String> = emptyList()
)
