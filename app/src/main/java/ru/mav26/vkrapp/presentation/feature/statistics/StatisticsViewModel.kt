package ru.mav26.vkrapp.presentation.feature.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.mav26.vkrapp.domain.usecase.StatisticsUseCase

class StatisticsViewModel(
    private val statisticsUseCase: StatisticsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(StatisticsState())
    val state: StateFlow<StatisticsState> = _state

    fun totalByDifficulty() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = statisticsUseCase.totalByDifficulty()
            _state.update { it.copy(summaryStats = response) }
        }
    }

    fun avgTimeByDifficulty() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = statisticsUseCase.avgTimeByDifficulty()
            _state.update { it.copy(avgTimeStats = response) }
        }
    }

    fun dailyCounts() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = statisticsUseCase.dailyCounts()
            _state.update { it.copy(dailyStats = response.counts) }
        }
    }
}