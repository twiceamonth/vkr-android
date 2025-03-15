package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.effects.ActiveEffect
import ru.mav26.vkrapp.domain.repository.EffectsRepository

class EffectsUseCase(
    private val effectsRepository: EffectsRepository
) {
    fun getActiveEffect() : ActiveEffect? {
        return effectsRepository.getActiveEffect()
    }
}