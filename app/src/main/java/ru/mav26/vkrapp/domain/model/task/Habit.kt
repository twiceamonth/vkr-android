package ru.mav26.vkrapp.domain.model.task

import java.time.LocalTime

data class Habit(
    val habitId: String,
    val title: String,
    val difficulty: String,
    val frequency: String,
    val timerInterval: LocalTime?,
    val description: String,
    val streakCount: Int,
    val lastPerformed: String
)
