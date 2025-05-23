package ru.mav26.vkrapp.data.remote.models.statistics

import kotlinx.serialization.Serializable

@Serializable
data class SummaryStatsApi(
    val total: Int,
    val light: Int,
    val medium: Int,
    val hard: Int
)
