package ru.mav26.vkrapp.data.remote.models.statistics

import kotlinx.serialization.Serializable

@Serializable
data class AvgTimeStatsApi(
    val lightDays: Double,
    val mediumDays: Double,
    val hardDays: Double
)
