package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.statistics.AvgTimeStats
import ru.mav26.vkrapp.domain.model.statistics.DailyStats
import ru.mav26.vkrapp.domain.model.statistics.SummaryStats
import ru.mav26.vkrapp.domain.repository.StatisticsRepository

class StatisticsUseCase(
    private val statisticsRepository: StatisticsRepository
) {

    suspend fun totalByDifficulty() : SummaryStats {
        return statisticsRepository.totalByDifficulty()
    }

    suspend fun avgTimeByDifficulty() : AvgTimeStats {
        return statisticsRepository.avgTimeByDifficulty()
    }

    suspend fun dailyCounts() : DailyStats {
        return statisticsRepository.dailyCounts()
    }

}