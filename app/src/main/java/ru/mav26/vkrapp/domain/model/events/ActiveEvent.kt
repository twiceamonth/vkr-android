package ru.mav26.vkrapp.domain.model.events

import java.util.Date

data class ActiveEvent(
    val activeEventId: String,
    val eventName: String,
    val description: String,
    val eventIcon: String,
    val money: Int,
    val exp: Int,
    val criteriaType: String,
    val criteriaValue: Int,
    val endDate: Date,
    val isCompleted: Boolean,
    val progress: Int
)
