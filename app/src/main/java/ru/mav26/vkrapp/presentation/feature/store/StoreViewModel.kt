package ru.mav26.vkrapp.presentation.feature.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mav26.vkrapp.domain.usecase.StoreUseCase

class StoreViewModel(
    private val storeUseCase: StoreUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(StoreState())
    val state: StateFlow<StoreState> = _state

    /* TODO: состояние персонажа */

    fun getItems(type: String, userLogin: String) {
        /* todo: получение логина из переменной окружения */
        viewModelScope.launch(Dispatchers.IO) {
            val itemsList =storeUseCase.getItems(type, userLogin)
            _state.update { it.copy(items = itemsList) }
        }
    }

    fun buyItem(storeId: String, userLogin: String) {
        /* todo: получение логина из переменной окружения */
        viewModelScope.launch(Dispatchers.IO) {
            storeUseCase.buyItem(storeId, userLogin)
        }
    }
}