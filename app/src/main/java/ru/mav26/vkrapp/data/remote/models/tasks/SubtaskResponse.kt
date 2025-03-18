package ru.mav26.vkrapp.data.remote.models.tasks

import kotlinx.serialization.Serializable


@Serializable
data class SubtaskResponse(
    val subtaskId: String,
    val title: String,
    val status: Boolean,
    val taskId: String
)
