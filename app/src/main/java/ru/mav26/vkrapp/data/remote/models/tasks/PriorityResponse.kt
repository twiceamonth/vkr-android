package ru.mav26.vkrapp.data.remote.models.tasks

import kotlinx.serialization.Serializable

@Serializable
data class PriorityResponse(
    val priorityName: String,
    val multiplier: Int
)
