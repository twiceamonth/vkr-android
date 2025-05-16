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

    fun getAchievementsProgress() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = achievementsUseCase.getAchievementsProgress()
            _state.update { it.copy(achievementsProgress = list) }
        }
    }

    fun updateProgress(progressId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            achievementsUseCase.updateProgress(progressId)
        }
    }

    fun startProgress(achievementId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            achievementsUseCase.startProgress(achievementId)
        }
    }

    fun resetProgress(progressId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            achievementsUseCase.resetProgress(progressId)
        }
    }
}