package ru.mav26.vkrapp.data.remote.mappers

import ru.mav26.vkrapp.data.remote.models.effects.ActiveEffectResponse
import ru.mav26.vkrapp.domain.model.effects.ActiveEffect
import java.time.OffsetDateTime

fun ActiveEffectResponse.fromApi() : ActiveEffect {
    return ActiveEffect(
        activeEffectId = this.activeEffectId,
        endDate = OffsetDateTime.parse(this.endDate),
        isCompleted = this.isCompleted,
        effectName = this.effectName,
        description = this.description,
        effectIcon = this.effectIcon,
        criteriaType = this.criteriaType,
        criteriaValue = this.criteriaValue
    )
}