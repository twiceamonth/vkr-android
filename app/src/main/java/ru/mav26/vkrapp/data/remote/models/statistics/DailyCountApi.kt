package ru.mav26.vkrapp.data.remote.models.statistics

import kotlinx.serialization.Serializable

@Serializable
data class DailyCountApi(
    val date: String,
    val light: Int,
    val medium: Int,
    val hard: Int
)
