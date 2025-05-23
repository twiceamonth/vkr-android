package ru.mav26.vkrapp.data.remote.models.statistics

import kotlinx.serialization.Serializable

@Serializable
data class DailyStatsApi(
    val counts: List<DailyCountApi>
)