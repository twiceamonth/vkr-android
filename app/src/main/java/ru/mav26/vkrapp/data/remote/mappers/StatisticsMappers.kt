package ru.mav26.vkrapp.data.remote.mappers

import ru.mav26.vkrapp.data.remote.models.statistics.AvgTimeStatsApi
import ru.mav26.vkrapp.data.remote.models.statistics.DailyCountApi
import ru.mav26.vkrapp.data.remote.models.statistics.DailyStatsApi
import ru.mav26.vkrapp.data.remote.models.statistics.SummaryStatsApi
import ru.mav26.vkrapp.domain.model.statistics.AvgTimeStats
import ru.mav26.vkrapp.domain.model.statistics.DailyCount
import ru.mav26.vkrapp.domain.model.statistics.DailyStats
import ru.mav26.vkrapp.domain.model.statistics.SummaryStats
import java.time.LocalDate

fun AvgTimeStatsApi.fromApi() : AvgTimeStats {
    return AvgTimeStats(
        lightDays = this.lightDays,
        mediumDays = this.mediumDays,
        hardDays = this.hardDays
    )
}

fun DailyCountApi.fromApi() : DailyCount {
    return DailyCount(
        date = LocalDate.parse(this.date),
        light = this.light,
        medium = this.medium,
        hard = this.hard
    )
}

fun DailyStatsApi.fromApi() : DailyStats {
    return DailyStats(
        counts = this.counts.map { it.fromApi() }
    )
}

fun SummaryStatsApi.fromApi() : SummaryStats {
    return SummaryStats(
        total = this.total,
        light = this.light,
        medium = this.medium,
        hard = this.hard
    )
}