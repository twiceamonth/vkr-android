package ru.mav26.vkrapp.domain.model.task

data class Subtask(
    val subtaskId: String,
    val title: String,
    val status: Boolean,
    val taskId: String
)
