package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.statistics.AvgTimeStats
import ru.mav26.vkrapp.domain.model.statistics.DailyStats
import ru.mav26.vkrapp.domain.model.statistics.SummaryStats

interface StatisticsRepository {

    suspend fun totalByDifficulty() : SummaryStats

    suspend fun avgTimeByDifficulty() : AvgTimeStats

    suspend fun dailyCounts() : DailyStats

}