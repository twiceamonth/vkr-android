package ru.mav26.vkrapp.data.remote.models.tasks

import kotlinx.serialization.Serializable

@Serializable
data class SubtaskEdit(
    val title: String?= null,
    val status: Boolean?= null
)
