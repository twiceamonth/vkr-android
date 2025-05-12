package ru.mav26.vkrapp.domain.model.task

import java.time.LocalTime
import java.time.OffsetDateTime

data class TaskEdit(
    val title: String? = null,
    val endTime: OffsetDateTime? = null,
    val difficulty: String? = null,
    val priority: String? = null,
    val frequency: String? = null,
    val status: Boolean? = null,
    val timerInterval: LocalTime? = null,
    val description: String? = null,
    val finishedAt: OffsetDateTime? = null,
    val subtasks: List<String> = emptyList()
)
