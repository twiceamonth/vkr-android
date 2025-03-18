package ru.mav26.vkrapp.data.remote.models.tasks

import kotlinx.serialization.Serializable

@Serializable
data class DifficultyResponse(
    val difficultyName: String,
    val multiplier: Int
)
