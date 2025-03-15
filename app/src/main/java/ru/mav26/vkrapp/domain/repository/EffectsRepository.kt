package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.effects.ActiveEffect

interface EffectsRepository {
    fun getActiveEffect() : ActiveEffect?
}