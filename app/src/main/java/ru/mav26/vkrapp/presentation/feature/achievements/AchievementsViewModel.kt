package ru.mav26.vkrapp.presentation.feature.achievements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mav26.vkrapp.domain.usecase.AchievementsUseCase

class AchievementsViewModel(
    private val achievementsUseCase: AchievementsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AchievementsState())
    val state: StateFlow<AchievementsState> = _state

    fun getAchievementsList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = achievementsUseCase.getAchievements()
            _state.update { it.copy(achievementsList = list) }
        }
    }

    fun getAchievementsProgress(userLogin: String) {
        /* todo: получение логина из переменной окружения */
        viewModelScope.launch(Dispatchers.IO) {
            val list = achievementsUseCase.getAchievementsProgress(userLogin)
            _state.update { it.copy(achievementsProgress = list) }
        }
    }

    fun updateProgress(progressId: String, userLogin: String) {
        /* todo: получение логина из переменной окружения */
        viewModelScope.launch(Dispatchers.IO) {
            achievementsUseCase.updateProgress(progressId, userLogin)
        }
    }

    fun startProgress(userLogin: String, achievementId: String) {
        /* todo: получение логина из переменной окружения */
        viewModelScope.launch(Dispatchers.IO) {
            achievementsUseCase.startProgress(userLogin, achievementId)
        }
    }

    fun resetProgress(progressId: String, userLogin: String) {
        /* todo: получение логина из переменной окружения */
        viewModelScope.launch(Dispatchers.IO) {
            achievementsUseCase.resetProgress(progressId, userLogin)
        }
    }
}