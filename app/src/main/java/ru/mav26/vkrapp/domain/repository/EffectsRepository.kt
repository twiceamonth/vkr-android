package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.effects.ActiveEffect

interface EffectsRepository {
    suspend fun getActiveEffect() : ActiveEffect?
}