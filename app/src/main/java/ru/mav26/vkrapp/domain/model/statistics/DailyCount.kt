package ru.mav26.vkrapp.domain.model.statistics

import java.time.LocalDate

data class DailyCount(
    val date: LocalDate,
    val light: Int,
    val medium: Int,
    val hard: Int
)
