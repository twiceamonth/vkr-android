package ru.mav26.vkrapp.data.remote.mappers

import ru.mav26.vkrapp.data.remote.models.effects.ActiveEffectResponse
import ru.mav26.vkrapp.domain.model.effects.ActiveEffect
import java.sql.Date

fun ActiveEffectResponse.fromApi() : ActiveEffect {
    return ActiveEffect(
        activeEffectId = this.activeEffectId,
        endDate = Date.valueOf(this.endDate),
        isCompleted = this.isCompleted,
        effectName = this.effectName,
        description = this.description,
        effectIcon = this.effectIcon,
        criteriaType = this.criteriaType,
        criteriaValue = this.criteriaValue
    )
}