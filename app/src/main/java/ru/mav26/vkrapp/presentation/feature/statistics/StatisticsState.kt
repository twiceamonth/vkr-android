package ru.mav26.vkrapp.presentation.feature.statistics

import ru.mav26.vkrapp.domain.model.statistics.AvgTimeStats
import ru.mav26.vkrapp.domain.model.statistics.DailyCount
import ru.mav26.vkrapp.domain.model.statistics.DailyStats
import ru.mav26.vkrapp.domain.model.statistics.SummaryStats

data class StatisticsState(
    val avgTimeStats: AvgTimeStats = AvgTimeStats(0.0, 0.0, 0.0),
    val dailyStats: List<DailyCount> = emptyList(),
    val summaryStats: SummaryStats = SummaryStats(0, 0, 0, 0)
)
