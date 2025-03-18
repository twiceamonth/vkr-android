package ru.mav26.vkrapp.data.remote.mappers

import ru.mav26.vkrapp.data.remote.models.events.ActiveEventResponse
import ru.mav26.vkrapp.domain.model.events.ActiveEvent
import java.sql.Date

fun ActiveEventResponse.fromApi() : ActiveEvent {
    return ActiveEvent(
        activeEventId = this.activeEventId,
        eventName = this.eventName,
        description = this.description,
        eventIcon = this.eventIcon,
        money = this.money,
        exp = this.exp,
        criteriaType = this.criteriaType,
        criteriaValue = this.criteriaValue,
        endDate = Date.valueOf(this.endDate),
        isCompleted = this.isCompleted,
        progress = this.progress
    )
}