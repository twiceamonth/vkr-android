package ru.mav26.vkrapp.data.remote.mappers

import ru.mav26.vkrapp.data.remote.models.effects.ActiveEffectResponse
import ru.mav26.vkrapp.domain.model.effects.ActiveEffect
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.util.Date
import java.util.Locale

fun String.toDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.parse(this)
}


fun ActiveEffectResponse.fromApi() : ActiveEffect {
    return ActiveEffect(
        activeEffectId = this.activeEffectId,
        endDate = this.endDate.toDate(),
        isCompleted = this.isCompleted,
        effectName = this.effectName,
        description = this.description,
        effectIcon = this.effectIcon,
        criteriaType = this.criteriaType,
        criteriaValue = this.criteriaValue
    )
}