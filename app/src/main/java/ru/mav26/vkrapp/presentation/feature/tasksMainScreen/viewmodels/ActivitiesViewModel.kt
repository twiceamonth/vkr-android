package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mav26.vkrapp.domain.usecase.BossesUseCase
import ru.mav26.vkrapp.domain.usecase.EffectsUseCase
import ru.mav26.vkrapp.domain.usecase.EventsUseCase
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.states.ActivitiesState

class ActivitiesViewModel(
    private val bossesUseCase: BossesUseCase,
    private val effectsUseCase: EffectsUseCase,
    private val eventsUseCase: EventsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ActivitiesState())
    val state: StateFlow<ActivitiesState> = _state

    fun getActiveBoss(userLogin: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val ab = bossesUseCase.getActiveBoss(userLogin)
            _state.update { it.copy(boss = ab) }
        }
    }

    fun makeBossDamage(userLogin: String, taskDiff: String) {
        viewModelScope.launch(Dispatchers.IO) {
            bossesUseCase.makeDamage(userLogin, taskDiff)
        }
    }

    fun getActiveEffect() {
        viewModelScope.launch(Dispatchers.IO) {
            val af = effectsUseCase.getActiveEffect()
            _state.update { it.copy(effect = af) }
        }
    }

    fun getActiveEvent(userLogin: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val ae = eventsUseCase.getActiveEvent(userLogin)
            _state.update { it.copy(event = ae) }
        }
    }

    fun updateProgress(activeEventId: String, userLogin: String, type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            eventsUseCase.updateProgress(activeEventId, userLogin, type)
        }
    }
}