package ru.mav26.vkrapp.domain.model.task

data class HabitCreate(
    val title: String,
    val difficulty: String,
    val frequency: String,
    val timerInterval: String? = null, //LocalTime
    val description: String
)
