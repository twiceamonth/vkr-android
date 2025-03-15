package ru.mav26.vkrapp.domain.model.task

import java.time.LocalTime
import java.time.OffsetDateTime

data class TaskDetails(
    val taskId: String,
    val title: String,
    val endTime: OffsetDateTime? = null,
    val difficulty: Difficulty,
    val priority: Priority,
    val frequency: Frequency,
    val details: List<Details> = emptyList(),
    val status: Boolean,
    val timerInterval: LocalTime? = null,
    val description: String,
    val createdAt: OffsetDateTime,
    val finishedAt: OffsetDateTime? = null,
    val subtasks: List<Subtask> = emptyList()
)
