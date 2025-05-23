package ru.mav26.vkrapp.presentation.feature.statistics

import ru.mav26.vkrapp.domain.model.statistics.AvgTimeStats
import ru.mav26.vkrapp.domain.model.statistics.DailyStats
import ru.mav26.vkrapp.domain.model.statistics.SummaryStats

data class StatisticsState(
    val avgTimeStats: AvgTimeStats? = null,
    val dailyStats: DailyStats? = null,
    val summaryStats: SummaryStats? = null
)
