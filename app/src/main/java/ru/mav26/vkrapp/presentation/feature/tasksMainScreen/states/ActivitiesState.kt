package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.states

import ru.mav26.vkrapp.domain.model.bosses.ActiveBoss
import ru.mav26.vkrapp.domain.model.effects.ActiveEffect
import ru.mav26.vkrapp.domain.model.events.ActiveEvent

data class ActivitiesState(
    val boss: ActiveBoss? = null,
    val effect: ActiveEffect? = null,
    val event: ActiveEvent? = null
)
