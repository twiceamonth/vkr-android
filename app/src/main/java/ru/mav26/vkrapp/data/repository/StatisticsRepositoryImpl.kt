package ru.mav26.vkrapp.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.mav26.vkrapp.data.remote.mappers.fromApi
import ru.mav26.vkrapp.data.remote.models.statistics.AvgTimeStatsApi
import ru.mav26.vkrapp.data.remote.models.statistics.DailyStatsApi
import ru.mav26.vkrapp.data.remote.models.statistics.SummaryStatsApi
import ru.mav26.vkrapp.domain.model.statistics.AvgTimeStats
import ru.mav26.vkrapp.domain.model.statistics.DailyStats
import ru.mav26.vkrapp.domain.model.statistics.SummaryStats
import ru.mav26.vkrapp.domain.repository.StatisticsRepository

class StatisticsRepositoryImpl(
    private val client: HttpClient
) : StatisticsRepository {
    override suspend fun totalByDifficulty(): SummaryStats {
        val response: SummaryStatsApi = client.get("/summary").body()
        return response.fromApi()
    }

    override suspend fun avgTimeByDifficulty(): AvgTimeStats {
        val response: AvgTimeStatsApi = client.get("/avg-time").body()
        return response.fromApi()
    }

    override suspend fun dailyCounts(): DailyStats {
        val response: DailyStatsApi = client.get("/daily").body()
        return response.fromApi()
    }
}